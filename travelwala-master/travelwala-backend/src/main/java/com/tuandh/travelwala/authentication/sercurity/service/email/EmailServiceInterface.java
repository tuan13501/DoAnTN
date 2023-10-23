package com.tuandh.travelwala.authentication.sercurity.service.email;

import javax.mail.MessagingException;

public interface EmailServiceInterface {
    public String sendHtmlEmail(String link,String toMail) throws MessagingException;
}
