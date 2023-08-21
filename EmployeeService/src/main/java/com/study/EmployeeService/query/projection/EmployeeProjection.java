package com.study.EmployeeService.query.projection;

import com.study.EmployeeService.command.data.Employee;
import com.study.EmployeeService.command.data.EmployeeRepository;
import com.study.EmployeeService.query.model.EmployeeResponseModel;
import com.study.EmployeeService.query.queries.GetAllEmployeeQuery;
import com.study.EmployeeService.query.queries.GetEmployeeByIdQuery;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeProjection {
    private final EmployeeRepository employeeRepository;

    @QueryHandler
    public List<EmployeeResponseModel> handle (GetAllEmployeeQuery getAllEmployeeQuery){
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponseModel> employeeResponseModels =
                employees.stream()
                        .map(employee -> EmployeeResponseModel.builder()
                                .firstName(employee.getFirstName())
                                .lastName(employee.getLastName())
                                .kin(employee.getKin())
                                .isDiscliped(employee.isDiscliped())
                                .build())
                        .collect(Collectors.toList());
        return employeeResponseModels;
    }

    @QueryHandler
    public EmployeeResponseModel handle (GetEmployeeByIdQuery getEmployeeByIdQuery){
        Employee employee = employeeRepository.findById(getEmployeeByIdQuery.getEmployeeId()).orElseThrow(()->new NotFoundException("Not Found"));
        EmployeeResponseModel employeeRes = EmployeeResponseModel.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .kin(employee.getKin())
                .isDiscliped(employee.isDiscliped())
                .build();
        return employeeRes;
    }
}
