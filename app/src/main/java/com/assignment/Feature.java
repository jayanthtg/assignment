package com.assignment;

import org.json.JSONObject;

public class Feature {
    private String name, value;

    public Feature(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static Feature from(String s) {
        try {
            JSONObject o = new JSONObject(s);
            String name = o.optString("name", "");
            String value = o.optString("description", "");
            if (!(name.isEmpty() && value.isEmpty())) {
                return new Feature(name, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}
