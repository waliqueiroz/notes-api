package br.com.notes.infrastructure.http.spark.transformers;

import br.com.notes.infrastructure.converter.gson.GsonConverter;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {
    @Override
    public String render(Object object) {
        return GsonConverter.toJson(object);
    }

}
