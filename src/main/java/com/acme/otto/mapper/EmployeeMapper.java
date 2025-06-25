package com.acme.otto.mapper;

import com.acme.otto.entity.EmployeeEntity;
import com.acme.otto.model.Employee;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface EmployeeMapper {

  EmployeeEntity toEntity(Employee employee);

  Employee fromEntity(EmployeeEntity employeeEntity);

  List<Employee> fromEntityList(List<EmployeeEntity> employeeEntities);
}
