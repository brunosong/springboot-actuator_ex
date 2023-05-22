package hello.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogController {

    @GetMapping("/log")
    public String log(){

        //http://localhost:8080/actuator/loggers
        //여기를 들어가보면 전체적으로 어떤 로그레벨을 확인할수 있다.
        //실시간으로 로그레벨 변경도 가능하다.
        //실시간으로 info에서 debug로 변경을 해서 운영서버에서 확인 할수 있는 기능이다.
        //http://localhost:8080/actuator/loggers/hello.controller 이렇게 post로 바디에 json으로 { "configuredLevel" : "TRACE" } 로 날리면 된다.

        log.trace("trace LogController");
        log.debug("debug LogController");
        log.info("info LogController");
        log.warn("warn LogController");
        log.error("error LogController");

        return "ok";

    }


}
