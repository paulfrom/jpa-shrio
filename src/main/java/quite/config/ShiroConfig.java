package quite.config;

import net.sf.ehcache.config.CacheConfiguration;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import quite.core.AuthRealm;
import quite.core.credentials.RetryLimitHashedCredentialsMatcher;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by acer on 2015/7/4.
 */
@Configuration
public class ShiroConfig {

    @Bean
    public SecurityManager securityManager(Realm shiroRealm,CacheManager ehCacheManager){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        securityManager.setCacheManager(ehCacheManager);
        return securityManager;
    }

    @Bean
    public EhCacheManager ehCacheManager(){
        net.sf.ehcache.config.Configuration managerConfiguration=new net.sf.ehcache.config.Configuration()
                .name("shiroCache").updateCheck(false).defaultCache(new CacheConfiguration()
                        .maxEntriesLocalHeap(10000).eternal(true).timeToIdleSeconds(120).timeToLiveSeconds(120).overflowToDisk(false).diskPersistent(false).diskExpiryThreadIntervalSeconds(120));
        net.sf.ehcache.CacheManager manager= net.sf.ehcache.CacheManager.create(managerConfiguration);
        EhCacheManager cacheManager=new EhCacheManager();
        cacheManager.setCacheManager(manager);
        return  cacheManager;
    }

    @Bean
    public Realm shiroRealm(RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher){
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(retryLimitHashedCredentialsMatcher);
        authRealm.setAuthorizationCachingEnabled(true);
        return authRealm;
    }

    @Bean
    public RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher(EhCacheManager ehCacheCacheManager){
        RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher=new RetryLimitHashedCredentialsMatcher(ehCacheCacheManager);
        retryLimitHashedCredentialsMatcher.setHashAlgorithmName("md5");
        retryLimitHashedCredentialsMatcher.setHashIterations(1);
        retryLimitHashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return retryLimitHashedCredentialsMatcher;
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/admin/login.html");
        shiroFilterFactoryBean.setSuccessUrl("/admin/home.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/admin/login.html");
        Map<String,Filter> filterMap=new HashMap<String, Filter>();
        filterMap.put("fromFilter",new FormAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        Map<String,String> filterChainDefinitionMap=new HashMap<String, String>();
        filterChainDefinitionMap.put("/admin/logout.html","logout");
        filterChainDefinitionMap.put("/**","fromFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Map<String,String> map=new HashMap<String, String>();
        map.put("org.apache.shiro.authz.UnauthorizedException","/mgt/unauthorized");
        map.put("org.apache.shiro.authz.UnauthenticatedException","/unauthenticated");
        Properties properties=new Properties();
        properties.putAll(map);
        simpleMappingExceptionResolver.setExceptionMappings(properties);
        return simpleMappingExceptionResolver;
    }



    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
