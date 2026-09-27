# FarmaConecta 

### Rede Inteligente de Disponibilidade de Medicamentos do SUS

O FarmaConecta é um projeto acadêmico desenvolvido para o Hackathon da Fase 5 da FIAP. Seu objetivo é facilitar a consulta da disponibilidade de medicamentos em diferentes Unidades Básicas de Saúde (UBS).

A proposta é permitir que a disponibilidade de um medicamento seja consultada em uma única API, reunindo informações de estoque de diferentes unidades de saúde.

> **Status:** MVP backend desenvolvido e testado. O sistema utiliza dados cadastrados para demonstração e não possui integração com bases oficiais do SUS.

## 1. Problema

A busca por medicamentos pode exigir que o cidadão entre em contato ou se desloque até diferentes unidades de saúde para descobrir onde há estoque disponível.

A falta de uma consulta centralizada pode dificultar o acesso à informação e gerar deslocamentos desnecessários.

## 2. Solução

O FarmaConecta propõe uma API REST para organizar o cadastro de UBS, medicamentos e estoques, permitindo consultar em quais unidades um medicamento possui quantidade disponível.

O MVP demonstra o funcionamento dessa consulta com dados cadastrados no próprio sistema.

## 3. Funcionalidades

- Cadastrar, listar, consultar e atualizar UBS.
- Cadastrar, listar, consultar e atualizar medicamentos.
- Cadastrar lotes de medicamentos no estoque de uma UBS.
- Listar o estoque de uma UBS.
- Registrar entrada de medicamentos no estoque.
- Registrar saída de medicamentos, impedindo quantidades superiores ao saldo disponível.
- Consultar a disponibilidade de um medicamento em diferentes UBS.
- Validar dados e tratar erros nas requisições.

A consulta de disponibilidade retorna apenas registros com quantidade maior que zero.

## 4. Tecnologias utilizadas

| Tecnologia | Utilização |
|---|---|
| Java 21 | Linguagem de programação |
| Spring Boot 4.1.1 | Desenvolvimento da API REST |
| Maven | Gerenciamento de dependências e execução dos testes |
| PostgreSQL 17 | Banco de dados relacional |
| Docker Compose | Execução local do PostgreSQL |
| Flyway | Versionamento do banco de dados |
| Spring Data JPA / Hibernate | Persistência dos dados |
| Jakarta Validation | Validação das requisições |
| JUnit e Mockito | Testes automatizados |
| Postman | Testes manuais dos endpoints |
| IntelliJ IDEA | Ambiente de desenvolvimento |

## 5. Arquitetura

O projeto foi organizado em camadas, seguindo princípios de arquitetura limpa e separação de responsabilidades.

```text
src/main/java/br/com/farmaconecta/
├── domain/
│   ├── exception/
│   ├── model/
│   └── repository/
├── application/
│   ├── dto/
│   └── usecase/
├── infrastructure/
│   ├── configuration/
│   └── persistence/
└── interfaces/
    └── rest/
        ├── controller/
        └── exception/
```

**Domain:** modelos, regras de negócio e contratos dos repositórios.

**Application:** casos de uso e objetos de transferência de dados (DTOs).

**Infrastructure:** configuração das dependências e persistência no PostgreSQL.

**Interfaces:** endpoints REST e tratamento das exceções HTTP.

## 6. Como executar o projeto

### Pré-requisitos

- Java 21
- Docker com Docker Compose
- Git, caso o projeto seja obtido por um repositório remoto

### 6.1. Iniciar o PostgreSQL

Na pasta principal do projeto, execute:

```powershell
docker compose up -d
```

O arquivo `compose.yaml` inicia o PostgreSQL 17 com as seguintes configurações locais:

| Configuração | Valor |
|---|---|
| Banco de dados | `farmaconecta` |
| Usuário | `farmaconecta` |
| Porta no computador | `5433` |
| Contêiner | `farmaconecta-postgres` |

**Atenção:** as credenciais presentes no `compose.yaml` e no `application.properties` são destinadas exclusivamente ao ambiente local de desenvolvimento e demonstração. Não devem ser reutilizadas em produção.

### 6.2. Executar a aplicação

No Windows, dentro da pasta principal do projeto, execute:

```powershell
.\mvnw.cmd spring-boot:run
```

A API estará disponível em:

```text
http://localhost:8081
```

As migrações do Flyway são executadas na inicialização da aplicação para preparar as tabelas do banco de dados.

## 7. Endpoints da API

### UBS

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/ubs` | Cadastrar UBS |
| GET | `/api/ubs` | Listar UBS |
| GET | `/api/ubs/{id}` | Buscar UBS por ID |
| PUT | `/api/ubs/{id}` | Atualizar UBS |

### Medicamentos

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/medicamentos` | Cadastrar medicamento |
| GET | `/api/medicamentos` | Listar medicamentos |
| GET | `/api/medicamentos/{id}` | Buscar medicamento por ID |
| PUT | `/api/medicamentos/{id}` | Atualizar medicamento |

### Estoque

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/estoques` | Cadastrar estoque |
| GET | `/api/estoques/ubs/{ubsId}` | Listar estoque de uma UBS |
| POST | `/api/estoques/{id}/entrada` | Registrar entrada |
| POST | `/api/estoques/{id}/saida` | Registrar saída |

### Disponibilidade

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/disponibilidade/medicamentos/{medicamentoId}` | Consultar disponibilidade de um medicamento nas UBS |

## 8. Exemplo de consulta de disponibilidade

**Requisição:**

```http
GET http://localhost:8081/api/disponibilidade/medicamentos/1
```

**Exemplo de resposta:**

```json
[
  {
    "estoqueId": 1,
    "ubsId": 1,
    "nomeUbs": "UBS Central de Santos",
    "medicamentoId": 1,
    "lote": "DIP2026001",
    "quantidade": 150
  },
  {
    "estoqueId": 2,
    "ubsId": 2,
    "nomeUbs": "UBS Zona Noroeste",
    "medicamentoId": 1,
    "lote": "DIP2026002",
    "quantidade": 80
  }
]
```

Quando o medicamento existe, mas não há estoque disponível, a API retorna uma lista vazia:

```json
[]
```

Quando o medicamento não existe, a API retorna `404 Not Found`.

## 9. Testes automatizados

Para executar os testes no Windows:

```powershell
.\mvnw.cmd test
```

Resultado registrado em 27/09/2026, após a revisão do tratamento de erros HTTP:

```text
Tests run: 17, Failures: 0, Errors: 0, Skipped: 0

BUILD SUCCESS
```

Os testes cobrem comportamentos da aplicação e regras de negócio. Os endpoints também foram exercitados manualmente no Postman durante o desenvolvimento.

## 10. Limites do MVP e próximos passos

Esta versão é uma demonstração funcional do backend. Ela não consulta estoques reais do SUS e não possui interface web ou aplicativo para o cidadão.

Possíveis evoluções futuras incluem integração autorizada com sistemas de saúde, autenticação, controle de acesso por unidade e uma interface de consulta para o público.

Essas evoluções não fazem parte do escopo implementado neste MVP.

## 11. Contexto acadêmico

Projeto desenvolvido para o Hackathon da Fase 5 da FIAP, com foco na aplicação prática de desenvolvimento backend para uma proposta de melhoria do acesso à informação sobre medicamentos do SUS.