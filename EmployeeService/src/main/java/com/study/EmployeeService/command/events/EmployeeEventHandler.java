package com.study.EmployeeService.command.events;

import com.study.EmployeeService.command.data.Employee;
import com.study.EmployeeService.command.data.EmployeeRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeEventHandler {

    private final EmployeeRepository employeeRepository;

    @EventHandler
    public void on (EmployeeCreatedEvent event){
        Employee employee = new Employee();
        BeanUtils.copyProperties(event,employee);
        employeeRepository.save(employee);
    }

    @EventHandler
    public void on (EmployeeUpdatedEvent event){
        Employee employee = employeeRepository.findById(event.getEmployeeId()).orElseThrow(()->new NotFoundException("User Not Found"));
        BeanUtils.copyProperties(event,employee);
        employeeRepository.save(employee);
    }
    @EventHandler
   public void on (EmployeeDeletedEvent event) {
       Employee employee = employeeRepository.findById(event.getEmployeeId()).orElseThrow(()->new NotFoundException("User Not Found"));
       employeeRepository.delete(employee);
    }
}