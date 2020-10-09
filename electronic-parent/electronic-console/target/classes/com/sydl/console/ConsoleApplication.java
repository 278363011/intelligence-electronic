package com.sydl.console;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Slf4j
@EnableWebMvc
@EnableAsync
@EnableTransactionManagement
@MapperScan("com.sydl.console.mapper")
@SpringBootApplication(scanBasePackages = {"com.sydl"},exclude = {})
public class ConsoleApplication {

    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(ConsoleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        log.info("#####################electronic-console is running!!!!!!!!######################");
    }


}
