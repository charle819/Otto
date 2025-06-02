package com.acme.otto.mapper;

import com.acme.otto.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface EmployeeMapper {

  com.acme.otto.entity.Employee toEntity(Employee employee);

  Employee fromEntity(com.acme.otto.entity.Employee employee);

}
