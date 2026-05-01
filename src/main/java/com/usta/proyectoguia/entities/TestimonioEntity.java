package com.usta.proyectoguia.entities;

import com.usta.proyectoguia.entities.ContactEntity;
import com.usta.proyectoguia.entities.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "testimonios")
public class TestimonioEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", updatable = false, nullable = false)
    private String uuid;

    @NotBlank
    @Size(max = 200)
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @NotBlank
    @Column(name = "contenido", columnDefinition = "TEXT", nullable = false)
    private String contenido;

    @Min(1)
    @Max(5)
    @Column(name = "estrellas", nullable = false)
    private Integer estrellas;

    @NotBlank
    @Size(max = 100)
    @Column(name = "pais", nullable = false)
    private String pais;

    @NotNull
    @Column(name = "fecha", nullable = false, updatable = false)
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", nullable = false)
    private ContactEntity contact;

    @PrePersist
    protected void onCreate() {
        fecha = LocalDateTime.now();
    }

    public TestimonioEntity() {}
}