package br.com.l2code.horarios_aula;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.com.l2code.horarios_aula.models.Aula;
import br.com.l2code.horarios_aula.models.Professor;
import br.com.l2code.horarios_aula.models.Sala;
import br.com.l2code.horarios_aula.repository.AulaRepository;
import br.com.l2code.horarios_aula.repository.ProfessorRepository;
import br.com.l2code.horarios_aula.repository.SalaRepository;
import br.com.l2code.horarios_aula.services.HorariosService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class HorariosAulaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private HorariosService horariosService;

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private SalaRepository salaRepository;

	@Autowired
	private AulaRepository aulaRepository;

	private Professor professor;
	private Sala sala;
	private Aula aula;

	@BeforeEach
	void setUp() {
		// Limpar dados antes de cada teste
		aulaRepository.deleteAll();
		professorRepository.deleteAll();
		salaRepository.deleteAll();

		// Criar dados de teste
		professor = new Professor("Prof. Teste", "teste@escola.com");
		professor = professorRepository.save(professor);

		sala = new Sala("T01", 30, "Sala de Teste");
		sala = salaRepository.save(sala);

		aula = new Aula("Disciplina Teste",
				LocalDateTime.of(2024, 1, 15, 8, 0),
				LocalDateTime.of(2024, 1, 15, 9, 30),
				professor, sala);
		aula = aulaRepository.save(aula);
	}

	@Test
	void contextLoads() {
		assertThat(horariosService).isNotNull();
	}

	// ==================== TESTES DOS ENDPOINTS PRINCIPAIS ====================

	@Test
	void testGetProfessoresComHoras() throws Exception {
		mockMvc.perform(get("/api/horarios/professores/horas"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].professorNome").value("Prof. Teste"))
				.andExpect(jsonPath("$[0].totalHoras").value(1.5));
	}

	@Test
	void testGetHorariosSalas() throws Exception {
		mockMvc.perform(get("/api/horarios/salas")
				.param("dataInicio", "2024-01-15T07:00:00")
				.param("dataFim", "2024-01-15T18:00:00"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].sala.numero").value("T01"));
	}

	@Test
	void testGetSalasLivres() throws Exception {
		// Criar uma sala adicional que estará livre
		Sala salaLivre = new Sala("T02", 25, "Sala Livre");
		salaRepository.save(salaLivre);

		mockMvc.perform(get("/api/horarios/salas/livres")
				.param("dataInicio", "2024-01-15T08:00:00")
				.param("dataFim", "2024-01-15T09:30:00"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].numero").value("T02"));
	}

	@Test
	void testGetSalasOcupadas() throws Exception {
		mockMvc.perform(get("/api/horarios/salas/ocupadas")
				.param("dataInicio", "2024-01-15T08:00:00")
				.param("dataFim", "2024-01-15T09:30:00"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].numero").value("T01"));
	}
}
