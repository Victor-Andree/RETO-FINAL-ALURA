package com.alura.foro.FORO_CHALLENGE.controller;


import com.alura.foro.FORO_CHALLENGE.Security.DatosJWTtoken;
import com.alura.foro.FORO_CHALLENGE.Security.TokenService;
import com.alura.foro.FORO_CHALLENGE.usuario.DatosUsuarioAutenticacion;
import com.alura.foro.FORO_CHALLENGE.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosUsuarioAutenticacion datosAtenticacionUsuario) {
        Authentication authtoken = new UsernamePasswordAuthenticationToken(
                datosAtenticacionUsuario.login(), datosAtenticacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authtoken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
    }

}
