package ru.neoflex.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.neoflex.dto.EmployeeResponseDTO;
import ru.neoflex.dto.RegistryEmployeeRequestDTO;
import ru.neoflex.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee convert(RegistryEmployeeRequestDTO registryEmployeeRequestDTO);

    @Mapping(source = "department.id", target = "departmentId")
    EmployeeResponseDTO convert(Employee employee);
}