# 🏥 Voll.med - API REST

Sistema de agendamento de consultas médicas desenvolvido com Spring Boot, oferecendo uma API REST completa para gerenciamento de médicos, pacientes e consultas.

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Pré-requisitos](#pré-requisitos)
- [Instalação e Configuração](#instalação-e-configuração)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Endpoints da API](#endpoints-da-api)
- [Autenticação e Segurança](#autenticação-e-segurança)
- [Documentação da API](#documentação-da-api)
- [Validações de Negócio](#validações-de-negócio)
- [Melhorias Recentes](#melhorias-recentes)
- [Contribuição](#contribuição)

## 🎯 Sobre o Projeto

O Voll.med é uma API REST desenvolvida para gerenciar consultas médicas, permitindo o cadastro de médicos e pacientes, agendamento de consultas com validações de negócio e sistema de autenticação JWT.

### Principais Características

- ✅ **Agendamento Inteligente**: Escolha automática de médico por especialidade
- ✅ **Validações de Negócio**: Horários de funcionamento, antecedência mínima, etc.
- ✅ **Autenticação JWT**: Sistema seguro de autenticação
- ✅ **Documentação Swagger**: API documentada automaticamente
- ✅ **Migração de Banco**: Flyway para controle de versão do banco
- ✅ **Validações Bean Validation**: Validação de dados de entrada
- ✅ **Tratamento de Erros**: Tratamento centralizado de exceções
- ✅ **Soft Delete**: Exclusão lógica com possibilidade de reativação

## 🚀 Funcionalidades

### Gestão de Médicos
- Cadastro de médicos com especialidades
- Listagem com paginação
- Atualização de dados
- Exclusão lógica (soft delete)
- **Reativação de médicos inativos**
- Busca por especialidade
- **Listagem de médicos inativos**
- **Detalhamento individual de médico**

### Gestão de Pacientes
- Cadastro de pacientes
- Listagem com paginação
- Atualização de dados
- Exclusão lógica
- **Detalhamento individual de paciente**

### Agendamento de Consultas
- Agendamento com médico específico ou escolha automática
- Validações de disponibilidade
- Verificação de conflitos de horário
- **Resposta enriquecida** com dados detalhados (nome do médico, paciente e especialidade)
- **Listagem de consultas com paginação**
- **Cancelamento de consultas por data/hora**

### Autenticação e Autorização
- Registro de usuários
- Login com JWT
- Controle de acesso por roles
- **Validação de usuários duplicados**

## 🛠 Tecnologias Utilizadas

- **Java 21** - Linguagem de programação
- **Spring Boot 3.5.0** - Framework principal
- **Spring Security** - Segurança e autenticação
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL** - Banco de dados
- **Flyway** - Migração de banco de dados
- **JWT (Auth0)** - Autenticação stateless
- **Lombok** - Redução de boilerplate
- **SpringDoc OpenAPI** - Documentação da API
- **Bean Validation** - Validação de dados
- **Maven** - Gerenciamento de dependências

## 📋 Pré-requisitos

- Java 21 ou superior
- Maven 3.6+
- PostgreSQL 12+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## ⚙️ Instalação e Configuração

### 1. Clone o repositório
```bash
git clone <url-do-repositorio>
cd SpringBootAPIRestVall.med
```

### 2. Configure o banco de dados
Crie um banco PostgreSQL e configure as variáveis de ambiente no arquivo `.env`:

```env
DB_URL=jdbc:postgresql://localhost:5432/vollmed_api
DB_USER=seu_usuario
DB_PASSWORD=sua_senha
```

### 3. Execute a aplicação
```bash
# Windows PowerShell
mvn spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📁 Estrutura do Projeto

```
src/main/java/med/voll/api/
├── ApiApplication.java                 # Classe principal
├── controller/                         # Controllers REST
│   ├── AutenticacaoController.java
│   ├── ConsultaController.java
│   ├── MedicoController.java
│   └── pacienteController.java
├── domain/                            # Domínio da aplicação
│   ├── consulta/                      # Entidades e lógica de consulta
│   │   ├── AgendamentoDeConsulta.java
│   │   ├── Consulta.java
│   │   ├── ConsultaRepository.java
│   │   └── validacoes/               # Validações de negócio
│   ├── medico/                        # Entidades e lógica de médico
│   ├── paciente/                      # Entidades e lógica de paciente
│   └── usuario/                       # Entidades de usuário
├── infra/                            # Infraestrutura
│   ├── exception/                     # Tratamento de exceções
│   ├── security/                      # Configurações de segurança
│   └── springdoc/                     # Configuração do Swagger
└── resources/
    └── application.properties         # Configurações da aplicação
```

## 🔌 Endpoints da API

### Autenticação
- `POST /login` - Login e geração de token
- `POST /login/register` - Registro de usuário

### Médicos
- `POST /medicos` - Cadastrar médico
- `GET /medicos` - Listar médicos ativos (com paginação)
- `GET /medicos/{id}` - Detalhar médico específico
- `GET /medicos/inativos` - Listar médicos inativos
- `PUT /medicos` - Atualizar médico
- `DELETE /medicos/{id}` - Excluir médico (soft delete)
- `POST /medicos/ativar/{id}` - Reativar médico

### Pacientes
- `POST /paciente` - Cadastrar paciente
- `GET /paciente` - Listar pacientes ativos (com paginação)
- `GET /paciente/{id}` - Detalhar paciente específico
- `PUT /paciente` - Atualizar paciente
- `DELETE /paciente/{id}` - Excluir paciente (soft delete)

### Consultas
- `POST /consulta` - Agendar consulta
- `GET /consulta` - Listar consultas (com paginação)
- `DELETE /consulta/{dataHora}` - Cancelar consulta

## 🔐 Autenticação e Segurança

### JWT Token
A API utiliza JWT (JSON Web Token) para autenticação. Para acessar endpoints protegidos:

1. Faça login em `/login`
2. Use o token retornado no header: `Authorization: Bearer <token>`

### Roles
- **ADMIN**: Acesso completo ao sistema
- **USER**: Acesso limitado a funcionalidades básicas

## 📚 Documentação da API

A documentação interativa está disponível através do Swagger UI:

- **URL**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

## ✅ Validações de Negócio

### Agendamento de Consultas
- ✅ Horário de funcionamento da clínica (7h às 19h)
- ✅ Antecedência mínima de 30 minutos
- ✅ Médico deve estar ativo
- ✅ Paciente deve estar ativo
- ✅ Sem conflitos de horário para médico
- ✅ Paciente sem outra consulta no mesmo dia
- ✅ Especialidade obrigatória quando médico não especificado

### Cadastro de Médicos
- ✅ Email único
- ✅ CRM único
- ✅ Especialidade válida

### Cadastro de Pacientes
- ✅ Email único
- ✅ CPF único

### Autenticação
- ✅ Login único por usuário
- ✅ Senha criptografada com BCrypt

## 🆕 Melhorias Recentes

### Correções Técnicas
- ✅ **Correção da Query JPQL**: Resolvido problema na consulta `findDisponiveis` do `MedicoRepository`
- ✅ **Mapeamento de Entidades**: Corrigido mapeamento das entidades `medicos` e `Consulta` nas queries

### Novas Funcionalidades
- ✅ **Listagem de Consultas**: Endpoint para listar todas as consultas com paginação
- ✅ **Reativação de Médicos**: Possibilidade de reativar médicos previamente excluídos
- ✅ **Listagem de Médicos Inativos**: Endpoint específico para médicos inativos
- ✅ **Detalhamento Individual**: Endpoints para obter detalhes completos de médicos e pacientes
- ✅ **Resposta Enriquecida**: Agendamento retorna nome do médico, paciente e especialidade

### Melhorias na Experiência
- ✅ **Tratamento de Erros Centralizado**: Tratamento uniforme de exceções com mensagens claras
- ✅ **Validação de Usuários Duplicados**: Prevenção de registros duplicados no sistema
- ✅ **Documentação Swagger Melhorada**: Configuração personalizada com informações de contato

## 🎯 Exemplo de Uso

### Agendamento de Consulta

**Request:**
```json
POST /consulta
{
  "idPaciente": 1,
  "especialidade": "CARDIOLOGIA",
  "dataHora": "2024-01-15T14:30:00"
}
```

**Response:**
```json
{
  "id": 1,
  "nomeMedico": "Dr. João Silva",
  "especialidade": "CARDIOLOGIA",
  "nomePaciente": "Maria Santos",
  "datahora": "2024-01-15T14:30:00"
}
```

### Reativação de Médico

**Request:**
```bash
POST /medicos/ativar/1
```

**Response:**
```json
{
  "id": 1,
  "nome": "Dr. João Silva",
  "crm": "12345",
  "email": "joao.silva@email.com",
  "telefone": "(11) 99999-9999",
  "especialidade": "CARDIOLOGIA",
  "endereco": {
    "logradouro": "Rua das Flores",
    "bairro": "Centro",
    "cep": "12345678",
    "cidade": "São Paulo",
    "uf": "SP",
    "complemento": "Sala 101",
    "numero": "123"
  }
}
```

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

Desenvolvido como parte do curso de Spring Boot da Alura.

---

⭐ Se este projeto te ajudou, deixe uma estrela! 