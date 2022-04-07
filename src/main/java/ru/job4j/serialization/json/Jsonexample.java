package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Jsonexample {
    public static void main(String[] args) {
        final Smartphone smartphone = new Smartphone(true, 64,
                new String[] {"external", "internal"}, new Contact("999-999-999"));

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(smartphone));

        final String smartphoneJson =
                "{"
                        + "\"available\":false,"
                        + "\"memsize\":128,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"999-888-888\""
                        + "},"
                        + "\"memory\":"
                        + "[\"ext\",\"int\"]"
                        + "}";
        final Smartphone smartphoneMod = gson.fromJson(smartphoneJson, Smartphone.class);
        System.out.println(smartphoneMod);
    }
}
