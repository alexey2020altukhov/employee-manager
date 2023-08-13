package ru.neoflex.util;

import ru.neoflex.dto.ResumeRequestDTO;
import ru.neoflex.dto.ResumeResponseDTO;

public class ResumeTestHelper {

    public static ResumeRequestDTO getResumeRequestDTO() {
        return ResumeRequestDTO.builder()
                .email("aaltukhov@mail.ru")
                .build();
    }

    public static ResumeResponseDTO getResumeResponseDTO() {
        return ResumeResponseDTO.builder()
                .linkResume("https://hh.ru/aaltukhov")
                .build();
    }
}
