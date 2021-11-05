package com.ics.icsoauth2server.email;

import com.ics.icsoauth2server.email.context.AbstractEmailContext;

import javax.mail.MessagingException;

public interface EmailServices {
    public void sendEmail(AbstractEmailContext context) throws MessagingException;
}
