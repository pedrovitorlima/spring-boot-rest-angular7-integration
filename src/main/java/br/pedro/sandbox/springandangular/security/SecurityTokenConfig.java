package br.pedro.sandbox.springandangular.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import br.pedro.sandbox.springandangular.security.filter.CORSFilter;
import br.pedro.sandbox.springandangular.security.filter.JWTLoginFilter;
import br.pedro.sandbox.springandangular.security.filter.JWTTokenuthenticationFilter;
import br.pedro.sandbox.springandangular.security.repository.UserRepository;
import br.pedro.sandbox.springandangular.security.service.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtConfig jwtConfig;

	@Autowired
	private UserDetailServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
			.anyRequest().authenticated()
			.and()
			
			.addFilterBefore(new CORSFilter(), CorsFilter.class)
			
			.addFilterBefore(new JWTLoginFilter(authenticationManager(), jwtConfig, userRepository), 
					UsernamePasswordAuthenticationFilter.class)
			
			.addFilterBefore(new JWTTokenuthenticationFilter(jwtConfig), 
					UsernamePasswordAuthenticationFilter.class);
        
		http.headers().frameOptions().disable();
	}
	
	@Bean
    public JWTTokenuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JWTTokenuthenticationFilter(jwtConfig);
    }
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManager();
	}

	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder());
    }

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtConfig jwtConfig() {
		return new JwtConfig();
	}
}