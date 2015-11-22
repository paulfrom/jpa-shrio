package quite.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by acer on 2015/7/4.
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer implements WebApplicationInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("quite.config");
        servletContext.addListener(new ContextLoaderListener(context));

        FilterRegistration.Dynamic charEncodingFilter=servletContext.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
        charEncodingFilter.setInitParameter("encoding","UTF-8");
        charEncodingFilter.setInitParameter("forceEncoding","true");
        charEncodingFilter.addMappingForUrlPatterns(null,false,"/*");

        FilterRegistration.Dynamic shiroFilter=servletContext.addFilter("shiroFilter",new DelegatingFilterProxy());
        shiroFilter.setInitParameter("dispatcher","REQUEST");
        shiroFilter.setInitParameter("dispatcher","FORWARD");
        shiroFilter.addMappingForUrlPatterns(null, false, "/admin/*");

        DispatcherServlet dispatcherServlet=new DispatcherServlet(context);
        ServletRegistration.Dynamic dispatcher=servletContext.addServlet("appServlet",dispatcherServlet);
        dispatcher.setAsyncSupported(true);
        dispatcher.setLoadOnStartup(0);
        dispatcher.addMapping("*.html","*.htm","*.json","*.api");
    }
}
