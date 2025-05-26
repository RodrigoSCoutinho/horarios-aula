package br.com.l2code.horarios_aula.dtos;

import java.util.*;

public record HorariosSalaDTO(
        SalaDTO sala,
        List<HorarioDTO> horariosOcupados,
        List<PeriodoLivreDTO> periodosLivres) {
}
