package kr.or.connect.healthproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.or.connect.healthproject.service.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Autowired
		CustomUserDetailsService customUserDetailsService;

	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers(
	                "/webjars/**");
	    }

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(customUserDetailsService);
	    }
   //   /, /main에 대한 요청은 누구나 할 수 있지만, 
   //  그 외의 요청은 모두 인증 후 접근 가능합니다.
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
               .csrf().disable()
               .authorizeRequests()
               .antMatchers("/resources/css/**","/resources/css/**","/resources/img/**/**","/resources/js/**").permitAll()
               .antMatchers("/", "/index","/shop","/product","/api/**","/swagger-ui.html#!/**", "/members/joinform", "/members/join", "/members/welcome").permitAll()
               .antMatchers("/securepage", "/members/**").hasRole("USER")
               .anyRequest().authenticated()
               .and()
                   .formLogin()
                   .loginPage("/members/loginform")
                   .usernameParameter("userId")
                   .passwordParameter("password")
                   .loginProcessingUrl("/authenticate")
                   .failureForwardUrl("/members/loginerror?login_error=1")
                   .defaultSuccessUrl("/",true)
                   .permitAll()
               .and()
                   .logout()
                   .logoutUrl("/logout")
                   .logoutSuccessUrl("/");
   }

   // 패스워드 인코더를 빈으로 등록합니다. 암호를 인코딩하거나, 
   // 인코딩된 암호와 사용자가 입력한 암호가 같은 지 확인할 때 사용합니다.
   @Bean
   public PasswordEncoder encoder() {
       return new BCryptPasswordEncoder();
   }
}
