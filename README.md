# Lavaceu API

[![Status de Desenvolvimento](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)](https://github.com/ericolivrib/lavaceu-api)

API para gerenciar o agendamento de uso da lavanderia da Casa do Estudante Universitário do Campus Sede da Universidade Federal de Santa Maria (UFSM). Este projeto foi desenvolvido para facilitar o gerenciamento de reservas e otimizar o uso dos recursos da lavanderia.

## 📋 Funcionalidades

- Cadastro e autenticação de usuários.
- Criação, visualização e exclusão de agendamentos.
- Validação de horários para evitar conflitos entre reservas.
- Exposição de dados via API REST para integração com o cliente front-end.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java
- **Framework:** Spring Boot
- **Banco de Dados:** H2
- **Gerenciamento de Dependências:** Maven

## 🚀 Configuração e Execução

### Pré-requisitos

- Java 21
- Maven 3.8 ou superior
- Banco de dados H2 configurado

### Passos para Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/ericolivrib/lavaceu-api.git
   cd lavaceu-api
   ```

3. Configure o banco de dados:
   - Crie um banco de dados no PostgreSQL.
   - Atualize o arquivo application.properties com as credenciais do banco.

4. Compile e execute a aplicação:
   ```
   mvn spring-boot:run
   ```

6. A API estará disponível em: http://localhost:8080/nutrigest/api

## 📚 Estrutura do Projeto

- **src/main/java:** Código fonte principal.
- **src/main/resources:** Arquivos de configuração e scripts SQL.
- **pom.xml:** Gerenciador de dependências Maven.

## 🧪 Testes

Para executar os testes unitários, use o comando:
```
mvn test
```

## 🌟 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

## 📝 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
