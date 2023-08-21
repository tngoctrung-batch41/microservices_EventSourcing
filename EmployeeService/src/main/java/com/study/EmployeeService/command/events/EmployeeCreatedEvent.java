package com.study.EmployeeService.command.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreatedEvent {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private boolean isDiscliped;
}
