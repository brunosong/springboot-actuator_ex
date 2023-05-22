package hello.order.v2;

import hello.order.OrderService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class OrderServiceV2 implements OrderService {

    /* V1에 가장큰 문제는 핵심로직에 Counter 가 끼워져 들어가져 있다는것이다.
    * 이런 부분을 분리해내려면 AOP 를 사용하면 된다.
    * 마이크로미터는 이미 만들어져있다. ( 수많은 사람들이 사용을 했었기 때문이다. )
    * */

    private AtomicInteger stock = new AtomicInteger(100);

    @Counted("my.order")  //태그에 메서드 이름이랑 클래스 이름이 자동으로 들어간다.
    @Override
    public void order() {
        log.info("주문");
        stock.decrementAndGet();
    }

    @Counted("my.order")
    @Override
    public void cancel() {
        log.info("취소");
        stock.incrementAndGet();
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }

}
