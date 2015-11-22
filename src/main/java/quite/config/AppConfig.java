package quite.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by acer on 2015/7/1.
 */
/*@Configuration ： 类似于spring配置文件，负责注册bean，对应的提供了@Bean注解。需要org.springframework.web.context.support.AnnotationConfigWebApplicationContext注册到容器中。

@ComponentScan ： 注解类查找规则定义 <context:component-scan/>

@EnableAspectJAutoProxy ： 激活Aspect自动代理 <aop:aspectj-autoproxy/>

@Import @ImportResource: 关联其它spring配置  <import resource="" />

@EnableCaching ：启用缓存注解  <cache:annotation-driven/>

@EnableTransactionManagement ： 启用注解式事务管理 <tx:annotation-driven />

@EnableWebMvcSecurity ： 启用springSecurity安全验证*/
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
