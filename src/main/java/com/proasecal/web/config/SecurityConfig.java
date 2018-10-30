
package com.proasecal.web.config;

import com.proasecal.web.filter.AclFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

/*
    @Autowired
    private UsuarioService usuarioService;*/

    @Autowired
    DataSource dataSource;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private final String USERS_QUERY = "select v_nombre_usuario, v_password, b_estado from USUARIOS where v_nombre_usuario=?";
    private final String ROLES_QUERY = "select u.v_nombre_usuario, r.v_descripcion from USUARIOS u inner join roles_usuarios ur on (u.id_usuarios = ur.id_usuarios) inner join ROLES r on (ur.id_roles=r.id_roles) where u.v_nombre_usuario=?";//Esto es con el join
    private final String ROLES_QUERYII = "select user_id, authority " + "from authorities where user_id=?";

    @Override
    public void configure(WebSecurity web) throws Exception {
        /*web.ignoring().antMatchers()*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * Credenciales administracion Usuario
         */
        auth.inMemoryAuthentication()
                .withUser("admin").password("$2a$10$2Bh/CFmuoofz2uPdDsuw4.FgvlDwk10t905WhUZgkD.EFtLrx6gAO").roles("USER", "ADMIN")
                .and()
                .passwordEncoder(new BCryptPasswordEncoder());
/**
 * Autenticacion por JDBC
 */
        //auth.jdbcAuthentication().usersByUsernameQuery(USERS_QUERY).authoritiesByUsernameQuery(ROLES_QUERY).dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);


    }

    public void configure(HttpSecurity http) throws Exception {


        /**
         * Org Work 23/10/18
         */
        http.cors().and();
        http.csrf().disable().authorizeRequests().antMatchers("/register", "/",
                "/images/**", "/login", "/css/**", "/js/**", "/webjars/**").permitAll() //Aqui le decimos que permita las carpetas de stylos y js para que sean publicos
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
