package com.lmx.lmxlucenewriter;

import com.lmx.lmxlucenewriter.service.WriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LmxLuceneWriterApplication implements ApplicationRunner {

    @Autowired
    WriteService writeService;

    public static void main(String[] args) {
        SpringApplication.run(LmxLuceneWriterApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        writeService.writerTestData();
    }
}
