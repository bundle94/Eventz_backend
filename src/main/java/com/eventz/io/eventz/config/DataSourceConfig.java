package com.eventz.io.eventz.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by Michael.Akobundu on 4/5/2019.
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource dataSource(@Value("${datasource.driverClassName}") String datasourceDriverClassName, @Value("${datasource.url}") String datasourceUrl, @Value("${datasource.username}") String datasourceUsername, @Value("${datasource.password}") String password, @Value("${datasource.max.connection.pool.size}") int maxPoolSize) {
        final HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName(datasourceDriverClassName);
        ds.setJdbcUrl(datasourceUrl);
        ds.setUsername(datasourceUsername);
        ds.setPassword(password);
        ds.setMaximumPoolSize(maxPoolSize);

        return ds;
    }

    @Bean
    public DataSource readOnlyDataSource(@Value("${datasource.readonly.driverClassName}") String datasourceDriverClassName, @Value("${datasource.readonly.url}") String datasourceUrl, @Value("${datasource.readonly.username}") String datasourceUsername, @Value("${datasource.readonly.password}") String password, @Value("${datasource.readonly.max.connection.pool.size}") int maxPoolSize) {
        final HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName(datasourceDriverClassName);
        ds.setJdbcUrl(datasourceUrl);
        ds.setUsername(datasourceUsername);
        ds.setPassword(password);
        ds.setMaximumPoolSize(maxPoolSize);

        return ds;
    }
}
