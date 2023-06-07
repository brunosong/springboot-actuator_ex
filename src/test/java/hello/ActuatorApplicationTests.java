package hello;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ActuatorApplicationTests {

    @Test
    void contextLoads() {
        int[] one = {1,3,5};
        int m = one.length;

        int[] two = {2,3,6,7,9};
        int n = two.length;

        List<Integer> answer = new ArrayList<>();

        int p1 = 0;
        int p2 = 0;

        while ( p1 < m && p2 < n ) {

        }

    }

}
