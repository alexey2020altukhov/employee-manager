package ru.neoflex.util;

import ru.neoflex.entity.Department;

public class DepartmentTestHelper {

    public static Department getDepartment() {
        return Department.builder()
                .name("Отдел эксплуатации")
                .build();
    }
}
