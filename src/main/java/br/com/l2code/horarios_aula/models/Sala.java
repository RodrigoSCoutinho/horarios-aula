package br.com.l2code.horarios_aula.models;

import java.util.ArrayList;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "salas")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Número da sala é obrigatório")
    private String numero;

    @Column(nullable = false)
    @NotNull(message = "Capacidade é obrigatória")
    private Integer capacidade;

    @Column
    private String descricao;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Aula> aulas = new ArrayList<>();

    public Sala() {
    }

    public Sala(String numero, Integer capacidade, String descricao) {
        this.numero = numero;
        this.capacidade = capacidade;
        this.descricao = descricao;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", capacidade=" + capacidade +
                ", descricao='" + descricao + '\'' +
                ", aulas=" + aulas +
                '}';
    }
}