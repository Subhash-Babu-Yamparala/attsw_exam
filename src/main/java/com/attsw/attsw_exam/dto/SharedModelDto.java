/**
 * Created by: nuwan_r
 * Created on: 6/19/2021
 **/
package com.attsw.attsw_exam.dto;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

public class SharedModelDto {

    private Date createdDate;
    private Date lastModifiedDate;
    private Integer status;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharedModelDto that = (SharedModelDto) o;
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
        return "SharedModelDto{" +
                "createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", status=" + status +
                '}';
    }
}
