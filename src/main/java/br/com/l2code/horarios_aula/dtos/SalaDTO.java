package br.com.l2code.horarios_aula.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SalaDTO(
        Long id,
        @NotBlank(message = "Número da sala é obrigatório") String numero,
        @NotNull(message = "Capacidade é obrigatória") Integer capacidade,
        String descricao) {
}