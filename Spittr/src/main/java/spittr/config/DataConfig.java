package spittr.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration 
//@ComponentScan("spittr.repository")
public class DataConfig { //用于配置数据库信息的配置文件

	//测试阶段的数据源(比较方便)
  @Bean
  public DataSource dataSource() { //该方法用于获得数据源
    return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("schema.sql")
            .build();
  }
  
  @Bean
  public JdbcOperations jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource); //使用jdbcTemplate可以极大简化JDBC编码
    //Spring对JDBC的支持就是通过JdbcTemplate完成的
  }

}
