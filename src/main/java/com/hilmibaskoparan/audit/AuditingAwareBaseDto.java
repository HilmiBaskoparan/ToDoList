package com.hilmibaskoparan.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
abstract public class AuditingAwareBaseDto implements Serializable {

    // Auditing: who created, who updated, when created, when updated

    public static final Long serialVersionUID = 1L;

    private Long id;
    @Builder.Default
    private Date systemDate=new Date(System.currentTimeMillis());

    // AUDITING
    @JsonIgnore // Stores Data to Backend
    protected String createdUser;

    @JsonIgnore
    protected Date createdDate;

    @JsonIgnore
    protected String updatedUser;

    @JsonIgnore
    protected Date updatedDate;
}