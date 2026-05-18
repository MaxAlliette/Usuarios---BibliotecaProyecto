package cl.bibliotecaproyecto.usuarios.service;

import cl.bibliotecaproyecto.usuarios.dto.UsuarioRequestDTO;
import cl.bibliotecaproyecto.usuarios.dto.UsuarioResponseDTO;
import cl.bibliotecaproyecto.usuarios.model.Usuario;
import cl.bibliotecaproyecto.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final WebClient webClient;

    private UsuarioResponseDTO mapToDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombreUsuario(),
                usuario.getApellidoPaternoUsuario(),
                usuario.getApellidoMaternoUsuario(),
                usuario.getCorreoUsuario(),
                usuario.getEstadoUsuario(),
                usuario.getIdRol()
        );
    }

    private void validarRol(Long idRol){
        try {
            webClient.get()
                    .uri("/api/roles/{id}", idRol)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            log.info("Rol {} validado correctamente", idRol);
        } catch (WebClientResponseException.NotFound e){
            throw new RuntimeException("Rol con id " + idRol + " no encontrado");
        } catch (Exception e){
            throw new RuntimeException("No se puede conectar con ms-roles: " + e.getMessage());
        }
    }

    public List<UsuarioResponseDTO> obtenerTodos(){
        return usuarioRepository.findAll().stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<UsuarioResponseDTO> obtenerUsuarioPorId(Long id){
        return usuarioRepository.findById(id).map(this::mapToDTO);
    }

    public List<UsuarioResponseDTO> buscarPorNombreAndApellido(String nombreUsuario, String apellidoUsuario) {
        return usuarioRepository.findByNombreUsuarioContainingIgnoreCaseAndApellidoUsuarioContainingIgnoreCase(nombreUsuario, apellidoUsuario)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UsuarioResponseDTO> buscarPorCorreo(String correoUsuario) {
        return usuarioRepository.findByCorreoContainingIgnoreCase(correoUsuario)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<UsuarioResponseDTO> buscarPorEstado(String estadoUsuario) {
        return usuarioRepository.findByEstadoContainingIgnoreCase(estadoUsuario)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public UsuarioResponseDTO guardar(UsuarioRequestDTO dto){
        validarRol(dto.getIdRol());
        Usuario usuario = new Usuario(
                null,
                dto.getNombreUsuario(),
                dto.getApellidoPaternoUsuario(),
                dto.getApellidoMaternoUsuario(),
                dto.getCorreoUsuario(),
                dto.getEstadoUsuario(),
                dto.getIdRol()
        );
        return  mapToDTO(usuarioRepository.save(usuario));
    }

    public Optional<UsuarioResponseDTO> actualizar (Long id, UsuarioRequestDTO dto){
        return usuarioRepository.findById(id).map(existente -> {
            validarRol(dto.getIdRol());
            existente.setNombreUsuario(dto.getNombreUsuario());
            existente.setApellidoPaternoUsuario(dto.getApellidoPaternoUsuario());
            existente.setApellidoMaternoUsuario(dto.getApellidoMaternoUsuario());
            existente.setCorreoUsuario(dto.getCorreoUsuario());
            existente.setEstadoUsuario(dto.getEstadoUsuario());
            existente.setIdRol(dto.getIdRol());
            return mapToDTO(usuarioRepository.save(existente));
        });
    }

    public void eliminar(Long id){ usuarioRepository.deleteById(id); }
}
