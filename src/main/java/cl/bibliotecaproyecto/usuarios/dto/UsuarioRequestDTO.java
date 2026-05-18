package cl.bibliotecaproyecto.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String nombreUsuario;

    @NotBlank(message = "El apellido paterno del usuario es obligatorio y no puede estar vacío")
    private String apellidoPaternoUsuario;

    @NotBlank(message = "El apellido materno del usuario es obligatorio y no puede estar vacío")
    private String apellidoMaternoUsuario;

    @NotBlank(message = "El correo del usuario es obligatorio y no puede estar vacío")
    @Email(message = "Debe escribir un correo válido")
    private String correoUsuario;

    @NotBlank(message = "El estado de usuario es obligatorio y no puede estar vacío")
    private String estadoUsuario;

    @NotNull(message = "El idRoles es obligatorio")
    private Long idRol;
}
