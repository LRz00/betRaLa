/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.config;

import br.com.lrz.betRaLa.repositories.UserRepository;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author lara
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepo;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token !=null){
            var subject = tokenService.validateToken(token);
            UserDetails user = userRepo.findByEmail(subject);
            
            var authentication = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else{
            filterChain.doFilter(request, response);
        }
    }
    
    private String recoverToken(HttpServletRequest request){
       var authHeader = request.getHeader("Authorization");
       if(authHeader==null)return null;
       return authHeader.replace("Bearer ", "");
    }
}
