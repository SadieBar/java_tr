package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.Issue;
import ru.stqa.pft.rest.appmanager.ApplicationManager;

import java.io.IOException;
import java.util.Set;


public class TestBase {
  protected static final ApplicationManager app = new ApplicationManager();

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun=true)
  public void tearDown() throws IOException {
    app.stop();
  }
  //@BeforeMethod
  //public void init() {
  //  RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490","");
  //}
  public boolean isIssueOpen(int issueId) {
    boolean isIssueOpen = true;
    RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490","");
    String json = RestAssured.get("http://bugify.stqa.ru/api/issues/"+issueId+".json?limit=1000").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    JsonElement state_name = issues.getAsJsonArray().get(0).getAsJsonObject().get("state_name");

    String status = state_name.getAsString();
    if (status.equals("Resolved")||status.equals("Closed")) isIssueOpen = false;
    // new Gson().fromJson(issueStatus, new TypeToken<Issue>(){}.getType());

    return isIssueOpen;
  }
  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
