package cl.bibliotecaproyecto.usuarios.controller;

import cl.bibliotecaproyecto.usuarios.dto.UsuarioRequestDTO;
import cl.bibliotecaproyecto.usuarios.dto.UsuarioResponseDTO;
import cl.bibliotecaproyecto.usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerTodos(){
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(@PathVariable Long id){
        return usuarioService.obtenerUsuarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("buscar/nombreapellido")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarPorNombreUsuarioyApellidoUsuario(
            @RequestParam String nombreUsuario,  @RequestParam String apellidoUsuario) {
        return ResponseEntity.ok(usuarioService.buscarPorNombreAndApellido(nombreUsuario, apellidoUsuario));
    }

    @GetMapping("buscar/correo")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarPorCorreo(
            @RequestParam String correoUsuario) {
        return ResponseEntity.ok(usuarioService.buscarPorCorreo(correoUsuario));
    }

    @GetMapping("buscar/estado")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarPorEstado(
            @RequestParam String estadoUsuario) {
        return ResponseEntity.ok(usuarioService.buscarPorEstado(estadoUsuario));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(@Valid @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequestDTO dto) {
        return usuarioService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
