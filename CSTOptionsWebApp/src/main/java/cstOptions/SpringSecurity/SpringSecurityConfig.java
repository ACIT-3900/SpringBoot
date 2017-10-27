package cstOptions.SpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
					.antMatchers("/index", "/home", "/login", "/user", "/done").permitAll()
					.antMatchers("/admin/**").hasAnyRole("ADMIN")
					.antMatchers("/user/**").hasAnyRole("USER")
					.antMatchers("/superuser/**").hasAnyRole("SUPER")
					.anyRequest().authenticated()
                .and()
                .formLogin()
					.loginPage("/login")
					.permitAll()
					.and()
                .logout()
					.permitAll()
					.and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER") /* Test roles */
                .and()
                .withUser("admin").password("password").roles("ADMIN") /* Test roles */
                .and()
                .withUser("whosyourdaddy").password("allyourbasearebelongtome").roles("SUPER"); /* Test roles */
        
        	/* Add your own USER and PASSWORD pair here, one for each person if necessary. */
		
    }
}