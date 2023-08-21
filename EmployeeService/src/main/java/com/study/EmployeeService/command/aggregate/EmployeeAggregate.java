package com.study.EmployeeService.command.aggregate;

import com.study.EmployeeService.command.commands.CreateEmployeeCommand;
import com.study.EmployeeService.command.commands.DeleteEmployeeCommand;
import com.study.EmployeeService.command.commands.UpdateEmployeeCommand;
import com.study.EmployeeService.command.events.EmployeeCreatedEvent;
import com.study.EmployeeService.command.events.EmployeeDeletedEvent;
import com.study.EmployeeService.command.events.EmployeeUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class EmployeeAggregate {
    @AggregateIdentifier
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private boolean isDiscliped;

    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand createEmployeeCommand) {
        EmployeeCreatedEvent employeeCreatedEvent = new EmployeeCreatedEvent();
        BeanUtils.copyProperties(createEmployeeCommand,employeeCreatedEvent);
        AggregateLifecycle.apply(employeeCreatedEvent);

    }
    @EventSourcingHandler
    public void on (EmployeeCreatedEvent employeeCreatedEvent){
        this.employeeId = employeeCreatedEvent.getEmployeeId();
        this.firstName= employeeCreatedEvent.getFirstName();
        this.lastName= employeeCreatedEvent.getLastName();
        this.kin= employeeCreatedEvent.getKin();
        this.isDiscliped= employeeCreatedEvent.isDiscliped();
    }

    @CommandHandler
    public void handle(UpdateEmployeeCommand updateEmployeeCommand){
        EmployeeUpdatedEvent employeeUpdatedEvent = new EmployeeUpdatedEvent();
        BeanUtils.copyProperties(updateEmployeeCommand,employeeUpdatedEvent);
        AggregateLifecycle.apply(employeeUpdatedEvent);
    }

    @EventSourcingHandler
    public void on (EmployeeUpdatedEvent employeeUpdatedEvent){
        this.employeeId = employeeUpdatedEvent.getEmployeeId();
        this.firstName= employeeUpdatedEvent.getFirstName();
        this.lastName= employeeUpdatedEvent.getLastName();
        this.kin= employeeUpdatedEvent.getKin();
        this.isDiscliped= employeeUpdatedEvent.isDiscliped();
    }

    @CommandHandler
    public void handle(DeleteEmployeeCommand deleteEmployeeCommand){
        EmployeeDeletedEvent employeeDeletedEvent = new EmployeeDeletedEvent();
        BeanUtils.copyProperties(deleteEmployeeCommand,employeeDeletedEvent);
        AggregateLifecycle.apply(employeeDeletedEvent);
    }

    @EventSourcingHandler
    public void on (EmployeeDeletedEvent employeeDeletedEvent){
        this.employeeId = employeeDeletedEvent.getEmployeeId();
    }

    public EmployeeAggregate() {
    }
}
