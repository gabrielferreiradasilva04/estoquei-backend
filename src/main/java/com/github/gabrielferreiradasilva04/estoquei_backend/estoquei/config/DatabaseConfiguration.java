package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfiguration {
	@Value("${spring.datasource.url}")
	String url;
	@Value("${spring.datasource.username}")
	String username;
	@Value("${spring.datasource.password}")
	String password;
	@Value("${spring.datasource.driver-class-name}")
	String driver;
    @Bean 
    DataSource hikariDataSource() {
    	
    	HikariConfig config = new HikariConfig();
    	config.setUsername(username);
    	config.setPassword(password);
    	config.setDriverClassName(driver);
    	config.setJdbcUrl(url);
    	/*principais*/
    	config.setMaximumPoolSize(10); /*configura a quantidade de acessos simultaneos a base de dados*/
    	config.setMinimumIdle(1); /*configura o minimo de conexões que vai ser liberada*/
    	config.setPoolName("library-db-pool");
    	config.setMaxLifetime(600000); /*Configura o tempo maximo para uma conexão, depois disso ela é morta : 600000 10 minutos*/
    	config.setConnectionTimeout(100000); /*máximo de tempo para tentar obter uma conexão, se não conseguir dentro do tempo ele da erro*/
    	/*opcionais*/
    	config.setConnectionTestQuery("select 1"); /*query para testar se o banco de dados está funcionando*/
    	
    	return new HikariDataSource(config);
    }

}
