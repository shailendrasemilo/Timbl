package com.np.tele.crm.utils;

import java.util.Set;

import com.np.validator.pojos.ValidationPojo;
import com.np.validator.util.ValidationPojoUtil;

public final class ICRMValidationCriteriaUtil
{
    public static final Set<ValidationPojo> COMMON_PAYMENT_CRITERIA                                      = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.payment.common." );
    public static final Set<ValidationPojo> PAYMENT_UPDATE_CRITERIA                                      = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.payment.update." );
    public static final Set<ValidationPojo> PAYMENT_UPDATE_STATUS_CRITERIA                               = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.payment.update.status." );
    public static final Set<ValidationPojo> PAYMENT_CHEQUE_BOUNCE_CRITERIA                               = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.payment.cheque.bounce." );
    public static final Set<ValidationPojo> CASH_PAYMENT_CRITERIA                                        = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.payment.cash." );
    public static final Set<ValidationPojo> CHEQUE_PAYMENT_CRITERIA                                      = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.payment.cheque." );
    public static final Set<ValidationPojo> ONLINE_PAYMENT_CRITERIA                                      = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.payment.online." );
    public static final Set<ValidationPojo> REVERSAL_PAYMENT_CRITERIA                                    = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.payment.reversal." );
    public static final Set<ValidationPojo> TRACK_PAYMENT_CRITERIA                                       = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.payment.track." );
    public static final Set<ValidationPojo> CUSTOMER_SEARCH_CRITERIA                                     = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.search.customer." );
    public static final Set<ValidationPojo> CMSFILE_TRACK_CRITERIA                                       = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.cmsfile.track." );
    public static final Set<ValidationPojo> CMSRECORDS_TRACK_CRITERIA                                    = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.cmsrecords.track." );
    public static final Set<ValidationPojo> CMSFILE_POST_CRITERIA                                        = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.cmsfile.post." );
    public static final Set<ValidationPojo> CMSFILE_UPDATE_CRITERIA                                      = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.cmsfile.update." );
    public static final Set<ValidationPojo> CMSRECORDS_POST_CRITERIA                                     = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.cmsrecords.post." );
    public static final Set<ValidationPojo> CMSRECORDS_RECTIFY_SUSPENSE_CRITERIA                         = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.cmsrecords.rectify.suspence." );
    public static final Set<ValidationPojo> CMSRECORDS_RECTIFY_REJECTED_CRITERIA                         = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.cmsrecords.rectify.rejected." );
    public static final Set<ValidationPojo> REMARKS_POST_CRITERIA                                        = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.remarks.post." );
    public static final Set<ValidationPojo> UPFRONT_POST_CRITERIA                                        = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.upfront.post." );
    public static final Set<ValidationPojo> CMSFILE_POST_CRITERIA_FILEID                                 = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.cmsrecords.file." );
    public static final Set<ValidationPojo> CMSFILE_PROCESS_CRITERIA                                     = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.cmsrecords.process." );
    public static final Set<ValidationPojo> CRF_SEARCH_CRITERIA                                          = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.crf.search." );
    public static final Set<ValidationPojo> VIEW_CRF_CRITERIA                                            = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.crf.view." );
    public static final Set<ValidationPojo> QRCPOST_SERVICE_REQUEST_CRITERIA                             = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrcpost.service.request." );
    public static final Set<ValidationPojo> QRC_FUNCTIONBIN_LIST_CRITERIA                                = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.functionbin.list." );
    public static final Set<ValidationPojo> QRC_CUST_INTERACTION_CREATE_CRITERIA                         = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.cust.interaction.create." );
    public static final Set<ValidationPojo> QRCPOST_WAIVER_SERVICE_REQUEST_CRITERIA                      = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrcpost.waiver.service.request." );
    public static final Set<ValidationPojo> QRC_GET_RCA_CONFIGURATIONS_CRITERIA                          = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.get.rca.configurations." );
    public static final Set<ValidationPojo> QRC_CREATE_RCA_CONFIGURATIONS_CRITERIA                       = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.create.rca.configurations." );
    public static final Set<ValidationPojo> QRC_GET_RESOLUTION_CODE_CRITERIA                             = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.get.qrc.resolution.code." );
    public static final Set<ValidationPojo> QRC_MAC_ADDRESS_CRITERIA                                     = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.mac.address." );
    public static final Set<ValidationPojo> QRC_NETWORK_OPTION82_CRITERIA                                = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.network.option82." );
    public static final Set<ValidationPojo> QRC_OWNERSHIP_CHANGE_CRITERIA                                = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.ownership.change." );
    public static final Set<ValidationPojo> QRC_GET_RESOLUTION_ID_CRITERIA                               = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.get.resolution.id." );
    public static final Set<ValidationPojo> QRC_CONFIG_RESOLUTION_CODE_CRITERIA                          = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.config.resolution.code." );
    public static final Set<ValidationPojo> QRC_CREATE_ATTRIBUTEDTO_CONFIGURATIONS_CRITERIA              = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.create.attributedTo.configurations." );
    public static final Set<ValidationPojo> QRC_GET_BIN_MAPPING_CRITERIA                                 = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.get.bin.mapping." );
    public static final Set<ValidationPojo> QRC_CREATE_BIN_MAPPING_CRITERIA                              = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.create.bin.mapping." );
    public static final Set<ValidationPojo> CUSTOMER_PLAN_MIGRATION_CRITERIA                             = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.customer.plan.migration." );
    public static final Set<ValidationPojo> POD_VALIATE_CRITERIA                                         = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.pod.data." );
    //--------Form Validation Criteria--------
    public static final Set<ValidationPojo> FORM_CASH_PAYMENT_CRITERIA                                   = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.payment.cash." );
    public static final Set<ValidationPojo> FORM_ONLINE_PAYMENT_CRITERIA                                 = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.payment.online." );
    public static final Set<ValidationPojo> FORM_CHEQUE_PAYMENT_CRITERIA                                 = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.payment.cheque." );
    public static final Set<ValidationPojo> FORM_CHEQUE_BOUNCE_CRITERIA                                  = ValidationPojoUtil
                                                                                                                 .processFormValidations( "criteria.payment.cheque.bounce." );
    public static final Set<ValidationPojo> FORM_PAYMENT_SEARCH_CRITERIA                                 = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.payment.search." );
    public static final Set<ValidationPojo> FORM_CUSTOMER_SEARCH_CRITERIA                                = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.search.customer." );
    public static final Set<ValidationPojo> FORM_PAYMENT_CRITERIA_COMMON                                 = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.payment.common." );
    public static final Set<ValidationPojo> FORM_REMARKS_CRITERIA_COMMON                                 = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.remarks.common." );
    public static final Set<ValidationPojo> FORM_REMARKS_CRITERIA_OTHER                                  = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.remarks.others." );
    public static final Set<ValidationPojo> FORM_UPFRONT_PAYMENT_POJO_CRITERIA_COMMON                    = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.crmupfrontpaymentpojo.common." );
    public static final Set<ValidationPojo> FORM_CMSRECORDS_POST_CRITERIA_DEPOSIT                        = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.cmsrecords.post.deposit." );
    public static final Set<ValidationPojo> FORM_CMSRECORDS_POST_CRITERIA_CLEARANCE                      = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.cmsrecords.post.clearance." );
    public static final Set<ValidationPojo> FORM_CMSRECORDS_CRITERIA_RECTIFY_REJECTED                    = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.cmsrecords.rectify.rejected." );
    public static final Set<ValidationPojo> FORM_CMSRECORDS_CRITERIA_RECTIFY_SUSPENSE                    = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.cmsrecords.rectify.suspense." );
    public static final Set<ValidationPojo> FORM_CRF_CRITERIA_SEARCH                                     = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.crf.search." );
    public static final Set<ValidationPojo> FORM_QRCTICKET_POST_CRITERIA_SR                              = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.qrcticket.post.sr." );
    public static final Set<ValidationPojo> FORM_UPFRONT_SEARCH_CRITERIA                                 = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.upfront.search." );
    public static final Set<ValidationPojo> FORM_UPFRONT_SEARCH_CRITERIA_FOR_BP                          = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.upfront.search.bp." );
    public static final Set<ValidationPojo> FORM_TICKET_HISTORY_CRITERIA_COMMON                          = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.ticket.history.common." );
    //--------Form Validation Criteria for INA--------
    public static final Set<ValidationPojo> FORM_CRF_REMARKS_ACTION                                      = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.crf.remarks.actions." );
    public static final Set<ValidationPojo> FORM_PARTNER_REQUIRED                                        = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.crf.partnerId." );
    public static final Set<ValidationPojo> FORM_CRF_REMARKS_FOR_APPROVE                                 = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.crf.remarks.approve." );
    public static final Set<ValidationPojo> FORM_CRF_FOR_NETWORK_CONFIGURATION                           = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.crf.for.network.configuration." );
    public static final Set<ValidationPojo> FORM_EMPTY_ASSIGN_TO                                         = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.crf.assignTo." );
    public static final Set<ValidationPojo> FORM_CRF_REASON_FOR_REJECTION                                = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.crf.reason.for.rejection." );
    public static final Set<ValidationPojo> FORM_CRF_REFERENCE_NUMBER                                    = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.crf.reference.number." );
    public static final Set<ValidationPojo> FORM_CRF_OPTION_82_FOR_MASTER_NAME_VALUES                    = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.crf.option82.masterName." );
    public static final Set<ValidationPojo> FORM_CRF_MAC_ADDRESS                                         = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.crf.mac.address." );
    public static final Set<ValidationPojo> FORM_CRF_ACTIVATION_DATE                                     = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.crf.activation.date." );
    public static final Set<ValidationPojo> FORM_CRF_ONT_RG_MDU_PORT                                     = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.crf.ont.rg.mdu." );
    public static final Set<ValidationPojo> FORM_CRF_CPE_MAC_MAP_ID                                      = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.crf.cpe.map.mac.id." );
    public static final Set<ValidationPojo> FORM_CRF_DISPLAY_ISR_DATE                                    = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.crf.display.isr.date." );
    public static final Set<ValidationPojo> FORM_ISR_REFERENCE_NUMBER                                    = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.isr.reference.number." );
    public static final Set<ValidationPojo> FORM_INA_CPE_CRITERIA_COMMON                                 = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.payment.cpe.common." );
    // --------- QRC Form Validations
    public static final Set<ValidationPojo> FORM_QRC_INSTALLATION_ADDRESS_CHANGE                         = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.qrc.installation.address.change." );
    public static final Set<ValidationPojo> FORM_QRC_REMARKS                                             = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.qrc.remraks." );
    public static final Set<ValidationPojo> FORM_QRC_DISCONNECTION                                       = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.qrc.disconnection." );
    public static final Set<ValidationPojo> FORM_QRC_WAIVER                                              = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.qrc.waiver." );
    // --------- QRC Mass Outage
    public static final Set<ValidationPojo> FORM_QRC_ADD_MASSOUTAGE                                      = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.qrc.add.massoutage." );
    //----------------------------------------Form Validation Criteria for QRC--------------------//
    public static final Set<ValidationPojo> FORM_QRC_RESOLVE_ACTION                                      = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.qrc.resolve.action." );
    public static final Set<ValidationPojo> FORM_QRC_FUTURESTAGE                                         = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.qrc.futureStage." );
    public static final Set<ValidationPojo> FORM_QRC_REMARKS_CHECK                                       = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.qrc.remarks.check." );
    public static final Set<ValidationPojo> FORM_QRC_CUST_INTERACTION_CREATE_CHECK                       = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.qrc.cust.interaction.create.check." );
    public static final Set<ValidationPojo> FORM_QRC_CONFIG_GET_RCA_CONFIGURATION_CHECK                  = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.qrc.config.get.rca.check." );
    public static final Set<ValidationPojo> FORM_QRC_CONFIG_GET_BIN_CONFIGURATION_CHECK                  = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.qrc.config.get.bin.check." );
    public static final Set<ValidationPojo> QRC_ADD_STATICIP_POJO                                        = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.add.staticip.pojo." );
    public static final Set<ValidationPojo> FORM_QRC_ADD_STATICIP_POJO                                   = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.qrc.add.staticip.pojo." );
    public static final Set<ValidationPojo> QRC_COMMON_ADD_CHARGE                                        = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.add.charge." );
    public static final Set<ValidationPojo> FORM_QRC_ADD_CHARGE                                          = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.qrc.add.charge." );
    public static final Set<ValidationPojo> FORM_QRC_CUSTOMER_ID                                         = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.validate.customer." );
    // ------------------------------------ QRC Config -------------------------------------
    public static final Set<ValidationPojo> FORM_CONFIG_RESOLUTION_CODE                                  = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.qrc.config.resolution.code." );
    public static final Set<ValidationPojo> FORM_CONFIG_SUB_SUB_CAT                                      = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.qrc.config.sub.sub.cat." );
    public static final Set<ValidationPojo> FORM_CONFIG_SUB_SUB_CAT_POJO                                 = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.qrc.config.sub.sub.cat.pojos." );
    /*public static final Set<ValidationPojo> CONFIG_SUB_SUB_CAT                         = ValidationPojoUtil
                                                                                                    .processValidations( "criteria.qrc.config.sub.sub.cat." );*/
    public static final Set<ValidationPojo> CONFIG_SUB_SUB_CAT_POJO                                      = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.config.sub.sub.cat.pojos." );
    /* -------------------- SELFCARE Form Validations -------------------- */
    public static final Set<ValidationPojo> FORM_SELFCARE_LOGIN_OPTIONS                                  = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.selfcare.login.options." );
    public static final Set<ValidationPojo> FORM_SELFCARE_QUICKPAY_OPTIONS                               = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.selfcare.quickpay.options." );
    public static final Set<ValidationPojo> FORM_SELFCARE_QUICKPAY_AMOUNT                                = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.selfcare.quickpay.amount." );
    //    public static final Set<ValidationPojo> FORM_SELFCARE_LOGIN_CUSTID                      = ValidationPojoUtil
    //                                                                                                    .processFormValidations( "form.criteria.selfcare.login.custid." );
    //    public static final Set<ValidationPojo> FORM_SELFCARE_LOGIN_RMN                         = ValidationPojoUtil
    //                                                                                                    .processFormValidations( "form.criteria.selfcare.login.rmn." );
    //    public static final Set<ValidationPojo> FORM_SELFCARE_LOGIN_EMAIL                       = ValidationPojoUtil
    //                                                                                                    .processFormValidations( "form.criteria.selfcare.login.email." );
    public static final Set<ValidationPojo> SELFCARE_LOGIN_OPTIONS                                       = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.selfcare.login.options." );
    public static final Set<ValidationPojo> FORM_SELFCARE_FORGOTPSWRD                                    = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.selfcare.forgotPassword." );
    public static final Set<ValidationPojo> FORM_SELFCARE_CHANGEPSWRD                                    = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.selfcare.changePassword." );
    public static final Set<ValidationPojo> FORM_SELFCARE_LOG_TICKET                                     = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.selfcare.log.ticket." );
    public static final Set<ValidationPojo> FORM_SELFCARE_LOG_TICKET_REMARKS                             = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.selfcare.log.ticket.remarks." );
    public static final Set<ValidationPojo> FORM_SELFCARE_UPDATE_BILLING_ADDRESS                         = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.selfcare.update.billing.address." );
    public static final Set<ValidationPojo> SELFCARE_QUICKPAY_INIT                                       = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.quickpay.init." );
    public static final Set<ValidationPojo> SELFCARE_QUICKPAY_POST                                       = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.quickpay.post." );
    public static final Set<ValidationPojo> FORM_SELFCARE_PAYMENT_CENTER_PINCODE                         = ValidationPojoUtil
                                                                                                                 .processValidations( "form.criteria.payment.center.pincode." );
    // ------------------------------------ CRM Master -------------------------------------
    public static final Set<ValidationPojo> FORM_ADMIN_CREATE_ALERTS_CONFIG                              = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.admin.create.alerts.config." );
    // ------------------------------------ External Trigger -------------------------------------
    public static final Set<ValidationPojo> COMMON_EXTERNAL_TRIGGER_CRITERIA                             = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.common.external.trigger." );
    public static final Set<ValidationPojo> EXTERNAL_TRIGGER_CRITERIA_FOR_PREPAID_PAYMENT                = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.ext.trigger.payment.request." );
    public static final Set<ValidationPojo> EXTERNAL_TRIGGER_CRITERIA_FOR_QRC_TICKET                     = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.ext.trigger.qrcTicket.request." );
    public static final Set<ValidationPojo> EXTERNAL_TRIGGER_CRITERIA_FOR_CUSTOMER                       = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.ext.trigger.customer.request." );
    public static final Set<ValidationPojo> FORM_QRC_TICKET_FOLLOWON_CHECKS                              = ValidationPojoUtil
                                                                                                                 .processValidations( "form.qrc.ticket.followon.checks." );
    public static final Set<ValidationPojo> CUSTOMER_PLAN_BOOSTER_CREATE_CRITERIA                        = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.customer.plan.booster.create." );
    public static final Set<ValidationPojo> CUSTOMER_BULK_PLAN_BOOSTER_CREATE_CRITERIA                   = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.customer.bulk.plan.booster.create." );
    public static final Set<ValidationPojo> QRC_SEARCH_ACTIVATED_VAS_CRITERIA                            = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.activated.vas.search." );
    public static final Set<ValidationPojo> EXTERNAL_TRIGGER_CRITERIA_FOR_CHANGE_PLAN_REQUEST            = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.ext.trigger.change.plan.request." );
    public static final Set<ValidationPojo> EXTERNAL_TRIGGER_CRITERIA_CHANGE_PLAN_REQUEST_AUTHENTICATION = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.ext.trigger.change.plan.request.authentication." );
    // ------------------------------------ External Trigger -------------------------------------
    public static final Set<ValidationPojo> COMMON_EASY_BILL_CRITERIA                                    = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.common.easyBill.request." );
    public static final Set<ValidationPojo> COMMON_EASY_BILL_TRANSACTION_STATUS_CRITERIA                 = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.easyBill.transaction.status.request." );
    public static final Set<ValidationPojo> EASY_BILL_PAYMENT_DTO_CRITERIA                               = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.easyBill.paymentDto.request." );
    public static final Set<ValidationPojo> EASY_BILL_PAYMENT_DATE_CRITERIA                              = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.easyBill.paymentDate.request." );
    public static final Set<ValidationPojo> EASY_BILL_CRITERIA_OPTION_FOR_CUSTOMERID_AND_RMN             = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.easyBill.option.customerId.and.rmn." );
    public static final Set<ValidationPojo> QRC_CHANGE_CATEGORY_CRITERIA_INDIVIDUAL                      = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.customer.category.individual." );
    public static final Set<ValidationPojo> QRC_CHANGE_CATEGORY_CRITERIA_LTD                             = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.customer.category.ltd." );
    public static final Set<ValidationPojo> QRC_PASSPORT_DETAILS                                         = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.customer.passport." );
    public static final Set<ValidationPojo> QRC_BILL_CYCLE_CHANGE                                        = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.customer.billcycle." );
    public static final Set<ValidationPojo> FORM_QRC_BILL_CYCLE_CHANGE                                   = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.validate.customer.billcycle." );
    // qrc category change
    public static final Set<ValidationPojo> FORM_QRC_CHANGE_CATEGORY_CRITERIA_INDIVIDUAL                 = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.qrc.customer.category.individual." );
    public static final Set<ValidationPojo> FORM_QRC_CHANGE_CATEGORY_CRITERIA_INDIVIDUAL_FORM60          = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.qrc.customer.category.individual.form60." );
    public static final Set<ValidationPojo> FORM_QRC_CHANGE_CATEGORY_CRITERIA_LTD                        = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.qrc.customer.category.ltd." );
    public static final Set<ValidationPojo> FORM_QRC_PASSPORT_DETAILS                                    = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.qrc.customer.passport." );
    // shifting workflow Form validation
    public static final Set<ValidationPojo> FORM_QRC_SHIFTING_INITIATION                                 = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.validate.shifting.initiation." );
    public static final Set<ValidationPojo> FORM_QRC_SHIFTING_IFR_STAGE_OP                               = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.validate.shifting.ifr.op." );
    public static final Set<ValidationPojo> FORM_QRC_SHIFTING_IFR_STAGE_WP                               = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.validate.shifting.ifr.wp." );
    public static final Set<ValidationPojo> FORM_QRC_SHIFTING_CPE_CHANGE                                 = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.criteria.validate.shifting.cpe.change." );
    // shifting workflow service validation
    public static final Set<ValidationPojo> QRC_SHIFTING_INITIATION                                      = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.validate.shifting.initiation." );
    public static final Set<ValidationPojo> QRC_SHIFTING_IFR_STAGE_OP                                    = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.validate.shifting.ifr.op." );
    public static final Set<ValidationPojo> QRC_SHIFTING_IFR_STAGE_WP                                    = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.validate.shifting.ifr.wp." );
    public static final Set<ValidationPojo> QRC_WAIVER_CRITERIA                                          = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.qrc.customer.waiver." );
    // finance refund form validation
    public static final Set<ValidationPojo> FORM_FINANCE_REFUND_CUSTOMER                                 = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.validation.finance.refund.customer." );
    public static final Set<ValidationPojo> FORM_FINANCE_REFUND_CHEQUE                                   = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.validation.finance.refund.cheque." );
    public static final Set<ValidationPojo> FORM_FINANCE_REFUND_ONLINE                                   = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.validation.finance.refund.online." );
    public static final Set<ValidationPojo> FORM_FINANCE_REFUND                                          = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.validation.finance.refund." );
    public static final Set<ValidationPojo> FORM_FINANCE_REMARKS                                         = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.validation.finance.remarks." );
    public static final Set<ValidationPojo> REFUND_PAYMENT_CRITERIA                                      = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.validate.customer.refund." );
    public static final Set<ValidationPojo> FORM_QRC_VALIDITY_EXTENSION                                  = ValidationPojoUtil
                                                                                                                 .processFormValidations( "form.validation.validity.extension." );
    public static final Set<ValidationPojo> LMS_WRAPPER_CRITERIA                                         = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.validate.lms.wrapper." );
    public static final Set<ValidationPojo> LMS_WRAPPER_SEARCH_CRITERIA                                  = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.validate.lms.search.wrapper." );
    public static final Set<ValidationPojo> USER_WRAPPER_CRITERIA                                        = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.validate.user.login.wrapper." );
    public static final Set<ValidationPojo> CHANGE_PASSWORD_WRAPPER_CRITERIA                             = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.validate.user.chnagePassword.wrapper." );
    public static final Set<ValidationPojo> FORGOT_PASSWORD_WRAPPER_CRITERIA                             = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.validate.user.forgotPassword.wrapper." );
    public static final Set<ValidationPojo> MASS_OUTAGE_TRACK_BY_CUSTOMER_ID                             = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.massOutage.track.customerId." );
    // ------------------------------------ E-CAF Validation  -------------------------------------
    public static final Set<ValidationPojo> ECAF_CUSTOMER_DETAILS_CRITERIA                               = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.ecaf.customer." );
    public static final Set<ValidationPojo> ECAF_PLAN_DETAILS_CRITERIA                                   = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.ecaf.custplan." );
    public static final Set<ValidationPojo> ECAF_PAYMENT_DETAILS_CRITERIA                                = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.ecaf.payment." );
    public static final Set<ValidationPojo> ECAF_AADHAR_DETAILS_CRITERIA                                 = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.ecaf.aadhar." );
    public static final Set<ValidationPojo> ECAF_CHEQUE_PAYMENT_CRITERIA                                 = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.ecaf.payment.cheque." );
    public static final Set<ValidationPojo> COMMON_AUTH_ECAF_CRITERIA                                    = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.common.auth.ecaf." );
    public static final Set<ValidationPojo> ECAF_CRITERIA_COMMON_FOR_CUSTOMER                            = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.ecaf.common." );
    public static final Set<ValidationPojo> LMS_ADDRESS_CRITERIA                                         = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.validate.lms.address." );
    public static final Set<ValidationPojo> ECAF_CASH_PAYMENT_CRITERIA                                 = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.ecaf.payment.cash." );
    //  ------------------------------------ Customer Feedback -------------------------------------
    public static final Set<ValidationPojo> CUSTOMER_FEEDBACK_CREATE_CRITERIA                            = ValidationPojoUtil
                                                                                                                 .processValidations( "criteria.customer.feedback.create." );
    //    private static Set<ValidationPojo> processValidations( final String inKey )
    //    {
    //        Set<ValidationPojo> validCriteria = new HashSet<ValidationPojo>();
    //        String strSize = PropertyUtils.getValidationDetails( inKey + IPropertiesConstant.KEY_SIZE );
    //        if ( StringUtils.isNotBlank( strSize ) && StringUtils.isNumeric( strSize ) )
    //        {
    //            int propertySize = Integer.parseInt( strSize );
    //            for ( int i = 1; i <= propertySize; i++ )
    //            {
    //                String validationProperty = PropertyUtils.getValidationDetails( inKey + i );
    //                if ( StringUtils.isNotBlank( validationProperty ) )
    //                {
    //                    String[] validations = StringUtils.split( validationProperty, IAppConstants.COMMA );
    //                    if ( StringUtils.isValidObj( validations ) && validations.length > 0 )
    //                    {
    //                        validations = Arrays.copyOf( validations, 11 );
    //                        if ( validations.length == 11 )
    //                        {
    //                            if ( StringUtils.isBlank( validations[10] ) )
    //                            {
    //                                validations[10] = "false";
    //                            }
    //                            validCriteria.add( new ValidationPojo( StringUtils.trimToNull( validations[0] ),
    //                                                                   StringUtils.trimToNull( validations[1] ),
    //                                                                   Boolean.parseBoolean( validations[2] ),
    //                                                                   Boolean.parseBoolean( validations[3] ),
    //                                                                   StringUtils.trimToNull( validations[4] ),
    //                                                                   StringUtils.trimToNull( validations[5] ),
    //                                                                   StringUtils.trimToNull( validations[6] ),
    //                                                                   StringUtils.trimToNull( validations[7] ),
    //                                                                   StringUtils.trimToNull( validations[8] ),
    //                                                                   StringUtils.trimToNull( validations[9] ),
    //                                                                   Boolean.parseBoolean( validations[10] ) ) );
    //                        }
    //                    }
    //                }
    //            }
    //        }
    //        LOGGER.info( inKey + "=" + validCriteria );
    //        return validCriteria;
    //    }
    //
    //    private static Set<ValidationPojo> processFormValidations( final String inKey )
    //    {
    //        Set<ValidationPojo> validCriteria = new HashSet<ValidationPojo>();
    //        String strSize = PropertyUtils.getFormValidationDetails( inKey + IPropertiesConstant.KEY_SIZE );
    //        if ( StringUtils.isNotBlank( strSize ) && StringUtils.isNumeric( strSize ) )
    //        {
    //            int propertySize = Integer.parseInt( strSize );
    //            for ( int i = 1; i <= propertySize; i++ )
    //            {
    //                String validationProperty = PropertyUtils.getFormValidationDetails( inKey + i );
    //                if ( StringUtils.isNotBlank( validationProperty ) )
    //                {
    //                    String[] validations = StringUtils.split( validationProperty, IAppConstants.COMMA );
    //                    if ( StringUtils.isValidObj( validations ) && validations.length > 0 )
    //                    {
    //                        System.out.println( inKey + "-----:" + Arrays.asList( validations ) );
    //                        validations = Arrays.copyOf( validations, 11 );
    //                        ValidationPojo pojo = null;
    //                        if ( validations.length == 11 )
    //                        {
    //                            if ( StringUtils.isBlank( validations[10] ) )
    //                            {
    //                                validations[10] = "false";
    //                            }
    //                            pojo = new ValidationPojo( StringUtils.trimToNull( validations[0] ),
    //                                                       StringUtils.trimToNull( validations[1] ),
    //                                                       Boolean.parseBoolean( validations[2] ),
    //                                                       Boolean.parseBoolean( validations[3] ),
    //                                                       StringUtils.trimToNull( validations[4] ),
    //                                                       StringUtils.trimToNull( validations[5] ),
    //                                                       StringUtils.trimToNull( validations[6] ),
    //                                                       StringUtils.trimToNull( validations[7] ),
    //                                                       StringUtils.trimToNull( validations[8] ),
    //                                                       StringUtils.trimToNull( validations[9] ),
    //                                                       Boolean.parseBoolean( validations[10] ) );
    //                            String[] placeHolders = StringUtils.split( PropertyUtils.getFormValidationDetails( inKey
    //                                    + i + ".plchldr" ) );
    //                            if ( StringUtils.isValidObj( placeHolders ) && placeHolders.length > 0 )
    //                            {
    //                                pojo.setPlaceHolders( Arrays.asList( placeHolders ) );
    //                            }
    //                            validCriteria.add( pojo );
    //                        }
    //                    }
    //                }
    //            }
    //        }
    //        LOGGER.info( inKey + "=" + validCriteria );
    //        System.out.println( inKey + "=" + validCriteria );
    //        return validCriteria;
    //    }
    //
    //    public static <E> boolean validatePojo( E pojo, Set<ValidationPojo> validCriteria )
    //    {
    //        boolean valid = true;
    //        if ( StringUtils.isValidObj( pojo ) && StringUtils.isValidObj( validCriteria ) && !validCriteria.isEmpty() )
    //        {
    //            for ( ValidationPojo validationPojo : validCriteria )
    //            {
    //                LOGGER.info( "Working on Property:" + validationPojo.getPropertyName() );
    //                if ( !org.apache.commons.beanutils.PropertyUtils.isReadable( pojo, validationPojo.getPropertyName() ) )
    //                {
    //                    valid = false;
    //                    break;
    //                }
    //                try
    //                {
    //                    Object value = org.apache.commons.beanutils.PropertyUtils.getProperty( pojo, validationPojo
    //                            .getPropertyName() );
    //                    if ( validationPojo.isValidcheck() )
    //                    {
    //                        if ( !StringUtils.isValidObj( value ) )
    //                        {
    //                            valid = false;
    //                            break;
    //                        }
    //                    }
    //                    if ( validationPojo.isEmptyCheck() )
    //                    {
    //                        valid = isNonEmpty( value );
    //                        if ( !valid )
    //                        {
    //                            break;
    //                        }
    //                        if ( StringUtils.isNotBlank( validationPojo.getConcreteValue() ) )
    //                        {
    //                            if ( value instanceof String
    //                                    && !StringUtils.equals( validationPojo.getConcreteValue(), String.valueOf( value ) ) )
    //                            {
    //                                valid = false;
    //                                break;
    //                            }
    //                            else if ( value instanceof Long
    //                                    && Long.parseLong( String.valueOf( value ) ) != Long.parseLong( String
    //                                            .valueOf( validationPojo.getConcreteValue() ) ) )
    //                            {
    //                                valid = false;
    //                                break;
    //                            }
    //                        }
    //                    }
    //                }
    //                catch ( IllegalAccessException ex )
    //                {
    //                    LOGGER.error( "Exception:", ex );
    //                }
    //                catch ( InvocationTargetException ex )
    //                {
    //                    LOGGER.error( "Exception:", ex );
    //                }
    //                catch ( NoSuchMethodException ex )
    //                {
    //                    LOGGER.error( "Exception:", ex );
    //                }
    //            }
    //            LOGGER.info( "Validation Finished Result:" + valid );
    //        }
    //        else
    //        {
    //            valid = false;
    //        }
    //        return valid;
    //    }
    //
    //    public static <E> boolean validateSinglePojoProperty( E pojo, Set<ValidationPojo> validCriteria )
    //    {
    //        boolean valid = false;
    //        if ( StringUtils.isValidObj( pojo ) && StringUtils.isValidObj( validCriteria ) && !validCriteria.isEmpty() )
    //        {
    //            for ( ValidationPojo validationPojo : validCriteria )
    //            {
    //                if ( org.apache.commons.beanutils.PropertyUtils.isReadable( pojo, validationPojo.getPropertyName() ) )
    //                {
    //                    try
    //                    {
    //                        Object value = org.apache.commons.beanutils.PropertyUtils.getProperty( pojo, validationPojo
    //                                .getPropertyName() );
    //                        if ( validationPojo.isValidcheck() )
    //                        {
    //                            valid = true;
    //                            if ( !StringUtils.isValidObj( value ) )
    //                            {
    //                                valid = false;
    //                            }
    //                        }
    //                        if ( validationPojo.isEmptyCheck() )
    //                        {
    //                            valid = isNonEmpty( value );
    //                            if ( StringUtils.isNotBlank( validationPojo.getConcreteValue() ) )
    //                            {
    //                                if ( value instanceof String
    //                                        && !StringUtils.equals( validationPojo.getConcreteValue(),
    //                                                                String.valueOf( value ) ) )
    //                                {
    //                                    valid = false;
    //                                }
    //                                else if ( value instanceof Long
    //                                        && Long.parseLong( String.valueOf( value ) ) != Long.parseLong( String
    //                                                .valueOf( validationPojo.getConcreteValue() ) ) )
    //                                {
    //                                    valid = false;
    //                                }
    //                            }
    //                        }
    //                    }
    //                    catch ( IllegalAccessException ex )
    //                    {
    //                        LOGGER.error( "Exception:", ex );
    //                    }
    //                    catch ( InvocationTargetException ex )
    //                    {
    //                        LOGGER.error( "Exception:", ex );
    //                    }
    //                    catch ( NoSuchMethodException ex )
    //                    {
    //                        LOGGER.error( "Exception:", ex );
    //                    }
    //                    if ( valid )
    //                    {
    //                        break;
    //                    }
    //                }
    //            }
    //        }
    //        else
    //        {
    //            valid = false;
    //        }
    //        return valid;
    //    }
    //
    //    public static <E> Map<String, Object[]> validateForm( final E pojo,
    //                                                          Set<ValidationPojo> validCriteria,
    //                                                          boolean isSave )
    //    {
    //        Map<String, Object[]> resultMap = null;
    //        //resultMap = new HashMap<String, Object[]>();
    //        boolean valid = true;
    //        if ( StringUtils.isValidObj( pojo ) && StringUtils.isValidObj( validCriteria ) && !validCriteria.isEmpty() )
    //        {
    //            for ( ValidationPojo validationPojo : validCriteria )
    //            {
    //                LOGGER.info( "Working on Property:" + validationPojo.getPropertyName() );
    //                if ( !org.apache.commons.beanutils.PropertyUtils.isReadable( pojo, validationPojo.getPropertyName() ) )
    //                {
    //                    valid = false;
    //                    break;
    //                }
    //                try
    //                {
    //                    Object value = org.apache.commons.beanutils.PropertyUtils.getProperty( pojo, validationPojo
    //                            .getPropertyName() );
    //                    if ( validationPojo.isValidcheck() )
    //                    {
    //                        if ( !StringUtils.isValidObj( value ) )
    //                        {
    //                            resultMap = setErrorMap( validationPojo, value );
    //                            valid = false;
    //                            break;
    //                        }
    //                    }
    //                    if ( isSave && validationPojo.isToSave() )
    //                    {
    //                        valid = isNonEmpty( value );
    //                        if ( valid )
    //                        {
    //                            if ( StringUtils.isNotBlank( validationPojo.getMinSizeLength() )
    //                                    && StringUtils.isNumeric( validationPojo.getMinSizeLength() ) )
    //                            {
    //                                if ( String.valueOf( value ).length() < Integer.parseInt( validationPojo
    //                                        .getMinSizeLength() ) )
    //                                {
    //                                    resultMap = setErrorMap( validationPojo, value );
    //                                    valid = false;
    //                                    break;
    //                                }
    //                            }
    //                            if ( StringUtils.isNotBlank( validationPojo.getMaxSizeLengh() )
    //                                    && StringUtils.isNumeric( validationPojo.getMaxSizeLengh() ) )
    //                            {
    //                                if ( String.valueOf( value ).length() > Integer.parseInt( validationPojo
    //                                        .getMaxSizeLengh() ) )
    //                                {
    //                                    resultMap = setErrorMap( validationPojo, value );
    //                                    valid = false;
    //                                    break;
    //                                }
    //                            }
    //                            if ( StringUtils.isNotBlank( validationPojo.getRegex() ) )
    //                            {
    //                                if ( !StringUtils
    //                                        .compareRegularExp( validationPojo.getRegex(), String.valueOf( value ) ) )
    //                                {
    //                                    resultMap = setErrorMap( validationPojo, value );
    //                                    valid = false;
    //                                    break;
    //                                }
    //                            }
    //                            if ( StringUtils.isNotBlank( validationPojo.getConcreteValue() ) )
    //                            {
    //                                if ( value instanceof String
    //                                        && !StringUtils.equals( validationPojo.getConcreteValue(),
    //                                                                String.valueOf( value ) ) )
    //                                {
    //                                    valid = false;
    //                                    break;
    //                                }
    //                                else if ( value instanceof Long
    //                                        && Long.parseLong( String.valueOf( value ) ) != Long.parseLong( String
    //                                                .valueOf( validationPojo.getConcreteValue() ) ) )
    //                                {
    //                                    valid = false;
    //                                    break;
    //                                }
    //                            }
    //                        }
    //                    }
    //                    else if ( validationPojo.isEmptyCheck() )
    //                    {
    //                        valid = isNonEmpty( value );
    //                        if ( !valid )
    //                        {
    //                            resultMap = setErrorMap( validationPojo, value );
    //                            break;
    //                        }
    //                        if ( StringUtils.isNotBlank( validationPojo.getMinSizeLength() )
    //                                && StringUtils.isNumeric( validationPojo.getMinSizeLength() ) )
    //                        {
    //                            if ( String.valueOf( value ).length() < Integer
    //                                    .parseInt( validationPojo.getMinSizeLength() ) )
    //                            {
    //                                resultMap = setErrorMap( validationPojo, value );
    //                                valid = false;
    //                                break;
    //                            }
    //                        }
    //                        if ( StringUtils.isNotBlank( validationPojo.getMaxSizeLengh() )
    //                                && StringUtils.isNumeric( validationPojo.getMaxSizeLengh() ) )
    //                        {
    //                            if ( String.valueOf( value ).length() > Integer.parseInt( validationPojo.getMaxSizeLengh() ) )
    //                            {
    //                                resultMap = setErrorMap( validationPojo, value );
    //                                valid = false;
    //                                break;
    //                            }
    //                        }
    //                        if ( StringUtils.isNotBlank( validationPojo.getRegex() ) )
    //                        {
    //                            if ( !StringUtils.compareRegularExp( validationPojo.getRegex(), String.valueOf( value ) ) )
    //                            {
    //                                resultMap = setErrorMap( validationPojo, value );
    //                                valid = false;
    //                                break;
    //                            }
    //                        }
    //                        if ( StringUtils.isNotBlank( validationPojo.getConcreteValue() ) )
    //                        {
    //                            if ( value instanceof String
    //                                    && !StringUtils.equals( validationPojo.getConcreteValue(), String.valueOf( value ) ) )
    //                            {
    //                                valid = false;
    //                                break;
    //                            }
    //                            else if ( value instanceof Long
    //                                    && Long.parseLong( String.valueOf( value ) ) != Long.parseLong( String
    //                                            .valueOf( validationPojo.getConcreteValue() ) ) )
    //                            {
    //                                valid = false;
    //                                break;
    //                            }
    //                        }
    //                    }
    //                }
    //                catch ( IllegalAccessException ex )
    //                {
    //                    LOGGER.error( "Exception:", ex );
    //                }
    //                catch ( InvocationTargetException ex )
    //                {
    //                    LOGGER.error( "Exception:", ex );
    //                }
    //                catch ( NoSuchMethodException ex )
    //                {
    //                    LOGGER.error( "Exception:", ex );
    //                }
    //            }
    //            LOGGER.info( "Validation Finished Result:" + valid );
    //        }
    //        else
    //        {
    //            valid = false;
    //        }
    //        return resultMap;
    //    }
    //
    //    public static <E> Map<String, Object[]> validateSingleFormProperty( final E pojo,
    //                                                                        Set<ValidationPojo> validCriteria,
    //                                                                        boolean isSave )
    //    {
    //        Map<String, Object[]> resultMap = null;
    //        //resultMap = new HashMap<String, Object[]>();
    //        boolean valid = true;
    //        if ( StringUtils.isValidObj( pojo ) && StringUtils.isValidObj( validCriteria ) && !validCriteria.isEmpty() )
    //        {
    //            for ( ValidationPojo validationPojo : validCriteria )
    //            {
    //                valid = true;
    //                LOGGER.info( "Working on Property:" + validationPojo.getPropertyName() );
    //                if ( !org.apache.commons.beanutils.PropertyUtils.isReadable( pojo, validationPojo.getPropertyName() ) )
    //                {
    //                    valid = false;
    //                }
    //                if ( valid )
    //                {
    //                    try
    //                    {
    //                        Object value = org.apache.commons.beanutils.PropertyUtils.getProperty( pojo, validationPojo
    //                                .getPropertyName() );
    //                        if ( validationPojo.isValidcheck() )
    //                        {
    //                            if ( !StringUtils.isValidObj( value ) )
    //                            {
    //                                resultMap = setErrorMap( validationPojo, value );
    //                                valid = false;
    //                            }
    //                        }
    //                        if ( valid && isSave && validationPojo.isToSave() )
    //                        {
    //                            valid = isNonEmpty( value );
    //                            if ( valid && StringUtils.isNotBlank( validationPojo.getMinSizeLength() )
    //                                    && StringUtils.isNumeric( validationPojo.getMinSizeLength() ) )
    //                            {
    //                                if ( String.valueOf( value ).length() < Integer.parseInt( validationPojo
    //                                        .getMinSizeLength() ) )
    //                                {
    //                                    resultMap = setErrorMap( validationPojo, value );
    //                                    valid = false;
    //                                }
    //                            }
    //                            if ( valid && StringUtils.isNotBlank( validationPojo.getMaxSizeLengh() )
    //                                    && StringUtils.isNumeric( validationPojo.getMaxSizeLengh() ) )
    //                            {
    //                                if ( String.valueOf( value ).length() > Integer.parseInt( validationPojo
    //                                        .getMaxSizeLengh() ) )
    //                                {
    //                                    resultMap = setErrorMap( validationPojo, value );
    //                                    valid = false;
    //                                }
    //                            }
    //                            if ( valid && StringUtils.isNotBlank( validationPojo.getRegex() ) )
    //                            {
    //                                if ( !StringUtils
    //                                        .compareRegularExp( validationPojo.getRegex(), String.valueOf( value ) ) )
    //                                {
    //                                    resultMap = setErrorMap( validationPojo, value );
    //                                    valid = false;
    //                                }
    //                            }
    //                            if ( valid && StringUtils.isNotBlank( validationPojo.getConcreteValue() ) )
    //                            {
    //                                if ( value instanceof String
    //                                        && !StringUtils.equals( validationPojo.getConcreteValue(),
    //                                                                String.valueOf( value ) ) )
    //                                {
    //                                    valid = false;
    //                                }
    //                                else if ( value instanceof Long
    //                                        && Long.parseLong( String.valueOf( value ) ) != Long.parseLong( String
    //                                                .valueOf( validationPojo.getConcreteValue() ) ) )
    //                                {
    //                                    valid = false;
    //                                }
    //                            }
    //                        }
    //                        else if ( valid && validationPojo.isEmptyCheck() )
    //                        {
    //                            valid = isNonEmpty( value );
    //                            if ( !valid )
    //                            {
    //                                resultMap = setErrorMap( validationPojo, value );
    //                            }
    //                            if ( valid && StringUtils.isNotBlank( validationPojo.getMinSizeLength() )
    //                                    && StringUtils.isNumeric( validationPojo.getMinSizeLength() ) )
    //                            {
    //                                if ( String.valueOf( value ).length() < Integer.parseInt( validationPojo
    //                                        .getMinSizeLength() ) )
    //                                {
    //                                    resultMap = setErrorMap( validationPojo, value );
    //                                    valid = false;
    //                                }
    //                            }
    //                            if ( valid && StringUtils.isNotBlank( validationPojo.getMaxSizeLengh() )
    //                                    && StringUtils.isNumeric( validationPojo.getMaxSizeLengh() ) )
    //                            {
    //                                if ( String.valueOf( value ).length() > Integer.parseInt( validationPojo
    //                                        .getMaxSizeLengh() ) )
    //                                {
    //                                    resultMap = setErrorMap( validationPojo, value );
    //                                    valid = false;
    //                                }
    //                            }
    //                            if ( valid && StringUtils.isNotBlank( validationPojo.getRegex() ) )
    //                            {
    //                                if ( !StringUtils
    //                                        .compareRegularExp( validationPojo.getRegex(), String.valueOf( value ) ) )
    //                                {
    //                                    resultMap = setErrorMap( validationPojo, value );
    //                                    valid = false;
    //                                }
    //                            }
    //                            if ( valid && StringUtils.isNotBlank( validationPojo.getConcreteValue() ) )
    //                            {
    //                                if ( value instanceof String
    //                                        && !StringUtils.equals( validationPojo.getConcreteValue(),
    //                                                                String.valueOf( value ) ) )
    //                                {
    //                                    valid = false;
    //                                }
    //                                else if ( value instanceof Long
    //                                        && Long.parseLong( String.valueOf( value ) ) != Long.parseLong( String
    //                                                .valueOf( validationPojo.getConcreteValue() ) ) )
    //                                {
    //                                    valid = false;
    //                                }
    //                            }
    //                        }
    //                    }
    //                    catch ( IllegalAccessException ex )
    //                    {
    //                        LOGGER.error( "Exception:", ex );
    //                    }
    //                    catch ( InvocationTargetException ex )
    //                    {
    //                        LOGGER.error( "Exception:", ex );
    //                    }
    //                    catch ( NoSuchMethodException ex )
    //                    {
    //                        LOGGER.error( "Exception:", ex );
    //                    }
    //                }
    //                if ( valid )
    //                {
    //                    resultMap = null;
    //                    break;
    //                }
    //            }
    //            LOGGER.info( "Validation Finished Result:" + valid );
    //        }
    //        else
    //        {
    //            valid = false;
    //        }
    //        return resultMap;
    //    }
    //
    //    private static boolean isNonEmpty( Object value )
    //    {
    //        boolean valid = StringUtils.isValidObj( value );
    //        if ( valid )
    //        {
    //            LOGGER.info( "Property instanceof:" + value.getClass() );
    //            if ( value instanceof String && StringUtils.isBlank( String.valueOf( value ) ) )
    //            {
    //                valid = false;
    //            }
    //            else if ( value instanceof Integer && Integer.parseInt( String.valueOf( value ) ) == 0 )
    //            {
    //                valid = false;
    //            }
    //            else if ( value instanceof Long && Long.parseLong( String.valueOf( value ) ) == 0 )
    //            {
    //                valid = false;
    //            }
    //            else if ( value instanceof Date && StringUtils.isBlank( String.valueOf( value ) ) )
    //            {
    //                valid = false;
    //            }
    //            else if ( value instanceof BigDecimal )
    //            {
    //                BigDecimal bd = new BigDecimal( String.valueOf( value ) );
    //                if ( bd.compareTo( zeroBd ) <= 0 )
    //                {
    //                    valid = false;
    //                }
    //            }
    //            else if ( value instanceof List<?> && ( (List<?>) value ).isEmpty() )
    //            {
    //                valid = false;
    //            }
    //            else if ( value instanceof Set<?> && ( (Set<?>) value ).isEmpty() )
    //            {
    //                valid = false;
    //            }
    //            else if ( value instanceof Map<?, ?> && ( (Map<?, ?>) value ).isEmpty() )
    //            {
    //                valid = false;
    //            }
    //        }
    //        return valid;
    //    }
    //
    //    private static Map<String, Object[]> setErrorMap( ValidationPojo validationPojo, Object value )
    //    {
    //        Map<String, Object[]> resultMap;
    //        resultMap = new HashMap<String, Object[]>();
    //        if ( StringUtils.isValidObj( validationPojo.getPlaceHolders() ) && !validationPojo.getPlaceHolders().isEmpty() )
    //        {
    //            List<Object> placeHolders = new ArrayList<Object>();
    //            for ( String placeHolder : validationPojo.getPlaceHolders() )
    //            {
    //                if ( StringUtils.equals( "objValue", placeHolder ) )
    //                {
    //                    placeHolders.add( value );
    //                }
    //                else if ( StringUtils.equals( "minSizeLength", placeHolder ) )
    //                {
    //                    placeHolders.add( validationPojo.getMinSizeLength() );
    //                }
    //                else if ( StringUtils.equals( "maxSizeLengh", placeHolder ) )
    //                {
    //                    placeHolders.add( validationPojo.getMaxSizeLengh() );
    //                }
    //                else
    //                {
    //                    placeHolders.add( placeHolder );
    //                }
    //            }
    //            resultMap.put( validationPojo.getErrorKey(), placeHolders.toArray() );
    //        }
    //        else
    //        {
    //            resultMap.put( validationPojo.getErrorKey(), null );
    //        }
    //        return resultMap;
    //    }
    //
    //    public static void main( String[] args )
    //    {
    //        ICRMValidationCriteriaUtil util = new ICRMValidationCriteriaUtil();
    //    }
}
