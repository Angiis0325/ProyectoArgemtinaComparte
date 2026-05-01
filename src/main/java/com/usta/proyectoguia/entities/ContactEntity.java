package com.usta.proyectoguia.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "contacts")
public class ContactEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", updatable = false, nullable = false)
    private String uuid;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @NotBlank
    @Email
    @Size(max = 150)
    @Column(name = "correo", length = 150, nullable = false, unique = true)
    private String correo;

    @NotBlank
    @Size(max = 100)
    @Column(name = "pais", length = 100, nullable = false)
    private String pais;

    @Size(max = 500)
    @Column(name = "foto_url", length = 500)
    private String fotoUrl;

    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TestimonioEntity> testimonios = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public ContactEntity() {}
}