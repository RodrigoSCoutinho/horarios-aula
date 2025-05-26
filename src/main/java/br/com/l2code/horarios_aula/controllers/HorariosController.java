package br.com.l2code.horarios_aula.controllers;

import br.com.l2code.horarios_aula.dtos.HorariosSalaDTO;

import br.com.l2code.horarios_aula.dtos.ProfessorHorasDTO;
import br.com.l2code.horarios_aula.dtos.SalaDTO;

import br.com.l2code.horarios_aula.services.HorariosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/horarios")
@Tag(name = "Horários", description = "API para gerenciamento de horários de aula")
public class HorariosController {

    @Autowired
    private HorariosService horariosService;

    @GetMapping("/professores/horas")
    @Operation(summary = "Buscar horas de cada professor", description = "Retorna a quantidade de horas que cada professor tem comprometido em aulas")
    public ResponseEntity<List<ProfessorHorasDTO>> getProfessoresComHoras() {
        List<ProfessorHorasDTO> professores = horariosService.getProfessoresComHoras();
        return ResponseEntity.ok(professores);
    }

    @GetMapping("/salas")
    @Operation(summary = "Buscar horários das salas", description = "Lista de salas com horários livres e ocupados em um período específico")
    public ResponseEntity<List<HorariosSalaDTO>> getHorariosSalas(
            @Parameter(description = "Data e hora de início do período", example = "2024-01-15T08:00:00") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @Parameter(description = "Data e hora de fim do período", example = "2024-01-15T18:00:00") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {

        List<HorariosSalaDTO> horarios = horariosService.getHorariosSalas(dataInicio, dataFim);
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/salas/livres")
    @Operation(summary = "Buscar salas livres", description = "Retorna as salas que estão livres em um período específico")
    public ResponseEntity<List<SalaDTO>> getSalasLivres(
            @Parameter(description = "Data e hora de início do período", example = "2024-01-15T08:00:00") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @Parameter(description = "Data e hora de fim do período", example = "2024-01-15T18:00:00") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {

        List<SalaDTO> salasLivres = horariosService.getSalasLivres(dataInicio, dataFim);
        return ResponseEntity.ok(salasLivres);
    }

    @GetMapping("/salas/ocupadas")
    @Operation(summary = "Buscar salas ocupadas", description = "Retorna as salas que estão ocupadas em um período específico")
    public ResponseEntity<List<SalaDTO>> getSalasOcupadas(
            @Parameter(description = "Data e hora de início do período", example = "2024-01-15T08:00:00") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @Parameter(description = "Data e hora de fim do período", example = "2024-01-15T18:00:00") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {

        List<SalaDTO> salasOcupadas = horariosService.getSalasOcupadas(dataInicio, dataFim);
        return ResponseEntity.ok(salasOcupadas);
    }
}