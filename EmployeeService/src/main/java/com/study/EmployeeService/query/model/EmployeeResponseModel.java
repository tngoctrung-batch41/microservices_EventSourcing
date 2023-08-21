package com.study.EmployeeService.query.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponseModel {
    private String firstName;
    private String lastName;
    private String kin;
    private boolean isDiscliped;
}
