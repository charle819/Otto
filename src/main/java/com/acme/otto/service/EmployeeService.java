package com.acme.otto.service;

import com.acme.otto.entity.EmployeeEntity;
import com.acme.otto.mapper.EmployeeMapper;
import com.acme.otto.model.Employee;
import com.acme.otto.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper employeeMapper;

  @Transactional
  public Employee create(Employee employee) {

    employeeRepository.findByEmployeeCode(employee.getEmployeeCode())
        .ifPresent(e -> {
          throw new RuntimeException(
              "Employee with employee code :" + e.getEmployeeCode() + " already exist");
        });

    EmployeeEntity employeeEntity = employeeMapper.toEntity(employee);
    employeeEntity = employeeRepository.saveAndFlush(employeeEntity);
    return employeeMapper.fromEntity(employeeEntity);
  }

  public Employee getById(Long employeeId) {

    EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
        .orElseThrow(() -> new NoSuchElementException("No Employee found with Id : " + employeeId));
    return employeeMapper.fromEntity(employeeEntity);
  }

}
