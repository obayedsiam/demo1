package com.example.studentCrud.utils;

import org.json.simple.JSONObject;

public interface Response {
    JSONObject getJson();

    JSONObject customGetJson();

    JSONObject customGetJsonForAll();
}
