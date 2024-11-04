package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
//        BeanB beanB = ac.getBean("BeanB", BeanB.class);
        Assertions.assertThat(beanA).isNotNull();

//        ac.getBean("beanB", BeanB.class);





    }
    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{}
}
