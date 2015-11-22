package quite.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by acer on 2015/7/1.
 */
/*@Configuration �� ������spring�����ļ�������ע��bean����Ӧ���ṩ��@Beanע�⡣��Ҫorg.springframework.web.context.support.AnnotationConfigWebApplicationContextע�ᵽ�����С�

@ComponentScan �� ע������ҹ����� <context:component-scan/>

@EnableAspectJAutoProxy �� ����Aspect�Զ����� <aop:aspectj-autoproxy/>

@Import @ImportResource: ��������spring����  <import resource="" />

@EnableCaching �����û���ע��  <cache:annotation-driven/>

@EnableTransactionManagement �� ����ע��ʽ������� <tx:annotation-driven />

@EnableWebMvcSecurity �� ����springSecurity��ȫ��֤*/
@Configuration
@ComponentScan("quite")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@EnableCaching
@Import({CacheConfig.class})
public class AppConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}
