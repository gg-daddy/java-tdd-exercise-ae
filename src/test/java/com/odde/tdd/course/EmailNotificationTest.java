package com.odde.tdd.course;

import com.odde.tdd.Email;
import com.odde.tdd.EmailNotification;
import com.odde.tdd.MailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailNotificationTest {

  private Email receivedMail;

  @Test
  public void testEmail() {
    // Arrange
    String expectedRecipient = "scott.chen@aexpec.com";
    EmailNotification notification =
        new EmailNotification(
            new MailService() {
              @Override
              public void send(Email mail) {
                receiveEmail(mail);
              }
            });

    // Act
    notification.welcome(expectedRecipient);

    // Assert
    Assertions.assertEquals(expectedRecipient, receivedMail.getTo());
    Assertions.assertEquals("zbcjackson@odd-e.com", receivedMail.getFrom());
    Assertions.assertEquals("Welcome", receivedMail.getTitle());
    Assertions.assertEquals("Hello", receivedMail.getContent());
  }

  private void receiveEmail(Email mail) {
    this.receivedMail = mail;
  }
}
