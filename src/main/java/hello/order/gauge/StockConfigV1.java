package hello.order.gauge;


import hello.order.OrderService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockConfigV1 {

    @Bean
    public MyStockMetric myStockMetric(OrderService orderService, MeterRegistry registry) {
        return new MyStockMetric(orderService, registry);
    }

    @Slf4j
    static class MyStockMetric {
        private OrderService orderService;
        private MeterRegistry registry;

        public MyStockMetric(OrderService orderService, MeterRegistry registry){
            this.orderService = orderService;
            this.registry = registry;
        }

        @PostConstruct
        public void init() {
            log.info("STOCK @PostConstruct 실행");
            //이렇게 등록을 하면 /actuator/metrics/my.stock 를 확인할때마다 service.getStock().get(); 이 실행된다.
            Gauge.builder("my.stock", orderService, service -> {

                log.info("stock gauge call");
                return service.getStock().get();  //측정되는 게이지 값

            }).register(registry);

        }
    }

}
