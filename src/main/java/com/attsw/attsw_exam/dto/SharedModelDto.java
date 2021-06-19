/**
 * Created by: nuwan_r
 * Created on: 6/19/2021
 **/
package com.attsw.attsw_exam.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SharedModelDto {

    private Date createdDate;
    private Date lastModifiedDate;
    private Integer status;

}
