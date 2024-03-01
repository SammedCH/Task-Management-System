package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	 @Autowired
//	    private UserDetailsService userDetailsService;
//	
//	 @Autowired
//	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	    }
	@Bean 
	public UserDetailsService getUserDetailsService() {
		
//		UserDetails admin = User.builder().username("chandan")
//					.password(passwordEncoder().encode("12345"))
//					.roles("ADMIN").build();
//		
//		UserDetails user = User.builder().username("anu")
//			.password(passwordEncoder().encode("12345"))
//			.roles("USER").build();		
//		
//		return new InMemoryUserDetailsManager(admin, user);
		
		return new CustomUserDetailsServiceImpl();
	}
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
		
		return daoAuthenticationProvider;
	}
	
//	@Autowired
//	public void globalConfig(AuthenticationManagerBuilder auth) {
//		auth.authenticationProvider(this.authenticationProvider());
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http
			.authorizeHttpRequests(auth -> 
						auth.requestMatchers(AntPathRequestMatcher.antMatcher("/admin/**")).hasAnyRole("ADMIN")
							.requestMatchers(AntPathRequestMatcher.antMatcher("/user/**")).hasRole("USER")
							.requestMatchers(AntPathRequestMatcher.antMatcher("/**")).permitAll()
					)
			.formLogin(form ->
						form
							.loginPage("/signin")
							.loginProcessingUrl("/dologin")
							.defaultSuccessUrl("/user/dashboard")
							//.successHandler(successHandler())
							//.failureUrl("/login-failure")
					)
			.logout(logout ->
						logout
							.logoutRequestMatcher(AntPathRequestMatcher.antMatcher("/logout"))
					)
			.authenticationProvider(this.authenticationProvider())
			//.httpBasic(Customizer.withDefaults())
			.csrf(c -> c.disable());
				
		return http.build();
	}
	
	private AuthenticationSuccessHandler successHandler() {
		
		SimpleUrlAuthenticationSuccessHandler simpleUrlAuthenticationSuccessHandler = new SimpleUrlAuthenticationSuccessHandler();
		simpleUrlAuthenticationSuccessHandler.setDefaultTargetUrl("/");
		return simpleUrlAuthenticationSuccessHandler;
	}

}
