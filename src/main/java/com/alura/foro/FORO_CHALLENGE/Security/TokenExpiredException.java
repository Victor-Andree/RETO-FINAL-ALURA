package com.alura.foro.FORO_CHALLENGE.Security;

public class TokenExpiredException extends RuntimeException{
    public TokenExpiredException(String message) {
        super(message);
    }
}
