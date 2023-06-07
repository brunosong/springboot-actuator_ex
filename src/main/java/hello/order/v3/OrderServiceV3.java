package hello.order.v3;

import hello.order.OrderService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class OrderServiceV3 implements OrderService {

    private final MeterRegistry meterRegistry;

    public OrderServiceV3(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    private AtomicInteger stock = new AtomicInteger(100);

    @Override
    public void order() {

        Timer register = Timer.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "order")
                .description("order")
                .register(meterRegistry);

        register.record(() -> {
            //여기서 부터 시간을 측정한다.
            log.info("주문");
            stock.decrementAndGet();
            sleep(500);
        });



    }

    private static void sleep(int i) {
        try {
            Thread.sleep(i + new Random().nextInt(200));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cancel() {

        Timer register = Timer.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "cancel")
                .description("order")
                .register(meterRegistry);

        register.record(() -> {
            //여기서 부터 시간을 측정한다.
            log.info("취소");
            stock.incrementAndGet();
            sleep(200);
        });

    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }

}
