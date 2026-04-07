# 👾 MEU_BACKLOG.exe | Game Tracker
**Projeto Final - Desenvolvimento Android Moderno com Kotlin**

![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=android&logoColor=white)
![Room Database](https://img.shields.io/badge/Room_Database-00599C?style=for-the-badge&logo=sqlite&logoColor=white)

---

## 👤 Informações do Aluno
* **Nome do Aluno:** Pedro Paulo
* **Data de Entrega:** Abril de 2026

---

## 💻 Sobre o Projeto
Este aplicativo foi desenvolvido como requisito final para o módulo de desenvolvimento Android. Trata-se de um **Rastreador de Backlog de Jogos**, um sistema robusto de catalogação pessoal construído 100% com o ecossistema Android moderno.

### 🎯 Justificativa da Escolha do Tema
A escolha do tema une a necessidade técnica de implementar um CRUD completo com uma aplicação de alto valor prático. Ter lidado ativamente com a organização, manutenção e venda de centenas de mídias físicas e consoles cria uma percepção clara da importância de um catálogo bem estruturado. O aplicativo digitaliza essa necessidade, permitindo gerenciar títulos, organizar a biblioteca por plataforma e manter um registro visual do que precisa ser jogado, servindo como uma excelente prova de conceito para armazenamento local e listas reativas.

---

## ⚙️ Descrição do Funcionamento e Features

O aplicativo foi projetado com uma estética imersiva (Dark/Cyberpunk) e conta com um fluxo de navegação fluido utilizando **Jetpack Navigation Compose**:

* **[ 01 ] Tela Inicial (HUB):** Apresenta uma lista reativa (`LazyColumn`) de todos os jogos cadastrados, lidos em tempo real do banco de dados Room. Cada card exibe a arte do jogo, título e plataforma, além de atalhos rápidos para edição e exclusão.
* **[ 02 ] Módulo de Cadastro/Edição:** Um formulário dinâmico que reaproveita a mesma tela para inserir novos dados ou dar *override* (editar) em registros existentes. 
* **[ 03 ] Anexo de Mídia:** Integração com a galeria nativa do dispositivo via `ActivityResultContracts`, permitindo anexar imagens reais (capas) aos registros usando a biblioteca **Coil**.

---

## 🛠️ Requisitos Técnicos Implementados

O projeto atende e expande os requisitos obrigatórios da avaliação:

| Camada | Tecnologia Utilizada | Descrição |
| :--- | :--- | :--- |
| **Interface (UI)** | `Jetpack Compose` | UI 100% declarativa, utilizando o Material Design 3 customizado com uma paleta de cores temática (Neon/Dark). |
| **Navegação** | `Navigation Compose` | Roteamento seguro com passagem de argumentos (IDs e Strings) entre as telas. |
| **Persistência** | `Room Database` | Banco de dados local contendo a entidade `Game`, utilizando Coroutines para operações assíncronas de Insert, Update e Delete. |
| **Arquitetura** | `MVVM` | Separação rigorosa de responsabilidades. Os dados transitam do Banco para o Repositório, que alimenta o `GameViewModel`, expondo estados via `StateFlow`. |
| **Imagens** | `Coil` | Carregamento e cache de imagens assíncrono diretamente das URIs da galeria do aparelho. |

---

## 🚀 Como Executar o Projeto

Este projeto foi estruturado utilizando Kotlin DSL (`build.gradle.kts`). Para compilar e testar via linha de comando (sem a necessidade do Android Studio pesado):

1. Clone este repositório.
2. Abra a pasta raiz em seu terminal ou VS Code.
3. Conecte um emulador ou dispositivo físico via Depuração USB.
4. Execute o comando de compilação e instalação:
   ```bash
   .\gradlew.bat installDebug
