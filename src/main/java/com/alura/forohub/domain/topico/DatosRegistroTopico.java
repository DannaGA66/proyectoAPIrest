package com.alura.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String descripcion,
        @NotNull
        Long usuario_id,
        @NotNull
        Long curso_id
) {
}
