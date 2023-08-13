package ru.neoflex.util;

import ru.neoflex.controller.rest.ErrorResponse;

public class ErrorResponseTestHelper {

    public static ErrorResponse getErrorResponseDepartmentNotFound() {
        return new ErrorResponse("Отдел с id 1 не найден");
    }
}