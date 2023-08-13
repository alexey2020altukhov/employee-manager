package ru.neoflex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.dto.EmployeeResponseDTO;
import ru.neoflex.dto.RegistryEmployeeRequestDTO;
import ru.neoflex.dto.ResumeRequestDTO;
import ru.neoflex.dto.ResumeResponseDTO;
import ru.neoflex.entity.Department;
import ru.neoflex.entity.Employee;
import ru.neoflex.feign.ResumeFeignClient;
import ru.neoflex.mapper.EmployeeMapper;
import ru.neoflex.repository.DepartmentRepository;
import ru.neoflex.repository.EmployeeRepository;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final ResumeFeignClient resumeFeignClient;

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponseDTO registryEmployee(RegistryEmployeeRequestDTO registryEmployeeRequestDTO) {
        Department department = departmentRepository.findById(registryEmployeeRequestDTO.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException("Отдел с id " +
                        registryEmployeeRequestDTO.getDepartmentId() + " не найден"));

        Employee employee = employeeMapper.convert(registryEmployeeRequestDTO);
        employee.setDepartment(department);

        ResumeRequestDTO resumeRequestDTO = ResumeRequestDTO.builder()
                .email(employee.getEmail())
                .build();

        ResumeResponseDTO resumeResponseDTO = resumeFeignClient.getLinkResume(resumeRequestDTO).getBody();
        if (resumeResponseDTO != null) {
            employee.setLinkResume(resumeResponseDTO.getLinkResume());
        }

        return employeeMapper.convert(employeeRepository.save(employee));
    }
}