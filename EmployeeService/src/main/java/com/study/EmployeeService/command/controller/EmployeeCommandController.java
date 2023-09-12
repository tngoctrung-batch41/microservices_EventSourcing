package com.study.EmployeeService.command.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.EmployeeService.command.commands.CreateEmployeeCommand;
import com.study.EmployeeService.command.commands.DeleteEmployeeCommand;
import com.study.EmployeeService.command.commands.UpdateEmployeeCommand;
import com.study.EmployeeService.command.model.EmployeeRequestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeCommandController {
    private final CommandGateway commandGateway;
    private final MessageChannel output;
    @Autowired
    public EmployeeCommandController(CommandGateway commandGateway, @Qualifier("ManageBook") MessageChannel output) {
        this.commandGateway = commandGateway;
        this.output = output;
    }

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

    @PostMapping("/sendmessage")
    public void sendMessage(@RequestBody String mesaqge) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(mesaqge);
            output.send(MessageBuilder.withPayload(json).build());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
