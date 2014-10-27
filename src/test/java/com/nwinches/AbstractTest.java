package com.nwinches;

import static org.powermock.api.easymock.PowerMock.verifyAll;

import org.junit.After;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class AbstractTest {
  
  @After
  public void verify() {
    verifyAll();
  }
}
