package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTest {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("4ea21dff8769a56969bd704fcd75ff5760ef825e");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("sinitsyn47", "java_training")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
