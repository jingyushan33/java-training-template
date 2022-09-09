package com.lunz.training;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * @author haha
 */

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class TrainingApplicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainingApplicationApplication.class, args);

        log.info("\n.-.-.      .-.-.      .-.-.      .-.-.      .-.-.      .-.-.      .-.-.  \n" +
                "'. S ).-.-.'. U ).-.-.'. C ).-.-.'. C ).-.-.'. E ).-.-.'. S ).-.-.'. S ) \n" +
                "  ).' '._.'  ).' '._.'  ).' '._.'  ).' '._.'  ).' '._.'  ).' '._.'  ).'  \n");
    }

}
