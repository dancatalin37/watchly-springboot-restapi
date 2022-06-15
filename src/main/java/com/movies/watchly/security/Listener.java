package com.movies.watchly.security;

import com.movies.watchly.models.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
public class Listener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void listener(List<String> movies){
        log.info("{} text is", movies);
          System.out.println(movies);
    }

}
