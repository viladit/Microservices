package mentorship.roadmap.microservices.service_a.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("messages")
@Data
public class Message {
    @Id
    private String id;
    private String messageId;
    private String type;
    private String content;
}
