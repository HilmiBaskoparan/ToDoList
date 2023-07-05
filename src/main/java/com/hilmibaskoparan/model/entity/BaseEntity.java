package com.hilmibaskoparan.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hilmibaskoparan.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = {"created_date,updated_date"},allowGetters = true) // // We order JSON not to follow these
@EntityListeners(AuditingEntityListener.class) // AUDITING
@MappedSuperclass
public class BaseEntity extends AuditingAwareBaseEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)   // for H2DB
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdDate;
}
