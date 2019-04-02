package br.pedro.sandbox.springandangular.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@Profile("SECURITY_MOCK")
public class SecurityTokenMockConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers("*").permitAll()
			.anyRequest().permitAll()
			.and()
//			
//			.addFilterBefore(new JWTTokenuthenticationFilter(jwtConfig), 
//					UsernamePasswordAuthenticationFilter.class)
			
			.cors().disable();
		
        
		http.headers().frameOptions().disable();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
