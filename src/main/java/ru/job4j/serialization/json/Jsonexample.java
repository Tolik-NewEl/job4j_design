package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Jsonexample {
    public static void main(String[] args) {
        JSONObject jsonContact = new JSONObject("{\"phone\":\"999-888-777\"}");

        List<String> list = new ArrayList<>();
        list.add("External");
        list.add("Internal");
        JSONArray jsonMemorys = new JSONArray(list);

        final Smartphone smartphone = new Smartphone(true, 64,
                new String[] {"external", "internal"}, new Contact("999-999-999"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("available", smartphone.isAvailable());
        jsonObject.put("memsize", smartphone.getMemsize());
        jsonObject.put("memory", jsonMemorys);
        jsonObject.put("contact", jsonContact);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(smartphone).toString());
    }
}
