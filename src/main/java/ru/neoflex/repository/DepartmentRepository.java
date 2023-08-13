package ru.neoflex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}