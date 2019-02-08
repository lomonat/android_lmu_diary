package com.example.natalia.diary_lmu;


import java.util.HashMap;
import java.util.Map;

public class DayEntry {


    public String content;
    public String date;
   // private final String img;

    public DayEntry() { }

    public DayEntry(String content, String date) {
        this.content = content;
        this.date = date;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("content", content);
        result.put("date", date);
        return result;
    }

}
