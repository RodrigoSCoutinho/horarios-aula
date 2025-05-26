package br.com.l2code.horarios_aula.dtos;

import java.time.LocalDateTime;

public record HorarioDTO(
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        String disciplina,
        String professorNome) {
}
