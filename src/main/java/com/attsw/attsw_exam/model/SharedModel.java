package com.attsw.attsw_exam.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public class SharedModel {

    private static final long serialVersionUID = 1L;

    private Date createdDate;
    private Date lastModifiedDate;
    private Integer status;


    @Column(name = "created_date", updatable = false)
    @CreatedDate
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "last_modified_date")
    @LastModifiedDate
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public SharedModel(Integer status) {
        this.status = status;
    }

    public SharedModel() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharedModel that = (SharedModel) o;
        return Objects.equals(getCreatedDate(), that.getCreatedDate()) &&
                Objects.equals(getLastModifiedDate(), that.getLastModifiedDate()) &&
                Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCreatedDate(), getLastModifiedDate(), getStatus());
    }

    @Override
    public String toString() {
        return "SharedModel{" +
                "createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", status=" + status +
                '}';
    }
}
