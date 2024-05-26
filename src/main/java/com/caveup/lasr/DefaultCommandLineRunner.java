package com.caveup.lasr;

import com.caveup.lasr.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author xueliang.wu
 */
@Slf4j
@Component
public class DefaultCommandLineRunner implements CommandLineRunner {

    @Autowired
    private AppConfig appConfig;

    @Override
    public void run(String... args) {
        log.info("App config:{}", appConfig);
        log.info("服务已经启动 >>>>>>>>>>>>>>>>>");
    }
}
