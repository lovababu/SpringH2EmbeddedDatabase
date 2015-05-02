package com.lova.spring.config;

import com.lova.spring.dao.SampleDao;
import com.lova.spring.dao.impl.SampleDaoImpl;
import com.lova.spring.service.SampleService;
import com.lova.spring.service.impl.SampleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Lovababu on 4/26/2015.
 */
@Configuration
@PropertySource("classpath:dbConfig.properties")
@EnableTransactionManagement
@Slf4j
public class AppConfig {

    @Value("${hibernate.hibernateDialect}")
    private String hibernateDialect;
    @Value("${hibernate.showSQL}")
    private String showSql;
    @Value("${hibernate.generateStatistics}")
    private String generateStatistics;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean (name = "sampleDao")
    public SampleDao sampleDao() throws IOException {
        SampleDao sampleDao = new SampleDaoImpl();
        sampleDao.sessionFactory(sessionFactory());
        return sampleDao;
    }


    @Bean (name = "sampleService")
    public SampleService sampleService(){
        return new SampleServiceImpl();
    }

    @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.lova.spring.domain");
        sessionFactoryBean.setAnnotatedPackages("com.lova.spring.domain");
        sessionFactoryBean.setHibernateProperties(getHibernateProperties());
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }

    /**
     * Spring provided H2 Embedded Database. Read the dbscript and initiates the Database with the name H2-Test-DB.
     *
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource dataSource(){
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setName("H2-Test-DB");
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:dbscript/my-schema.sql")
                .addScript("classpath:dbscript/my-test-data.sql").build();
        log.info("Initiating the database from dbscript.");
        return db;
    }


    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager() throws Exception {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(this.sessionFactory());
        return transactionManager;
    }

    public Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.cache.use_second_level_cache", false);
        properties.put("hibernate.generate_statistics", generateStatistics);
        return properties;
    }

}
