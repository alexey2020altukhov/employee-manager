package ru.neoflex.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.neoflex.dto.ResumeRequestDTO;
import ru.neoflex.dto.ResumeResponseDTO;

@FeignClient(url = "${feign.resume-manager.url}", name = "feign-resume-manager-client")
public interface ResumeFeignClient {

    @PostMapping("/resume")
    ResponseEntity<ResumeResponseDTO> getLinkResume(@RequestBody ResumeRequestDTO resumeRequestDTO);
}