package br.com.notes.infrastructure.database.mysql;

import br.com.notes.infrastructure.configurations.Configuration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class MySQLConnectionPool {

    private final Configuration configuration;

    public MySQLConnectionPool(Configuration configuration) {
        this.configuration = configuration;
    }

    public DataSource getDataSource() {
        HikariConfig config = new HikariConfig();

        String jdbcURL = String.format("jdbc:mysql://%s:%s/%s",
                configuration.getDatabase().getHost(),
                configuration.getDatabase().getPort(),
                configuration.getDatabase().getDatabase());

        config.setJdbcUrl(jdbcURL);
        config.setUsername(configuration.getDatabase().getUsername());
        config.setPassword(configuration.getDatabase().getPassword());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(config);
    }
}
