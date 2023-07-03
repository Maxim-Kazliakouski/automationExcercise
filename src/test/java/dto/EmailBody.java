package dto;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
public class EmailBody {
    String name;
    String email;
    String subject;
    String textMessage;
    String uploadingFile;
}
