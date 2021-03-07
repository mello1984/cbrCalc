package mello.cbrcalc.springmvcdemo;

import lombok.extern.slf4j.Slf4j;
import mello.cbrcalc.configs.SpringConfig;
import mello.cbrcalc.downloader.ValCodeDownloader;
import mello.cbrcalc.downloader.ValRateDownloader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBException;
import java.io.IOException;

//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan("mello.cbrcalc")
@Slf4j
public class SpringMvcDemoApplication {
//    public static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    public static void main(String[] args) {
//        ValCodeDownloader valCodeDownloader = context.getBean(ValCodeDownloader.class);
//        ValRateDownloader valRateDownloader = context.getBean(ValRateDownloader.class);

//		try {
//			valCodeDownloader.updateCodesTable();
//			valRateDownloader.updateCodesTable();
//		} catch (IOException | JAXBException e) {
//			e.printStackTrace();
//		}
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>TEST STRING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        SpringApplication.run(SpringMvcDemoApplication.class, args);
    }

}
