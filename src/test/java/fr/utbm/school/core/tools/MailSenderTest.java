package fr.utbm.school.core.tools;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MailSenderTest {

    private MailSender ms = new MailSender();

    @Test
    void sendMail() {
        assertTrue(ms.sendMail("LO54.projetutbm@gmail.com", "test", "test"));
    }
}