package com.websystique.springmvc.controller;

import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.UserDocument;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zeroturnaround.zip.ZipUtil;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.*;
import java.util.*;


/**
 * Created by admin on 25/2/2016.
 */
@Component
public class Email {

    private static String LOC;
    private static String EMAILUSER;
    private static String EMAILPASS;

    @Value("${path.location}")
    private void setLoc(String privateLoc) {
        Email.LOC = privateLoc;
    }

    @Value("${email.username}")
    private void setUsername(String privateUser) {
        Email.EMAILUSER = privateUser;
    }

    @Value("${email.password}")
    private void setPass(String privatePass) {
        Email.EMAILPASS = privatePass;
    }

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;


    public static void generateAndSendEmail(UserDocument document, String timestamp,boolean check, boolean isEmpty) throws AddressException, MessagingException, java.text.ParseException {

        User user = document.getUser();

        // Step1
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");

        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        /*Address[] array = new Address[1];
        array[0] = new InternetAddress("Selenium@noreply.com");
        generateMailMessage.addFrom(array);*/
        try {
            generateMailMessage.setFrom(new InternetAddress("Selenium@noreply.com", "Selenium"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String emailList = user.getEmail();
        String[] recipientList = emailList.split(",");
        for (int i = 0; i < recipientList.length; i++) {
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientList[i]));
        }


        generateMailMessage.setSubject("Test case - " + document.getName());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date d = sdf.parse(timestamp);
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        String newDateString = sdf.format(d);

        //Date date = new Date();
        //DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //df.setTimeZone(TimeZone.getDefault());
        String emailBody;
        if (check) {
            emailBody = "The test case for " + document.getName() + " had passed. The run time was at "
                    + newDateString + ". " + "<br><br> This is an automated email service.";
        } else {
            emailBody = "The test case for " + document.getName() + " had failed. The run time was at "
                    + newDateString + ". " + "<br><br> This is an automated email service.";
        }


        MimeBodyPart messageBody = new MimeBodyPart();
        messageBody.setContent(emailBody, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBody);

        if (isEmpty) {

            MimeBodyPart messageBodyPart = new MimeBodyPart();


            String tempName = FilenameUtils.removeExtension(document.getName());

            if (tempName.equals(document.getName())) {
                tempName += "Result";
            }
            tempName += "-" + timestamp;
            File f = new File(LOC + document.getId() + "/" + tempName + "/");
            ZipUtil.pack(f, new File(LOC + document.getId() + "/" + tempName + ".zip"));

            DataSource source = new FileDataSource(LOC + document.getId() + "/" + tempName + ".zip");

            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(tempName + ".zip");
            multipart.addBodyPart(messageBodyPart);

        }
        generateMailMessage.setContent(multipart);
        System.out.println("Mail Session has been created successfully..");

        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect("smtp.gmail.com",
                EMAILUSER, EMAILPASS);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }

}


