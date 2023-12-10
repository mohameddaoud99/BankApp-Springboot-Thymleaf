package tn.iit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySpringSecurityConfig  {

	@Bean
	@ConditionalOnMissingBean(UserDetailsService.class)
	InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		return new InMemoryUserDetailsManager(
				User.withUsername("admin").password("{noop}1111").roles("ADMIN").build(),
				User.withUsername("client").password("{noop}1111").roles("USER").build(),
				User.withUsername("compte").password("{noop}1111").roles("USER").build()
		);
	}



	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
						(requests) -> requests.requestMatchers("/client").permitAll()
								.requestMatchers("/client/**").hasAnyRole("USER", "ADMIN")
								.requestMatchers("/client/**").hasRole("ADMIN")
								.anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/login").defaultSuccessUrl("/client",
						true).permitAll()).logout((logout) -> logout.permitAll());
		return http.build();
	}
}




