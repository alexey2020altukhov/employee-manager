package ru.neoflex.service;

import ru.neoflex.dto.EmployeeResponseDTO;
import ru.neoflex.dto.RegistryEmployeeRequestDTO;

public interface EmployeeService {

    EmployeeResponseDTO registryEmployee(RegistryEmployeeRequestDTO registryEmployeeRequestDTO);
}