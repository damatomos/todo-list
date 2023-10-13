# todo-list (curso de java #rocketseat)
Curso de java para criação de um Gerenciador de Tarefas com spring e spring boot oferecido pela Rocketseat

## Day 01
- Configuração de ambiente
- Criação de projeto spring com [Spring Initializr](https://start.spring.io)
- Criação do Controller de User
- Criação do Model de User

## Day 02
- Configuração do Project Lombok para auto generate de Getters and Setters
- Configuração do Spring Data JPA para uso do ORM Hibernate integrado ao Spring
- Configuração do MySQL Connector Java para uso do MySQL Database (Author)
- Configuração da entidade User com uso de UUID e auto generate
- Configuração de Interface do Repository com JPA
- Validação e criação de usuários no banco de dados.

## Day 03
- Reparo na biblioteca do MySQL (Author)
- Criptografia da senha do usuário com BCrypt
- Criação da entidade Task
- Criação do Repository da entidade Task
- Criação do Controller da entidade Task
- Criação de um middleware através das classes Filter do spring
- Validação do usuário na filtragem

## Day 04
- Mudança na criação de tasks a partir da autenticação do usuário
- Listagem de Tasks
- Atualização de Tasks
- Utils para mesclagem de objectos

## Day 05
- Validação na atualização de tasks, garantindo que o usuário dono seja o único com essa funcionalidade.
- Atualização do código enquanto roda a aplicação através do spring-boot-devtools
- Modelos de execução da aplicação (Através do VSCode e do CLI do maven)
- Criação do Dockerfile para Deploy
- Deploy no render.com

##--- Running the project---
```
  mvn spring-boot:run
```