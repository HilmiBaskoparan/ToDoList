package com.hilmibaskoparan.model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoUpdateRequest {

    private String description;
    private Boolean isDone;
}
