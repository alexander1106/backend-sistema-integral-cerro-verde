package com.alexander.sistema_cerro_verde_backend.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.stereotype.Service;

@Service
public class SmsService {
  // Find your Account Sid and Token at twilio.com/console
  public static final String ACCOUNT_SID = "[SID]";
  public static final String AUTH_TOKEN = "[Token]";
  public static final PhoneNumber NUMBER = new PhoneNumber("+51925057443");
  public void enviarSms(String mensaje) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message.creator(NUMBER,     // destino
        "MG9793f7609e258187e1c12d0609e43132",     // origen (Twilio)
        mensaje).create();
    System.out.println(message.getSid());
  }
}

