package ru.stqa.pft.rest;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import org.testng.annotations.BeforeSuite;

import java.io.IOException;

import java.util.Set;


public class TestBase {


  @BeforeSuite

  public Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }


  public boolean isIssueOpen(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/" + issueId + ".json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> issue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    Issue newIssue = issue.iterator().next();

    if (newIssue.getState_name().equals("Resolved") || newIssue.getState_name().equals("Closed")) {
      return false;
    }
    return true;
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }


}
