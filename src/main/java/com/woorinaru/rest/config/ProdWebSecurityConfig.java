package com.woorinaru.rest.config;

import com.woorinaru.rest.filter.JwtAuthenticationEntryPoint;
import com.woorinaru.rest.filter.JwtAuthenticationFilter;
import com.woorinaru.rest.security.authentication.JwtAuthenticationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

@EnableWebSecurity
@Profile("prod")
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ProdWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationUserDetailsService jwtAuthenticationUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(preAuthProvider());
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) {
        // exclude this api for spring security
        web.ignoring()
            .antMatchers("/authenticate");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            // disable CSRF
            .csrf().disable()
            // all requests need to be authenticated
            .authorizeRequests().anyRequest().authenticated().and()
            // make sure we use stateless session; session won't be used to store user's state
            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(tokenAuthenticationFilter(), RequestHeaderAuthenticationFilter.class);
//
//        // Add a filter to validate the tokens with every request
//        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
//        jwtAuthenticationFilter.setJwtTokenUtil(jwtTokenUtil);
//        jwtAuthenticationFilter.setUserDetailsMapper(userDetailsMapper);
//        jwtAuthenticationFilter.setUserService(userService);
//        jwtAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
//        jwtAuthenticationFilter.setAuthenticationManager(this.authenticationManagerBean());
//        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean(name= BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public AuthenticationProvider preAuthProvider() {
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(jwtAuthenticationUserDetailsService);
        return provider;
    }

    @Bean
    public JwtAuthenticationFilter tokenAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationFilter;
    }
}
