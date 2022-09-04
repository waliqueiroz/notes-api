package br.com.notes.infrastructure.http.spark.controllers;

import spark.Request;
import spark.Response;

public class UserController {
    public String create(Request request, Response response) {
        return "Criou";
    }
}
