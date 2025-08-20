package mentorship.roadmap.microservices.service_b.dto;

import lombok.Data;

@Data
public class MessageDTO {
    private String messageId;
    private String type;
    private String content;
}
