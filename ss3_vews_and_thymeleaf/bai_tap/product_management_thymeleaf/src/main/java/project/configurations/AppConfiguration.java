package project.configurations;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
@EnableWebMvc
@ComponentScan("project")
public class AppConfiguration implements WebMvcConfigurer,  ApplicationContextAware{

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //Cấu hình Thymleaf
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setForceContentType(true);
        viewResolver.setContentType("text/html; charset=UTF-8");
        return viewResolver;
    }

    // equivalents for <mvc:resources/> tags
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }

    // equivalent for <mvc:default-servlet-handler/> tag
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setForceEncoding(true);
//        characterEncodingFilter.setEncoding("UTF-8");
//        registrationBean.setFilter(characterEncodingFilter);
//        return registrationBean;
//    }

//    @Bean
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
//        encodingFilter.setInitParameter("encoding", "UTF-8");
//        encodingFilter.setInitParameter("forceEncoding", "true");
//        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
//    }

//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine(templateEngine());
//        resolver.setCharacterEncoding("UTF-8"); // <- this was added
//        resolver.setForceContentType(true); // <- this was added
//        resolver.setContentType("text/html; charset=UTF-8"); // <- this was added
//        registry.viewResolver(resolver);
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        CharacterEncodingFilter filter = new CharacterEncodingFilter();
//        filter.setEncoding("UTF-8");
//        filter.setForceEncoding(true);
//
//        http.authorizeRequests().anyRequest().anonymous()
//                .antMatchers("/login**", "/*.js", "/*.css", "/*.svg" ).permitAll()
//                // ... some other config
//                .invalidateHttpSession(true)
//                .permitAll()
//                .and()
//                .addFilterBefore(filter, CsrfFilter.class); // <- this was added
//    }
}
