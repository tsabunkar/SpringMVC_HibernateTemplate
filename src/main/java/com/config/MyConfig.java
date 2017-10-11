package com.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.spring.model.Employee;

@Configuration
@EnableWebMvc

@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan({ "com.spring" })
public class MyConfig implements TransactionManagementConfigurer {

	@Bean(name = "bds")
	public BasicDataSource basicDataSource() {
		System.out.println("Basic data source started..........");
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		bds.setUrl("jdbc:mysql://localhost:3306/mysqldb");
		bds.setUsername("root");
		bds.setPassword("root");
		System.out.println("Basic data source end..........");
		return bds;
	}

	@Bean(name = "LocalSF")
	public LocalSessionFactoryBean localSessionFactory() throws IOException {
		System.out.println(" local SF started...");
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(basicDataSource());// property 1
		Properties prop = new Properties();
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		prop.setProperty("hibernate.hbm2ddl.auto", "update");
		prop.setProperty("hibernate.show_sql", "true");
		prop.setProperty("hibernate.format_sql", "true");
		sf.setHibernateProperties(prop); // property 2
		sf.setAnnotatedClasses(Employee.class); // property 3
		System.out.println(" local SF end...");
		sf.afterPropertiesSet();// mychange1

		return sf;
	}

	@Bean(name = "transactionManager")
	@Autowired
	public HibernateTransactionManager transactionManager() {
		System.out.println("Tranx Manager started....");
		HibernateTransactionManager htm = new HibernateTransactionManager();

		try {
			SessionFactory sf = localSessionFactory().getObject();
			htm.setSessionFactory(sf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("tranx Manager end....");
		return htm;
	}

	@Bean(name = "hiberTemp")
	public HibernateTemplate m14() throws IOException {
		System.out.println("hibernate template started...");
		HibernateTemplate ht = new HibernateTemplate();
		SessionFactory sf = localSessionFactory().getObject();
		ht.setSessionFactory(sf);

		System.out.println("hibernate template end...");
		return ht;
	}

	@Bean
	public InternalResourceViewResolver internalViewResolver() {
		InternalResourceViewResolver ivr = new InternalResourceViewResolver();
		ivr.setPrefix("/output/");
		ivr.setSuffix(".jsp");
		return ivr;
	}

	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManager();
	}

}
