package hk.edu.hkmu.jiang.javafinal;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@MapperScan("hk.edu.hkmu.jiang.javafinal.Infrastructure.persistence.mapper")
public class JavaFinalProject8020Application {

    public static void main(String[] args) {
        SpringApplication.run(JavaFinalProject8020Application.class, args);
    }
}

