package com.nwinches.dao;

public interface SmsNotifier {
  public void sendSms(String text, String toNumber);
}
