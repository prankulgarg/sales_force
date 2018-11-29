package com.rc.config;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.rc.controller.DeleteResource;
import com.rc.dao.DeleteResourceDao;
import com.rc.dao.GetResorcesDao;
import com.rc.dao.LoginServiceDao;
import com.rc.dao.UpdateResourceDao;
import com.rc.dao.impl.DeleteResourceDaoimpl;
import com.rc.dao.impl.GetResourceDaoimpl;
import com.rc.dao.impl.LoginServiceDaoImpl;
import com.rc.dao.impl.UpdateResourceDaoimpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.rc")
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
	private static Logger logger = Logger.getLogger(WebMvcConfiguration.class);
	DriverManagerDataSource dataSource = null;

	private static boolean isCacheLoaded = false;

/*	public WebMvcConfiguration() {
		super();
		this.loadCache();
	}
*/
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		// resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public DataSource getDataSource() {
		System.out.println("Before loging");
		logger.info("Datasource creation");
		System.out.println("After loging");
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		/*dataSource.setUrl("jdbc:mysql://103.206.248.235:3306/salesforce_new?useSSL=false");
		dataSource.setUsername("ravendra");
		dataSource.setPassword("Chauhan@123");*/ 
		         
        dataSource.setUrl("jdbc:mysql://localhost:3306/salesforce_new?useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("root"); 
               
                
		return dataSource;
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager getTransactionManager() {

		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(getDataSource());

		return transactionManager;

	}

	@Bean
	public LoginServiceDao getLoginServiceDao() {
		return new LoginServiceDaoImpl(getDataSource(), getTransactionManager());
	}

	@Bean
	public GetResorcesDao getGetResorcesDao() {
		return new GetResourceDaoimpl(getDataSource());
	}
	
	@Bean
	public UpdateResourceDao getUpdateResourceDao() {
		return new UpdateResourceDaoimpl(getDataSource(), getTransactionManager());
	}
	
	@Bean
	public DeleteResourceDao getDeleteResourceDao() {
		return new DeleteResourceDaoimpl(getDataSource());
	}
	

	/*public void afterConstruct() {
		logger.debug("post construct");
		 loadCache();
	}

	@Scheduled(fixedRate = 600000)
	public void loadCache() {
		// log "alive" every hour for sanity checks
		if (!isCacheLoaded) {
			
			  CacheManagerService cacheManagerService = new
			  CacheManagerService(getDataSource());
			  cacheManagerService.getRetailerData();
			  cacheManagerService.getMemberData();
			 
			 System.out.println("Retailer Data:" +
			 cacheManagerService.retailerdata);
			System.out.println("Cache Loaded");
			isCacheLoaded = true;
		}
	}
*/
	/*
	 * @Bean public CacheManagerService getCacheManagerService() { return new
	 * CacheManagerService(getDataSource()); }
	 */
}