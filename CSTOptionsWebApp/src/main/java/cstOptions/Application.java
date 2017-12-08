package cstOptions;

import java.util.Arrays;

import javax.servlet.ServletContextListener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

import cstOptions.storage.StorageProperties;
import cstOptions.storage.StorageService;
import cstOptions.Selections.*;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
    
    @Bean
    ServletRegistrationBean myServletRegistration () {
        ServletRegistrationBean srb = new ServletRegistrationBean();
        srb.setServlet(new StudentSelections());
        srb.setUrlMappings(Arrays.asList("/saveSelections"));
        return srb;
    }

    @Bean
    ServletRegistrationBean myServletRegistration2 () {
        ServletRegistrationBean srb = new ServletRegistrationBean();
        srb.setServlet(new StudentID());
        srb.setUrlMappings(Arrays.asList("/saveID"));
        return srb;
    }

    @Bean
    FilterRegistrationBean myFilterRegistration () {
        FilterRegistrationBean frb = new FilterRegistrationBean();
        frb.setFilter(new ServletFilter());
        frb.setUrlPatterns(Arrays.asList("/*"));
        return frb;
    }

    @Bean
    ServletListenerRegistrationBean<ServletContextListener> myServletListener () {
        ServletListenerRegistrationBean<ServletContextListener> srb =
                  new ServletListenerRegistrationBean<>();
        srb.setListener(new ServletListener());
        return srb;
    }
}