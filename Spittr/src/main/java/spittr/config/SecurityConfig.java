package spittr.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

@Configuration
@EnableWebSecurity				//这个注解有一个已过时的带MVC的子类，区别就是子父类，为了springMVC的框架使用
//写这个类就相当于启用了安全配置
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource; 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
	/*	http
		.authorizeRequests().anyRequest().authenticated()//拦截所有请求
			.and()
		.formLogin()//增加一个登录窗口
			.and()
		.httpBasic();//防止跨站请求
*/		
		http.formLogin()
		.loginPage("/login")
		.and()
			.logout()
			.logoutSuccessUrl("/")//跳回首页
		.and()
		.rememberMe() //记住我多久时间
			.tokenRepository(new InMemoryTokenRepositoryImpl())
			.tokenValiditySeconds(2419200)
			.key("spittrkey")
		.and()//http认证
		.httpBasic()
			.realmName("spittr")
		.and()//授权
		.authorizeRequests()
		 .antMatchers("/").authenticated()
		 .antMatchers("/spitter/me").authenticated()
		 .antMatchers(HttpMethod.POST,"/spittles").authenticated()
		 .anyRequest().permitAll()
		 .and().csrf().disable();
			
			
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//测试环境
//		auth
//		.inMemoryAuthentication()
//		.withUser("user").password("password").roles("USER")
//		.and()
//		.withUser("admin").password("password").roles("USER","ADMIN");
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("SELECT username, password, true FROM spitter WHERE username = ?")
		.authoritiesByUsernameQuery("SELECT username,'ROLE_USER' FROM spitter WHERE username=?");
	}
	
	

}
