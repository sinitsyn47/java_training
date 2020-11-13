package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main (String[] args){
    String[] langs = {"Java", "C#", "Python", "PHP"};

    List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    languages.add("PHP");

    List<String> languag = Arrays.asList("Java", "C#", "Python", "PHP");

    for (String l : langs){
      System.out.println("1 Я хочу выучить " + l);
    }

    for (String l : languages){
      System.out.println("2 Я хочу выучить " + l);
    }

    for (String l : languag){
      System.out.println("3 Я хочу выучить " + l);
    }

    for (int i = 0; i < languag.size(); i++){
      System.out.println("4 Я хочу выучить " + languag.get(i));
    }

  }
}
