![Extraindo valor em dados](assets/extraindo-valor-em-dados.png)

<img src="https://blogdozouza.wordpress.com/wp-content/uploads/2025/08/alexsouza.png" alt="Alex Souza — cientista de dados e professor" width="220" align="right">

# Alex Souza
## Dados, Machine Learning e IA Generativa

**Da pergunta de negócio à análise. Da análise à aplicação.**

Sou **cientista de dados e professor**. Neste portfólio, compartilho projetos que conectam análise de dados, desenvolvimento de modelos e construção de aplicações, com explicações para quem também quer aprender a fazer.

Minha trajetória passa por dashboards em **Power BI e Tableau**, modelos de **Machine Learning com Python** e aplicações de **IA Generativa**, incluindo consulta a documentos, agentes e integração com bancos de dados.

[LinkedIn](https://www.linkedin.com/in/alex-souza/) · [Blog técnico](https://medium.com/blog-do-zouza) · [YouTube](https://www.youtube.com/@alexsouzamsc) · [Kaggle](https://www.kaggle.com/aasouzaconsult/notebooks)

<br clear="all">

---

### Escolha por onde começar

| Seu interesse | Projeto sugerido |
| --- | --- |
| Entender como agentes usam ferramentas e dados de negócio | [Atendimento multiagente — Zouza Motors](IA%20Generativa/Projeto_multiagente.md) |
| Consultar documentos com IA Generativa | [RAG com LangChain](IA%20Generativa/RAG%20%2B%20Langchain/README.md) |
| Conectar IA a uma rotina de gestão | [CRM educacional com Gemini](IA%20Generativa/sistema_crm/README.md) |
| Explorar um projeto de Machine Learning | [Previsão de valores de imóveis](Previs%C3%A3o%20Valores%20de%20Im%C3%B3veis/PrevisaoValores_Regressao.ipynb) |
| Estudar o caminho entre banco de dados e dashboard | [Análise de vendas com Data Warehouse e Power BI](Projeto%20de%20An%C3%A1lise%20de%20Dados%20-%20Exemplo/readme.md) |

**Navegação:** [Projetos em destaque](#-projetos-em-destaque) · [Demais projetos](#-demais-projetos) · [Como explorar](#como-explorar-os-projetos) · [Sobre mim e contato](#-sobre-mim)

> Os projetos têm caráter acadêmico, didático ou experimental. As fontes incluem dados públicos e cenários fictícios, conforme indicado em cada estudo.

## 🌟 Projetos em Destaque

### 🤖 IA Generativa: documentos, agentes e processos

#### Atendimento multiagente — Zouza Motors

**Como organizar o atendimento de uma concessionária com agentes especializados?**

Estudo de uma concessionária fictícia com agentes de recepção, vendas e manutenção. O material apresenta consultas a veículos e clientes, agendamentos e transferência de atendimento entre agentes, com ferramentas conectadas a um banco PostgreSQL.

**Tecnologias:** Python · FastMCP · Streamlit · PostgreSQL  
**Explore:** [Arquitetura e passo a passo](IA%20Generativa/Projeto_multiagente.md)

#### Consulta a documentos PDF com RAG

**Como encontrar informações em documentos e usá-las para responder a perguntas?**

Pipeline de *Retrieval-Augmented Generation* que combina extração de PDFs, divisão de texto, embeddings, busca semântica e geração de respostas. O projeto reúne documentação, código e interface em Streamlit.

**Tecnologias:** Python · LangChain · FAISS · HuggingFace Embeddings · Streamlit  
**Explore:** [Documentação](IA%20Generativa/RAG%20%2B%20Langchain/README.md) · [Código da aplicação](IA%20Generativa/RAG%20%2B%20Langchain/RAG-PDF.py)

#### CRM educacional com IA — Zouza School

**Como dar visibilidade à evolução de leads e apoiar a comunicação da equipe?**

Aplicação para uma empresa fictícia de sistemas escolares, com gestão de leads, acompanhamento do funil e dashboard. A integração com Google Gemini acrescenta resumos e notificações por e-mail ao fluxo de trabalho.

**Tecnologias:** Python · Flask · SQLAlchemy · PostgreSQL · Google Gemini  
**Explore:** [Documentação e funcionalidades](IA%20Generativa/sistema_crm/README.md)

#### Integração Flask + Gemini

**Como criar uma aplicação de perguntas e respostas com um modelo de linguagem?**

Exemplo de integração entre Flask e a API Gemini, com interface web e histórico da conversa. Um ponto de partida para explorar a conexão entre uma aplicação Python e um serviço de IA Generativa.

**Tecnologias:** Python · Flask · Google Gemini  
**Explore:** [Passo a passo](Integra%C3%A7%C3%A3o%20Flask%20Python%20%2B%20Gemini/readme.md)

### 🧠 Machine Learning: previsão e classificação

| Projeto | Pergunta e abordagem | Explore |
| --- | --- | --- |
| **Previsão de valores de imóveis** | Como estimar o valor de um imóvel a partir de seus atributos? Análise exploratória, tratamento de dados e comparação de Regressão Linear, Decision Tree e Random Forest, com aplicação em Streamlit. | [Notebook](Previs%C3%A3o%20Valores%20de%20Im%C3%B3veis/PrevisaoValores_Regressao.ipynb) · [Aplicação](Previs%C3%A3o%20Valores%20de%20Im%C3%B3veis/Deploy/app.py) |
| **Análise de crédito — empréstimo** | Como explorar dados de empréstimos e construir um classificador? Estudo com análise exploratória, Random Forest e aplicação em Flask. | [Notebook](Analise%20de%20Cr%C3%A9dito%20-%20Empr%C3%A9stimo/An%C3%A1lise%20de%20Cr%C3%A9dito.ipynb) · [Projeto](Analise%20de%20Cr%C3%A9dito%20-%20Empr%C3%A9stimo/) |
| **Classificação de notícias curtas em português** | Como representar textos e classificá-los por tema? Experimentos com SVM, KNN, Random Forest e Decision Tree, além de BoW, TF-IDF, Word2Vec e FastText. | [Notebook](NLP%20-%20Classifica%C3%A7%C3%A3o%20de%20Not%C3%ADcias%20Curtas%20PTB/ClassificacaoNoticiasCurtas_Z6NewsBrasil.ipynb) · [Artigo](https://medium.com/blog-do-zouza/classifica%C3%A7%C3%A3o-de-not%C3%ADcias-usando-aprendizado-de-m%C3%A1quina-aeeb9b6efe61) |

### 📊 Do banco de dados ao dashboard

#### Análise de vendas com Data Warehouse e Power BI

**Como estruturar os dados de uma empresa de vendas para apoiar análises de desempenho?**

Projeto exemplo voltado a alunos e mentorados, com um cenário fictício para explorar banco relacional, modelagem dimensional, ETL e dashboards. O material apresenta uma sequência de trabalho para quem está começando em análise de dados.

**Tecnologias:** SQL · Python · Power BI  
**Explore:** [Roteiro do projeto](Projeto%20de%20An%C3%A1lise%20de%20Dados%20-%20Exemplo/readme.md) · [Arquivo Power BI](Projeto%20de%20An%C3%A1lise%20de%20Dados%20-%20Exemplo/portfolio.pbix)

## 📂 Demais Projetos

### Análise de dados, NLP e organização do conhecimento

| Projeto | O que você encontra | Acesso |
| --- | --- | --- |
| **Covid-19 — Einstein Data4u** | Desafio com dados do Hospital Albert Einstein: exploração, balanceamento de classes e aplicação de classificadores. | [Estudo no Kaggle](https://www.kaggle.com/aasouzaconsult/prevendo-covid-19-no-brasil) · [Dados](https://www.kaggle.com/einsteindata4u/covid19) |
| **K-Dijkstra4Doc** | Exploração de agrupamento de documentos por grafos direcionados com a base 20 Newsgroups. | [Notebook](NLP%20-%20Classifica%C3%A7%C3%A3o%20de%20Not%C3%ADcias%20Curtas%20PTB/Grafos%20-%20K-Dijkstra4Doc.ipynb) |
| **Cluster de escolas — Censo 2019** | Agrupamento de escolas com K-Means a partir de dados do Censo Escolar. | [Notebook](Cluster%20de%20Escolas%20-%20Censo%202019/Censo2019.ipynb) · [Projeto e dados](Cluster%20de%20Escolas%20-%20Censo%202019/) |
| **Sistema de base de conhecimento** | Mini sistema em Java para registro de erros conhecidos e dúvidas frequentes, no contexto de gestão do conhecimento em TI. | [Código e documentação](BaseConhecimento/) |

### Dashboards e histórias com dados

| Projeto | Tema e ferramenta | Acesso |
| --- | --- | --- |
| **Censo Escolar 2020** | Escolas, alunos e regiões com dados do INEP, em Power BI. | [Dashboard](https://app.powerbi.com/view?r=eyJrIjoiODJmNGY3YzctNGRhOC00MWMwLWI0YWQtNDE2NWViMzc0ZmNhIiwidCI6Ijc5YmY2Y2FlLWMwZWEtNGMzOS04ODgzLWVkNzIzY2Y1NTQ5MiJ9) · [Contexto da análise](https://www.linkedin.com/pulse/censo-escolar-2020-alex-souza-msc/) |
| **Monitoramento do coronavírus** | Evolução da pandemia no Ceará, no Brasil e no mundo, com storytelling em Power BI e dados da Johns Hopkins. | [Dashboard](https://app.powerbi.com/view?r=eyJrIjoiYmY1ZGFkNWItYTExOC00MTVhLThhMWMtMDg2ZTQ0OTc1ZWMwIiwidCI6Ijc5YmY2Y2FlLWMwZWEtNGMzOS04ODgzLWVkNzIzY2Y1NTQ5MiJ9) · [Artigo](https://blogdozouza.wordpress.com/2020/02/04/monitoramento-do-coronavirus-usando-powerbi/) |
| **Produção de milho** | Visualização de dados agrícolas do IBGE em Tableau. | [Dashboard](https://public.tableau.com/profile/alex.souza#!/vizhome/EstudoProduo-Milho/ProduoMilho-2016) |
| **Enem 2015** | Exploração do desempenho no exame em Tableau. | [Dashboard](https://public.tableau.com/profile/alex.souza#!/vizhome/Enem2015/Enem2015_) |
| **Enem 2014** | Gráfico de tendência relacionando prova objetiva e redação, em Tableau. | [Dashboard](https://public.tableau.com/profile/alex.souza#!/vizhome/RankingEnem2014-GrficodeTendncias/RankingEnem2014) |

## Como explorar os projetos

1. **Escolha uma pergunta ou tema.** Os projetos estão organizados por aplicação para facilitar a escolha.
2. **Leia a documentação do projeto.** Consulte o contexto, os dados utilizados e a abordagem.
3. **Explore o notebook ou o código.** Os notebooks podem ser lidos diretamente no GitHub; a execução exige as dependências e os arquivos de cada estudo.
4. **Interprete os resultados junto com as limitações.** Período dos dados, hipóteses e decisões de preparação fazem parte da análise.

As configurações de execução variam entre os projetos. Aplicações com APIs ou bancos de dados exigem a configuração do seu próprio ambiente.

## 👤 Sobre mim

Meu interesse está em conectar dados a problemas que as pessoas precisam resolver — e tornar esse caminho compreensível para quem está aprendendo.

Como cientista de dados e professor, compartilho aqui estudos que vão da exploração de uma base à construção de modelos e aplicações. A proposta é mostrar as escolhas, as ferramentas e as possibilidades de uso em cada contexto.

**Quer conversar sobre projetos de dados, aplicações de IA Generativa ou capacitação de equipes?** Entre em contato pelo [LinkedIn](https://www.linkedin.com/in/alex-souza/).

### Conteúdo e conexões

[LinkedIn](https://www.linkedin.com/in/alex-souza/) · [YouTube](https://www.youtube.com/@alexsouzamsc) · [Instagram](https://www.instagram.com/alexsouzamsc/) · [Blog técnico](https://medium.com/blog-do-zouza) · [Kaggle](https://www.kaggle.com/aasouzaconsult/notebooks) · [Currículo Lattes](http://lattes.cnpq.br/3318149614519892) · [X](https://x.com/alexsouza_sql)
