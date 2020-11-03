package com.np.tele.crm.qrc.businessmgr;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.MDC;

import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMEvents;
import com.np.tele.crm.constants.CRMFunctionalBinStages;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.CrmTicketActions;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.constants.QRCRolCategories;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.ext.pojos.CustomerUsageDetailsPojo;
import com.np.tele.crm.pojos.CrmAddressDetailsPojo;
import com.np.tele.crm.pojos.CrmCustomerActivityPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmNpMobilePojo;
import com.np.tele.crm.pojos.CrmPaymentDetailsPojo;
import com.np.tele.crm.pojos.CrmQrcCategoriesPojo;
import com.np.tele.crm.pojos.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.pojos.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.CrmSrTicketsPojo;
import com.np.tele.crm.pojos.CrmTicketHistoryPojo;
import com.np.tele.crm.pojos.LMSPojo;
import com.np.tele.crm.pojos.PartnerPojo;
import com.np.tele.crm.qrc.dao.IBillingDataDao;
import com.np.tele.crm.qrc.dao.ICrmTicketDao;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.ExcelWriteUtils;
import com.np.tele.crm.utils.FileUtils;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.PropertyUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

public class CrmTicketManagerImpl implements ICrmTicketManager {
	private ICrmTicketDao ticketDao = null;
	private IBillingDataDao billingDao = null;
	private static final Logger LOGGER = Logger.getLogger(CrmTicketManagerImpl.class);
	private static final CrmSrTicketsPojo TICKET_IUD = new CrmSrTicketsPojo();

	public ICrmTicketDao getTicketDao() {
		return ticketDao;
	}

	public void setTicketDao(ICrmTicketDao inTicketDao) {
		ticketDao = inTicketDao;
	}

	public IBillingDataDao getBillingDao() {
		return billingDao;
	}

	public void setBillingDao(IBillingDataDao inBillingDao) {
		billingDao = inBillingDao;
	}

	@Override
	public CrmQrcDto ticketOperations(String inServiceParam, String inCrmParam, CrmQrcDto inCrmQrcDto) {
		CRMServiceCode serviceCode = CRMServiceCode.CRM997;
		CrmCustomerDetailsPojo customerDetailsPojo = null;
		LMSPojo lmsPojo = null;
		if (!StringUtils.isValidObj(inCrmQrcDto)) {
			inCrmQrcDto = new CrmQrcDto();
			return inCrmQrcDto;
		}
		try {
			CrmSrTicketsPojo ticket = inCrmQrcDto.getCrmSrTicketsPojo();
			CrmTicketHistoryPojo ticketHistory = inCrmQrcDto.getTicketHistory();
			if (StringUtils.equals(CrmSrTicketsPojo.class.getSimpleName(), inCrmParam)) {
				CRMEvents crmEvent = null;
				if (!StringUtils.isValidObj(ticket)) {
					return inCrmQrcDto;
				}
				if (StringUtils.equals(ServiceParameter.VIEW.getParameter(), inServiceParam)) {
					if (ticket.getTicketId() > 0) {
						inCrmQrcDto.setCrmSrTicketsPojo(
								CRMServiceUtils.getDBValues(CrmSrTicketsPojo.class, ticket.getTicketId()));
						serviceCode = CRMServiceCode.CRM001;
					} else if (StringUtils.isNotBlank(ticket.getSrId())) {
						inCrmQrcDto.setCrmSrTicketsPojo(
								CRMServiceUtils.getDBValues(CrmSrTicketsPojo.class, "srId", ticket.getSrId()));
						serviceCode = CRMServiceCode.CRM001;
					}
					return inCrmQrcDto;
				} else if (StringUtils.equals(ServiceParameter.LIST.getParameter(), inServiceParam)) {
					serviceCode = getTicketDao().listTickets(inCrmQrcDto);
					return inCrmQrcDto;
				} else if (StringUtils.equals(ServiceParameter.UPDATE.getParameter(), inServiceParam)) {
					serviceCode = getTicketDao().updateTicket(ticket);
					return inCrmQrcDto;
				}
				if (!StringUtils.isValidObj(ticketHistory)) {
					ticketHistory = getGeneratedHistory(inServiceParam, ticket);
				}
				if (StringUtils.equals(CrmActionEnum.OPENED.getActionCode(), inServiceParam)) {
					if (ticket.getQrcCategoryId() <= 0 && StringUtils.isNotEmpty(ticket.getQrcCategory())) {
						getTicketDao().preparedQrcTicketPojo(ticket);
						inCrmQrcDto.setCrmSrTicketsPojo(ticket);
					}
					if (ValidationPojoUtil.validatePojo(ticket,
							ICRMValidationCriteriaUtil.QRCPOST_SERVICE_REQUEST_CRITERIA) == null) {
						boolean isValid = true;
						if (StringUtils.equals(inCrmQrcDto.getCrmSrTicketsPojo().getModuleType(),
								CRMRequestType.QRC.getRequestCode())) {
							customerDetailsPojo = CRMServiceUtils.getDBValues(CrmCustomerDetailsPojo.class,
									"customerId", inCrmQrcDto.getCrmSrTicketsPojo().getMappingId());
							if (!StringUtils.isValidObj(customerDetailsPojo)) {
								isValid = false;
								serviceCode = CRMServiceCode.CRM408;
								LOGGER.info("Customer doesn't exist");
							}
						} else {
							lmsPojo = CRMServiceUtils.getDBValues(LMSPojo.class, "leadId",
									inCrmQrcDto.getCrmSrTicketsPojo().getMappingId());
							if (!StringUtils.isValidObj(lmsPojo)) {
								isValid = false;
								serviceCode = CRMServiceCode.CRM204;
								LOGGER.info("Lead ID doesn't exist.");
							}
						}
						if (isValid) {
							serviceCode = getTicketDao().openTicket(inCrmQrcDto, ticketHistory);
						}
						ticket = inCrmQrcDto.getCrmSrTicketsPojo();
						if (customerDetailsPojo.getCustomerId().equals(ticket.getCreatedBy())) {

						} else {

							if (CRMServiceCode.CRM001 == serviceCode) {
								sendAlertForNP(ticket, crmEvent);
								crmEvent = getTicketIUDEvent(ticket, inCrmQrcDto.getCustomerDetailsPojo());
								if (!StringUtils.isValidObj(crmEvent) && !StringUtils.equals(ticket.getQrcType(),
										CRMDisplayListConstants.QUERY.getCode())) {
									if (StringUtils.equals(CRMStatusCode.REOPEN.getStatusCode(), ticket.getStatus())) {
										serviceCode = CRMServiceCode.CRM405;
										crmEvent = CRMEvents.REOPEN_SERVICE_REQUEST;
									} else if (StringUtils.equals(CRMStatusCode.RESOLVED.getStatusCode(),
											ticket.getStatus())) {
										if (StringUtils.equalsIgnoreCase(ticket.getQrcSubCategory(),
												"Reset Wifi Security Key")
												&& StringUtils.equalsIgnoreCase(ticket.getQrcSubSubCategory(),
														"Reset Wifi Security Key")) {
											crmEvent = CRMEvents.RESET_WIFI_SECURITY_KEY;
										} else
											crmEvent = CRMEvents.RESOVLE_SERVICE_REQUEST;
									} else if (StringUtils.equals(CRMStatusCode.OPEN.getStatusCode(),
											ticket.getStatus())) {
										crmEvent = CRMEvents.OPEN_SR_REQUEST;
										if (StringUtils.equals(ticket.getQrcType(),
												CRMDisplayListConstants.COMPLAINTS.getCode())) {
											crmEvent = CRMEvents.OPEN_SR_COMPLAINT;
										} else if (StringUtils.equalsIgnoreCase(ticket.getQrcSubCategory(),
												"Reset Wifi Security Key")
												&& StringUtils.equalsIgnoreCase(ticket.getQrcSubSubCategory(),
														"Reset Wifi Security Key")) {
											crmEvent = CRMEvents.RESET_WIFI_SECURITY_KEY_WITH_SMS;
										}
									}
								}
							} else {
								serviceCode = CRMServiceCode.CRM997;
							}
						}
					}
				} else if (StringUtils.equals(CrmActionEnum.FOLLOW_UP.getActionCode(), inServiceParam)) {
					/*
					 * if ( StringUtils.isValidObj( ticket.getFollowupOn() ) &&
					 * Calendar.getInstance().getTime().before(
					 * ticket.getFollowupOn() ) ) {
					 */serviceCode = getTicketDao().modifyFollowUpTime(inCrmQrcDto, ticketHistory);
					ticket = inCrmQrcDto.getCrmSrTicketsPojo();
					/*
					 * } else { serviceCode = CRMServiceCode.CRM997; }
					 */
				} else if (StringUtils.equals(CrmActionEnum.RESOLVED.getActionCode(), inServiceParam)) {
					serviceCode = getTicketDao().resolveTicket(inCrmQrcDto, ticketHistory);
					ticket = inCrmQrcDto.getCrmSrTicketsPojo();
					if (CRMServiceCode.CRM001 == serviceCode) {
						if (StringUtils.equalsIgnoreCase(ticket.getQrcSubCategory(), "Reset Wifi Security Key")
								&& StringUtils.equalsIgnoreCase(ticket.getQrcSubSubCategory(),
										"Reset Wifi Security Key")) {
							crmEvent = CRMEvents.RESET_WIFI_SECURITY_KEY;
						} else {
							crmEvent = CRMEvents.RESOVLE_SERVICE_REQUEST;
						}
					}
				} else if (StringUtils.equals(CrmActionEnum.CLOSED.getActionCode(), inServiceParam)) {
					serviceCode = getTicketDao().closeTicket(ticket, ticketHistory);
					// if ( CRMServiceCode.CRM001 == serviceCode )
					// {
					// crmEvent = CRMEvents.CLOSE_SERVICE_REQUEST;
					// }
				} else if (StringUtils.equals(CrmActionEnum.FORWARDED.getActionCode(), inServiceParam)) {
					if (StringUtils.isNotBlank(inCrmQrcDto.getFutureStage())) {
						serviceCode = getTicketDao().forwardTicket(inCrmQrcDto, ticket, ticketHistory,
								inCrmQrcDto.getFutureStage(), inCrmQrcDto.getFutureOwner());
						if (ticket.isEditable()) {
							LOGGER.info("Inbox is valid");
							sendAlertForNP(inCrmQrcDto.getCrmSrTicketsPojo(), crmEvent);
						}
					} else {
						serviceCode = CRMServiceCode.CRM997;
					}
				} else if (StringUtils.equals(CrmActionEnum.REOPENED.getActionCode(), inServiceParam)) {
					serviceCode = getTicketDao().reopenedTicket(inCrmQrcDto, ticketHistory);
					ticket = inCrmQrcDto.getCrmSrTicketsPojo();
					if (StringUtils.isNumeric(ticket.getCreatedBy())) {

					} else {

						if (CRMServiceCode.CRM001 == serviceCode) {
							sendAlertForNP(inCrmQrcDto.getCrmSrTicketsPojo(), crmEvent);
							serviceCode = CRMServiceCode.CRM405;
							crmEvent = CRMEvents.REOPEN_SERVICE_REQUEST;
						}
					}
				}
				if ((CRMServiceCode.CRM001 == serviceCode || serviceCode == CRMServiceCode.CRM405)
						&& StringUtils.isValidObj(crmEvent)) {
					if (crmEvent == CRMEvents.TICKET_IUD || crmEvent == CRMEvents.TICKET_IUD_INITIA) {
						CrmCustomerActivityPojo activityLogPojo = new CrmCustomerActivityPojo();
						if (sendAlertUsageDispute(ticket, crmEvent,
								inCrmQrcDto.getCustAdditionalDetails().getActivationDate(), activityLogPojo)) {
							createActivityLog(activityLogPojo, inCrmQrcDto,
									CRMCustomerActivityActions.UNBILLED_USAGE_SENT,
									"Ticket " + CRMStatusCode.getStatus(ticket.getStatus())
											+ "for Unbilled Usage Details");
						}
					} else if (crmEvent == CRMEvents.RESET_WIFI_SECURITY_KEY) {
						List<String> attachments = null;
						try {
							String path = FileUtils.getFilePath("Steps_to_change_Wireless_Network.pdf");
							LOGGER.info("File path:" + path);
							if (StringUtils.isNotBlank(path)) {
								attachments = new ArrayList<String>();
								attachments.add(path);
							}
							CRMServiceUtils.sendAlerts(crmEvent, CRMRequestType.QRC, ticket.getSrId(), attachments);
						} catch (Exception ex) {
							LOGGER.warn("Exception loading File : Steps_to_change_Wireless_Network.pdf - " + ex, ex);
						}
					} else if (crmEvent == CRMEvents.RESET_WIFI_SECURITY_KEY_WITH_SMS) {
						List<String> attachments = null;
						try {
							String path = FileUtils.getFilePath("Steps_to_change_Wireless_Network.pdf");
							LOGGER.info("File path:" + path);
							if (StringUtils.isNotBlank(path)) {
								attachments = new ArrayList<String>();
								attachments.add(path);
							}
							CRMServiceUtils.sendAlerts(crmEvent, CRMRequestType.QRC, ticket.getSrId(), attachments);
						} catch (Exception ex) {
							LOGGER.warn("Exception loading File : Steps_to_change_Wireless_Network.pdf - " + ex, ex);
						}
					} else if (!StringUtils.equals(ticket.getQrcType(), CRMDisplayListConstants.QUERY.getCode())
							&& !isBackendCategory(ticket.getQrcCategoryId())
							&& ticket.getResolutionType() != CrmTicketActions.ROL.getCode()) {
						if (StringUtils.equals(ticket.getModuleType(), CRMRequestType.QRC.getRequestCode())) {
							CRMServiceUtils.sendAlerts(crmEvent, CRMRequestType.QRC, ticket.getSrId(), null);
						} else {
							CRMServiceUtils.sendAlerts(crmEvent, CRMRequestType.LMS_TKT, ticket.getSrId(), null);
						}
					}
				}
			} else if (StringUtils.equals(CrmTicketHistoryPojo.class.getSimpleName(), inCrmParam)) {
				if (!StringUtils.isValidObj(ticketHistory)) {
					return inCrmQrcDto;
				}
				if (StringUtils.equals(CrmActionEnum.SAVED_REMARKS.getActionCode(), inServiceParam)
						|| StringUtils.equals(CrmActionEnum.ASSIGNED.getActionCode(), inServiceParam)) {
					serviceCode = getTicketDao().saveTicketHistory(ticketHistory, null);
					if (CRMServiceCode.CRM001 == serviceCode) {
						CrmSrTicketsPojo ticketObj = CRMServiceUtils.getDBValues(CrmSrTicketsPojo.class, "srId",
								ticketHistory.getTicketId());
						if (StringUtils.isValidObj(ticketObj)) {
							if (StringUtils.equals(ticketHistory.getAction(),
									CrmActionEnum.FLAG_REMARKS.getActionCode())) {
								ticketObj.setFlagRemarks(ticketHistory.getRemarks());
							}
							ticketObj.setLastModifiedTime(Calendar.getInstance().getTime());
							CRMServiceUtils.mergeDBValues(ticketObj);
						}
					}
				} else if (StringUtils.equals(ServiceParameter.LIST.getParameter(), inServiceParam)) {
					if (StringUtils.isNotBlank(ticketHistory.getTicketId())) {
						serviceCode = getTicketDao().listTicketHistory(inCrmQrcDto);
					}
				}
			}
		} catch (Exception ex) {
			serviceCode = CRMServiceCode.CRM999;
			LOGGER.error("Exception while executing ticketOperations for " + inServiceParam, ex);
		} finally {
			inCrmQrcDto.setStatusCode(serviceCode.getStatusCode());
			inCrmQrcDto.setStatusDesc(serviceCode.getStatusDesc());
		}
		return inCrmQrcDto;
	}

	private void sendAlertForNP(CrmSrTicketsPojo inTicket, CRMEvents inCrmEvent) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Criteria criteria = null;
		List<CrmNpMobilePojo> crmNpMobilePojos = null;
		CrmRcaReasonPojo crmRcaReasonPojo = null;
		PartnerPojo partnerPojo = null;
		CrmCustomerDetailsPojo crmCustPojo = null;
		CrmQrcSubSubCategoriesPojo subSubCat = null;
		String address = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			if (StringUtils.isValidObj(inTicket)) {
				crmRcaReasonPojo = (CrmRcaReasonPojo) session.get(CrmRcaReasonPojo.class,
						inTicket.getFunctionalbinId());
				if (StringUtils.isValidObj(crmRcaReasonPojo) && StringUtils.equals(crmRcaReasonPojo.getCategoryValue(),
						CRMFunctionalBinStages.EOC_SERVICE.getFunctionalBin())) {
					crmCustPojo = CRMServiceUtils.getDBValues(CrmCustomerDetailsPojo.class, "customerId",
							inTicket.getMappingId(), session);
					if (StringUtils.isValidObj(crmCustPojo)) {
						Map<String, Object> criteriaMap = new HashMap<String, Object>();
						criteriaMap.put("installationStatus", CRMDisplayListConstants.PREINSTALLATION.getCode());
						List<String> entityType = new ArrayList<String>();
						entityType.add(CRMDisplayListConstants.TELESERVICES.getCode());
						criteriaMap.put("entityType", entityType);
						criteriaMap.put(IAppConstants.PARAM_CUSTOMER_RECORD_ID, crmCustPojo.getRecordId());
						List<CrmPaymentDetailsPojo> paymentDetailsPojos = CRMServiceUtils
								.getDBValueList(CrmPaymentDetailsPojo.class, criteriaMap);
						Set<CrmAddressDetailsPojo> addresses = crmCustPojo.getCrmAddressDetailses();
						if (CommonValidator.isValidCollection(addresses)) {
							for (CrmAddressDetailsPojo crmAddressDetailsPojo : addresses) {
								if (StringUtils.equals(IAppConstants.ADDRESS_TYPE_INSTALLATION,
										crmAddressDetailsPojo.getAddressType())) {
									address = crmAddressDetailsPojo.getAddLine1() + IAppConstants.SPACE
											+ crmAddressDetailsPojo.getAddLine2() + IAppConstants.SPACE
											+ crmAddressDetailsPojo.getAddLine3();
								}
							}
						}
						criteria = session.createCriteria(CrmNpMobilePojo.class);
						criteria.add(Restrictions.eq("npId", crmCustPojo.getNpId()));
						criteria.add(Restrictions.eq("status", CRMStatusCode.ACTIVE.getStatusCode()));
						crmNpMobilePojos = criteria.list();
						partnerPojo = (PartnerPojo) session.get(PartnerPojo.class, crmCustPojo.getNpId());
						if (StringUtils.isValidObj(partnerPojo)) {
							inCrmEvent = CRMEvents.ASSIGN_EOC_SERVICE;
							subSubCat = CRMServiceUtils.getDBValues(CrmQrcSubSubCategoriesPojo.class,
									inTicket.getQrcSubSubCategoryId());
							if (CommonValidator.isValidCollection(crmNpMobilePojos)
									&& StringUtils.isValidObj(subSubCat)) {
								for (CrmNpMobilePojo crmNpMobilePojo : crmNpMobilePojos) {
									Map<String, String> paramValues = new HashMap<String, String>();
									paramValues.put("TICKETID", inTicket.getSrId());
									paramValues.put("LCONAME", partnerPojo.getPartnerName());
									paramValues.put("CUSTOMERFIRSTNAME", crmCustPojo.getCustFname() + " "
											+ StringUtils.trimToEmpty(crmCustPojo.getCustLname()));
									paramValues.put("CUSTOMER_ADDRESS", address);
									paramValues.put("USERMOBILENO", crmNpMobilePojo.getMobileNo() + "");
									paramValues.put("CUSTOMER_RMN", crmCustPojo.getRmn() + "");
									paramValues.put("QRC_SUB_SUB_CATEGORY", subSubCat.getQrcSubSubCategory());
									if (CommonValidator.isValidCollection(paymentDetailsPojos)) {
										paramValues.put("SECURITY_DEPOSIT",
												paymentDetailsPojos.get(0).getSecurityCharges() + "");
									}
									CRMServiceUtils.sendAlerts(inCrmEvent, CRMRequestType.ALERT, inTicket.getSrId(),
											null, paramValues);
									LOGGER.info("Send Successfully....");
								}
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			LOGGER.info("Getting Error In sendAlertForNP Method :: ", ex);
		} finally {
			CRMServiceUtils.closeSession(session);
		}
	}

	private CRMEvents getTicketIUDEvent(CrmSrTicketsPojo inTicket, CrmCustomerDetailsPojo inCrmCustomerDetailsPojo) {
		CRMEvents crmEvent = null;
		if (TICKET_IUD.getQrcCategoryId() <= 0) {
			CrmQrcCategoriesPojo categoriesPojo = getTicketDao().getQrcCategoriesID(
					QRCRolCategories.USAGE_DISPUTE.getCategory(), QRCRolCategories.USAGE_DISPUTE.getSubCategory(),
					QRCRolCategories.USAGE_DISPUTE.getSubSubCategory(), QRCRolCategories.USAGE_DISPUTE.getQrcType());
			if (StringUtils.isValidObj(categoriesPojo)) {
				outer: for (CrmQrcSubCategoriesPojo subCategory : categoriesPojo.getCrmQrcSubCategorieses()) {
					if (StringUtils.equals(subCategory.getQrcSubCategory(),
							QRCRolCategories.USAGE_DISPUTE.getSubCategory())) {
						for (CrmQrcSubSubCategoriesPojo subSubCat : subCategory.getCrmQrcSubSubCategorieses()) {
							if (StringUtils.equals(subSubCat.getQrcSubSubCategory(),
									QRCRolCategories.USAGE_DISPUTE.getSubSubCategory())) {
								TICKET_IUD.setQrcCategoryId(categoriesPojo.getQrcCategoryId());
								TICKET_IUD.setQrcSubCategoryId(subCategory.getQrcSubCategoryId());
								TICKET_IUD.setQrcSubSubCategoryId(subSubCat.getQrcSubSubCategoryId());
								break outer;
							}
						}
					}
				}
			}
		}
		if (TICKET_IUD.getQrcCategoryId() > 0) {
			if (TICKET_IUD.getQrcCategoryId() == inTicket.getQrcCategoryId()
					&& TICKET_IUD.getQrcSubCategoryId() == inTicket.getQrcSubCategoryId()
					&& TICKET_IUD.getQrcSubSubCategoryId() == inTicket.getQrcSubSubCategoryId()) {
				crmEvent = CRMEvents.TICKET_IUD;
				if (StringUtils.equals(inCrmCustomerDetailsPojo.getBrand(), IAppConstants.BRAND_INITIA)) {
					crmEvent = CRMEvents.TICKET_IUD_INITIA;
				}
			}
		}
		return crmEvent;
	}

	private boolean sendAlertUsageDispute(CrmSrTicketsPojo inTicketPOJO, CRMEvents inCrmEvent, Date inExpiryDate,
			CrmCustomerActivityPojo inActivityLogPojo) {
		CrmCustomerDetailsPojo crmCustomerDetailsPojo = null;
		boolean sendValue = false;
		String filePath = null;
		String fromDate = null;
		String toDate = DateUtils.getCurrentDateInFormat(IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS);
		try {
			filePath = PropertyUtils.getServiceDetails(IPropertiesConstant.UNBILLED_USAGE_PATH)
					+ IAppConstants.UNBILLED;
			crmCustomerDetailsPojo = CRMServiceUtils.getDBValues(CrmCustomerDetailsPojo.class, "customerId",
					inTicketPOJO.getMappingId());
			if (StringUtils.isValidObj(crmCustomerDetailsPojo)) {
				if (StringUtils.equals(crmCustomerDetailsPojo.getServiceType(),
						CRMDisplayListConstants.PRE_PAID.getCode())) {
					fromDate = DateUtils.getFormattedDate(inExpiryDate, IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS);
				} else {
					fromDate = DateUtils.getFormattedDate(
							DateUtils.getPreviousBillingDate(crmCustomerDetailsPojo.getBillDate()),
							IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS);
				}
				if (StringUtils.isValidObj(inActivityLogPojo)) {
					inActivityLogPojo.setOldValue(fromDate);
					inActivityLogPojo.setNewValue(toDate);
				}
				CrmQrcDto crmQrcDto = new CrmQrcDto();
				crmQrcDto.setUsageFormDate(fromDate);
				crmQrcDto.setUsageToDate(toDate);
				crmQrcDto.setCustomerDetailsPojo(crmCustomerDetailsPojo);
				crmQrcDto = getCustomerPriodicUsageDetails(crmQrcDto);
				if (StringUtils.equals(crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode())) {
					String customerName = CRMUtils.prepareCustomerFullName(crmCustomerDetailsPojo.getCustFname(),
							crmCustomerDetailsPojo.getCustLname(), crmCustomerDetailsPojo.getConnectionType(),
							crmCustomerDetailsPojo.getCustGender());
					ExcelWriteUtils excelWriteUtils = new ExcelWriteUtils(customerName,
							crmCustomerDetailsPojo.getCustomerId(), filePath);
					excelWriteUtils.prepareTopRows(IAppConstants.UNBILLED, fromDate, toDate);
					if (CommonValidator.isValidCollection(crmQrcDto.getCustomerUsageDetailsPojos())) {
						for (CustomerUsageDetailsPojo usageDetailsPojo : crmQrcDto.getCustomerUsageDetailsPojos()) {
							excelWriteUtils.addUsageDetailsRow(usageDetailsPojo.getCallEndDate(),
									usageDetailsPojo.getStartTime(), usageDetailsPojo.getEndTime(),
									usageDetailsPojo.getUploadKB(), usageDetailsPojo.getDownloadKB());
						}
						excelWriteUtils.prepareBottomRows();
						excelWriteUtils.lastRowCalculate();
					}
					if (CommonValidator.isValidCollection(crmQrcDto.getCustomerUsageDetailsPojos())) {
						SortingComparator<CustomerUsageDetailsPojo> sorter = new SortingComparator<CustomerUsageDetailsPojo>(
								"endTime");
						Collections.sort(crmQrcDto.getCustomerUsageDetailsPojos(), Collections.reverseOrder(sorter));
						excelWriteUtils.prepareTopRowsForGB(IAppConstants.UNBILLED, fromDate, toDate);
						for (CustomerUsageDetailsPojo usageDetailsPojo : crmQrcDto.getCustomerUsageDetailsPojos()) {
							if (usageDetailsPojo.getUploadKB().doubleValue() > 0
									|| usageDetailsPojo.getDownloadKB().doubleValue() > 0) {
								excelWriteUtils.addUsageDetailsRow(usageDetailsPojo.getEndTime(),
										usageDetailsPojo.getUploadKB(), usageDetailsPojo.getDownloadKB());
							}
						}
						excelWriteUtils.prepareBottomRowsForGB();
						excelWriteUtils.lastRowCalculateForGB();
					}
					excelWriteUtils.createExcel();
					LOGGER.info("File created : " + excelWriteUtils.getFileName());
					if (StringUtils.isValidObj(excelWriteUtils.getCreatedExcel())) {
						List<String> attachments = new ArrayList<String>();
						String invoicePath = excelWriteUtils.getCreatedExcel().getAbsolutePath();
						attachments.add(invoicePath);
						if (!StringUtils.equals(crmCustomerDetailsPojo.getBrand(), IAppConstants.BRAND_INITIA)) {
							try {
								String path = FileUtils.getFilePath("FAQs_Usage_Billing.pdf");
								LOGGER.info("File path:" + path);
								if (StringUtils.isNotBlank(path)) {
									attachments.add(path);
								}
							} catch (Exception ex) {
								LOGGER.warn("Exception loading File : FAQs_Usage_Billing.pdf - " + ex, ex);
							}
						}
						sendValue = CRMServiceUtils.sendAlerts(inCrmEvent, CRMRequestType.QRC, inTicketPOJO.getSrId(),
								attachments);
						LOGGER.info("sendAlerts ::" + sendValue);
					}
				}
			}
		} catch (Exception ex) {
			LOGGER.error("Exception while sending Alert Usage Dispute ", ex);
		}
		return sendValue;
	}

	private CrmQrcDto getCustomerPriodicUsageDetails(CrmQrcDto inCrmQrcDto) {
		LOGGER.info("Inside CrmQrcBusinessImpl, getCustomerPriodicUsageDetails");
		MDC.put("KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID" + inCrmQrcDto.getUserId());
		if (StringUtils.isNotBlank(inCrmQrcDto.getUsageFormDate())
				&& StringUtils.isNotBlank(inCrmQrcDto.getUsageToDate()) && validateUsagePeriod(inCrmQrcDto)) {
			LOGGER.info("Form : " + inCrmQrcDto.getUsageFormDate() + " To : " + inCrmQrcDto.getUsageToDate());
			inCrmQrcDto.setCustomerUsageDetailsPojos(new ArrayList<CustomerUsageDetailsPojo>());
			inCrmQrcDto = getBillingDao().getCustUsageDetailsInGB(inCrmQrcDto);
			if (StringUtils.equals(inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode())) {
				CRMServiceCode serviceCode = getBillingDao().getCustUsageDetails(inCrmQrcDto.getUsageFormDate(),
						inCrmQrcDto.getUsageToDate(), inCrmQrcDto.getCustomerDetailsPojo().getCustomerId(),
						inCrmQrcDto.getCustomerUsageDetailsPojos());
				inCrmQrcDto.setStatusCode(serviceCode.getStatusCode());
				inCrmQrcDto.setStatusDesc(serviceCode.getStatusDesc());
				if (StringUtils.equals(inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode())) {
					LOGGER.info("Usage Details Size : " + inCrmQrcDto.getCustomerUsageDetailsPojos().size());
				}
			}
		}
		MDC.remove("KEY");
		return inCrmQrcDto;
	}

	private boolean validateUsagePeriod(CrmQrcDto inCrmQrcDto) {
		LOGGER.info("Inside CrmQrcBusinessImpl, validateUsagePeriod");
		boolean validDates = true;
		Date fromDate = null;
		Date toDate = null;
		Date currDate = null;
		if (StringUtils.contains(inCrmQrcDto.getUsageFormDate(), "/")) {
			fromDate = DateUtils.getDate(inCrmQrcDto.getUsageFormDate(), "dd/MM/yyyy").getTime();
			toDate = DateUtils.getDate(inCrmQrcDto.getUsageToDate(), "dd/MM/yyyy").getTime();
			inCrmQrcDto.setUsageFormDate(DateUtils.getFormattedDate(fromDate, IDateConstants.SDF_DD_MMM_YYYY));
			inCrmQrcDto.setUsageToDate(DateUtils.getFormattedDate(toDate, IDateConstants.SDF_DD_MMM_YYYY));
		} else {
			fromDate = DateUtils.getDate(inCrmQrcDto.getUsageFormDate(), "dd-MMM-yyyy").getTime();
			toDate = DateUtils.getDate(inCrmQrcDto.getUsageToDate(), "dd-MMM-yyyy").getTime();
		}
		LOGGER.info("fromDate :" + fromDate + ",toDate" + toDate);
		currDate = DateUtils.getCurrentDate();
		if (DateUtils.daysDiff(fromDate, currDate) < 0) {
			validDates = false;
			LOGGER.info("fromDate daysDiff");
		} else if (DateUtils.daysDiff(toDate, currDate) < 0) {
			validDates = false;
			LOGGER.info("toDate daysDiff");
		} else if (DateUtils.daysDiff(fromDate, toDate) < 0) {
			validDates = false;
			LOGGER.info("fromDate, toDate daysDiff");
		} else if (DateUtils.daysDiff(fromDate, toDate) > 90) {
			validDates = false;
			LOGGER.info("fromDate, 90 daysDiff");
		}
		return validDates;
	}

	private CrmTicketHistoryPojo getGeneratedHistory(String inServiceParam, CrmSrTicketsPojo ticket) {
		CrmTicketHistoryPojo ticketHistory = new CrmTicketHistoryPojo();
		ticketHistory.setAction(inServiceParam);
		if (StringUtils.isNotBlank(ticket.getLastModifiedBy())) {
			ticketHistory.setCreatedBy(ticket.getLastModifiedBy());
		} else {
			ticketHistory.setCreatedBy(ticket.getCreatedBy());
		}
		if (StringUtils.equals(CrmActionEnum.CLOSED.getActionCode(), inServiceParam)) {
			ticketHistory.setRemarks("Closed by System");
		} else {
			ticketHistory.setRemarks("Auto generated remarks.");
		}
		return ticketHistory;
	}

	private void createActivityLog(CrmCustomerActivityPojo activityLogPojo, CrmQrcDto inCrmQrcDto,
			CRMCustomerActivityActions inAction, String inReason) {
		if (!StringUtils.isValidObj(activityLogPojo)) {
			activityLogPojo = new CrmCustomerActivityPojo();
		}
		activityLogPojo.setAction(inAction.getActionCode());
		activityLogPojo.setReason(inReason);
		activityLogPojo.setCustomerId(inCrmQrcDto.getCrmSrTicketsPojo().getMappingId());
		activityLogPojo.setCreatedBy(inCrmQrcDto.getUserId());
		activityLogPojo.setServiceIp(CRMUtils.getServerIp());
		activityLogPojo.setClientIp(inCrmQrcDto.getClientIPAddress());
		activityLogPojo.setServerIp(inCrmQrcDto.getServerIPAddress());
		CRMServiceUtils.createActivityLog(activityLogPojo);
	}

	private boolean isBackendCategory(long inCategoryId) {
		CrmQrcCategoriesPojo qrcCatPojo = CRMServiceUtils.getDBValues(CrmQrcCategoriesPojo.class, inCategoryId);
		if (StringUtils.isValidObj(qrcCatPojo) && StringUtils.isBlank(qrcCatPojo.getFunctionalBin())) {
			return true;
		}
		return false;
	}
}
