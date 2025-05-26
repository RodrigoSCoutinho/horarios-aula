package br.com.l2code.horarios_aula.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "aulas")
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Disciplina é obrigatória")
    private String disciplina;

    @Column(nullable = false, name = "data_inicio")
    @NotNull(message = "Data de início é obrigatória")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataInicio;

    @Column(nullable = false, name = "data_fim")
    @NotNull(message = "Data de fim é obrigatória")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataFim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id", nullable = false)
    @NotNull(message = "Professor é obrigatório")
    private Professor professor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sala_id", nullable = false)
    @NotNull(message = "Sala é obrigatória")
    private Sala sala;

    public Aula() {
    }

    public Aula(String disciplina, LocalDateTime dataInicio, LocalDateTime dataFim, Professor professor, Sala sala) {
        this.disciplina = disciplina;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.professor = professor;
        this.sala = sala;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "id=" + id +
                ", disciplina='" + disciplina + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", professor=" + professor.getNome() +
                ", sala=" + sala.getNumero() +
                '}';
    }
}