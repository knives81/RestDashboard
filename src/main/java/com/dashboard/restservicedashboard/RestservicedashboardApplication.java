package com.dashboard.restservicedashboard;

import com.dashboard.restservicedashboard.configuration.AppProperties;
import com.dashboard.restservicedashboard.info.Infos;
import com.dashboard.restservicedashboard.utils.AlmAuthentication;
import com.dashboard.restservicedashboard.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableScheduling
@EnableMongoRepositories(basePackages="com.dashboard")
public class RestservicedashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestservicedashboardApplication.class, args);
	}

	@Bean
	public Infos Infos() {
		return new Infos();
	}

}


@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AppProperties appProp;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/users/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.anyRequest().authenticated()
				.and().httpBasic()
				.and().csrf().disable();
	}

	@Autowired
	private UserDetailsService userDetailsServiceImpl;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		auth.eraseCredentials(false);
	}


	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		AlmAuthentication almAuthentication = new AlmAuthentication();
		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider() {
			protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
				if (authentication.getCredentials() == null) {
					this.logger.debug("Authentication failed: no credentials provided");
					throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
				} else {
					String presentedPassword = authentication.getCredentials().toString();
					if(appProp.getAlmAuthentication()) {
						this.logger.debug(userDetails.getUsername()+":"+presentedPassword);
						if(!almAuthentication.checkAuth(userDetails.getUsername(),presentedPassword,appProp)) {
							throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
						}
					} else{
						//System.out.println(userDetails.getUsername()+"-"+userDetails.getPassword());
						if (!this.getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
							this.logger.debug("Authentication failed: password does not match stored value");
							throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
						}
					}
				}
			}
		};
		authProvider.setUserDetailsService(userDetailsServiceImpl);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}