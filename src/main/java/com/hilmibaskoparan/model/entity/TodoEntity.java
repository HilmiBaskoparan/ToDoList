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

    @Column(name = "description", columnDefinition = "varchar(255) default 'you didn't enter description'")
    private String description;

    @Column(name = "is_done")
    private Boolean isDone;

    /*@Column(name = "username")
    private String userName;*/
}