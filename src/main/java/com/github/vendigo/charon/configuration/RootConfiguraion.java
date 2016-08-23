package com.github.vendigo.charon.configuration;

import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.github.vendigo.charon")
@ImportResource("classpath:file-config.xml")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = {"com.github.vendigo.charon"})
public class RootConfiguraion {

    @Bean
    public static PropertySourcesPlaceholderConfigurer devPropertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(destroyMethod = "close")
    @Profile("dev")
    public DataSource dataSource(@Value("${sql.driverClassName}") String sqlDriverClassName,
                                 @Value("${sql.url}") String sqlUrl,
                                 @Value("${sql.username}") String sqlUserName,
                                 @Value("${sql.password}") String sqlPassword,
                                 @Value("${sql.validationQuery}") String validationQuery) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(sqlDriverClassName);
        dataSource.setUrl(sqlUrl);
        dataSource.setUsername(sqlUserName);
        dataSource.setPassword(sqlPassword);
        dataSource.setValidationQuery(validationQuery);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "false");
        entityManagerFactory.setJpaProperties(properties);
        entityManagerFactory.setPackagesToScan("com.github.vendigo.charon");
        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager(FactoryBean<EntityManagerFactory> entityManagerFactory) throws Exception {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean
    public SpringTransactionPolicy springTransactionPolicy(DataSourceTransactionManager dataSourceTransactionManager) {
        SpringTransactionPolicy springTransactionPolicy = new SpringTransactionPolicy();
        springTransactionPolicy.setTransactionManager(dataSourceTransactionManager);
        return springTransactionPolicy;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
