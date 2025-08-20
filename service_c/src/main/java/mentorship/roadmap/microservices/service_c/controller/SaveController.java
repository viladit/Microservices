package mentorship.roadmap.microservices.service_c.controller;

import lombok.RequiredArgsConstructor;
import mentorship.roadmap.microservices.service_c.dto.MessageDTO;
import mentorship.roadmap.microservices.service_c.service.SaveService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SaveController {

    private final SaveService saveService;

    @PostMapping("/save")
    public void save(@RequestBody MessageDTO processDTO) {
        saveService.save(processDTO);
    }
}
