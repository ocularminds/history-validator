package com.leadway.ps;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.context.annotation.ComponentScan;
import java.util.Properties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.leadway.ps.service")
@EnableJpaRepositories(basePackages = {"com.leadway.ps.repository"})
public class Db {
    private static final String[] ENTITY_PACKAGES = {"com.leadway.ps.model"};
    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create().driverClassName(environment.getProperty("spring.datasource.driverClassName"))
                .url(environment.getProperty("spring.datasource.url"))
                .username(environment.getProperty("spring.datasource.username"))
                .password(environment.getProperty("spring.datasource.password")).build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public SimpleJdbcCall simpleJdbcCall(JdbcTemplate jdbcTemplate) {
        return new SimpleJdbcCall(jdbcTemplate);
    }

    /**
     * Creates the bean that creates the JPA entity manager factory.
     *
     * Configures the used database dialect. This allows Hibernate to create SQL
     * that is optimized for the used database.
     *
     * Specifies the action that is invoked to the database when the Hibernate
     * SessionFactory is created or closed.
     *
     * Configures the naming strategy that is used when Hibernate creates new
     * database objects and schema elements.
     *
     * @param dataSource The data source that provides the database connections.
     * @param env The runtime environment of our application.
     * @return {@code LocalContainerEntityManagerFactoryBean}
     */
    @Primary
    @Bean(name = "entityManagerFactory")
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
            Environment env) {
        Properties properties = new Properties();
        properties.put(HIBERNATE_DIALECT, env.getRequiredProperty(HIBERNATE_DIALECT));
        properties.put(HIBERNATE_HBM2DDL_AUTO, env.getProperty(HIBERNATE_HBM2DDL_AUTO));
        properties.put(HIBERNATE_NAMING_STRATEGY,
                env.getRequiredProperty(HIBERNATE_NAMING_STRATEGY));
        properties.put(HIBERNATE_SHOW_SQL, env.getRequiredProperty(HIBERNATE_SHOW_SQL));
        properties.put(HIBERNATE_FORMAT_SQL, env.getRequiredProperty(HIBERNATE_FORMAT_SQL));
        properties.put("hibernate.jdbc.batch_size", 250);
        properties.put("spring.jpa.hibernate.temp.use_jdbc_metadata_defaults", "false");
        properties.put("spring.jpa.hibernate.ddl-auto", "none");
        properties.put("hibernate.order_inserts", true);
        properties.put("hibernate.order_updates", true);

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setJpaProperties(properties);
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setPackagesToScan(ENTITY_PACKAGES);
        emf.setPersistenceUnitName("THPU"); // <- giving 'default' as name
        emf.afterPropertiesSet();
        return emf;
    }

}
