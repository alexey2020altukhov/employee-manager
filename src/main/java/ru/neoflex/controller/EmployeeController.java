package ru.neoflex.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.dto.EmployeeResponseDTO;
import ru.neoflex.dto.RegistryEmployeeRequestDTO;
import ru.neoflex.resource.EmployeeApi;
import ru.neoflex.service.EmployeeService;

@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeApi {

    private final EmployeeService employeeService;

    @Override
    public ResponseEntity<EmployeeResponseDTO> registryEmployee(RegistryEmployeeRequestDTO registryEmployeeRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.registryEmployee(registryEmployeeRequestDTO);
        return ResponseEntity.ok(employeeResponseDTO);
    }
}