package Calculator.Web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Web {
    public static void main(String[] args) {
        SpringApplication.run(Web.class, args);
    }
}
