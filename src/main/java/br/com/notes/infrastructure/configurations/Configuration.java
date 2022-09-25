package br.com.notes.infrastructure.configurations;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

public class Configuration {
    private Database database;

    public static Configuration load(){
        Yaml yaml = new Yaml();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.yml");
        return yaml.loadAs(inputStream, Configuration.class);
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "database=" + database +
                '}';
    }
}
