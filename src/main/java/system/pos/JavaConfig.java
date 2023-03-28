package system.pos;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import system.pos.item.ItemDao;
import system.pos.item.ItemRegisterService;
import system.pos.item.StockChangeService;
import system.pos.member.MemberDao;
import system.pos.member.MemberRegistService;
@Configuration
public class JavaConfig {
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/POS?characterEncoding=utf8&serverTimezone=UTC");
		ds.setUsername("root");
		ds.setPassword("1234");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(60000 * 3);
		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
		return ds;
	}
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
	@Bean
	public MemberRegistService memRegSvc() {
		return new MemberRegistService(memberDao());
	}
	@Bean
	public ItemDao itemDao() {
		return new ItemDao(dataSource());
	}
	@Bean
	public StockChangeService stockChangeSvc() {
		return new StockChangeService(itemDao());
	}
	@Bean
	public ItemRegisterService ItemRegSvc() {
		return new ItemRegisterService(itemDao());
	}

	
} 