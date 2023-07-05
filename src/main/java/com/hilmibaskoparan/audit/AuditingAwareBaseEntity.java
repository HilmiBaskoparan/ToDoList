package com.hilmibaskoparan.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@MappedSuperclass
@JsonIgnoreProperties(value = {"created_date,updated_date"},allowGetters = true) // We order JSON not to follow these
abstract public class AuditingAwareBaseEntity {

    // Who Created
    @CreatedBy
    @Column(name="created_user")
    protected String createdUser;

    // When Created
    @CreatedDate
    @Column(name="created_date")
    protected Date createdDate;

    // Who Updated
    @LastModifiedBy
    @Column(name="updated_user")
    protected String updatedUser;

    // When Updated
    @LastModifiedDate
    @Column(name="updated_date")
    protected Date updatedDate;
}