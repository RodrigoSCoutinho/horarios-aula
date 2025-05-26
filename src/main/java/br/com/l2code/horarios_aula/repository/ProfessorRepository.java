package br.com.l2code.horarios_aula.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.l2code.horarios_aula.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("""
                SELECT p.nome as professorNome,
                       COALESCE(SUM(FUNCTION('TIMESTAMPDIFF', HOUR, a.dataInicio, a.dataFim)), 0) as totalHoras
                FROM Professor p
                LEFT JOIN p.aulas a
                GROUP BY p.id, p.nome
                ORDER BY p.nome
            """)
    List<Object[]> findProfessorsWithTotalHours();

    @Query("""
                SELECT p.nome as professorNome,
                       COALESCE(SUM(FUNCTION('TIMESTAMPDIFF', HOUR, a.dataInicio, a.dataFim)), 0) as totalHoras
                FROM Professor p
                LEFT JOIN p.aulas a
                WHERE p.id = :professorId
                GROUP BY p.id, p.nome
            """)
    Object[] findProfessorWithTotalHours(Long professorId);
}
