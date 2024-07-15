package com.alura.forohub.domain.usuario;

import jakarta.validation.constraints.Email;

public record DatosAutenticacionUsuario(
        @Email
        String email,
        String clave
) {
}
