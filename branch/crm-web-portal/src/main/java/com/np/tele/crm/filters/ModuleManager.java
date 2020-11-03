package com.np.tele.crm.filters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.resource.spi.IllegalStateException;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.pojos.ModulesPojo;
import com.np.tele.crm.utils.PropertyUtils;
import com.np.tele.crm.utils.StringUtils;

public final class ModuleManager
{
    private static final Logger        LOGGER    = Logger.getLogger( ModuleManager.class );
    private static Map<String, String> roleLinks = new HashMap<String, String>();
    static
    {
        getRoleLinks();
    }

    private static void processModules( String inKey,
                                        List<ModulesPojo> inModules,
                                        long inModuleId,
                                        int moduleLevel,
                                        long inParentModuleId )
    {
        String strSize = PropertyUtils.getModuleDetails( inKey + IPropertiesConstant.KEY_SIZE );
        if ( StringUtils.isNotBlank( strSize ) && StringUtils.isNumeric( strSize ) )
        {
            int moduleSize = Integer.parseInt( strSize );
            String[] moduleDetails = null;
            String module = null;
            ModulesPojo modulesPojo = null;
            for ( int i = 1; i <= moduleSize; i++ )
            {
                module = PropertyUtils.getModuleDetails( inKey + i );
                if ( StringUtils.isNotBlank( module ) && StringUtils.contains( module, IAppConstants.COMMA ) )
                {
                    moduleDetails = module.split( IAppConstants.COMMA );
                    modulesPojo = new ModulesPojo( i,
                                                   moduleDetails[0],
                                                   moduleDetails[1],
                                                   Boolean.parseBoolean( moduleDetails[2] ),
                                                   moduleLevel );
                    modulesPojo.setModuleSize( moduleSize );
                    if ( inParentModuleId > 0 )
                    {
                        modulesPojo.setParentModuleId( inParentModuleId );
                    }
                    if ( moduleDetails.length > 3 )
                    {
                        List<String> roles = new ArrayList<String>();
                        for ( int j = 3; j < moduleDetails.length; j++ )
                        {
                            roles.add( moduleDetails[j] );
                        }
                        modulesPojo.setRoles( roles );
                    }
                    if ( inModuleId > 0 )
                    {
                        if ( modulesPojo.getModuleId() == inModuleId )
                        {
                            modulesPojo.setSelected( true );
                        }
                        else
                        {
                            modulesPojo.setSelected( false );
                        }
                    }
                    if ( modulesPojo.isSelected() )
                    {
                        modulesPojo.setStyleClass( IAppConstants.STYLE_SELECTED );
                    }
                    inModules.add( modulesPojo );
                }
            }
        }
    }

    private static void processModules( String inKey, List<ModulesPojo> inModules, long inModuleId, int moduleLevel )
    {
        processModules( inKey, inModules, inModuleId, moduleLevel, 0 );
    }

    public static Map<String, List<ModulesPojo>> getSelectedModule( String inMethodName )
        throws IllegalStateException
    {
        Map<String, List<ModulesPojo>> moduleMap = new HashMap<String, List<ModulesPojo>>( 3 );
        long moduleId = 0;
        long subModuleId = 0;
        long subSubModuleId = 0;
        if ( StringUtils.isNotBlank( inMethodName ) )
        {
            String strModule = PropertyUtils.getModuleDetails( inMethodName );
            String[] modulesArr = null;
            if ( StringUtils.isNotBlank( strModule ) && StringUtils.contains( strModule, IAppConstants.COMMA ) )
            {
                modulesArr = StringUtils.split( strModule, IAppConstants.COMMA );
                strModule = modulesArr[0];
            }
            if ( StringUtils.isNotBlank( strModule ) && StringUtils.contains( strModule, IAppConstants.DOT ) )
            {
                LOGGER.info( "Selected Module = " + strModule );
                modulesArr = StringUtils.split( strModule, IAppConstants.DOT );
                if ( modulesArr.length == 3 )
                {
                    if ( StringUtils.isNumeric( modulesArr[0] ) )
                    {
                        moduleId = Integer.parseInt( modulesArr[0] );
                    }
                    if ( StringUtils.isNumeric( modulesArr[1] ) )
                    {
                        subModuleId = Integer.parseInt( modulesArr[1] );
                    }
                    if ( StringUtils.isNumeric( modulesArr[2] ) )
                    {
                        subSubModuleId = Integer.parseInt( modulesArr[2] );
                    }
                    if ( moduleId > 0 && subModuleId > 0 && subSubModuleId > 0 )
                    {
                        moduleMap.put( IAppConstants.MODULES, getSelectedModules( moduleId ) );
                        moduleMap.put( IAppConstants.SUB_MODULES, getSelectedSubModules( moduleId, subModuleId ) );
                        moduleMap.put( IAppConstants.SUB_SUB_MODULES,
                                       getSelectedSubSubModules( moduleId, subModuleId, subSubModuleId ) );
                    }
                }
            }
        }
        else
        {
            moduleMap.put( IAppConstants.MODULES, getSelectedModules( moduleId ) );
            for ( ModulesPojo modulesPojo : moduleMap.get( IAppConstants.MODULES ) )
            {
                if ( modulesPojo.isSelected() )
                {
                    moduleId = modulesPojo.getModuleId();
                    break;
                }
            }
            moduleMap.put( IAppConstants.SUB_MODULES, getSelectedSubModules( moduleId, subModuleId ) );
            for ( ModulesPojo modulesPojo : moduleMap.get( IAppConstants.SUB_MODULES ) )
            {
                if ( modulesPojo.isSelected() )
                {
                    subModuleId = modulesPojo.getModuleId();
                    break;
                }
            }
            moduleMap.put( IAppConstants.SUB_SUB_MODULES,
                           getSelectedSubSubModules( moduleId, subModuleId, subSubModuleId ) );
        }
        return moduleMap;
    }

    private static List<ModulesPojo> getSelectedModules( final long inModuleId )
    {
        String key = IPropertiesConstant.KEY_MODULE;
        List<ModulesPojo> modules = new ArrayList<ModulesPojo>();
        processModules( key, modules, inModuleId, 1 );
        return modules;
    }

    private static List<ModulesPojo> getSelectedSubModules( final long inModuleId, final long inSubModuleId )
    {
        String key = IPropertiesConstant.KEY_MODULE + inModuleId + IAppConstants.DOT;
        List<ModulesPojo> modules = new ArrayList<ModulesPojo>();
        processModules( key, modules, inSubModuleId, 2, inModuleId );
        return modules;
    }

    private static List<ModulesPojo> getSelectedSubSubModules( final long inModuleId,
                                                               final long inSubModuleId,
                                                               final long inSubSubModuleId )
    {
        String key = IPropertiesConstant.KEY_MODULE + inModuleId + IAppConstants.DOT + inSubModuleId
                + IAppConstants.DOT;
        List<ModulesPojo> modules = new ArrayList<ModulesPojo>();
        processModules( key, modules, inSubSubModuleId, 3, inSubModuleId );
        return modules;
    }

    public static void processRoles( List<ModulesPojo> inModules, List<String> inRoles, boolean modifyUrl )
    {
        Iterator<ModulesPojo> moIterator = inModules.iterator();
        boolean toRemove = false;
        while ( moIterator.hasNext() )
        {
            toRemove = true;
            ModulesPojo modulesPojo = moIterator.next();
            List<String> moRoles = modulesPojo.getRoles();
            if ( StringUtils.isValidObj( moRoles ) )
            {
                for ( String moRole : moRoles )
                {
                    if ( inRoles.contains( moRole ) || StringUtils.equals( moRole, "default" ) )
                    {
                        toRemove = false;
                        if ( modifyUrl && !roleLinks.isEmpty() && !StringUtils.equals( moRole, "default" )
                                && !moRoles.contains( "same_url" ) )
                        {
                            if ( modulesPojo.getModuleLevel() == 1 )
                            {
                                for ( int i = 1; i <= modulesPojo.getModuleSize(); i++ )
                                {
                                    String roleKey = moRole + IAppConstants.DOT + modulesPojo.getModuleId()
                                            + IAppConstants.DOT + i;
                                    if ( roleLinks.containsKey( roleKey ) )
                                    {
                                        modulesPojo.setModuleUrl( roleLinks.get( roleKey ) );
                                        break;
                                    }
                                }
                            }
                            else if ( modulesPojo.getModuleLevel() == 2 )
                            {
                                String roleKey = moRole + IAppConstants.DOT + modulesPojo.getParentModuleId()
                                        + IAppConstants.DOT + modulesPojo.getModuleId();
                                if ( roleLinks.containsKey( roleKey ) )
                                {
                                    modulesPojo.setModuleUrl( roleLinks.get( roleKey ) );
                                }
                            }
                        }
                        break;
                    }
                }
            }
            if ( toRemove )
            {
                moIterator.remove();
            }
        }
    }

    private static void getRoleLinks()
    {
        if ( roleLinks.isEmpty() )
        {
            String key = IPropertiesConstant.KEY_MODULE;
            List<ModulesPojo> modules = new ArrayList<ModulesPojo>();
            processModules( key, modules, 0, 1 );
            List<ModulesPojo> subModules = null;
            List<ModulesPojo> subSubModules = null;
            for ( ModulesPojo modulesPojo : modules )
            {
                subModules = new ArrayList<ModulesPojo>();
                key = IPropertiesConstant.KEY_MODULE + modulesPojo.getModuleId() + IAppConstants.DOT;
                processModules( key, subModules, 0, 2, modulesPojo.getModuleId() );
                for ( ModulesPojo subModulesPojo : subModules )
                {
                    subSubModules = new ArrayList<ModulesPojo>();
                    key = IPropertiesConstant.KEY_MODULE + modulesPojo.getModuleId() + IAppConstants.DOT
                            + subModulesPojo.getModuleId() + IAppConstants.DOT;
                    processModules( key, subSubModules, 0, 3, subModulesPojo.getModuleId() );
                    for ( ModulesPojo subSubModule : subSubModules )
                    {
                        List<String> roles = subSubModule.getRoles();
                        if ( StringUtils.isValidObj( roles ) && !roles.isEmpty() )
                        {
                            for ( String role : roles )
                            {
                                String roleKey = role + IAppConstants.DOT + modulesPojo.getModuleId()
                                        + IAppConstants.DOT + subModulesPojo.getModuleId();
                                if ( !roleLinks.containsKey( roleKey ) )
                                {
                                    roleLinks.put( roleKey, subSubModule.getModuleUrl() );
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
