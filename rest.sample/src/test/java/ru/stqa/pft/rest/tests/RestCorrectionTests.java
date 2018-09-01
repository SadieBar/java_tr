package ru.stqa.pft.rest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RestCorrectionTests extends TestBase {

  @Test
  public void testCorrection() {
    skipIfNotFixed(2);
    System.out.println("Test was started");
  }
}
