# Relatório de Desenvolvimento do Sistema Cafeteria

## 1. Introdução

O presente relatório detalha o projeto de desenvolvimento do **Sistema Cafeteria**, uma aplicação web desenhada para modernizar e otimizar a gestão de vendas, estoque e relacionamento com o cliente em estabelecimentos de pequeno e médio porte do setor de alimentação.

No cenário atual, a agilidade no atendimento e o controle rigoroso de insumos são fatores críticos de sucesso. O principal problema que o Sistema Cafeteria visa resolver é a **ineficiência operacional** causada por processos manuais ou desconectados, como:
*   **Gestão de Estoque Ineficaz:** Falta de visibilidade em tempo real sobre a disponibilidade de ingredientes, resultando em desperdício ou na incapacidade de atender pedidos (ruptura de estoque).
*   **Atendimento Lento:** Registro de pedidos propenso a erros e demorado, impactando a satisfação do cliente.
*   **Falta de Fidelização:** Ausência de um mecanismo estruturado para reter clientes e incentivar a recorrência.

O Sistema Cafeteria transforma a operação do negócio, fornecendo uma plataforma integrada que garante:
*   **Controle de Insumos:** Permite que o Estoquista registre a entrada e saída de ingredientes, garantindo o preparo contínuo dos itens do cardápio (RF.002).
*   **Agilidade no Ponto de Venda (PDV):** O Atendente pode registrar pedidos rapidamente, minimizando filas e erros (RF.003).
*   **Inteligência de Negócio:** O Dono da cafeteria acessa relatórios estratégicos para análise de desempenho (RF.005).

O objetivo geral é desenvolver e implementar um sistema web robusto e intuitivo para a gestão completa de uma cafeteria, com os seguintes objetivos específicos:

| Código | Objetivo Específico |
| :--- | :--- |
| RF.001 | Manter o cardápio de produtos sempre atualizado e acessível. |
| RF.002 | Garantir a disponibilidade de insumos através do controle de estoque. |
| RF.003 | Reduzir o tempo de registro de pedidos para agilizar o atendimento. |
| RF.004 | Implementar um programa de fidelidade para aumentar a retenção de clientes. |
| RF.005 | Fornecer relatórios de vendas para subsidiar a tomada de decisão estratégica. |

---

## 2. Desenvolvimento

### 2.1 Processo de Software

O desenvolvimento do Sistema Cafeteria adota uma **metodologia Ágil**, focada em entregas incrementais e adaptabilidade. Essa abordagem é ideal para projetos de pequeno e médio porte, permitindo que as funcionalidades mais críticas (como o registro de pedidos e o controle de estoque) sejam implementadas e testadas rapidamente.

#### 2.1.1 Análise de Requisitos

A análise de requisitos foi baseada nas necessidades dos principais usuários do sistema: o Gerente, o Estoquista, o Atendente e o Cliente. Os requisitos funcionais (RFs) mapeados no diretório `docs/requisitos` do projeto são:

| Código | Descrição (História de Usuário) | Ator Principal |
| :--- | :--- | :--- |
| RF.001 | Cadastrar e atualizar produtos do cardápio para manter o menu atualizado. | Gerente |
| RF.002 | Registrar a entrada e saída de ingredientes e produtos para garantir insumos. | Estoquista |
| RF.003 | Registrar os pedidos dos clientes rapidamente para agilizar o atendimento. | Atendente |
| RF.004 | Acumular pontos a cada compra para ganhar recompensas ou descontos. | Cliente |
| RF.005 | Acessar relatórios diários, semanais e mensais de vendas para análise de desempenho. | Dono da Cafeteria |

**Requisitos Não-Funcionais (RNFs) Inferidos:**

| Código | Descrição |
| :--- | :--- |
| RNF.001 | **Usabilidade:** A interface de registro de pedidos (PDV) deve ser intuitiva e otimizada para telas *touchscreen* ou *mobile*. |
| RNF.002 | **Segurança:** O sistema deve exigir autenticação de usuário (login) para acesso às funcionalidades de gestão e vendas. |
| RNF.003 | **Desempenho:** O registro de um pedido deve ser concluído em menos de 3 segundos, mesmo em horários de pico. |
| RNF.004 | **Manutenibilidade:** O código deve ser modular e seguir padrões de projeto para facilitar futuras expansões e correções. |

### 2.1.2 Projeto: Arquitetura (MVC)

O Sistema Cafeteria está estruturado no padrão de arquitetura **Model-View-Controller (MVC)**, implementado com o *framework* **Spring Boot** (Java). A escolha do MVC garante a separação de responsabilidades, o que é fundamental para a manutenibilidade (RNF.004) e o desenvolvimento colaborativo.

| Componente | Responsabilidade no Sistema Cafeteria | Exemplo de Implementação (Pacote/Classe) |
| :--- | :--- | :--- |
| **Model (Modelo)** | Representa os dados e a lógica de negócio. Gerencia a persistência e as regras de negócio (ex.: cálculo de estoque, pontuação de fidelidade). | `br.edu.ifrn.cafeteria.persistencia.modelo` (Classes: `Produto`, `Estoque`, `Venda`, `Usuario`) |
| **View (Visão)** | Camada de apresentação. Responsável por renderizar a interface e capturar a interação do usuário. | `src/main/resources/templates` (Arquivos: `index.html`, `produtos/cardapio.html`) |
| **Controller (Controlador)** | Atua como intermediário. Recebe requisições da View, chama a lógica do Model e seleciona a View correta para exibir o resultado. | `br.edu.ifrn.cafeteria.web.controladores` (Classes: `ProdutoControler`, `VendasControler`, `LoginController`) |

A separação clara entre os pacotes `modelo`, `repositorio` (acesso a dados) e `controladores` demonstra a aderência estrita ao padrão MVC, o que facilita a implementação de testes unitários e de integração.

---

## 3. Ferramentas e Tecnologias

O projeto Cafeteria utiliza um conjunto de ferramentas padrão da indústria para garantir a qualidade, o controle de versão e a automação do ciclo de *build*.

### 3.1 Git e GitHub

O **Git** é utilizado como o Sistema de Controle de Versão Distribuído, permitindo o rastreamento de todas as modificações no código. O **GitHub** serve como a plataforma de hospedagem do repositório remoto, facilitando a colaboração, a revisão de código (*Pull Requests*) e a gestão de *issues*.

| Ferramenta | Papel no Projeto Cafeteria |
| :--- | :--- |
| **Git** | Manter o histórico completo e imutável do código-fonte. |
| **GitHub** | Repositório central para sincronização e colaboração da equipe. |

### 3.2 Maven

O **Maven** é o gerenciador de *build* e dependências do projeto. Ele é configurado através do arquivo `app/pom.xml` e é essencial para:
1.  **Gerenciamento de Dependências:** Baixar e gerenciar bibliotecas de terceiros (como Spring Boot, Thymeleaf, etc.) de forma padronizada.
2.  **Ciclo de Build:** Automatizar processos como compilação (`mvn compile`), execução de testes (`mvn test`) e empacotamento da aplicação (`mvn package`).

A utilização do Maven garante que o projeto possa ser construído e executado de forma idêntica em qualquer ambiente de desenvolvimento, promovendo a consistência e a portabilidade.

### 3.3 Tecnologias Core

O projeto é construído sobre as seguintes tecnologias principais:

| Tecnologia | Função |
| :--- | :--- |
| **Java** | Linguagem de programação principal. |
| **Spring Boot** | *Framework* para desenvolvimento rápido de aplicações web e APIs. |
| **Thymeleaf** | Motor de *templates* utilizado na camada View para renderizar as páginas HTML. |
| **SQL** | Utilizado para persistência de dados (arquivos `.sql` em `docs/`). |

---

## 4. Conclusão

O Sistema Cafeteria é um projeto bem estruturado que segue as melhores práticas de engenharia de software, utilizando o padrão MVC e ferramentas robustas como Git/GitHub e Maven. A análise de requisitos demonstra um foco claro nas necessidades do negócio, abrangendo desde a gestão de insumos (RF.002) até a inteligência de vendas (RF.005) e a fidelização de clientes (RF.004). A arquitetura modular em Java/Spring Boot garante que o sistema seja escalável, seguro e de fácil manutenção, pronto para atender às demandas de uma cafeteria moderna.
