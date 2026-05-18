package cl.bibliotecaproyecto.usuarios.config;

import cl.bibliotecaproyecto.usuarios.model.Usuario;
import cl.bibliotecaproyecto.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializr implements CommandLineRunner {
    private final UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() > 0) {
            log.info("Usuarios ya cargados. Se omite inicialización.");
            return;
        }
        log.info("Cargando usuarios iniciales...");
        usuarioRepository.save(new Usuario(null, "Camilo", "Baeza", "Polanco", "camilob@gmail.com", "habilitado", 2L));
        usuarioRepository.save(new Usuario(null, "Felipe", "Lopez", "Leon", "felipelp@gmail.com", "habilitado", 1L));
        usuarioRepository.save(new Usuario(null, "Scarleth", "Gonzalez", "Ponce", "scar@gmail.com", "inhabilitado", 2L));
        log.info("Usuarios cargados.");
    }
}
