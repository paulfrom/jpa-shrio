package quite.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;


/**
 * Created by acer on 2015/7/1.
 */
@Configuration
@EnableJpaRepositories(basePackages = "quite.dao",transactionManagerRef = "jpaTransactionManager",entityManagerFactoryRef = "entityManagerFactory")
@PropertySource({"classpath:app.properties"})
public class DataConfig {

   @Bean
   public DataSource dataSource( @Value("${database.className}") String driverClass,
                                 @Value("${database.url}") String url,
                                 @Value("${database.username}") String userName,
                                 @Value("${database.password}")String passWord){
       DataSource dataSource=new DataSource();
       dataSource.setDriverClassName(driverClass);
       dataSource.setUrl(url);
       dataSource.setUsername(userName);
       dataSource.setPassword(passWord);
       dataSource.setMaxAge(86400000);
       dataSource.setTestOnBorrow(true);
       dataSource.setDefaultAutoCommit(false);
       return dataSource;
   }

    /*shiwu*/
    @Bean
    public org.springframework.orm.jpa.JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager jpaTransactionManager=new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

//    <bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
//    <property name="dataSource" ref="dataSource"/>
//    <property name="jpaVendorAdapter" ref="hibernateJpaVendorAdapter"/>
//    <property name="packagesToScan" value="org.springside.examples.showcase"/>
//    <property name="jpaProperties">
//    <props>
//    <prop key="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</prop>
//    <prop key="net.sf.ehcache.configurationResourceName">cache/ehcache-hibernate-local.xml</prop>
//    <prop key="hibernate.ejb.naming_strategy">org.hibernate.cfg.ImprovedNamingStrategy</prop>
//    </props>
//    </property>
//    </bean>
    @Bean
    public org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan("quite");
//        entityManagerFactory.setPersistenceUnitName();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        return entityManagerFactory;
    }
}
