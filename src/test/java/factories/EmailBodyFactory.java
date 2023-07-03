package factories;

import com.github.javafaker.Faker;
import dto.EmailBody;

public class EmailBodyFactory {
    static Faker faker = new Faker();

//    public static EmailBody getEmailBody(String name, String email, String subject, String messageText, String uploadingFile) {
    public static EmailBody getEmailBody(String pathToUploadingFile) {
        return EmailBody.builder()
                .name(faker.name().name())
                .email(faker.internet().emailAddress())
                .subject(faker.hitchhikersGuideToTheGalaxy().planet())
                .textMessage(faker.harryPotter().location())
                .uploadingFile(pathToUploadingFile)
                .build();
    }
}
