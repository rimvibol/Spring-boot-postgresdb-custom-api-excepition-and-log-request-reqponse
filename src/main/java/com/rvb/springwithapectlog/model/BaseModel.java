package com.rvb.springwithapectlog.model;

import org.hibernate.loader.plan.build.internal.LoadGraphLoadPlanBuildingStrategy;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
public class BaseModel {

    @Column ( name = "CREATED_DATE")
    private Timestamp createdDate;

    @Column (name = "MODIFIED_DATE")
    private Timestamp ModifiedDate;

    @Column ( name = "CREATED_BY")
    private String createdBy;

    @Column (name = "MODIFIED_BY")
    private String modifiedBy;

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
