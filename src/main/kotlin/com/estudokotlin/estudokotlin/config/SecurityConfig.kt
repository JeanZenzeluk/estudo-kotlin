package com.estudokotlin.estudokotlin.config

import com.estudokotlin.estudokotlin.enums.Role
import com.estudokotlin.estudokotlin.repository.CustomerRepository
import com.estudokotlin.estudokotlin.security.AuthenticationFilter
import com.estudokotlin.estudokotlin.security.AuthorizationFilter
import com.estudokotlin.estudokotlin.security.CustomAuthenticationEntryPoint
import com.estudokotlin.estudokotlin.service.JwtUtil
import com.estudokotlin.estudokotlin.service.UserDetailsCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
        private val customerRepository: CustomerRepository,
        private val userDetails: UserDetailsCustomService,
        private val jwtUtil: JwtUtil,
        private val customEntryPoint: CustomAuthenticationEntryPoint
) : WebSecurityConfigurerAdapter() {

    private val PUBLIC_MATCHERS = arrayOf<String>(
            // colocar aqui as URL's totalmente publica
    )

    private val PUBLIC_POST_MATCHERS = arrayOf(
        "/customers"
    )

    private val ADMIN_MATCHERS = arrayOf(
            "/admin/**"
    )

    private val PUBLIC_GET_MATCHERS = arrayOf(
            "/books"
    )

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
        http.authorizeRequests()
                .antMatchers(*PUBLIC_MATCHERS).permitAll()  // permite acesso sem autenticação
                .antMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHERS).permitAll() // permite os POSTS de customer
                .antMatchers(*ADMIN_MATCHERS).hasAuthority(Role.ADMIN.description)
                .antMatchers(HttpMethod.GET, *PUBLIC_GET_MATCHERS).permitAll()
                .anyRequest().authenticated()
        http.addFilter(AuthenticationFilter(authenticationManager(), customerRepository, jwtUtil))
        http.addFilter(AuthorizationFilter(authenticationManager(), userDetails, jwtUtil))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.exceptionHandling().authenticationEntryPoint(customEntryPoint)
    }

    @Bean
    fun corsConfig(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOriginPattern("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
                "/configuration/**", "/swagger-ui.html", "/webjars/**")
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

}