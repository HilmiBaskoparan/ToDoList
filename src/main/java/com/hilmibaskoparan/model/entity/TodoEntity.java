package com.hilmibaskoparan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "todo_list")
public class TodoEntity extends BaseEntity implements Serializable {

    public static final Long serialVersionUID = 1L;

    @Column(name = "description", columnDefinition = "varchar(255) default 'you didn't enter description'")
    private String description;

    @Column(name = "is_done")
    private Boolean isDone;

    /*@Column(name = "username")
    private String userName;*/
}