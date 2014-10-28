package com.nwinches.dao;

import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;

@Setter
public class TwilioSmsNotifier implements SmsNotifier {
  private MessageFactory messageFactory;
  private String fromNumber;
  private String defaultToNumber;
  
  public void sendSms(String text, String toNumber) {
    List<NameValuePair> params = Lists.newArrayList();
    
    params.add(new BasicNameValuePair("To", StringUtils.isEmpty(toNumber) ? defaultToNumber : toNumber));
    params.add(new BasicNameValuePair("From", fromNumber));
    params.add(new BasicNameValuePair("Body", text));
    
    try {
      messageFactory.create(params);
    } catch (TwilioRestException e) {
      System.out.println("Message not sent.  Giving up.");
      e.printStackTrace();
    }
  }
}
