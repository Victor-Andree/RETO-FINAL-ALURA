package com.alura.foro.FORO_CHALLENGE.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTopico(
        @NotBlank
        String mensaje,
        @NotBlank
        String nombre_curso,
        @NotBlank
        String titulo

) {
}
