package management.security;

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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import management.dao.dao_security_concept.UserRepository;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;


	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService)
	.passwordEncoder(bCryptPasswordEncoder);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
	http.csrf().disable();
	http.headers().frameOptions().disable();
	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	

	
	http.authorizeRequests().antMatchers("/login/**","/register/**","/refreshToken").permitAll();
	//les routes specifiées

	//hardware
	 http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/hardware/**").permitAll();
	 http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/v1/hardware/**").hasAuthority("ADMIN");
	 http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/v1/hardware/**").hasAuthority("ADMIN");
	 http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/hardware/**").hasAuthority("ADMIN");

	 //order
	 http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/order/**").hasAuthority("ADMIN");

	 //user
	 http.authorizeRequests().antMatchers(HttpMethod.GET,  "/api/v1/user/**").hasAuthority("ADMIN");
	 http.authorizeRequests().antMatchers(HttpMethod.DELETE,  "/api/v1/user/**").hasAuthority("ADMIN");

	 //chech authenticated after
	 http.authorizeRequests().anyRequest().authenticated();

	 //all filter
	 http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
	 http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception{
		return super.authenticationManager();
	}
}

