-- Inserir professores (com nome e email válidos)
INSERT INTO professores (id, nome, email) VALUES (1, 'Professor A', 'a@escola.com');
INSERT INTO professores (id, nome, email) VALUES (2, 'Professor B', 'b@escola.com');
INSERT INTO professores (id, nome, email) VALUES (3, 'Professor C', 'c@escola.com');
INSERT INTO professores (id, nome, email) VALUES (4, 'Professor D', 'd@escola.com');
INSERT INTO professores (id, nome, email) VALUES (5, 'Professor E', 'e@escola.com');

-- Inserir salas
INSERT INTO salas (id, numero, capacidade, descricao) VALUES (1, 'Sala 101', 30, 'Sala padrão');
INSERT INTO salas (id, numero, capacidade, descricao) VALUES (2, 'Sala 102', 30, 'Sala padrão');
INSERT INTO salas (id, numero, capacidade, descricao) VALUES (3, 'Sala 103', 30, 'Sala padrão');
INSERT INTO salas (id, numero, capacidade, descricao) VALUES (4, 'Sala 104', 30, 'Sala padrão');

-- Inserir aulas (Segunda-feira, 15 de Janeiro de 2024)
INSERT INTO aulas (disciplina, data_inicio, data_fim, professor_id, sala_id) 
VALUES ('Matemática', '2024-01-15 08:00:00', '2024-01-15 09:30:00', 1, 1);

INSERT INTO aulas (disciplina, data_inicio, data_fim, professor_id, sala_id) 
VALUES ('História', '2024-01-15 09:45:00', '2024-01-15 11:15:00', 2, 2);

INSERT INTO aulas (disciplina, data_inicio, data_fim, professor_id, sala_id) 
VALUES ('Ciências', '2024-01-15 13:30:00', '2024-01-15 15:00:00', 3, 3);

INSERT INTO aulas (disciplina, data_inicio, data_fim, professor_id, sala_id) 
VALUES ('Informática', '2024-01-15 15:15:00', '2024-01-15 16:45:00', 4, 4);

INSERT INTO aulas (disciplina, data_inicio, data_fim, professor_id, sala_id) 
VALUES ('Matemática Avançada', '2024-01-15 10:00:00', '2024-01-15 11:30:00', 1, 1);

-- Inserir aulas (Terça-feira, 16 de Janeiro de 2024)
INSERT INTO aulas (disciplina, data_inicio, data_fim, professor_id, sala_id) 
VALUES ('Geografia', '2024-01-16 08:00:00', '2024-01-16 09:30:00', 2, 2);

INSERT INTO aulas (disciplina, data_inicio, data_fim, professor_id, sala_id) 
VALUES ('Física', '2024-01-16 10:00:00', '2024-01-16 11:30:00', 3, 3);

INSERT INTO aulas (disciplina, data_inicio, data_fim, professor_id, sala_id) 
VALUES ('Literatura', '2024-01-16 14:00:00', '2024-01-16 15:30:00', 5, 1);

INSERT INTO aulas (disciplina, data_inicio, data_fim, professor_id, sala_id) 
VALUES ('Programação', '2024-01-16 16:00:00', '2024-01-16 17:30:00', 4, 4);

-- Aulas adicionais para demonstrar conflitos e horários livres
INSERT INTO aulas (disciplina, data_inicio, data_fim, professor_id, sala_id) 
VALUES ('Álgebra', '2024-01-15 16:00:00', '2024-01-15 17:30:00', 1, 1);

INSERT INTO aulas (disciplina, data_inicio, data_fim, professor_id, sala_id) 
VALUES ('Química', '2024-01-16 08:30:00', '2024-01-16 10:00:00', 3, 3);

INSERT INTO aulas (disciplina, data_inicio, data_fim, professor_id, sala_id) 
VALUES ('Redação', '2024-01-16 11:00:00', '2024-01-16 12:30:00', 5, 2);
