CREATE TABLE foro (
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje VARCHAR(255) NOT NULL,
    nombre_curso VARCHAR(255) NOT NULL,
    titulo VARCHAR(255) NOT NULL
);