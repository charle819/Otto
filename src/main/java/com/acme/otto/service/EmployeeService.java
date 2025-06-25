package com.acme.otto.service;

import com.acme.otto.entity.EmployeeEntity;
import com.acme.otto.entity.PermissionEntity;
import com.acme.otto.entity.RoleEntity;
import com.acme.otto.mapper.EmployeeMapper;
import com.acme.otto.model.Employee;
import com.acme.otto.model.PaginatedEmployeeResponse;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties.Server.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
              "Employee with employee code :" + username + " does not exist");
        });

    Set<SimpleGrantedAuthority> authorities = employeeRepository
        .findPermissionByEmployeeId(employeeEntity.getEmployeeId())
        .stream()
        .map(permissionEntity -> new SimpleGrantedAuthority(permissionEntity.getName()))
        .collect(Collectors.toSet());

    return new User(username, employeeEntity.getPassword(), authorities);
  }

  @Transactional
  public void delete(Long employeeId) {

    var employee = employeeRepository.findById(employeeId).orElseThrow(() -> {
      throw new UsernameNotFoundException(
          "Employee with employee Id :" + employeeId + " dos not exist");
    });

    employee.setIsActive(Boolean.FALSE);
    employeeRepository.save(employee);
  }

  public PaginatedEmployeeResponse search(Integer offset, Integer limit,
      String baseLocation, String name, String email, Boolean isActive) {

    Specification<EmployeeEntity> spec = hasBaseLocation(baseLocation)
        .and(hasNameContaining(name))
        .and(hasEmail(email))
        .and(isActive(isActive));
    Pageable pageable = PageRequest.of(offset / limit, limit); // convert offset to page number
    Page<EmployeeEntity> page = employeeRepository.findAll(spec, pageable);
    return PaginatedEmployeeResponse.builder()
        .count(page.getNumberOfElements())
        .total((int) page.getTotalElements())
        .offset(offset)
        .limit(limit)
        .data(employeeMapper.fromEntityList(page.getContent()))
        .build();
  }

  private Specification<EmployeeEntity> hasBaseLocation(String baseLocation) {
    return (root, query, criteriaBuilder)
        -> StringUtils.isNoneEmpty(baseLocation) ?
        criteriaBuilder.equal(criteriaBuilder.upper(root.get("baseLocation")),
            baseLocation.toUpperCase())
        : null;
  }

  private Specification<EmployeeEntity> hasNameContaining(String name) {
    return (root, query, criteriaBuilder)
        -> StringUtils.isNoneEmpty(name) ?
        criteriaBuilder.like(criteriaBuilder.upper(root.get("fullName")),
            "%" + name.toUpperCase() + "%")
        : null;
  }

  private Specification<EmployeeEntity> hasEmail(String email) {
    return (root, query, criteriaBuilder)
        -> StringUtils.isNoneEmpty(email) ?
        criteriaBuilder.equal(root.get("email"), email)
        : null;
  }

  private Specification<EmployeeEntity> isActive(Boolean isActive) {
    return (root, query, criteriaBuilder)
        -> isActive != null ?
        criteriaBuilder.equal(root.get("isActive"), isActive)
        : null;
  }

}
