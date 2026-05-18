package cl.bibliotecaproyecto.usuarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombreUsuario;

    @Column(nullable = false, length = 100)
    private String apellidoPaternoUsuario;

    @Column(nullable = false, length = 100)
    private String apellidoMaternoUsuario;

    @Column(nullable = false, length = 100, unique = true)
    private String correoUsuario;

    @Column(nullable = false, length = 20)
    private String estadoUsuario;

    @Column(nullable = false)
    private Long idRol;
}
