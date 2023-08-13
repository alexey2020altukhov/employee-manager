package ru.neoflex.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.neoflex.AbstractTest;
import ru.neoflex.dto.RegistryEmployeeRequestDTO;
import ru.neoflex.entity.Department;
import ru.neoflex.repository.DepartmentRepository;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.MediaType.APPLICATION_JSON;
import static org.mockserver.model.StringBody.exact;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.neoflex.util.DepartmentTestHelper.getDepartment;
import static ru.neoflex.util.EmployeeTestHelper.getEmployeeResponseDTO;
import static ru.neoflex.util.EmployeeTestHelper.getRegistryEmployeeRequestDTO;
import static ru.neoflex.util.ErrorResponseTestHelper.getErrorResponseDepartmentNotFound;
import static ru.neoflex.util.ResumeTestHelper.getResumeRequestDTO;
import static ru.neoflex.util.ResumeTestHelper.getResumeResponseDTO;

class EmployeeControllerIntegrationTest extends AbstractTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    @Sql(scripts = "/sql/clear_tables.sql", executionPhase = AFTER_TEST_METHOD)
    void registryEmployeeTest() throws Exception {
        Department department = getDepartment();
        department.setId(1L);
        departmentRepository.save(department);

        mockServerClient
                .when(request()
                        .withMethod("POST")
                        .withPath("/resume-manager/resume")
                        .withBody(exact(mapper.writeValueAsString(getResumeRequestDTO()))))
                .respond(response()
                        .withStatusCode(200)
                        .withContentType(APPLICATION_JSON)
                        .withBody(mapper.writeValueAsString(getResumeResponseDTO()))
                );

        mockMvc.perform(post("/employee/registry")
                        .content(mapper.writeValueAsString(getRegistryEmployeeRequestDTO()))
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(getEmployeeResponseDTO())));
    }

    @Test
    void registryEmployeeDepartmentNotFoundTest() throws Exception {
        RegistryEmployeeRequestDTO registryEmployeeRequestDTO = getRegistryEmployeeRequestDTO();
        mockMvc.perform(post("/employee/registry")
                        .content(mapper.writeValueAsString(registryEmployeeRequestDTO))
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().json(mapper.writeValueAsString(getErrorResponseDepartmentNotFound())));
    }
}