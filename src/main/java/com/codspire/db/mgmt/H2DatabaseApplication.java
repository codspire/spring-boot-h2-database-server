package com.codspire.db.mgmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;
import java.util.Arrays;

/**
 * Credit: https://stackoverflow.com/questions/43256295/how-to-access-in-memory-h2-database-of-one-spring-boot-application-from-another/43276769#43276769
 */
@SpringBootApplication
public class H2DatabaseApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(H2DatabaseApplication.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");

        SpringApplication.run(H2DatabaseApplication.class, args);

        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");
        LOG.info("{}", Arrays.asList(args));
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public org.h2.tools.Server h2Server() throws SQLException {
        return org.h2.tools.Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
}