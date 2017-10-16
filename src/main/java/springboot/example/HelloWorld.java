package springboot.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lemostic on 2017/10/11.
 */
@SpringBootApplication
@RestController
public class HelloWorld {

    @RequestMapping("/")
    String hello(){
        return "hello";
    }

    public static void main(String [] args){
        SpringApplication.run(HelloWorld.class,args);
    }
}