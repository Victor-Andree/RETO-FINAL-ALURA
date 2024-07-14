package com.alura.foro.FORO_CHALLENGE.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "foro")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idUsuario")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String mensaje;
    private String nombre_curso;
    private String titulo;

    public Topico(DatosRegistroTopico datosRegistroTopico) {

        this.mensaje = datosRegistroTopico.mensaje();
        this.nombre_curso = datosRegistroTopico.nombre_curso();
        this.titulo = datosRegistroTopico.titulo();

    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombre_curso() {
        return nombre_curso;
    }

    public void setNombre_curso(String nombreCurso) {
        this.nombre_curso = nombreCurso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void actualizarForo(DatosActualizarTopico datosActualizarTopico){
        if (datosActualizarTopico.mensaje() != null){
            this.setMensaje(datosActualizarTopico.mensaje());
        }
        if (datosActualizarTopico.nombreCurso() != null){
            this.setNombre_curso(datosActualizarTopico.nombreCurso());
        }
        if (datosActualizarTopico.titulo() != null){
            this.setTitulo(datosActualizarTopico.titulo());
        }


    }
}
