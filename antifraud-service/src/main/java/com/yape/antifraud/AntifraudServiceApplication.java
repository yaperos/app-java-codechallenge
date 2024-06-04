package com.yape.antifraud;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
public class AntifraudServiceApplication {
  static { System.setProperty("os.arch", "i686_64"); }
  public static void main(String[] args) throws UnknownHostException {

    Environment env = SpringApplication.run(AntifraudServiceApplication.class, args).getEnvironment();
    log.info("\n----------------------------------------------------------\n\t"
            .concat("Application '{}' is running! Access URLs:\n\t")
            .concat("Local: \t\thttp://localhost:{}\n\t")
            .concat("External: \thttp://{}:{}\n\t")
            .concat("DB: \t{}\n\t")
            .concat("Profile(s): \t{}\n----------------------------------------------------------"),
        env.getProperty("spring.application.name"),
        env.getProperty("server.port"),
        InetAddress.getLocalHost().getHostAddress(),
        env.getProperty("server.port"),
        env.getProperty("spring.data.mongodb.database"),
        env.getActiveProfiles());

    String configServerStatus = env.getProperty("configserver.status");
    log.info("\n----------------------------------------------------------\n\t"
            .concat("Config Server: \t{}\n----------------------------------------------------------"),
        configServerStatus == null ? "Not found or not setup for this application" : configServerStatus);
  }

}
