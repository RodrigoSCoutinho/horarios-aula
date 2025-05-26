package br.com.l2code.horarios_aula.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.l2code.horarios_aula.dtos.HorarioDTO;
import br.com.l2code.horarios_aula.dtos.HorariosSalaDTO;
import br.com.l2code.horarios_aula.dtos.PeriodoLivreDTO;
import br.com.l2code.horarios_aula.dtos.ProfessorHorasDTO;
import br.com.l2code.horarios_aula.dtos.SalaDTO;
import br.com.l2code.horarios_aula.models.Aula;
import br.com.l2code.horarios_aula.models.Sala;
import br.com.l2code.horarios_aula.repository.AulaRepository;
import br.com.l2code.horarios_aula.repository.ProfessorRepository;
import br.com.l2code.horarios_aula.repository.SalaRepository;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HorariosService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private AulaRepository aulaRepository;

    public List<ProfessorHorasDTO> getProfessoresComHoras() {
        List<Object[]> results = professorRepository.findProfessorsWithTotalHours();
        return results.stream()
                .map(result -> new ProfessorHorasDTO(
                        (String) result[0],
                        ((Number) result[1]).doubleValue()))
                .collect(Collectors.toList());
    }

    public List<HorariosSalaDTO> getHorariosSalas(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<Sala> todasSalas = salaRepository.findAll();

        return todasSalas.stream()
                .map(sala -> {
                    List<Aula> aulasNaSala = aulaRepository.findAulasBySalaAndPeriodo(
                            sala.getId(), dataInicio, dataFim);

                    List<HorarioDTO> horariosOcupados = aulasNaSala.stream()
                            .map(aula -> new HorarioDTO(
                                    aula.getDataInicio(),
                                    aula.getDataFim(),
                                    aula.getDisciplina(),
                                    aula.getProfessor().getNome()))
                            .sorted((h1, h2) -> h1.dataInicio().compareTo(h2.dataInicio()))
                            .collect(Collectors.toList());

                    List<PeriodoLivreDTO> periodosLivres = calcularPeriodosLivres(
                            dataInicio, dataFim, horariosOcupados);

                    return new HorariosSalaDTO(
                            new SalaDTO(sala.getId(), sala.getNumero(), sala.getCapacidade(), sala.getDescricao()),
                            horariosOcupados,
                            periodosLivres);
                })
                .collect(Collectors.toList());
    }

    private List<PeriodoLivreDTO> calcularPeriodosLivres(LocalDateTime inicio, LocalDateTime fim,
            List<HorarioDTO> horariosOcupados) {
        List<PeriodoLivreDTO> periodosLivres = new ArrayList<>();

        if (horariosOcupados.isEmpty()) {
            periodosLivres.add(new PeriodoLivreDTO(inicio, fim));
            return periodosLivres;
        }

        // Período livre antes da primeira aula
        LocalDateTime primeiraAula = horariosOcupados.get(0).dataInicio();
        if (inicio.isBefore(primeiraAula)) {
            periodosLivres.add(new PeriodoLivreDTO(inicio, primeiraAula));
        }

        // Períodos livres entre aulas
        for (int i = 0; i < horariosOcupados.size() - 1; i++) {
            LocalDateTime fimAulaAtual = horariosOcupados.get(i).dataFim();
            LocalDateTime inicioProximaAula = horariosOcupados.get(i + 1).dataInicio();

            if (fimAulaAtual.isBefore(inicioProximaAula)) {
                periodosLivres.add(new PeriodoLivreDTO(fimAulaAtual, inicioProximaAula));
            }
        }

        // Período livre após a última aula
        LocalDateTime ultimaAula = horariosOcupados.get(horariosOcupados.size() - 1).dataFim();
        if (ultimaAula.isBefore(fim)) {
            periodosLivres.add(new PeriodoLivreDTO(ultimaAula, fim));
        }

        return periodosLivres;
    }

    public List<SalaDTO> getSalasLivres(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<Sala> salasLivres = salaRepository.findSalasLivres(dataInicio, dataFim);
        return salasLivres.stream()
                .map(sala -> new SalaDTO(sala.getId(), sala.getNumero(),
                        sala.getCapacidade(), sala.getDescricao()))
                .collect(Collectors.toList());
    }

    public List<SalaDTO> getSalasOcupadas(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<Sala> salasOcupadas = salaRepository.findSalasOcupadas(dataInicio, dataFim);
        return salasOcupadas.stream()
                .map(sala -> new SalaDTO(sala.getId(), sala.getNumero(),
                        sala.getCapacidade(), sala.getDescricao()))
                .collect(Collectors.toList());
    }
}