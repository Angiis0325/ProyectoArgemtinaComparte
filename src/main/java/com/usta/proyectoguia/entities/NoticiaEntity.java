package com.usta.proyectoguia.entities;

import com.usta.proyectoguia.entities.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "noticias")
public class NoticiaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", updatable = false, nullable = false)
    private String uuid;

    @NotBlank
    @Size(max = 200)
    @Column(name = "titulo", length = 200, nullable = false)
    private String titulo;

    @NotBlank
    @Column(name = "resumen", columnDefinition = "TEXT", nullable = false)
    private String resumen;

    @NotBlank
    @Column(name = "contenido", columnDefinition = "TEXT", nullable = false)
    private String contenido;

    @Size(max = 500)
    @Column(name = "imagen_url", length = 500)
    private String imagenUrl;

    @NotNull
    @Column(name = "fecha_publicacion", nullable = false, updatable = false)
    private LocalDateTime fechaPublicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private UserEntity autor;

    @PrePersist
    protected void onCreate() {
        fechaPublicacion = LocalDateTime.now();
    }

    public NoticiaEntity() {}
}