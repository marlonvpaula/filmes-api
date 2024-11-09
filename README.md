# Filmes API

**Filmes API** é uma API RESTful para gerenciar um banco de dados de filmes, permitindo operações CRUD, além de consultas avançadas, como intervalo entre prêmios dos produtores. Esta aplicação utiliza Spring Boot, Spring Data JPA e integra-se com Swagger para documentação da API.

## Funcionalidades

- **CRUD de Filmes**: Criação, leitura, atualização e exclusão de registros de filmes.
- **Consulta de Intervalos de Prêmios**: Recupera os produtores que ganharam prêmios em múltiplos anos e calcula o intervalo entre as vitórias.
- **Documentação Interativa**: Interface Swagger disponível em `/swagger-ui/index.html` para explorar e testar os endpoints.

## Tecnologias Utilizadas

- **Java** 17+
- **Spring Boot** 3.x
- **Spring Data JPA** com H2 Database para desenvolvimento/teste
- **OpenCSV** para importação de dados de um arquivo CSV
- **Springdoc OpenAPI** para documentação da API com Swagger UI

## Requisitos

- **Java** 17 ou superior
- **Maven** para gerenciar dependências e executar a aplicação

## Configuração do Projeto

### Clonando o Repositório

```bash
git clone https://github.com/marlonvpaula/filmes-api.git
cd filmes-api
```

### Testando a Consulta de Intervalos de Prêmios:

GET
http://localhost:8080/movie/intervalopremio
