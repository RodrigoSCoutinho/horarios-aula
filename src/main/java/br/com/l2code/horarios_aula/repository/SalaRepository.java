package br.com.l2code.horarios_aula.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.l2code.horarios_aula.models.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

    @Query("""
                SELECT s FROM Sala s
                WHERE s.id NOT IN (
                    SELECT DISTINCT a.sala.id FROM Aula a
                    WHERE (a.dataInicio <= :dataFim AND a.dataFim >= :dataInicio)
                )
            """)
    List<Sala> findSalasLivres(@Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim);

    @Query("""
                SELECT s FROM Sala s
                WHERE s.id IN (
                    SELECT DISTINCT a.sala.id FROM Aula a
                    WHERE (a.dataInicio <= :dataFim AND a.dataFim >= :dataInicio)
                )
            """)
    List<Sala> findSalasOcupadas(@Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim);
}
