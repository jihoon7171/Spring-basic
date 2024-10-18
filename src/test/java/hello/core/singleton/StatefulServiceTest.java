package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.swing.plaf.nimbus.State;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        statefulService1.order("a", 10000);
        statefulService1.order("b", 20000);

        int price = statefulService1.getPrice();
        System.out.println(price); // 10000원을 기대했으나 20000이 등장함 -> 중간에 b가 바꿨기때문에. 둘은 같은 인스턴스를 사용하기때문에 발생하는 문제
        

    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }



}