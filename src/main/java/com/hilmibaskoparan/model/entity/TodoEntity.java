package com.hilmibaskoparan.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
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

    @Column(name = "username")
    private String userName;

    @Size(min = 5, message = "Enter at least 5 Characters...")
    @Column(name = "description", columnDefinition = "varchar(255) default 'You didn't enter a description'")
    private String description;
}
