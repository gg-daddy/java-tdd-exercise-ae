package com.odde.tdd;

public class EmailNotification {

    private final MailService mailService;

    public EmailNotification() {
        mailService = new MailService();
    }

    public void welcome(String email) {
        Email mail = new Email();
        mail.setFrom("zbcjackson@odd-e.com");
        mail.setTo(email);
        mail.setTitle("Welcome");
        mail.setContent("Hello");
        mailService.send(mail);
    }
}
