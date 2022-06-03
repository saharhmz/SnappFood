package controller;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailController {
    private final  String FROM= "zahramehdipoor13@gmail.com";

    public SendEmailController(String code , String sendTo , Label errorLBL){
        // ba daryaft email girande va ferestande code random sakhte shode ra ersal mikonad
        String to = sendTo;

        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // password va username ferestande ra daryaft mikonad
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("zahramehdipoor13@gmail.com", "m.mehdipoor/");

            }

        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(FROM));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setText(code);

            errorLBL.setTextFill(Color.BLACK);

            Transport.send(message);
            errorLBL.setText("Check your email");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
