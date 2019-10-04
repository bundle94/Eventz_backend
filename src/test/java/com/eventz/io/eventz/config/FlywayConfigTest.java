package com.eventz.io.eventz.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by Michael.Akobundu on 4/5/2019.
 */
@Configuration
public class FlywayConfigTest {

    @Bean
    public Flyway flyway(@Qualifier(value = "dataSource") DataSource dataSource){
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setBaselineOnMigrate(true);

        flyway.setIgnoreFutureMigrations(true);
        flyway.setIgnoreMissingMigrations(true);
        flyway.setOutOfOrder(true);


        flyway.repair();
        flyway.migrate();

        return flyway;
    }
}
