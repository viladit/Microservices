package mentorship.roadmap.microservices.service_a.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mentorship.roadmap.microservices.service_a.dto.MessageDTO;
import mentorship.roadmap.microservices.service_a.repository.MessageRepository;
import org.springframework.http.HttpHeaders;
import lombok.RequiredArgsConstructor;
import mentorship.roadmap.microservices.service_a.model.Message;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final MessageRepository repository;
    private final ObjectMapper mapper;
    private final RestTemplate restTemplate;

    @KafkaListener(topics = "in", groupId = "service-a")
    public void consume(String messsage) throws JsonProcessingException {
        var node = mapper.readTree(messsage);

        Message doc = new Message();
        doc.setMessageId(node.path("id").asText(null));
        doc.setType(node.path("type").asText(null));
        doc.setContent(messsage);

        MessageDTO dto = new MessageDTO();
        dto.setContent(messsage);
        dto.setType(doc.getType());
        dto.setMessageId(doc.getMessageId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MessageDTO> request = new HttpEntity<>(dto, headers);

        repository.save(doc);

        restTemplate.postForEntity("http://service-b:8080/api/process", request, String.class);
    }
}
