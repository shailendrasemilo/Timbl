package com.np.tele.crm.pojos;

// default package
// Generated Sep 9, 2014 6:05:03 PM by Hibernate Tools 3.4.0.CR1
import java.util.Date;
import java.util.Set;

/**
 * CrmQrcSubSubCategoriesPojo generated by hbm2java
 */
public class CrmQrcSubSubCategoriesPojo
    implements java.io.Serializable
{
    private long    qrcSubSubCategoryId;
    private long    qrcSubCategoryId;
    private String  qrcSubSubCategory;
    private String  qrcType;
    private Byte    resolutionType;
    private long    functionalBinId;
    private String  modificationAllowed;
    private String  createdBy;
    private Date    createdTime;
    private String  lastModifiedBy;
    private Date    lastModifiedTime;
    private String  status;
    private String  massOutageRol;
    private Integer sla;
    private String  slaUnit;
    private String  moduleType;

    public String getModuleType()
    {
        return moduleType;
    }

    public void setModuleType( String inModuleType )
    {
        moduleType = inModuleType;
    }

    // private Set<CrmQrcCategoryBinMappingPojo> crmQrcCategoryBinMappings = new HashSet<CrmQrcCategoryBinMappingPojo>( 0 );
    //    private Set                 crmSrTicketses            = new HashSet( 0 );
    public CrmQrcSubSubCategoriesPojo()
    {
    }

    //    public CrmQrcSubSubCategoriesPojo( long qrcSubSubCategoryId,
    //                                       CrmQrcSubCategoriesPojo crmQrcSubCategoriesPojo,
    //                                       String qrcSubSubCategory,
    //                                       String createdBy )
    //    {
    //        this.qrcSubSubCategoryId = qrcSubSubCategoryId;
    //        this.crmQrcSubCategoriesPojo = crmQrcSubCategoriesPojo;
    //        this.qrcSubSubCategory = qrcSubSubCategory;
    //        this.createdBy = createdBy;
    //    }
    public CrmQrcSubSubCategoriesPojo( Long qrcSubSubCategoryId,
                                       String qrcSubSubCategory,
                                       String qrcType,
                                       Byte resolutionType,
                                       Long functionalBinId,
                                       String modificationAllowed,
                                       String createdBy,
                                       Date createdTime,
                                       String lastModifiedBy,
                                       Date lastModifiedTime,
                                       String status,
                                       String massOutageRol,
                                       Set<CrmQrcCategoryBinMappingPojo> crmQrcCategoryBinMappings )
    {
        this.qrcSubSubCategoryId = qrcSubSubCategoryId;
        this.qrcSubSubCategory = qrcSubSubCategory;
        this.qrcType = qrcType;
        this.resolutionType = resolutionType;
        this.functionalBinId = functionalBinId;
        this.modificationAllowed = modificationAllowed;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
        this.status = status;
        this.massOutageRol = massOutageRol;
        //this.crmQrcCategoryBinMappings = crmQrcCategoryBinMappings;
    }

    //    public CrmQrcSubSubCategoriesPojo( long qrcSubSubCategoryId,
    //                                       CrmQrcSubCategoriesPojo crmQrcSubCategoriesPojo,
    //                                       String qrcSubSubCategory,
    //                                       String qrcType,
    //                                       Byte resolutionType,
    //                                       Long functionalBinId,
    //                                       String modificationAllowed,
    //                                       String createdBy,
    //                                       Date createdTime,
    //                                       String lastModifiedBy,
    //                                       Date lastModifiedTime,
    //                                       String status,
    //                                       Set<CrmQrcCategoryBinMappingPojo> crmQrcCategoryBinMappings,
    //                                       Set crmSrTicketses )
    //    {
    //        this.qrcSubSubCategoryId = qrcSubSubCategoryId;
    //        this.crmQrcSubCategoriesPojo = crmQrcSubCategoriesPojo;
    //        this.qrcSubSubCategory = qrcSubSubCategory;
    //        this.qrcType = qrcType;
    //        this.resolutionType = resolutionType;
    //        this.functionalBinId = functionalBinId;
    //        this.modificationAllowed = modificationAllowed;
    //        this.createdBy = createdBy;
    //        this.createdTime = createdTime;
    //        this.lastModifiedBy = lastModifiedBy;
    //        this.lastModifiedTime = lastModifiedTime;
    //        this.status = status;
    //        this.crmQrcCategoryBinMappings = crmQrcCategoryBinMappings;
    //        this.crmSrTicketses = crmSrTicketses;
    //    }
    public String getMassOutageRol()
    {
        return massOutageRol;
    }

    public void setMassOutageRol( String inMassOutageRol )
    {
        massOutageRol = inMassOutageRol;
    }

    public String getQrcSubSubCategory()
    {
        return this.qrcSubSubCategory;
    }

    public void setQrcSubSubCategory( String qrcSubSubCategory )
    {
        this.qrcSubSubCategory = qrcSubSubCategory;
    }

    public String getQrcType()
    {
        return this.qrcType;
    }

    public void setQrcType( String qrcType )
    {
        this.qrcType = qrcType;
    }

    public Byte getResolutionType()
    {
        return this.resolutionType;
    }

    public void setResolutionType( Byte resolutionType )
    {
        this.resolutionType = resolutionType;
    }

    public String getModificationAllowed()
    {
        return this.modificationAllowed;
    }

    public void setModificationAllowed( String modificationAllowed )
    {
        this.modificationAllowed = modificationAllowed;
    }

    public String getCreatedBy()
    {
        return this.createdBy;
    }

    public void setCreatedBy( String createdBy )
    {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime()
    {
        return this.createdTime;
    }

    public void setCreatedTime( Date createdTime )
    {
        this.createdTime = createdTime;
    }

    public String getLastModifiedBy()
    {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy( String lastModifiedBy )
    {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedTime()
    {
        return this.lastModifiedTime;
    }

    public void setLastModifiedTime( Date lastModifiedTime )
    {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus( String status )
    {
        this.status = status;
    }

    /*  public Set<CrmQrcCategoryBinMappingPojo> getCrmQrcCategoryBinMappings()
      {
          return crmQrcCategoryBinMappings;
      }

      public void setCrmQrcCategoryBinMappings( Set<CrmQrcCategoryBinMappingPojo> inCrmQrcCategoryBinMappings )
      {
          crmQrcCategoryBinMappings = inCrmQrcCategoryBinMappings;
      }*/
    public String getSlaUnit()
    {
        return slaUnit;
    }

    public void setSlaUnit( String inSlaUnit )
    {
        slaUnit = inSlaUnit;
    }

    public long getQrcSubSubCategoryId()
    {
        return qrcSubSubCategoryId;
    }

    public void setQrcSubSubCategoryId( long qrcSubSubCategoryId )
    {
        this.qrcSubSubCategoryId = qrcSubSubCategoryId;
    }

    public long getQrcSubCategoryId()
    {
        return qrcSubCategoryId;
    }

    public void setQrcSubCategoryId( long qrcSubCategoryId )
    {
        this.qrcSubCategoryId = qrcSubCategoryId;
    }

    public long getFunctionalBinId()
    {
        return functionalBinId;
    }

    public void setFunctionalBinId( long functionalBinId )
    {
        this.functionalBinId = functionalBinId;
    }

    public Integer getSla()
    {
        return sla;
    }

    public void setSla( Integer sla )
    {
        this.sla = sla;
    }

    //    public Set getCrmSrTicketses()
    //    {
    //        return this.crmSrTicketses;
    //    }
    //
    //    public void setCrmSrTicketses( Set crmSrTicketses )
    //    {
    //        this.crmSrTicketses = crmSrTicketses;
    //    }
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmQrcSubSubCategoriesPojo [qrcSubSubCategoryId=" ).append( qrcSubSubCategoryId )
                .append( ", qrcSubCategoryId=" ).append( qrcSubCategoryId ).append( ", qrcSubSubCategory=" )
                .append( qrcSubSubCategory ).append( ", qrcType=" ).append( qrcType ).append( ", resolutionType=" )
                .append( resolutionType ).append( ", functionalBinId=" ).append( functionalBinId )
                .append( ", modificationAllowed=" ).append( modificationAllowed ).append( ", createdBy=" )
                .append( createdBy ).append( ", createdTime=" ).append( createdTime ).append( ", status=" )
                .append( status ).append( ", massOutageRol=" ).append( massOutageRol ).append( "]" );
        return builder.toString();
    }
}
