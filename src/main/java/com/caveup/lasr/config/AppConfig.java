package com.caveup.lasr.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xw80329
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "app")

public class AppConfig {

    @Value("${plcHost:192.168.100.50}")
    private String plcHost;

    @Value("${plcPort:102}")
    private Integer plcPort;

    @Value("${statusAdd:V1802}")
    private String statusAdd;

    @Value("${productAdd:V1800}")
    private String productAdd;

    @Value("${markingHost:127.0.0.1}")
    private String markingHost;

    @Value("${markingPort:8080}")
    private Integer markingPort;

    @Value("${markingRootDir:/data}")
    private String markingRootDir;
}
