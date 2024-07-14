package com.alura.foro.FORO_CHALLENGE.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull
        Long idUsuario,
        String mensaje,
        String nombreCurso,
        String titulo
) {
}
