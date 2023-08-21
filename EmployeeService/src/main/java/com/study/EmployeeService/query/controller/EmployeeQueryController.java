package com.study.EmployeeService.query.controller;

import com.study.EmployeeService.query.model.EmployeeResponseModel;
import com.study.EmployeeService.query.queries.GetAllEmployeeQuery;
import com.study.EmployeeService.query.queries.GetEmployeeByIdQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeQueryController {
    private final QueryGateway queryGateway;

    @GetMapping("/getall")
    public List<EmployeeResponseModel> getAllEmployee(){
        GetAllEmployeeQuery getAllEmployeeQuery = new GetAllEmployeeQuery();
        List<EmployeeResponseModel> res =
                queryGateway.query(getAllEmployeeQuery,
                        ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
        return res;
    }

    @GetMapping("/get/{id}")
    public EmployeeResponseModel getById(@PathVariable String id){
        GetEmployeeByIdQuery getEmployeeByIdQuery = new GetEmployeeByIdQuery();
        getEmployeeByIdQuery.setEmployeeId(id);
        EmployeeResponseModel res = queryGateway.query(getEmployeeByIdQuery,
                ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();
        return res;
    }

}
