package mentorship.roadmap.microservices.service_b.service;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import mentorship.roadmap.microservices.service_b.dto.MessageDTO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProcessService {

    private final StringRedisTemplate redis;

    private final RestTemplate restTemplate;


    public void process(MessageDTO dto) {
        if (dto.getType().equalsIgnoreCase("important")) {
            String key = "msg:" + dto.getMessageId();
            String value = dto.getContent();
            redis.opsForValue().set(key, value, Duration.ofMinutes(5));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MessageDTO> request = new HttpEntity<>(dto, headers);

        restTemplate.postForEntity("http://service-c:8080/api/save", request, String.class);
    }
}
