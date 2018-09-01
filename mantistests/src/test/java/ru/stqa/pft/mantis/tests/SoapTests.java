package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase {
  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    //MantisConnectPortType mc = new MantisConnectLocator()
    //        .getMantisConnectPort(new URL("http://localhost/mantisbt-2.16.0/api/soap/mantisconnect.php"));
    //ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project:projects) {
      System.out.println(project.getName());
    }

  }
  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("Test Issue").withDescription("Test issue description")
            .withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    Assert.assertEquals(issue.getSummary(),created.getSummary());
  }

  @Test
  public void testCheckIssue() throws RemoteException, ServiceException, MalformedURLException {
    //System.out.println(isIssueOpen(1));
    skipIfNotFixed(1);
    System.out.println("Test is running");
  }
}
