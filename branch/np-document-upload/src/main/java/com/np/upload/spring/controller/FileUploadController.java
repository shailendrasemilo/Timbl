package com.np.upload.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.np.upload.constants.DocumentTypes;
import com.np.upload.constants.IAppConstantsDms;
import com.np.upload.constants.IJspPages;
import com.np.upload.manager.FileUploadManager;
import com.np.upload.pojo.NpUploads;
import com.np.upload.spring.form.UploadForm;

@Controller
@RequestMapping(value = "/files")
public class FileUploadController
    implements HandlerExceptionResolver
{
    private FileUploadManager fileUploadManager;

    public FileUploadManager getFileUploadManager()
    {
        return fileUploadManager;
    }

    public void setFileUploadManager( FileUploadManager inFileUploadManager )
    {
        fileUploadManager = inFileUploadManager;
    }
    static
    {
        Resource resource = new ClassPathResource( "/common.properties" );
        try
        {
            Properties properties = PropertiesLoaderUtils.loadProperties( resource );
            System.setProperty( "file.path", properties.getProperty( "upload.dir" ) );
        }
        catch ( FileNotFoundException e )
        {
            e.printStackTrace();
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/upload/{module}/{mapping}", method = RequestMethod.GET)
    public String uploadPage( @PathVariable(value = "module") String module,
                              @PathVariable(value = "mapping") String mapping,
                              ModelMap map )
    {
        prepareUploadPage( module, mapping, map );
        return IJspPages.UPLOAD_PAGE;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile( @ModelAttribute(value = "UploadForm") UploadForm form,
                              @RequestParam(value = "document") MultipartFile[] files,
                              ModelMap modelMap )
    {
        String forward = IJspPages.UPLOAD_PAGE;
        List<String> errors = new ArrayList<String>();
        for ( int i = 0; i < files.length; i++ )
        {
            MultipartFile file = files[i];
            String fileName = file.getOriginalFilename();
            if ( !file.isEmpty() )
            {
                String docType = null;
                if ( form.getDocTypes().length > 0 && !"".equals( docType = form.getDocTypes()[i] ) )
                {
                    if ( FilenameUtils.isExtension( fileName, IAppConstantsDms.DOCUMENT_EXTENSIONS ) )
                    {
                        try
                        {
                            byte[] bytes = file.getBytes();
                            // Creating the directory to store file
                            String rootPath = System.getProperty( "file.path" );
                            String uploadPath = ( rootPath + File.separator + IAppConstantsDms.DOC_DIR + File.separator
                                    + form.getModule() + File.separator + form.getMapping() + File.separator + docType )
                                    .replaceAll( " ", "_" );
                            System.out.println( "upload path:: " + uploadPath );
                            File dir = new File( uploadPath );
                            if ( !dir.exists() )
                                dir.mkdirs();
                            // Create the file on server
                            File serverFile = new File( dir.getAbsolutePath() + File.separator
                                    + Calendar.getInstance().getTimeInMillis() + "_" + fileName.replaceAll( " ", "_" ) );
                            BufferedOutputStream stream = new BufferedOutputStream( new FileOutputStream( serverFile ) );
                            stream.write( bytes );
                            stream.close();
                            System.out.println( "Server File Location :: " + serverFile.getAbsolutePath() );
                            NpUploads npUploads = new NpUploads( form.getModule(), form.getMapping() );
                            npUploads.setDocType( docType );
                            npUploads.setDocName( fileName );
                            npUploads.setDocPath( serverFile.getAbsolutePath() );
                            getFileUploadManager().saveFile( npUploads, modelMap );
                        }
                        catch ( Exception e )
                        {
                            System.err.println( "Failed to upload " + fileName + " => " + e.getMessage() );
                            errors.add( "Failed to upload <b>" + fileName + "</b>" );
                        }
                    }
                    else
                    {
                        errors.add( "<b>" + fileName + "</b> has no valid extension ("
                                + StringUtils.arrayToCommaDelimitedString( IAppConstantsDms.DOCUMENT_EXTENSIONS )
                                + ")." );
                    }
                }
                else
                {
                    errors.add( "Document Type not selected for <b>" + fileName + "</b>" );
                }
            }
        }
        modelMap.put( IAppConstantsDms.APP_ERROR, errors );
        prepareUploadPage( form.getModule(), form.getMapping(), modelMap );
        return forward;
    }

    @RequestMapping(value = "/view/{module}/{mapping}", method = RequestMethod.GET)
    public String viewPage( @PathVariable(value = "module") String module,
                            @PathVariable(value = "mapping") String mapping,
                            ModelMap map )
    {
        NpUploads npUploads = new NpUploads( module, mapping );
        map.put( "mappingId", mapping );
        map.put( "uploaded", getFileUploadManager().listAllUploadedFiles( npUploads ) );
        return IJspPages.VIEW_PAGE;
    }

    @RequestMapping(value = "/latest/{module}/{mapping}", method = RequestMethod.GET)
    public String viewLatestPage( @PathVariable(value = "module") String module,
                                  @PathVariable(value = "mapping") String mapping,
                                  ModelMap map )
    {
        prepareUploadPage( module, mapping, map );
        return IJspPages.VIEW_LATEST_PAGE;
    }

    @RequestMapping(value = "/view/{module}/{mapping}/{doc}", method = RequestMethod.GET)
    public String viewDocument( @PathVariable(value = "module") String module,
                                @PathVariable(value = "mapping") String mapping,
                                @PathVariable(value = "doc") String docType,
                                HttpServletRequest request,
                                HttpServletResponse response,
                                ModelMap map )
    {
        NpUploads npUploads = new NpUploads( module, mapping, docType );
        List<NpUploads> uploads = getFileUploadManager().listAllUploadedFiles( npUploads );
        if ( uploads != null && uploads.size() > 0 )
        {
            NpUploads uploaded = uploads.get( 0 );
            showDocument( request, response, uploaded.getDocPath(), true );
            return null;
        }
        return IJspPages.VIEW_PAGE;
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public String downloadFile( HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestParam(name = "module") String module,
                                @RequestParam(name = "mapping") String mapping,
                                @RequestParam(name = "doc") String doc,
                                @RequestParam(name = "version") Integer version,
                                ModelMap modelMap )
    {
        NpUploads npUploads = new NpUploads( module, mapping, doc );
        List<NpUploads> uploads = getFileUploadManager().listAllUploadedFiles( npUploads, version );
        if ( uploads != null && uploads.size() > 0 )
        {
            NpUploads uploaded = uploads.get( 0 );
            showDocument( request, response, uploaded.getDocPath(), false );
            return null;
        }
        return IJspPages.VIEW_PAGE;
    }

    private void showDocument( HttpServletRequest request, HttpServletResponse response, String docPath, boolean inline )
    {
        ServletContext context = request.getServletContext();
        File downloadFile = new File( docPath );
        FileInputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream( downloadFile );
        }
        catch ( FileNotFoundException ex1 )
        {
            ex1.printStackTrace();
        }
        // get MIME type of the file
        String mimeType = context.getMimeType( docPath );
        if ( mimeType == null )
        {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println( "MIME type: " + mimeType );
        // set content attributes for the response
        response.setContentType( mimeType );
        response.setContentLength( (int) downloadFile.length() );
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format( ( inline ? "inline" : "attachment" ) + "; filename=\"%s\"",
                                            downloadFile.getName() );
        response.setHeader( headerKey, headerValue );
        try
        {
            // get output stream of the response
            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            // write bytes read from the input stream into the output stream
            while ( ( bytesRead = inputStream.read( buffer ) ) != -1 )
            {
                outStream.write( buffer, 0, bytesRead );
            }
            inputStream.close();
            outStream.close();
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
        }
    }

    private void prepareUploadPage( String module, String mapping, ModelMap map )
    {
        NpUploads npUploads = new NpUploads( module, mapping );
        map.put( "uploaded", getFileUploadManager().listUploadedFiles( npUploads ) );
        UploadForm form = new UploadForm( module, mapping );
        map.put( UploadForm.class.getSimpleName(), form );
        map.put( "docTypes", DocumentTypes.values() );
    }

    public ModelAndView resolveException( HttpServletRequest request,
                                          HttpServletResponse response,
                                          Object handler,
                                          Exception exception )
    {
        Map<String, Object> map = new HashMap<String, Object>();
        if ( exception instanceof MaxUploadSizeExceededException )
        {
            map.put( IAppConstantsDms.APP_ERROR, "Error! Max upload size allowed is "
                    + ( (MaxUploadSizeExceededException) exception ).getMaxUploadSize() / 1024 / 1024 + " MB" );
        }
        return new ModelAndView( IJspPages.UPLOAD_FAILURE, map );
    }
}
