package ru.neoflex.util;

import ru.neoflex.dto.EmployeeResponseDTO;
import ru.neoflex.dto.RegistryEmployeeRequestDTO;

public class EmployeeTestHelper {

    public static RegistryEmployeeRequestDTO getRegistryEmployeeRequestDTO() {
        return RegistryEmployeeRequestDTO.builder()
                .firstName("Алтухов")
                .lastName("Алексей")
                .email("aaltukhov@mail.ru")
                .departmentId(1L)
                .build();
    }

    public static EmployeeResponseDTO getEmployeeResponseDTO() {
        return EmployeeResponseDTO.builder()
                .id(1L)
                .firstName("Алтухов")
                .lastName("Алексей")
                .email("aaltukhov@mail.ru")
                .linkResume("https://hh.ru/aaltukhov")
                .departmentId(1L)
                .build();
    }
}