# Extensão em Ação - Gestão de Voluntários

Sistema web desenvolvido em Java para auxiliar no gerenciamento de voluntários e atividades de extensão universitária.

O projeto permite o cadastro de voluntários, atividades e participações, além do registro de presença, controle de carga horária, consulta de relatórios e acompanhamento do progresso dos voluntários para certificação.

## Resumo

O **Extensão em Ação - Gestão de Voluntários** é uma aplicação web desenvolvida com Java e Spring Boot, criada para centralizar e organizar as informações relacionadas a projetos de extensão universitária.

A solução permite que coordenadores realizem o gerenciamento de voluntários, atividades e participações, enquanto os voluntários podem acompanhar suas atividades, horas realizadas e progresso. O sistema utiliza arquitetura MVC, persistência com banco de dados H2 e boas práticas de organização em camadas.

## Sobre o projeto

Projetos de extensão aproximam a universidade da comunidade, permitindo que estudantes, professores e voluntários participem de ações sociais, educacionais e culturais.

Com o aumento da quantidade de participantes e atividades, o controle manual por planilhas, anotações ou documentos separados pode dificultar a organização das informações. Pensando nisso, foi desenvolvido este sistema web para facilitar o gerenciamento dos dados e tornar o acompanhamento das atividades mais simples e centralizado.

Este projeto foi desenvolvido como trabalho acadêmico da disciplina de **Programação Orientada a Objetos 2**, do curso de **Engenharia de Software** da **Universidade Tecnológica Federal do Paraná - Campus Cornélio Procópio**.

## Objetivo geral

Desenvolver um sistema web capaz de auxiliar no gerenciamento de voluntários e atividades de extensão universitária.

## Objetivos específicos

- Permitir o cadastro de voluntários.
- Permitir o cadastro de atividades de extensão.
- Registrar a participação de voluntários em atividades.
- Registrar presença, horas realizadas e observações.
- Calcular a carga horária acumulada dos voluntários.
- Gerar relatórios de carga horária.
- Indicar a aptidão do voluntário para certificação.
- Permitir que voluntários acompanhem suas atividades e progresso.
- Organizar o projeto utilizando arquitetura MVC e boas práticas de programação.

## Usuários do sistema

O sistema possui dois perfis principais de usuários:

### Coordenador

Usuário responsável pelo gerenciamento do sistema. Pode cadastrar, editar, consultar e excluir voluntários e atividades, além de registrar participações, presença e horas realizadas.

### Voluntário

Usuário que participa das atividades de extensão. Pode consultar suas atividades, horas acumuladas e progresso para certificação.

## Requisitos funcionais

| Identificador | Descrição | Prioridade |
|---|---|---|
| RF01 | Permitir a autenticação por e-mail e senha. | Alta |
| RF02 | Identificar o perfil e direcionar o usuário à área correspondente. | Alta |
| RF03 | Apresentar ao coordenador um dashboard com indicadores do projeto. | Média |
| RF04 | Permitir cadastrar, consultar, editar e excluir voluntários. | Alta |
| RF05 | Permitir cadastrar, consultar, editar e excluir atividades. | Alta |
| RF06 | Permitir registrar a participação de um voluntário em uma atividade. | Alta |
| RF07 | Impedir participação duplicada do mesmo voluntário na mesma atividade. | Alta |
| RF08 | Permitir registrar presença, horas realizadas e observações. | Alta |
| RF09 | Calcular a carga horária acumulada de cada voluntário. | Alta |
| RF10 | Gerar relatório de carga horária e indicar aptidão para certificado. | Média |
| RF11 | Permitir ao voluntário consultar suas atividades, horas e progresso. | Alta |
| RF12 | Permitir encerrar a sessão do usuário. | Média |

## Requisitos não funcionais

| Identificador | Descrição | Tipo |
|---|---|---|
| RNF01 | O sistema deve possuir uma interface simples e intuitiva. | Facilidade de uso |
| RNF02 | O sistema deve garantir a integridade dos dados. | Integridade |
| RNF03 | O sistema deve permitir acesso apenas a pessoas autorizadas. | Segurança |
| RNF04 | O sistema deve funcionar em diferentes sistemas operacionais. | Portabilidade |
| RNF05 | O sistema deve possuir possibilidade de integração com outras APIs. | Interoperabilidade |

## Funcionalidades

### Funcionalidades do coordenador

- Realizar login no sistema.
- Visualizar o dashboard principal.
- Cadastrar voluntários.
- Consultar voluntários cadastrados.
- Editar dados de voluntários.
- Excluir voluntários.
- Cadastrar atividades.
- Consultar atividades cadastradas.
- Editar atividades.
- Excluir atividades.
- Registrar a participação de voluntários em atividades.
- Registrar presença.
- Informar horas realizadas.
- Adicionar observações.
- Consultar relatórios.
- Verificar aptidão para certificado.
- Encerrar sessão.

### Funcionalidades do voluntário

- Realizar login no sistema.
- Consultar suas atividades.
- Visualizar horas acumuladas.
- Acompanhar o progresso para certificação.
- Encerrar sessão.

## Tecnologias utilizadas

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- HTML
- CSS
- Maven
- Banco de dados H2
- Bean Validation
- Git
- GitHub
- Visual Studio Code

## Arquitetura do projeto

O sistema foi desenvolvido seguindo o padrão arquitetural **MVC**, separando as responsabilidades entre dados, regras de negócio e interfaces.

A estrutura principal do projeto está organizada da seguinte forma:

```text
src/main/java/br/edu/extensao/voluntarios
├── config
├── controller
├── dto
├── exception
├── interceptor
├── model
├── repository
├── service
└── util

src/main/resources
├── static
├── templates
└── application.properties
```

## Organização das camadas

### Model

Contém as entidades principais do sistema, representando os dados que serão armazenados no banco.

Exemplos:

- Coordenador
- Voluntário
- Atividade
- Participação

### Controller

Responsável por receber as requisições feitas pelo usuário, controlar o fluxo das páginas e direcionar as ações para a camada de serviço.

### Service

Contém as regras de negócio do sistema, como validações, controle de carga horária, registro de participação e verificação de progresso.

### Repository

Responsável pela comunicação com o banco de dados, utilizando Spring Data JPA.

### Templates

Contém as páginas HTML renderizadas com Thymeleaf.

### Static

Contém arquivos estáticos, como CSS e outros recursos visuais.

## Entidades principais

### Coordenador

Representa o usuário responsável pela administração do sistema.

Principais responsabilidades:

- Gerenciar voluntários.
- Gerenciar atividades.
- Registrar participações.
- Consultar relatórios.

### Voluntário

Representa o participante das atividades de extensão.

Principais responsabilidades:

- Consultar atividades.
- Verificar horas acumuladas.
- Acompanhar progresso para certificado.

### Atividade

Representa uma atividade de extensão cadastrada no sistema.

Pode conter informações como:

- Nome da atividade.
- Descrição.
- Data.
- Carga horária.
- Status.

### Participação

Representa o vínculo entre um voluntário e uma atividade.

Pode conter informações como:

- Voluntário participante.
- Atividade relacionada.
- Presença.
- Horas realizadas.
- Observações.

## Banco de dados

O sistema utiliza o banco de dados relacional **H2**, utilizado no projeto para desenvolvimento e testes.

As principais tabelas do sistema são:

- `COORDENADORES`
- `VOLUNTARIOS`
- `ATIVIDADES`
- `PARTICIPACOES`

O acesso ao banco é realizado por meio do **Spring Data JPA**, permitindo a persistência das entidades do sistema.

## Acesso ao console H2

Durante a execução da aplicação, o console do H2 pode ser acessado pelo navegador em:

```text
http://localhost:8080/h2-console
```

As configurações do banco ficam no arquivo:

```text
src/main/resources/application.properties
```

## Boas práticas utilizadas

Durante o desenvolvimento do sistema, foram aplicadas boas práticas para melhorar a organização, segurança e manutenção do código.

### Arquitetura MVC

O projeto foi estruturado com base no padrão MVC, separando responsabilidades entre:

- Interface com o usuário.
- Controle das requisições.
- Regras de negócio.
- Persistência de dados.

Essa separação facilita a manutenção do código e permite que novas funcionalidades sejam adicionadas com mais organização.

### Separação em camadas

O código foi organizado em pacotes específicos, como `model`, `controller`, `service` e `repository`.

Essa divisão evita que todo o código fique concentrado em uma única classe e torna o sistema mais fácil de entender, modificar e testar.

### Proteção das senhas

As senhas dos usuários não são armazenadas diretamente em texto comum no banco de dados.

Antes de serem salvas, elas passam por um processo de geração de hash utilizando o algoritmo **PBKDF2**, com salt aleatório e múltiplas iterações. Isso aumenta a segurança do sistema e reduz riscos caso os dados sejam acessados indevidamente.

### Validação de dados

O sistema utiliza **Bean Validation** para validar informações inseridas nos formulários, contribuindo para a integridade dos dados cadastrados.

## Diagramas UML

O projeto utiliza diagramas UML para representar a estrutura e o funcionamento do sistema.

### Diagrama de casos de uso

Representa as funcionalidades disponíveis para cada ator do sistema, como coordenador e voluntário.

### Diagrama de classes

Representa as principais classes do sistema, seus atributos, métodos e relacionamentos.

### Diagrama de atividades

Representa o fluxo principal de funcionamento do sistema, desde a autenticação até o acesso às funcionalidades conforme o perfil do usuário.

## Especificação dos casos de uso

### Autenticar usuário

**Ator:** Coordenador e Voluntário  
**Descrição:** Permite que o usuário acesse o sistema utilizando e-mail e senha.

### Gerenciar voluntários

**Ator:** Coordenador  
**Descrição:** Permite cadastrar, consultar, editar e excluir voluntários.

### Gerenciar atividades

**Ator:** Coordenador  
**Descrição:** Permite cadastrar, consultar, editar e excluir atividades de extensão.

### Registrar participação

**Ator:** Coordenador  
**Descrição:** Permite vincular um voluntário a uma atividade.

### Registrar presença e horas

**Ator:** Coordenador  
**Descrição:** Permite registrar presença, horas realizadas e observações sobre a participação.

### Gerar relatório

**Ator:** Coordenador  
**Descrição:** Permite consultar a carga horária dos voluntários e verificar a aptidão para certificado.

### Consultar progresso

**Ator:** Voluntário  
**Descrição:** Permite ao voluntário consultar suas atividades, horas acumuladas e progresso.

### Encerrar sessão

**Ator:** Coordenador e Voluntário  
**Descrição:** Permite que o usuário saia do sistema.

## Como executar o projeto

### Pré-requisitos

Antes de executar o sistema, é necessário ter instalado:

- Java 17 ou superior
- Maven
- Git
- Visual Studio Code ou outra IDE de preferência

### Passo 1: clonar o repositório

```bash
git clone https://github.com/lettiebento/gerenciamento-voluntarios.git
```

### Passo 2: acessar a pasta do projeto

```bash
cd gerenciamento-voluntarios
```

### Passo 3: executar o projeto

```bash
mvn spring-boot:run
```

### Passo 4: acessar no navegador

```text
http://localhost:8080
```

## Estrutura esperada do sistema

O sistema possui páginas para autenticação, área do coordenador, gerenciamento de voluntários, gerenciamento de atividades, registro de participações e área do voluntário.

Entre as principais telas estão:

- Tela de login.
- Painel principal do coordenador.
- Tela de gerenciamento de voluntários.
- Tela de gerenciamento de atividades.
- Tela de registro de participação.
- Tela de relatórios.
- Área do voluntário.

## Resultados obtidos

Como resultado, foi desenvolvido um sistema funcional para apoiar o gerenciamento de voluntários e atividades de extensão.

A aplicação permite:

- Organização dos dados dos voluntários.
- Cadastro e controle das atividades.
- Registro de participação.
- Controle de presença.
- Cálculo de horas realizadas.
- Acompanhamento do progresso dos voluntários.
- Separação de acesso entre coordenador e voluntário.

## Dificuldades encontradas

Durante o desenvolvimento, algumas dificuldades foram encontradas, principalmente relacionadas a:

- Configuração do Spring Boot.
- Organização das camadas do projeto.
- Conexão com o banco de dados.
- Relacionamento entre entidades.
- Implementação das regras de participação e carga horária.

Essas dificuldades contribuíram para o aprendizado prático sobre desenvolvimento web com Java, banco de dados e programação orientada a objetos.

## Melhorias futuras

Algumas melhorias que podem ser implementadas futuramente são:

- Recuperação de senha.
- Exportação de relatórios em PDF.
- Envio automático de certificados.
- Implantação do sistema em servidor online.
- Utilização de banco de dados próprio para produção.
- Implementação de mais testes automatizados.
- Melhorias na interface do usuário.
- Integração com outras APIs.
- Envio de notificações por e-mail.
- Filtros mais avançados nos relatórios.

## Aprendizados

O projeto contribuiu para o aprendizado de:

- Programação orientada a objetos.
- Desenvolvimento de aplicações web com Java.
- Uso do Spring Boot.
- Organização em arquitetura MVC.
- Persistência de dados com JPA.
- Uso de banco de dados relacional.
- Criação de diagramas UML.
- Versionamento com Git e GitHub.
- Aplicação de boas práticas de programação.

## Autora

Desenvolvido por **Letícia Bento Pinto**.

Projeto acadêmico desenvolvido para a disciplina de **Programação Orientada a Objetos 2** da **Universidade Tecnológica Federal do Paraná - Campus Cornélio Procópio**.

## Licença

Este projeto foi desenvolvido para fins acadêmicos.
