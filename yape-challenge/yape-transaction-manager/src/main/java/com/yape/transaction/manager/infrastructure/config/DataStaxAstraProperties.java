package com.yape.transaction.manager.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;

@Getter
@Setter
@ConfigurationProperties("datastax.astra")
public class DataStaxAstraProperties {

    private File secureConnectBundle;

}
