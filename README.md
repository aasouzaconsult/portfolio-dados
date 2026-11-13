<div align="center">

<a href="https://omestredosdados.com.br/portfolio">
  <img src="assets/extraindo-valor-em-dados.png" alt="Extraindo valor em dados" width="100%">
</a>

# Alex Souza
### Liderança em Dados · Professor · Mentor

**Conecto dados, IA e aprendizado a problemas de negócio.**

Minha atuação reúne liderança em dados e ensino. Aqui você encontra uma seleção de aplicações e estudos que mostram como transformar uma pergunta em análise, modelo ou solução.

<a href="https://omestredosdados.com.br/portfolio"><img src="https://img.shields.io/badge/PORTF%C3%93LIO_COMPLETO-0F766E?style=for-the-badge" alt="Acessar portfólio completo"></a>
<a href="https://www.linkedin.com/in/alex-souza/"><img src="https://img.shields.io/badge/VAMOS_CONVERSAR-1D4ED8?style=for-the-badge" alt="Conversar pelo LinkedIn"></a>
<a href="https://t.me/mestredosdados"><img src="https://img.shields.io/badge/COMUNIDADE_TELEGRAM-334155?style=for-the-badge" alt="Participar da comunidade no Telegram"></a>

[Projetos selecionados](#projetos-selecionados) · [Dados e BI](#dados-e-bi) · [Machine Learning](#machine-learning-e-nlp) · [Ensino](#ensinar-também-é-construir) · [Contato](#vamos-conversar)

</div>

---

## Da necessidade à aplicação

| **Entender** | **Construir** | **Compartilhar** |
| --- | --- | --- |
| Definir a pergunta, o público e a decisão que precisa de apoio. | Conectar dados, modelos e ferramentas em uma solução que possa ser explorada. | Explicar escolhas e tornar o conhecimento acessível a quem vai usar ou aprender. |

Os projetos abaixo são **demonstrações didáticas e experimentais**, com dados públicos ou cenários fictícios. As imagens são capturas das próprias aplicações; os links levam aos materiais e ao código disponível.

<a id="-projetos-em-destaque"></a>

## Projetos selecionados

### IA aplicada a documentos e processos

<table>
<tr>
<td width="50%" valign="top">
<p><strong>01 / CONHECIMENTO</strong></p>
<h3>Converse com seus documentos</h3>
<a href="IA%20Generativa/RAG%20%2B%20Langchain/README.md">
<img src="IA%20Generativa/RAG%20%2B%20Langchain/imagens/RAG.jpeg" alt="Aplicação RAG com envio de PDFs, pergunta, resposta e referência ao documento consultado" width="100%">
</a>
<p>Transformar PDFs em uma base consultável por linguagem natural, com recuperação de trechos e apresentação das fontes.</p>
<p><strong>O que demonstra</strong><br>Um fluxo que conecta documentos, embeddings, busca semântica e geração de respostas.</p>
<p><sub>Python · LangChain · FAISS · Streamlit</sub></p>
<p><a href="IA%20Generativa/RAG%20%2B%20Langchain/README.md"><strong>Explorar o projeto →</strong></a> · <a href="IA%20Generativa/RAG%20%2B%20Langchain/RAG-PDF.py">Ver código</a></p>
</td>
<td width="50%" valign="top">
<p><strong>02 / GESTÃO</strong></p>
<h3>CRM educacional com IA</h3>
<a href="IA%20Generativa/sistema_crm/README.md">
<img src="IA%20Generativa/sistema_crm/imagens/Dashboarddeleads.jpeg" alt="Dashboard do CRM educacional com distribuição dos leads pelas etapas do funil" width="100%">
</a>
<p>Acompanhar leads de uma empresa fictícia de sistemas escolares e integrar resumos gerados por IA à comunicação da equipe.</p>
<p><strong>O que demonstra</strong><br>Uma aplicação que reúne gestão de leads, funil, dashboard e integração com Gemini.</p>
<p><sub>Python · Flask · PostgreSQL · Google Gemini</sub></p>
<p><a href="IA%20Generativa/sistema_crm/README.md"><strong>Explorar o projeto →</strong></a> · <a href="IA%20Generativa/sistema_crm/app.py">Ver código</a></p>
</td>
</tr>
</table>

### 03 / Atendimento com agentes — Zouza Motors

<table>
<tr>
<td width="48%" valign="top">
<a href="IA%20Generativa/Projeto_multiagente.md">
<img src="IA%20Generativa/imagens/img1.jpeg" alt="Agente da Zouza Motors apresenta um veículo e conduz a conversa para um test drive" width="100%">
</a>
</td>
<td width="52%" valign="top">
<h3>Uma conversa, diferentes especialidades.</h3>
<p>Uma concessionária fictícia recebe perguntas sobre veículos, vendas e manutenção. O estudo organiza esse atendimento em agentes especializados, conectados a ferramentas e dados.</p>
<p><strong>O que demonstra</strong><br>Transferência entre agentes, consultas a PostgreSQL e simulações de agendamento na jornada do cliente.</p>
<p><sub>Python · FastMCP · Streamlit · PostgreSQL</sub></p>
<p><a href="IA%20Generativa/Projeto_multiagente.md"><strong>Ver arquitetura e percurso da demonstração →</strong></a></p>
</td>
</tr>
</table>

**Mais uma porta de entrada para IA aplicada:** [integração Flask + Gemini](Integra%C3%A7%C3%A3o%20Flask%20Python%20%2B%20Gemini/readme.md), com interface web e histórico de conversa.

## Dados e BI

### 04 / Vendas — da conversa com o negócio ao dashboard

Um projeto exemplo para acompanhar a construção de uma análise: entender a necessidade, estruturar a base, explorar os dados e organizar o consumo.

| **Problema** | **Construção** | **Material para explorar** |
| --- | --- | --- |
| Entender vendas e desempenho em uma empresa fictícia. | Banco relacional, análise com Python, modelo dimensional, ETL e visualização. | Roteiro por etapas e arquivo Power BI. |

**Percurso do estudo**

```text
Necessidade do negócio → Base de dados → Exploração
                      → Data Warehouse → Dashboard → Discussão da entrega
```

[**Conhecer o projeto →**](Projeto%20de%20An%C3%A1lise%20de%20Dados%20-%20Exemplo/readme.md) · [Abrir o arquivo Power BI](Projeto%20de%20An%C3%A1lise%20de%20Dados%20-%20Exemplo/portfolio.pbix)

### Histórias com dados públicos

| Estudo | Pergunta que orienta a exploração | Acesso |
| --- | --- | --- |
| **Censo Escolar 2020** | Como explorar escolas, alunos e diferenças entre regiões? | [Dashboard Power BI](https://app.powerbi.com/view?r=eyJrIjoiODJmNGY3YzctNGRhOC00MWMwLWI0YWQtNDE2NWViMzc0ZmNhIiwidCI6Ijc5YmY2Y2FlLWMwZWEtNGMzOS04ODgzLWVkNzIzY2Y1NTQ5MiJ9) · [Contexto](https://www.linkedin.com/pulse/censo-escolar-2020-alex-souza-msc/) |
| **Monitoramento do coronavírus** | Como comunicar a evolução histórica da pandemia em diferentes recortes geográficos? | [Dashboard Power BI](https://app.powerbi.com/view?r=eyJrIjoiYmY1ZGFkNWItYTExOC00MTVhLThhMWMtMDg2ZTQ0OTc1ZWMwIiwidCI6Ijc5YmY2Y2FlLWMwZWEtNGMzOS04ODgzLWVkNzIzY2Y1NTQ5MiJ9) · [Artigo](https://blogdozouza.wordpress.com/2020/02/04/monitoramento-do-coronavirus-usando-powerbi/) |

**Para continuar:** [Lakehouse com Databricks e Delta Lake](https://github.com/aasouzaconsult/lakehouse) · [Guias e cases de Business Intelligence](https://github.com/aasouzaconsult/business_intelligence)

## Machine Learning e NLP

Três estudos para explorar preparação de dados, comparação de abordagens e construção de aplicações.

| **Previsão de imóveis** | **Análise de crédito** | **Classificação de notícias** |
| --- | --- | --- |
| Estimar valores a partir dos atributos dos imóveis e comparar modelos de regressão. | Explorar dados de empréstimos e desenvolver um classificador. | Investigar representações de texto e classificadores para notícias curtas em português. |
| Python · Regressão · Streamlit | Python · Random Forest · Flask | BoW · TF-IDF · Word2Vec · FastText |
| [**Notebook →**](Previs%C3%A3o%20Valores%20de%20Im%C3%B3veis/PrevisaoValores_Regressao.ipynb) · [Aplicação](Previs%C3%A3o%20Valores%20de%20Im%C3%B3veis/Deploy/app.py) | [**Notebook →**](Analise%20de%20Cr%C3%A9dito%20-%20Empr%C3%A9stimo/An%C3%A1lise%20de%20Cr%C3%A9dito.ipynb) · [Projeto](Analise%20de%20Cr%C3%A9dito%20-%20Empr%C3%A9stimo/) | [**Notebook →**](NLP%20-%20Classifica%C3%A7%C3%A3o%20de%20Not%C3%ADcias%20Curtas%20PTB/ClassificacaoNoticiasCurtas_Z6NewsBrasil.ipynb) · [Artigo](https://medium.com/blog-do-zouza/classifica%C3%A7%C3%A3o-de-not%C3%ADcias-usando-aprendizado-de-m%C3%A1quina-aeeb9b6efe61) |

<a id="-demais-projetos"></a>

<details>
<summary><strong>📂 Explorar outros estudos da minha trajetória</strong></summary>

<br>

| Estudo | O que explorar | Acesso |
| --- | --- | --- |
| **Covid-19 — Einstein Data4u** | Exploração, balanceamento de classes e classificadores. | [Kaggle](https://www.kaggle.com/aasouzaconsult/prevendo-covid-19-no-brasil) · [Dados](https://www.kaggle.com/einsteindata4u/covid19) |
| **K-Dijkstra4Doc** | Agrupamento de documentos por grafos com a base 20 Newsgroups. | [Notebook](NLP%20-%20Classifica%C3%A7%C3%A3o%20de%20Not%C3%ADcias%20Curtas%20PTB/Grafos%20-%20K-Dijkstra4Doc.ipynb) |
| **Cluster de escolas — Censo 2019** | Agrupamento de escolas com K-Means. | [Notebook](Cluster%20de%20Escolas%20-%20Censo%202019/Censo2019.ipynb) · [Projeto e dados](Cluster%20de%20Escolas%20-%20Censo%202019/) |
| **Base de conhecimento em TI** | Sistema em Java para erros conhecidos e dúvidas frequentes. | [Código e documentação](BaseConhecimento/) |
| **Produção de milho** | Visualização com dados agrícolas do IBGE. | [Tableau](https://public.tableau.com/profile/alex.souza#!/vizhome/EstudoProduo-Milho/ProduoMilho-2016) |
| **Enem 2015** | Exploração do desempenho no exame. | [Tableau](https://public.tableau.com/profile/alex.souza#!/vizhome/Enem2015/Enem2015_) |
| **Enem 2014** | Relação entre prova objetiva e redação. | [Tableau](https://public.tableau.com/profile/alex.souza#!/vizhome/RankingEnem2014-GrficodeTendncias/RankingEnem2014) |

</details>

## Ensinar também é construir

Além das aplicações, compartilho roteiros, bases e explicações para que outras pessoas possam praticar e compreender o caminho.

| **Fundamentos** | **Prática** | **Troca** |
| --- | --- | --- |
| [SQL para profissionais de dados](https://github.com/aasouzaconsult/banco-de-dados-para-analistas-e-cientistas-de-dados) | [Bases, notebooks e materiais auxiliares](https://github.com/aasouzaconsult/auxiliares) | [Comunidade Mestre dos Dados no Telegram](https://t.me/mestredosdados) |
| [Formação Data Analytics](https://github.com/aasouzaconsult/DC_Data-Analytics) | [Aulas e demonstrações no YouTube](https://www.youtube.com/@alexsouzamsc) | [Artigos e reflexões no blog](https://medium.com/blog-do-zouza) |

<a id="-sobre-mim"></a>

## Vamos conversar

Atuo em **liderança em dados**, sou **professor e mentor** e compartilho conteúdo como **O Mestre dos Dados**. Minha trajetória conecta BI, bancos de dados, Machine Learning e aplicações de IA Generativa.

Tenho interesse em conversas sobre **dados e negócio, aplicação de IA e desenvolvimento de pessoas**.

[**Fale comigo pelo LinkedIn →**](https://www.linkedin.com/in/alex-souza/) · [**Conheça meu portfólio no site →**](https://omestredosdados.com.br/portfolio)

---

<details>
<summary><strong>Orientações para explorar e executar os estudos</strong></summary>

<a id="como-explorar-os-projetos"></a>

- As capturas mostram execuções das aplicações; elas não indicam um serviço público hospedado.
- Cada projeto tem suas próprias dependências, fontes e configurações. Consulte a documentação antes de executar.
- APIs e bancos de dados exigem configuração do ambiente e, quando aplicável, credenciais próprias.
- Período dos dados, hipóteses e limitações fazem parte da interpretação dos resultados.

</details>

<div align="center">

**O Mestre dos Dados**

[Site](https://omestredosdados.com.br) · [Portfólio](https://omestredosdados.com.br/portfolio) · [LinkedIn](https://www.linkedin.com/in/alex-souza/) · [Telegram](https://t.me/mestredosdados) · [YouTube](https://www.youtube.com/@alexsouzamsc)

[Instagram](https://www.instagram.com/alexsouzamsc/) · [Blog](https://medium.com/blog-do-zouza) · [Kaggle](https://www.kaggle.com/aasouzaconsult/notebooks) · [Lattes](http://lattes.cnpq.br/3318149614519892) · [X](https://x.com/alexsouza_sql)

</div>
