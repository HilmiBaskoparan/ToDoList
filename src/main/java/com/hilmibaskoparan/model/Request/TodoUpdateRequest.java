package com.hilmibaskoparan.model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoUpdateRequest implements Serializable {

    public static final Long serialVersionUID = 1L;

    private String description;
    private Boolean isDone;
}
