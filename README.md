# 🤝 Extensão em Ação — Gestão de Voluntários

Desenvolvido como parte da avaliação da disciplina de **Programação Orientada a Objetos 2**, do curso de **Engenharia de Software da UTFPR — Campus Cornélio Procópio**, o projeto **Extensão em Ação** é um sistema web criado para apoiar o gerenciamento de voluntários e atividades de extensão.

A aplicação foi desenvolvida em **Java com Spring Boot**, utilizando arquitetura em camadas, persistência em banco de dados relacional e interfaces web responsivas. O sistema permite que coordenadores organizem voluntários, atividades, participações e cargas horárias, enquanto os voluntários acompanham seu próprio progresso.

---

## 🎯 Objetivos

- Centralizar o cadastro de voluntários e atividades de extensão.
- Registrar participações, presença e horas realizadas.
- Facilitar o acompanhamento da carga horária de cada voluntário.
- Disponibilizar um painel com indicadores gerais do projeto.
- Permitir que cada voluntário consulte suas atividades e seu progresso.
- Indicar automaticamente quando o participante atinge a carga horária necessária para certificação.

---

## 🛠️ Ferramentas e tecnologias utilizadas

- **Java 17** — linguagem principal do projeto.
- **Spring Boot 3.3.5** — configuração e execução da aplicação.
- **Spring MVC** — tratamento das requisições e organização dos controladores.
- **Thymeleaf** — renderização das páginas HTML.
- **Spring Data JPA** — persistência e acesso aos dados.
- **H2 Database** — banco de dados relacional persistido em arquivo.
- **Bean Validation** — validação dos dados dos formulários.
- **HTML e CSS** — construção e estilização das interfaces.
- **Maven** — gerenciamento das dependências.
- **Visual Studio Code** — ambiente utilizado no desenvolvimento.
- **Git e GitHub** — versionamento e disponibilização do código-fonte.
- **Microsoft Word** — elaboração da documentação acadêmica.

---

## 📌 Metodologia

O desenvolvimento foi realizado de forma incremental. Inicialmente, foram definidos o problema, os usuários, os requisitos e as regras de negócio. Em seguida, foram elaborados os diagramas UML e o modelo do banco de dados.

A implementação foi organizada em camadas, separando entidades, repositórios, serviços, controladores e interfaces. Após a criação da estrutura principal, foram implementadas as funcionalidades de autenticação, cadastros, participações, relatórios e acompanhamento da carga horária.

Por fim, o sistema foi executado localmente e suas principais funcionalidades foram verificadas por meio de testes manuais.

---

## 🧩 Construção do sistema

### ✅ Requisitos funcionais

| Identificador | Descrição | Prioridade | Implementado |
|---|---|---:|:---:|
| RF01 | O sistema deve permitir a autenticação por e-mail e senha. | Alta | ✅ |
| RF02 | O sistema deve identificar o perfil do usuário autenticado. | Alta | ✅ |
| RF03 | O coordenador deve visualizar um painel com indicadores do projeto. | Média | ✅ |
| RF04 | O coordenador deve realizar o CRUD de voluntários. | Alta | ✅ |
| RF05 | O coordenador deve realizar o CRUD de atividades. | Alta | ✅ |
| RF06 | O coordenador deve registrar participações em atividades. | Alta | ✅ |
| RF07 | O sistema deve impedir a duplicidade de participação do mesmo voluntário na mesma atividade. | Alta | ✅ |
| RF08 | O coordenador deve registrar presença e horas realizadas. | Alta | ✅ |
| RF09 | O sistema deve calcular a carga horária acumulada dos voluntários. | Alta | ✅ |
| RF10 | O sistema deve gerar um relatório de carga horária. | Média | ✅ |
| RF11 | O sistema deve indicar a elegibilidade para certificado. | Média | ✅ |
| RF12 | O voluntário deve consultar suas atividades, horas e progresso. | Alta | ✅ |

### ⚙️ Requisitos não funcionais

| Identificador | Descrição | Tipo | Implementado |
|---|---|---|:---:|
| RNF01 | O sistema deve ser implementado em Java 17. | Implementação | ✅ |
| RNF02 | A aplicação deve seguir uma arquitetura em camadas baseada em MVC. | Arquitetura | ✅ |
| RNF03 | Os dados devem ser armazenados em banco relacional persistente. | Armazenamento | ✅ |
| RNF04 | As senhas não devem ser armazenadas em texto puro. | Segurança | ✅ |
| RNF05 | Os formulários devem validar campos obrigatórios e dados inválidos. | Confiabilidade | ✅ |
| RNF06 | A interface deve ser compreensível e adaptável a diferentes telas. | Usabilidade | ✅ |
| RNF07 | O código-fonte deve ser disponibilizado em um repositório Git. | Manutenção | ✅ |
| RNF08 | O sistema deve restringir o acesso conforme o perfil autenticado. | Segurança | ✅ |

---

## 👥 Perfis do sistema

### Coordenador

O perfil de coordenador possui acesso às funções administrativas:

- dashboard com indicadores gerais;
- gerenciamento de voluntários;
- gerenciamento de atividades;
- registro e edição de participações;
- controle de presença e horas;
- consulta de relatórios;
- verificação de elegibilidade para certificado.

### Voluntário

O perfil de voluntário possui acesso individual:

- visualização das atividades realizadas;
- consulta da carga horária acumulada;
- acompanhamento do progresso;
- verificação da situação para certificação.

---

## 🏗️ Arquitetura e boas práticas

### MVC e separação em camadas

O projeto utiliza uma organização inspirada no padrão **Model-View-Controller**, complementada pelas camadas de serviço e repositório.

```text
src/main/java/br/edu/extensao/voluntarios
├── config          # configuração web e dados iniciais
├── controller      # tratamento das requisições
├── dto             # objetos utilizados nos formulários
├── exception       # exceções e regras de negócio
├── interceptor     # autenticação e autorização
├── model           # entidades do domínio
├── repository      # acesso ao banco de dados
├── service         # regras de negócio
└── util            # utilitários, como proteção de senhas

src/main/resources
├── static/css      # estilos da aplicação
├── templates       # páginas Thymeleaf
└── application.properties

---

## 👩‍💻 Autoria
Aluna: Letícia Bento Pinto
