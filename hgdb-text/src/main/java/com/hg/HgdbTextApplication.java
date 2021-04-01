package com.hg;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
@MapperScan("com.hg.mapper")
public class HgdbTextApplication {

    private final static Logger logger = LoggerFactory.getLogger(HgdbTextApplication.class);

    public static void main(String[] args) {
        Environment env = SpringApplication.run(HgdbTextApplication.class, args).getEnvironment();
        logger.info(
                "\n---------------------------------------------------------\n\t"
                        +"Application '{}' is running ! Arrces URLs:\n\t"
                        +"Local:\t\thttp://localhost:{}{}"
                        +"\n-----------------------------------------------------",
                env.getProperty("spring.application.name"),env.getProperty("server.port"),
                env.getProperty("server.servlet.context.path") != null ? env.getProperty("server.servlet.context-path") : ""
        );
    }
}
