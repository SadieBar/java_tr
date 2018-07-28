package org.ruqa;

import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main (String []args) {
    String[] test = new String[4];
    String[] lang = {"Java", "C#"};
    List<String> langs = Arrays.asList("Java", "C#", "Python");

    for (String l : langs) {
      System.out.println(l);
    }
  }
}
