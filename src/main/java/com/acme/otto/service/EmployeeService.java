package com.acme.otto.service;

import com.acme.otto.entity.EmployeeEntity;
import com.acme.otto.entity.PermissionEntity;
import com.acme.otto.entity.RoleEntity;
import com.acme.otto.mapper.EmployeeMapper;
import com.acme.otto.model.Employee;
import com.acme.otto.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import java.security.Permission;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService implements UserDetailsService {

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

  @Override
  @Transactional // Adding Trx here to do LAZY loading
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    EmployeeEntity employeeEntity = employeeRepository.findByEmployeeCode(username)
        .orElseThrow(() -> {
          throw new UsernameNotFoundException(
              "Employee with employee code :" + username + " already exist");
        });

    Set<SimpleGrantedAuthority> authorities = employeeRepository
        .findPermissionByEmployeeId(employeeEntity.getEmployeeId())
        .stream()
        .map(permissionEntity -> new SimpleGrantedAuthority(permissionEntity.getName()))
        .collect(Collectors.toSet());

    return new User(username, employeeEntity.getPassword(), authorities);
  }
}
