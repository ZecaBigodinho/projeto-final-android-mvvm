# Projeto Final - Desenvolvimento Android Moderno com Kotlin

## Informações do Aluno
* **Nome do Aluno:** Pedro Paulo
* **Matrícula:** [INSERIR SUA MATRÍCULA AQUI]
* **Data de Entrega:** [INSERIR A DATA AQUI]

## Sobre o Projeto
Este aplicativo foi desenvolvido como requisito final para o módulo de desenvolvimento Android. Trata-se de um **Rastreador de Backlog de Jogos (Game Backlog Tracker)**. 

### Justificativa da Escolha do Tema
A escolha do tema une a necessidade técnica de um CRUD básico com uma aplicação útil para o dia a dia. Gerenciar títulos para jogar, organizar a biblioteca por plataforma e ter um histórico visual facilita a administração do tempo livre, servindo como uma excelente prova de conceito para armazenamento local e listas reativas.

### Descrição do Funcionamento do Aplicativo
O aplicativo conta com duas telas principais interligadas pelo Jetpack Navigation Compose:
1. **Tela Inicial (Game List):** Apresenta uma lista reativa (`LazyColumn`) de todos os jogos cadastrados lidos do banco de dados Room. Possui um botão flutuante (FAB) que dispara a navegação.
2. **Tela de Cadastro (Add Game):** Um formulário simples onde o usuário insere o "Título" e a "Plataforma" do jogo. Ao clicar em salvar, a camada ViewModel é acionada, os dados são persistidos via Room de forma assíncrona usando Coroutines, e a tela retorna automaticamente à lista inicial, que se atualiza dinamicamente usando `StateFlow`.

### Requisitos Técnicos Implementados
* **Interface:** Construída inteiramente utilizando **Jetpack Compose** e componentes do Material Design 3.
* **Navegação:** Implementada com `androidx.navigation:navigation-compose`.
* **Persistência:** Feita com **Room Database**, contendo a entidade `Game` e o DAO correspondente.
* **Arquitetura:** Padrão **MVVM** seguido rigorosamente. Os dados transitam do Banco (Room) para o Repositório, que alimenta o `GameViewModel`, expondo estados reativos (`StateFlow`) para a UI (Compose).