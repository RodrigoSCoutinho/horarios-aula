package br.com.l2code.horarios_aula.dtos;

import java.time.LocalDateTime;

public record PeriodoLivreDTO(
        LocalDateTime dataInicio,
        LocalDateTime dataFim) {
}