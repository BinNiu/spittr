package spittr.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/*
 * 	该类会在Servlet3.0以上版本的容器中被自动发现，随着Tomcat的启动而启动
 * 该类主要目的是被发现后引导容器去找WebConfig.class和RootConfig.class
 */
public class SpittrWebAppInitializer 
	extends AbstractAnnotationConfigDispatcherServletInitializer {
		//实现该类便替代了之前的Web.xml文件
	
	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class<?>[] { RootConfig.class };
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class<?>[]{WebConfig.class};
	}
	/*该方法的作用是让项目启动后（访问/），则去调用Spring MVC的核心控制器DispatcherServlet */
	@Override
	protected String[] getServletMappings() {
	
		return new String[] {"/"} ;
	}



}
