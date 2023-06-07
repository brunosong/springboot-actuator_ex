package hello.order.v2;

import hello.order.OrderService;
import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfigV2 {



    @Bean
    OrderServiceV2 orderService(){
        return new OrderServiceV2();
    }

    //AOP 가 작동하도록 어드바이스를 만들어 놔야 한다.  이걸 해놔야 @Counted 어노테이션이 작동한다.
    @Bean
    public CountedAspect countedAspect(MeterRegistry meterRegistry) {
        return new CountedAspect(meterRegistry);
    }

}
