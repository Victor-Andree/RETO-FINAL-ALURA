package com.alura.foro.FORO_CHALLENGE.topico;

public record DatosLIstadoTopico(
        Long idUsuario,
        String mensaje,
        String nombre_curso,
        String titulo
) {
    public DatosLIstadoTopico(Topico topico){
        this(topico.getIdUsuario(),
                topico.getMensaje(),
                topico.getNombre_curso(),
                topico.getTitulo());
    }
}
