<h1 align="center">
  API de Horários de Aula com Spring Boot
</h1>

API para gerenciamento de horários de aula, professores e salas, que permite consultar disponibilidade de salas, horários livres e ocupados, e carga horária dos professores.

---

## Resumo do Projeto

Sistema de gerenciamento de horários de aula que permite visualizar a ocupação das salas, horários disponíveis e a carga horária dos professores. A API fornece endpoints para consultar salas livres e ocupadas em determinados períodos, além de mostrar a distribuição de horas por professor.

---

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [H2 Database](https://www.h2database.com)
- [Swagger/OpenAPI](https://springdoc.org/)
- [Docker](https://www.docker.com/)

## Práticas adotadas

- API REST
- Consultas customizadas com JPQL
- Documentação com OpenAPI/Swagger
- Banco de dados em memória (H2)
- Docker para containerização
- Testes automatizados

---

## Como Executar

### Usando Docker

```bash
# Construir a imagem
docker build -t horarios-aula .

# Executar o container
docker-compose up
```

### Localmente

```bash
# Compilar o projeto
./mvnw clean package

# Executar
./mvnw spring-boot:run
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080)
O Swagger UI estará disponível em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
Console do H2 disponível em [localhost:8080/h2-console](http://localhost:8080/h2-console)

---

## API Endpoints

### Consultar Horas dos Professores
```http
GET /api/horarios/professores/horas
```
Retorna a quantidade de horas que cada professor tem comprometido em aulas.

### Consultar Horários das Salas
```http
GET /api/horarios/salas?dataInicio={dataInicio}&dataFim={dataFim}
```
Lista todas as salas com seus horários livres e ocupados no período especificado.

### Consultar Salas Livres
```http
GET /api/horarios/salas/livres?dataInicio={dataInicio}&dataFim={dataFim}
```
Retorna as salas que estão livres no período especificado.

### Consultar Salas Ocupadas
```http
GET /api/horarios/salas/ocupadas?dataInicio={dataInicio}&dataFim={dataFim}
```
Retorna as salas que estão ocupadas no período especificado.

## Formato das Datas
As datas devem ser enviadas no formato ISO: `yyyy-MM-dd'T'HH:mm:ss`

Exemplo: `2024-01-15T08:00:00`

## Documentação da API

A documentação completa da API pode ser acessada através do Swagger UI após iniciar a aplicação:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Banco de Dados

O projeto utiliza o H2 Database, um banco de dados em memória. 
Os dados são carregados automaticamente ao iniciar a aplicação através do arquivo `data.sql`.

Acesso ao console do H2:
- URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`