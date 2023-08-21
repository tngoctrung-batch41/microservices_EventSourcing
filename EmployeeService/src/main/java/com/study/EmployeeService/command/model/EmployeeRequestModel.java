package com.study.EmployeeService.command.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeRequestModel {
    private String firstName;
    private String lastName;
    private String kin;
    private boolean isDiscliped;
}
