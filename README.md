# API Naruto

Esta é uma aplicação Spring Boot desenvolvida para consumir APIs externas relacionadas ao universo de Naruto. A aplicação permite buscar informações sobre personagens, realizar operações básicas de CRUD e integrar com um banco de dados MySQL para armazenar dados locais.

## ✨ Funcionalidades

- 🔍 **Buscar Personagem por Nome**: Endpoint para buscar personagens por seus nomes.
- 💾 **Salvar Personagem**: Endpoint para salvar personagens encontrados na API externa no banco de dados local.
- 📜 **Listar Todos os Personagens**: Endpoint para listar todos os personagens salvos no banco de dados local.
- 🗑️ **Excluir Personagem por ID**: Endpoint para excluir um personagem do banco de dados local.

## 🛠️ Tecnologias Utilizadas

- **Java** com **Spring Boot**
- **JPA** para persistência de dados
- **RestTemplate** para consumo de APIs externas
- **SLF4J** para logs

## 🗂️ Estrutura do Projeto

- `ApiNarutoApplication.java`: Classe principal para inicializar a aplicação Spring Boot.
- `NarutoModel.java`: Modelo representando um personagem, com informações como nome, imagens, família e jutsus.
- `NarutoService.java`: Classe de serviço responsável por buscar dados de personagens da API externa.
- `NarutoRepository.java`: Repositório JPA para manipulação dos dados no banco de dados.
- `NarutoController.java`: Controlador REST com os endpoints para interação com os personagens.

## 🔗 Endpoints

### 🔍 Buscar Personagem por Nome
- **URL**: `/ninjas/characters`
- **Método**: `GET`
- **Parâmetro de Consulta**: `name` (nome do personagem)
- **Descrição**: Busca um personagem por nome, consultando uma API externa.

### 💾 Salvar Personagem
- **URL**: `/ninjas`
- **Método**: `POST`
- **Corpo da Requisição**: JSON com dados básicos do personagem.
- **Descrição**: Salva um personagem no banco de dados, com base no primeiro nome encontrado na API externa.

### 📜 Listar Todos os Personagens
- **URL**: `/ninjas`
- **Método**: `GET`
- **Descrição**: Retorna uma lista com todos os personagens salvos no banco de dados.

### 🗑️ Deletar Personagem por ID
- **URL**: `/ninjas/characters/{id}`
- **Método**: `DELETE`
- **Parâmetro de URL**: `id` (ID do personagem)
- **Descrição**: Deleta um personagem específico com base no ID fornecido.

## 📝 Logs e Tratamento de Erros

A classe `NarutoService` utiliza o **SLF4J** para registrar logs detalhados de cada requisição e resposta, incluindo erros. Caso ocorra uma falha durante a chamada de API externa, uma exceção personalizada `ApiException` é lançada.


