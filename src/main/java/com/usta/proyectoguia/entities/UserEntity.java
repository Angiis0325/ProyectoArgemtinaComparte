package com.usta.proyectoguia.entities;

import com.usta.proyectoguia.entities.NotificationEntity;
import com.usta.proyectoguia.entities.TestimonioEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class UserEntity implements Serializable {
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
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String passwordHash;

    @NotBlank
    @Pattern(regexp = "ADMIN|EDITOR|LECTOR")
    @Column(name = "rol", length = 20, nullable = false)
    private String rol;

    @ColumnDefault("false")
    @Column(name = "two_factor_enabled", nullable = false)
    private Boolean twoFactorEnabled = false;

    @Size(max = 255)
    @Column(name = "two_factor_secret")
    private String twoFactorSecret;

    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TestimonioEntity> testimonios = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<NotificationEntity> notifications = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public UserEntity() {}
}