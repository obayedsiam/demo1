package com.example.studentCrud.utils;


import com.example.studentCrud.constant.ResponseStatus;
import org.json.simple.JSONObject;


public class ResponseBuilder implements Response {
    private final ResponseType type;
    private String status = null;
    private Object data = null;
    private final Object meta = null;
    private String message = null;
    private Object errors = null;

    public ResponseBuilder(ResponseType type) {
        this.type = type;
    }

    public static ResponseBuilder error(Object errors) {
        ResponseBuilder response = new ResponseBuilder(ResponseType.ERROR);
        response.errors = errors;
        response.status = ResponseStatus.ERROR;
        return response;
    }

    public static ResponseBuilder success(Object data) {
        ResponseBuilder response = new ResponseBuilder(ResponseType.DATA);
        response.data = data;
        response.status = ResponseStatus.SUCCESS;
        return response;
    }

    public static ResponseBuilder success(Object data, String message) {
        ResponseBuilder response = new ResponseBuilder(ResponseType.DATA);
        response.data = data;
        response.message = message;
        response.status = ResponseStatus.SUCCESS;
        return response;
    }

    @Override
    public JSONObject getJson() {
        return null;
    }

    @Override
    public JSONObject customGetJson() {
        return null;
    }

    @Override
    public JSONObject customGetJsonForAll() {
        return null;
    }
}
