package Trail.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource securityDatasource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(securityDatasource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable().authorizeRequests().
		antMatchers("/register/**").permitAll()
		.antMatchers("/login/**").permitAll()
		.antMatchers("/").permitAll()
		.and()
		.formLogin().loginPage("/").loginProcessingUrl("/trail").defaultSuccessUrl("/menu", true).permitAll()
		.and()
		.logout().invalidateHttpSession(true).clearAuthentication(true).permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/access_denied");
	}

}