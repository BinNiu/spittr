package spittr.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc //启用注解驱动的springMvc
@ComponentScan(value="spittr.web")
public class WebConfig extends WebMvcConfigurerAdapter {
	//视图解析器
	@Bean
	public ViewResolver viewResolver(SpringTemplateEngine templateEngine){
		ThymeleafViewResolver viewResolver=new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		return viewResolver;
	}
	//模板引擎
	@Bean
	public SpringTemplateEngine templateEngine(TemplateResolver temlateResolver){
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(temlateResolver);
	    
	    templateEngine.addDialect(new SpringSecurityDialect());
	    
	    return templateEngine;
	}
	//模板解析器
	 @Bean
	  public TemplateResolver templateResolver() {
	    TemplateResolver templateResolver = new ServletContextTemplateResolver();
	    templateResolver.setPrefix("/WEB-INF/views/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode("HTML5");
	    return templateResolver;
	  }
	
	
	//配置Spring MVC让其不对静态资源如html css等进行拦截，而是直接放行
		@Override
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
			configurer.enable();
		}
		
		
		//作用是动态添加一个视图控制器		
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/login").setViewName("login");;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
