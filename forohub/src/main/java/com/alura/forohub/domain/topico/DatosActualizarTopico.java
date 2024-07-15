package com.alura.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull
        Long id,
        String titulo,
        String descripcion,
        Estado estado
) {
}
