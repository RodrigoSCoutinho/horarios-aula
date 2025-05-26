package br.com.l2code.horarios_aula.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AulaDTO(
        Long id,
        @NotBlank(message = "Disciplina é obrigatória") String disciplina,
        @NotNull(message = "Data de início é obrigatória") @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dataInicio,
        @NotNull(message = "Data de fim é obrigatória") @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dataFim,
        @NotNull(message = "ID do professor é obrigatório") Long professorId,
        @NotNull(message = "ID da sala é obrigatório") Long salaId,
        String professorNome,
        String salaNumero) {
}