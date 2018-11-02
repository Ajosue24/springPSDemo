
package com.proasecal.web.config;

import com.proasecal.web.filter.AclFilter;
import com.proasecal.web.service.seguridad.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("$2a$10$2Bh/CFmuoofz2uPdDsuw4.FgvlDwk10t905WhUZgkD.EFtLrx6gAO").roles("ADMIN")
                .and()
                .passwordEncoder(new BCryptPasswordEncoder());

    	auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
    	
    }
    
    @Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

    public void configure(HttpSecurity http) throws Exception {


        /**
         * Org Work 23/10/18
         */
        http.cors().and();
        http.csrf().disable().authorizeRequests().antMatchers("/register", "/",
                "/images/**", "/login", "/css/**", "/js/**","/vendors/**","/build/**", "/webjars/**").permitAll() //Aqui le decimos que permita las carpetas de stylos y js para que sean publicos
        		.anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll().
                defaultSuccessUrl("/index").
                successForwardUrl("/index").
                failureUrl("/login?error").
                usernameParameter("username").
                passwordParameter("password").
                and().
                logout().
                logoutSuccessUrl("/login?logout").
                deleteCookies("JSESSIONID").
                and().addFilterAfter(aclFilter(), BasicAuthenticationFilter.class).
                sessionManagement().
                maximumSessions(1).
                maxSessionsPreventsLogin(false).//El segundo saca al primero
                expiredUrl("/401");


    }

    /**
     * Este metodo, es por si se desea que el sistema recuerde al usuario
     *
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    private AclFilter aclFilter() {
        return new AclFilter();
    }

}
