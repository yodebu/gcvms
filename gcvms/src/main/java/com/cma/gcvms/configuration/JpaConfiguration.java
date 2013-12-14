/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-backend-jpa:src/main/java/configuration/JpaConfiguration.p.vm.java
 */
package com.cma.gcvms.configuration;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class JpaConfiguration {

    @Value("classpath:hibernate.properties")
    private Properties jpaProperties;

    @Resource(name = "dataSource")
    private DataSource dataSource;

    /**
     * Enable exception translation for beans annotated with @Repository
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * @see read http://www.springframework.org/docs/reference/transaction.html
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    /**
     * Build the entity manager with Hibernate as a provider.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        // We set the persistenceXmlLocation to a different name to make it work on JBoss.
        emf.setPersistenceXmlLocation("classpath:META-INF/spring-persistence.xml");
        emf.setPersistenceUnitName("gcvmsPU");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaProperties(jpaProperties);
        return emf;
    }

    @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.getSessionFactory();
    }
}
