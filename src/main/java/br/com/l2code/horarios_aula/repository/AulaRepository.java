package br.com.l2code.horarios_aula.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.l2code.horarios_aula.models.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

    @Query("""
                SELECT a FROM Aula a
                WHERE a.sala.id = :salaId
                AND (a.dataInicio <= :dataFim AND a.dataFim >= :dataInicio)
            """)
    List<Aula> findAulasBySalaAndPeriodo(@Param("salaId") Long salaId,
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim);

    @Query("""
                SELECT a FROM Aula a
                WHERE a.professor.id = :professorId
                ORDER BY a.dataInicio
            """)
    List<Aula> findAulasByProfessor(@Param("professorId") Long professorId);

    @Query("""
                SELECT a FROM Aula a
                WHERE a.dataInicio >= :dataInicio AND a.dataFim <= :dataFim
                ORDER BY a.dataInicio
            """)
    List<Aula> findAulasByPeriodo(@Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim);
}
