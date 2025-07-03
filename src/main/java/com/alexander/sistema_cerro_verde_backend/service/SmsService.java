package com.alexander.sistema_cerro_verde_backend.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.stereotype.Service;

@Service
public class SmsService {
  // Find your Account Sid and Token at twilio.com/console
  public static final String ACCOUNT_SID = "ACb3822a105764f288d04edb8f9befa7a3";
  public static final String AUTH_TOKEN = "08fd7d3d9c689eb6a93ec4743202e02a";
  public static final PhoneNumber NUMBER = new PhoneNumber("+51925057443");
  public void enviarSms(String mensaje) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message.creator(NUMBER,     // destino
        "MG9793f7609e258187e1c12d0609e43132",     // origen (Twilio)
        mensaje).create();
    System.out.println(message.getSid());
  }
}

