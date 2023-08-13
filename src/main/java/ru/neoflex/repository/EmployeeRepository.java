package ru.neoflex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}