package com.acme.otto.mapper;

import com.acme.otto.entity.EmployeeEntity;
import com.acme.otto.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface EmployeeMapper {

  EmployeeEntity toEntity(Employee employee);

  Employee fromEntity(EmployeeEntity employeeEntity);

}
