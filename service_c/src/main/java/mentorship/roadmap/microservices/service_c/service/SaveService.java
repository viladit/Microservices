package mentorship.roadmap.microservices.service_c.service;

import lombok.RequiredArgsConstructor;
import mentorship.roadmap.microservices.service_c.dto.MessageDTO;
import mentorship.roadmap.microservices.service_c.entity.MessageEntity;
import mentorship.roadmap.microservices.service_c.repository.MessageRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaveService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static String TOPIC = "out";
    private final MessageRepository messageRepository;

    @Transactional
    public void save(MessageDTO messageDTO) {
        MessageEntity message = new MessageEntity();
        message.setMessageId(messageDTO.getMessageId());
        message.setContent(messageDTO.getContent());
        message.setType(messageDTO.getType());
        messageRepository.save(message);
        kafkaTemplate.send(TOPIC, messageDTO.toString());
    }
}
