package com.proasecal.ProasecalApp.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception{
        /*web.ignoring().antMatchers()*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("admin").password("$2a$10$2Bh/CFmuoofz2uPdDsuw4.FgvlDwk10t905WhUZgkD.EFtLrx6gAO").roles("ADMIN")
                .and()
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    public void configure(HttpSecurity http)throws Exception{

        http.cors().and();
        http.csrf().disable().authorizeRequests().antMatchers("/register", /*"/"*/
                "/images/**", "/login", "/css/**", "/js/**","/webjars/**").permitAll() //Aqui le decimos que permita las carpetas de stylos y js para que sean publicos
                //.anyRequest().authenticated()//
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
                and(). //.and().addFilterAfter(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                sessionManagement().
                maximumSessions(1).
                maxSessionsPreventsLogin(false).//El segundo saca al primero
                expiredUrl("/401");





        /*.and()
                .addFilterAfter(jwtFilter(), UsernamePasswordAuthenticationFilter.class);*/
/*

 http.authorizeRequests().
                antMatchers("/admin**").
                hasAnyRole("ADMIN", "USER").
                and().
                formLogin().
                loginPage("/login").
                defaultSuccessUrl("/index").
                failureUrl("/login?error").
                usernameParameter("username").
                passwordParameter("password").
                and().
                logout().
                logoutSuccessUrl("/login?logout").
                deleteCookies("JSESSIONID").
                and().
                sessionManagement().
                maximumSessions(1).
                expiredUrl("/401");
*/



    }

    //private JwtFilter jwtFilter(){
    //   return new JwtFilter(usuarioService);}


}
