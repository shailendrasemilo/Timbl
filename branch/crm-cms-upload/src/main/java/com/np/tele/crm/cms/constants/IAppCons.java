package com.np.tele.crm.cms.constants;

import java.util.ArrayList;
import java.util.List;

import com.np.tele.crm.service.client.CrmCmsFilePojo;

public interface IAppCons
{
    String               MONITOR_LOCATION          = "monitor.path";
    String               PROCESS_LOCATION          = "process.path";
    String               ARCHIVE_LCATION           = "archive.path";
    String               MONITOR_TIME_INTERVAL     = "monitor.time.interval";
    String               PROCESS_TIME_INTERVAL     = "process.time.interval";
    String               ARCHIVE_TIME_INTERVAL     = "archive.time.interval";
    String               APP_CONFIG_FILE           = "appconfig.properties";
    String               LOG4J_CONFIG_FILE         = "log4j.configuration";
    String               CMS_FILE_OWNER            = "cms.file.owner";
    List<CrmCmsFilePojo> cmsFilesToProcess         = new ArrayList<CrmCmsFilePojo>();
    List<CrmCmsFilePojo> cmsFilesToPayments        = new ArrayList<CrmCmsFilePojo>();
    List<Long>           cmsFileIDsToSendStatus    = new ArrayList<Long>();
    String               ERROR_INVALID_FILE_TYPE   = "error.invalid.file.type";
    String               ERROR_INVALID_FILE_NAME   = "error.invalid.file.name";
    String               ERROR_INVALID_FILE_HEADER = "error.invalid.file.header";
    String               ERROR_VALID_SHEET_MISSING = "error.valid.sheet.missing";
    String               ERROR_INPUT_NOT_FILE      = "error.input.not.file";
    String               ERROR_INVALID_RECORDS     = "error.invalid.records";
    String               CMS_RECORDS_CHUNKS_SIZE   = "cms.records.chunks.size";
}
