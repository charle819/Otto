package com.acme.otto.repository;

import com.acme.otto.entity.EmployeeEntity;
import com.acme.otto.entity.PermissionEntity;
import java.security.Permission;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> ,
    JpaSpecificationExecutor<EmployeeEntity> {

  Optional<EmployeeEntity> findByEmployeeCode(String employeeCode);

  @Query("SELECT DISTINCT p FROM EmployeeEntity e"
      + " JOIN e.roleEntities r"
      + " JOIN r.permissionEntities p "
      + " WHERE e.employeeId = :employeeId ")
  List<PermissionEntity> findPermissionByEmployeeId(@Param("employeeId") Long employeeId);
}
