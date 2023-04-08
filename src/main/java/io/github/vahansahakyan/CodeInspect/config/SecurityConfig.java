package io.github.vahansahakyan.CodeInspect.config;

import io.github.vahansahakyan.CodeInspect.filter.JwtFilter;
import io.github.vahansahakyan.CodeInspect.util.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private CustomPasswordEncoder customPasswordEncoder;
  @Autowired
  private JwtFilter jwtFilter;

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    // TODO Auto-generated method stub
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(customPasswordEncoder.getPasswordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http = http.csrf().disable().cors().disable();
    http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
    http = http.exceptionHandling().authenticationEntryPoint((req, res, ex) -> {
      res.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
    }).and();

//    http.authorizeRequests().antMatchers("/api/auth/**").permitAll().anyRequest().authenticated();
    http.authorizeRequests().antMatchers("/api/**").permitAll().anyRequest().authenticated();

    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
  }

}
