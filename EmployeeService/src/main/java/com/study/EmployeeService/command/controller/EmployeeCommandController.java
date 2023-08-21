package com.study.EmployeeService.command.controller;

import com.study.EmployeeService.command.commands.CreateEmployeeCommand;
import com.study.EmployeeService.command.commands.DeleteEmployeeCommand;
import com.study.EmployeeService.command.commands.UpdateEmployeeCommand;
import com.study.EmployeeService.command.model.EmployeeRequestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeCommandController {

    private final CommandGateway commandGateway;
    @PostMapping("/create")
    public String createEmployee (@RequestBody EmployeeRequestModel employeeRequestModel){
        CreateEmployeeCommand createEmployeeCommand =
                CreateEmployeeCommand.builder()
                        .employeeId(UUID.randomUUID().toString())
                        .firstName(employeeRequestModel.getFirstName())
                        .lastName(employeeRequestModel.getLastName())
                        .kin(employeeRequestModel.getKin())
                        .isDiscliped(false)
                        .build();
        commandGateway.sendAndWait(createEmployeeCommand);
        return "created employee";
    }

    @PutMapping("/update/{id}")
    public String updateEmployee (@RequestBody EmployeeRequestModel employeeRequestModel, @PathVariable String id){
        UpdateEmployeeCommand updateEmployeeCommand = UpdateEmployeeCommand.builder()
                .employeeId(id)
                .firstName(employeeRequestModel.getFirstName())
                .lastName(employeeRequestModel.getLastName())
                .kin(employeeRequestModel.getKin())
                .isDiscliped(employeeRequestModel.isDiscliped())
                .build();
        commandGateway.sendAndWait(updateEmployeeCommand);
        return "Employee Updated";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee (@PathVariable String id){
        DeleteEmployeeCommand deleteEmployeeCommand = DeleteEmployeeCommand.builder()
                .employeeId(id)
                .build();
        commandGateway.sendAndWait(deleteEmployeeCommand);
        return "Employee Deleted";
    }
}
