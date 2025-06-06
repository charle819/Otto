package com.acme.otto.repository;

import com.acme.otto.entity.EmployeeEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

  Optional<EmployeeEntity> findByEmployeeCode(String employeeCode);
}
