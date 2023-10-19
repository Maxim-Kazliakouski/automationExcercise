package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String text = "Max Kazliakosuki is QA Automation Engineer";
        Pattern pattern = Pattern.compile("i.++n");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(text.substring(matcher.start(), matcher.end()));
        }
    }
}