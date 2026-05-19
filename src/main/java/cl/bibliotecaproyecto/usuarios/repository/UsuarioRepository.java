package cl.bibliotecaproyecto.usuarios.repository;

import cl.bibliotecaproyecto.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNombreUsuarioAndApellidoPaternoUsuario(
            String nombreUsuario, String apellidoUsuario);

    List<Usuario> findByCorreoUsuario(
            String correoUsuario);

    List<Usuario> findByEstadoUsuario(
            String estadoUsuario);
}
