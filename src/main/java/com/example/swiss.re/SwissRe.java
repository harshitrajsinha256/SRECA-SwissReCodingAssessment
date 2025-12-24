package com.example.swiss.re;

import com.example.swiss.re.service.SRECAService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwissRe {


    static SRECAService service = new SRECAService();

	public static void main(String[] args) {

		SpringApplication.run(SwissRe.class, args);

        service.process();
    }

}
