package br.com.notes.infrastructure.http.spark.transformers;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object object) {
        return gson.toJson(object);
    }

}
