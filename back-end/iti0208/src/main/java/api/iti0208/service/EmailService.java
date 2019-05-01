package api.iti0208.service;

import api.iti0208.data.entity.AppUser;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSenderImpl mailSender;

    public EmailService(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }

    public void sendResetTokenEmail(String frontEndAddress, String token, AppUser user) {
        String url = frontEndAddress + "#/resetPassword?username=" + user.getUsername() +
                "&token=" + token;
        String subject = "Reset password";
        String message = "Here is a link to reset your password. This link expires in 2 hours!";
        SimpleMailMessage mail = constructEmail(subject, message + " \r\n" + url, user);
        sendEmail(mail);
    }

    private SimpleMailMessage constructEmail(String subject, String body, AppUser user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom("teadust00d@gmail.com");
        return email;
    }

}
