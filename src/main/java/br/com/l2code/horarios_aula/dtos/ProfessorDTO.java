package br.com.l2code.horarios_aula.dtos;

import jakarta.validation.constraints.NotBlank;

public record ProfessorDTO(
                Long id,
                @NotBlank(message = "Nome é obrigatório") String nome,
                @NotBlank(message = "Email é obrigatório") String email) {
}
