package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActuatorApplication.class, args);
    }


    //http://localhost:8080/actuator/httpexchanges 이렇게 확인이 가능하다. 최대 100개까지 메모리 디비에 올라간다.
    //개발단계에서만 사용하는걸 추천한다.
    //운영에서는 pinpoint 를 사용하는걸 추천한다고 한다.

    /*
    * 보안!!!!
    * 내부적으로 너무 많은 정보를 제공하기때문에 보안을 설정을 해야한다.
    * 엑츄에이터 엔드포인트들은 외부인터넷에서 막는게 좋다. 내부에서만 접근 가능한 내부망을 사용하는 것이 안전하다.
    *
    * 엑츄에이터 포트를 설정할수 있다.
    * management.server.port=9292 이런식으로 들어갈수 있다.
    * 
    * 경로에 대한 보안을 처리를 해야 한다. 스프링 시큐리티를 사용하든 필터를 사용하든 인터셉트를 사용하든간에
    *
    * 엔드포인트 경로도 변경이 가능하다 /actuator /manage 이런식으로
    * */

    @Bean
    public InMemoryHttpExchangeRepository inMemoryHttpExchangeRepository(){
        return new InMemoryHttpExchangeRepository();
    }

}
