package ru.stqa.pft.rest;

import org.testng.SkipException;
import org.testng.annotations.Test;

import java.io.IOException;


public class BugTests extends TestBase {

  @Test
  public void testBug() {
    int issueId = 1;
    try {
      skipIfNotFixed(issueId);
      if (! isIssueOpen(issueId)) {
        System.out.println("баг исправлен");
      } else System.out.println("баг еще не исправлен");
    } catch (SkipException | IOException e) {
      e.printStackTrace();
    }
  }
}
