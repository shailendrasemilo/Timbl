package com.np.tele.crm.qrc.forms;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.FileHeaderConstants;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.gis.bm.ExcelWriteUtils;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.ValidationPojoUtil;

public class BulkUploadFormHelper
{
    private static final Logger LOGGER = Logger.getLogger( BulkUploadFormHelper.class );

    public static ActionMessages bulkMountBoosterFormValidation( ActionMessages actionErrors,
                                                                 BulkUploadForm bulkUploadForm,
                                                                 String filePath,
                                                                 String UserId )
    {
        Map<String, Object[]> resultMap = null;
        LOGGER.info( "File : " + bulkUploadForm.getMountBoosterExcelFile().getFileName() + " Size : "
                + bulkUploadForm.getMountBoosterExcelFile().getFileSize() );
        if ( bulkUploadForm.getMountBoosterExcelFile().getFileSize() == 0 )
        {
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_FILE_REQUIRED ) );
        }
        else if ( bulkUploadForm.getMountBoosterExcelFile().getFileSize() > 20480 )
        {
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_FILE_SIZE ) );
        }
        else
        {
            try
            {
                String inPath = ExcelWriteUtils.downloadUploadedFile( filePath, UserId,
                                                                      bulkUploadForm.getMountBoosterExcelFile(),
                                                                      CRMParameter.MOUNT_BOOSTER.getParameter() );
                LOGGER.info( "File Uploaded AbsolutePath  : " + inPath );
                FileInputStream inputStream = new FileInputStream( inPath );
                XSSFWorkbook workbook = new XSSFWorkbook( inputStream );
                XSSFSheet sheet = workbook.getSheet( FileHeaderConstants.MOUNT_BOOSTER_SHEET.getValue() );
                if ( StringUtils.isValidObj( sheet ) && sheet.getLastRowNum() > 25 )
                {
                    actionErrors.add( IAppConstants.APP_ERROR,
                                      new ActionMessage( IPropertiesConstant.ERROR_FILE_MAX_RECORD_SIZE ) );
                }
            }
            catch ( IOException ex )
            {
                LOGGER.error( "Reason : " + ex.getMessage() );
            }
        }
        resultMap = validateRemarks( resultMap, bulkUploadForm.getRemarksPojo() );
        ValidationUtil.prepareErrorMessage( actionErrors, resultMap );
        return actionErrors;
    }

    public static ActionMessages bulkValidityExtensionFormValidation( ActionMessages actionErrors,
                                                                      BulkUploadForm bulkUploadForm,
                                                                      String filePath,
                                                                      String UserId )
    {
        Map<String, Object[]> resultMap = null;
        LOGGER.info( "File : " + bulkUploadForm.getValidityExtensionExcelFile().getFileName() + " Size : "
                + bulkUploadForm.getValidityExtensionExcelFile().getFileSize() );
        if ( bulkUploadForm.getValidityExtensionExcelFile().getFileSize() == 0 )
        {
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_FILE_REQUIRED ) );
        }
        else if ( bulkUploadForm.getValidityExtensionExcelFile().getFileSize() > 20480 )
        {
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_FILE_SIZE ) );
        }
        else
        {
            try
            {
                String inPath = ExcelWriteUtils.downloadUploadedFile( filePath, UserId,
                                                                      bulkUploadForm.getValidityExtensionExcelFile(),
                                                                      CRMParameter.VALIDITY_EXTENSION.getParameter() );
                LOGGER.info( "File Uploaded AbsolutePath  : " + inPath );
                FileInputStream inputStream = new FileInputStream( inPath );
                XSSFWorkbook workbook = new XSSFWorkbook( inputStream );
                XSSFSheet sheet = workbook.getSheet( FileHeaderConstants.VALIDITY_EXTENSION_SHEET.getValue() );
                if ( StringUtils.isValidObj( sheet ) && sheet.getLastRowNum() > 25 )
                {
                    actionErrors.add( IAppConstants.APP_ERROR,
                                      new ActionMessage( IPropertiesConstant.ERROR_FILE_MAX_RECORD_SIZE ) );
                }
            }
            catch ( IOException ex )
            {
                LOGGER.error( "Reason : " + ex.getMessage() );
            }
        }
        resultMap = validateRemarks( resultMap, bulkUploadForm.getRemarksPojo() );
        ValidationUtil.prepareErrorMessage( actionErrors, resultMap );
        return actionErrors;
    }

    public static Map<String, Object[]> validateRemarks( Map<String, Object[]> inResultMap, RemarksPojo inRemarksPojo )
    {
        if ( StringUtils.isValidObj( inResultMap ) )
        {
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( inRemarksPojo, ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
            if ( StringUtils.isValidObj( resultMap ) )
            {
                inResultMap.putAll( resultMap );
            }
        }
        else
        {
            inResultMap = ValidationPojoUtil.validateForm( inRemarksPojo,
                                                           ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
        }
        return inResultMap;
    }

    public static void resetBulkUploadForm( BulkUploadForm bulkUploadForm, String inMethodName )
    {
        LOGGER.info( "In side resetBulkUploadForm........." );
        if ( StringUtils.equals( "bulkValidityExtensionPage", inMethodName ) )
        {
            bulkUploadForm.setGracePeriodReason( new String() );
            bulkUploadForm.setRemarksPojo( new RemarksPojo() );
        }
    }
}
