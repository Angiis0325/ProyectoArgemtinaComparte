package com.usta.proyectoguia.entities;

import com.usta.proyectoguia.entities.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notifications")
public class NotificationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", updatable = false, nullable = false)
    private String uuid;

    @NotBlank
    @Size(max = 50)
    @Column(name = "tipo", length = 50, nullable = false)
    private String tipo;

    @NotBlank
    @Size(max = 500)
    @Column(name = "mensaje", length = 500, nullable = false)
    private String mensaje;

    @ColumnDefault("false")
    @Column(name = "leida", nullable = false)
    private Boolean leida = false;

    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)  // ← Cambiado updatable=false
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;  // ← Import corregido (sin package completo)

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public NotificationEntity() {}
}