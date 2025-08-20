package mentorship.roadmap.microservices.service_c.dto;

import lombok.Data;

@Data
public class MessageDTO {
    private String messageId;
    private String type;
    private String content;
}
