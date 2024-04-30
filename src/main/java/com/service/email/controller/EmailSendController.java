package com.service.email.controller;


import com.service.email.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/mail")
public class EmailSendController {

    private EmailService emailService;

    public EmailSendController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendMail(@RequestParam(value = "file", required = false)MultipartFile[] file, String to, String[] cc, String subject, String body){
        return emailService.sendMail( to, cc, subject, body);
    }

    @PostMapping("/welcome-email")
    public String sendWelcomeMail(@RequestParam(value = "file", required = false)MultipartFile[] file, String to, String[] cc){
       //String[] cc = new String[]{""};
       String subject = "A Warm Welcome to LearnCentral";
       String body = "Dear " + to + " , \n\n" +

               "On behalf of our entire team, I want to extend a heartfelt thank you for choosing to embark on this learning journey with us. Your decision to join LearnCentral fills us with excitement and gratitude.\n" +
               "We have poured our hearts and minds into crafting content that we believe will not only meet but exceed your expectations. With every module, we aim to provide you with invaluable insights and skills that will empower you in your personal and professional endeavors."
               +
               "Should you have any questions, feedback, or require assistance at any point, our dedicated support team is here to assist you. Feel free to reach out to us anytime; we're here to ensure your learning experience is as smooth and enriching as possible.\n" +
               "\n" +
               "Once again, thank you for placing your trust in us. We are genuinely thrilled to have you onboard and can't wait to witness the progress and growth you'll achieve through LearnCentral.\n" +
               "\n" +
               "Here's to a transformative journey ahead!"+
        "\n\nBest Regards," +
                "\nLearnCentral Team";
        return emailService.sendMail(to, cc, subject,  body);
    }


    @PostMapping("/course-enroll-email")
    public String sendCourseEnrollMail(@RequestParam(value = "file", required = false)MultipartFile[] file, String to, String[] cc){
        //String[] cc = new String[]{""};
        String subject = "Welcome to LearnCentral";
        String body = "Dear " + to + " , \n\n" +
                "We want to express our heartfelt gratitude for choosing to purchase our course.\n" +
                "We are thrilled to have you onboard, and we're confident that you'll find immense value and satisfaction in the content we've prepared for you." +
                "If you have any questions or need assistance, please don't hesitate to reach out to our support team." +
                "Once again, thank you for trusting us. We look forward to being a part of your learning journey!" +
                "\n\nBest Regards," +
                "\nLearnCentral Team"
                ;
        return emailService.sendMail( to, cc, subject,  body);
    }


    @PostMapping("/checkout-notification-email")
    public String sendCheckoutNotificationMail (@RequestParam(value = "file", required = false)MultipartFile[] file, String to, String[] cc){
        //String[] cc = new String[]{""};
        String subject = "Thank You for Your Purchase!";
        String body = "Dear " + to + " , \n\n" +
                "We're thrilled to inform you that your purchase has been successfully processed! Thank you for choosing LearnCentral." +
                "\n\nBest Regards," +
                "\nLearnCentral Team" ;





        return emailService.sendMail( to, cc, subject,  body);
    }
}
