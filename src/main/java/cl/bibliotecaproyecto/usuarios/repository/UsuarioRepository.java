package cl.bibliotecaproyecto.usuarios.repository;

import cl.bibliotecaproyecto.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNombreUsuarioContainingIgnoreCaseAndApellidoUsuarioContainingIgnoreCase(
            String nombreUsuario, String apellidoUsuario);

    List<Usuario> findByCorreoContainingIgnoreCase(
            String correoUsuario);

    List<Usuario> findByEstadoContainingIgnoreCase(
            String estadoUsuario);
}
