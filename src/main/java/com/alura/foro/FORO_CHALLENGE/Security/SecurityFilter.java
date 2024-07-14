package com.alura.foro.FORO_CHALLENGE.Security;

import com.alura.foro.FORO_CHALLENGE.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class SecurityFilter extends OncePerRequestFilter  {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public SecurityFilter(TokenService tokenservice, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenservice;
        this.usuarioRepository = usuarioRepository;
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenjwt = recuperarToken(request);
        if (tokenjwt != null){
           try {
               String subject = tokenService.getSubject(tokenjwt);
               var usuario = usuarioRepository.findByLogin(subject);

               if (usuario != null){
                   var autenticacion = new UsernamePasswordAuthenticationToken(usuario, null , usuario.getAuthorities());
                   SecurityContextHolder.getContext().setAuthentication(autenticacion);

               }
           } catch (TokenExpiredException  e){
               response.setStatus(HttpStatus.UNAUTHORIZED.value());
               response.setContentType("application/json");
               response.getWriter().write("{\"message\": \"" + e.getMessage() + "\"}");
               return;


           }

        }

     }



}
