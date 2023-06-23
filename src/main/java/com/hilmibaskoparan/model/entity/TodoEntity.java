package com.hilmibaskoparan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "todo_list")
public class TodoEntity extends BaseEntity {

    /*@Column(name = "username")
    private String userName;*/

    @Column(name = "description", columnDefinition = "varchar(255) default 'content girmediniz'")
    private String description;

    @Column(name = "status")
    private Boolean isDone;
}