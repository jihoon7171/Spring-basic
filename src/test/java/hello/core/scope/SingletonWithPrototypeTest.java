package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.util.Assert;

public class SingletonWithPrototypeTest {
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean pb1 = ac.getBean(PrototypeBean.class);
        pb1.addcount();
        Assertions.assertThat(pb1.getcount()).isEqualTo(1);

        PrototypeBean pb2 = ac.getBean(PrototypeBean.class);
        pb2.addcount();
         Assertions.assertThat(pb2.getcount()).isEqualTo(1);
    }
    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count1).isNotEqualTo(count2);
    }


    @Scope("singleton")
    static class ClientBean{
        private final PrototypeBean prototypeBean; // 생성 시점에 주입됨.
        @Autowired
        public ClientBean(PrototypeBean prototypeBean){
            this.prototypeBean = prototypeBean;
        }
        public int logic(){
            prototypeBean.addcount();
            return prototypeBean.getcount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count=0;
        public void addcount(){
            count++;
        }
        public int getcount(){
            return count;
        }
    }

    @PostConstruct
    public void init(){
        System.out.println("PrototypeBean.init"+this);
    }
    @PreDestroy
    public void destroy(){
        System.out.println("PrototypeBean.destroy");
    }
}
