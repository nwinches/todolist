package com.nwinches.dao;

import static org.easymock.EasyMock.expect;
import static org.powermock.api.easymock.PowerMock.replayAll;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.annotation.Mock;

import com.nwinches.AbstractTest;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;

public class TwilioSmsNotifierTest extends AbstractTest {
  private static final String FROM_NUMBER = "+1234567890";
  private static final String DEFAULT_TO_NUMBER = "+0987654321";

  @Mock
  private MessageFactory messageFactory;

  private TwilioSmsNotifier smsNotifier;

  @Before
  public void setup() {
    smsNotifier = new TwilioSmsNotifier();
    smsNotifier.setMessageFactory(messageFactory);
    smsNotifier.setFromNumber(FROM_NUMBER);
    smsNotifier.setDefaultToNumber(DEFAULT_TO_NUMBER);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testSendSms_DefaultNumber() throws Exception {
    expect(messageFactory.create(EasyMock.anyObject(List.class))).andReturn(null);

    replayAll();
    smsNotifier.sendSms("body", null);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testSendSms_ExceptionSwallowed() throws Exception {
    expect(messageFactory.create(EasyMock.anyObject(List.class))).andThrow(new TwilioRestException("", -1));

    replayAll();
    smsNotifier.sendSms("body", "+1231231231");
  }
}
