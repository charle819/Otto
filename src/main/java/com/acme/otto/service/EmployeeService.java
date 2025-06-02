package com.acme.otto.service;

import com.acme.otto.mapper.EmployeeMapper;
import com.acme.otto.model.Employee;
import com.acme.otto.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  private EmployeeRepository employeeRepository;
  private EmployeeMapper employeeMapper;

  public Employee create(Employee employee) {

    return null;
  }

  public Employee getById(Long employeeId) {

    return null;
  }

}
