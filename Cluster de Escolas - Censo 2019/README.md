<div align="center">

# 🏫 Cluster de Escolas — Censo 2019
### Dados públicos · Análise exploratória · K-Means · Power BI

**Explorando perfis semelhantes de escolas a partir do Censo Escolar.**

Um estudo de aprendizagem não supervisionada que conecta preparação de dados, agrupamento e análise visual.

[Notebook](Censo2019.ipynb) · [Planilha de resultados](Censo2019.xlsx) · [Relatório exploratório](dataframe_report.html) · [Fonte dos dados](https://www.gov.br/inep/pt-br/acesso-a-informacao/dados-abertos/microdados/censo-escolar)

</div>

---

| **288.284 registros** | **3 variáveis no modelo** | **7 clusters** |
| --- | --- | --- |
| Dimensão da base carregada nas saídas do notebook. | Situação de funcionamento, dependência e localização. | Configuração adotada no experimento. |

## A pergunta do projeto

**Quais escolas apresentam perfis semelhantes nas características selecionadas?**

O projeto utiliza dados do **Censo Escolar 2019**, disponibilizados pelo INEP, para explorar agrupamentos de registros de escolas com **K-Means**. O percurso vai da análise inicial de uma base com 234 colunas à exportação dos grupos para exploração no Excel e no Power BI.

Os perfis deste estudo descrevem características administrativas e de localização. **Não constituem um ranking de qualidade educacional**, uma medida de desempenho dos estudantes ou uma avaliação das escolas.

## Caminho da análise

```mermaid
flowchart LR
    A["INEP<br/>Censo Escolar 2019"] --> B["ESCOLAS.CSV<br/>Leitura com pandas"]
    B --> C["Análise exploratória<br/>Tipos, ausentes e profiling"]
    C --> D["Seleção<br/>3 variáveis"]
    D --> E["Método do cotovelo<br/>k de 1 a 10"]
    E --> F["K-Means<br/>7 grupos"]
    F --> G["Análise dos grupos<br/>Centroides e estatísticas"]
    G --> H["Censo2019.xlsx<br/>Consulta e visualização"]
    H -.-> I["Power BI<br/>Exploração visual"]
```

A etapa de Power BI faz parte da proposta de análise visual do estudo. **Esta pasta não contém um arquivo PBIX**; o artefato tabular disponível para exploração é a planilha Excel.

## Dados e recorte

| Item | Descrição |
| --- | --- |
| **Fonte** | Microdados do Censo Escolar — INEP. |
| **Referência temporal** | 2019. |
| **Entrada do notebook** | Arquivo `ESCOLAS.CSV`, lido com separador `\|`. |
| **Dimensão registrada** | 288.284 linhas e 234 colunas. |
| **Base para consulta** | 11 colunas de identificação, geografia e características das escolas, acrescidas do cluster na exportação. |
| **Entrada do modelo** | Apenas `TP_SITUACAO_FUNCIONAMENTO`, `TP_DEPENDENCIA` e `TP_LOCALIZACAO`. |

O notebook **não filtra apenas escolas em atividade**: inclui as situações de funcionamento presentes na base, inclusive registros de escolas paralisadas ou extintas. Por isso, o total carregado não deve ser apresentado como quantidade de escolas ativas.

Código, nome e códigos geográficos são mantidos em `df_full` para consulta, mas **não entram no ajuste do K-Means**.

### Variáveis utilizadas no agrupamento

| Variável | Significado | Códigos descritos no notebook |
| --- | --- | --- |
| **TP_SITUACAO_FUNCIONAMENTO** | Situação de funcionamento | 1 — Em atividade; 2 — Paralisada; 3 — Extinta no ano do Censo; 4 — Extinta em anos anteriores. |
| **TP_DEPENDENCIA** | Dependência administrativa | 1 — Federal; 2 — Estadual; 3 — Municipal; 4 — Privada. |
| **TP_LOCALIZACAO** | Localização | 1 — Urbana; 2 — Rural. |

Consulte o dicionário da edição de 2019 para interpretar os campos e suas regras de preenchimento.

## Método e decisões do experimento

1. **Exploração inicial:** dimensões, tipos, estatísticas e valores ausentes.
2. **Profiling:** geração de um relatório HTML com `pandas-profiling`.
3. **Seleção:** escolha das três variáveis acima; identificação e geografia ficam fora do modelo.
4. **Exploração de k:** cálculo da inércia para valores de 1 a 10 e gráfico do cotovelo.
5. **Agrupamento:** ajuste de `KMeans(n_clusters=7)`.
6. **Interpretação:** consulta dos rótulos, centroides e estatísticas por grupo.
7. **Exportação:** inclusão da coluna `clusters` e gravação em `Censo2019.xlsx`.

A escolha de sete grupos é uma decisão exploratória registrada pelo autor. O gráfico do cotovelo ajuda a comparar soluções, mas não comprova, sozinho, um número “ideal” de clusters.

## Resultados registrados

A tabela abaixo reúne os tamanhos dos grupos e os centroides **salvos no notebook**. A ordem dos componentes do centroide é:

```text
(situação de funcionamento, dependência administrativa, localização)
```

| Cluster | Registros | Centroide aproximado |
| --- | ---: | --- |
| **0** | 50.378 | (1,175; 4,000; 1,000) |
| **1** | 44.954 | (4,000; 2,910; 1,852) |
| **2** | 34.297 | (2,127; 2,958; 1,872) |
| **3** | 32.915 | (1,053; 1,978; 1,165) |
| **4** | 59.750 | (1,000; 3,000; 1,000) |
| **5** | 49.965 | (1,000; 3,013; 2,000) |
| **6** | 16.025 | (3,924; 4,000; 1,029) |
| **Total** | **288.284** | |

Como exemplo de leitura, o centroide do **grupo 4** corresponde aos códigos de escolas **em atividade, municipais e urbanas**. Já os valores fracionários de outros grupos representam médias de códigos; não são categorias reais.

**Há uma divergência no material original:** as descrições textuais da seção de conclusões não correspondem, para todos os rótulos, aos centroides exibidos. Antes de atribuir nomes aos grupos ou atualizar um painel, reexecute o notebook em ordem e confira as frequências das categorias em cada cluster.

Os números acima documentam as saídas existentes; **não foram recalculados nesta revisão**. Os identificadores 0 a 6 são rótulos arbitrários, sem ordem de qualidade ou prioridade, e podem mudar em outro ajuste.

## Como explorar e reproduzir

### 1. Comece pelos artefatos disponíveis

- Abra [Censo2019.ipynb](Censo2019.ipynb) para acompanhar o raciocínio e as saídas salvas.
- Baixe [Censo2019.xlsx](Censo2019.xlsx) para consultar a exportação disponível e explorar a coluna `clusters`.
- Baixe [dataframe_report.html](dataframe_report.html) e abra-o no navegador para consultar o relatório exploratório. O GitHub pode exibir apenas o código ou a opção de download do HTML.

### 2. Obtenha os dados de entrada

Acesse a [página oficial de microdados do Censo Escolar](https://www.gov.br/inep/pt-br/acesso-a-informacao/dados-abertos/microdados/censo-escolar), localize a edição de **2019** e consulte o pacote e sua documentação.

O arquivo `ESCOLAS.CSV` **não está incluído nesta pasta**. Disponibilize-o no diretório de trabalho do notebook ou ajuste o caminho de `pd.read_csv`. Confirme o nome, o separador e a codificação do arquivo obtido.

### 3. Prepare o ambiente Python

O notebook importa **NumPy, pandas, Matplotlib, Seaborn, Plotly e scikit-learn**, além de utilizar `pandas-profiling` para o relatório. A exportação para Excel também requer um mecanismo compatível, como `openpyxl`.

Não há arquivo de dependências com versões fixadas nesta pasta. As saídas registram bibliotecas antigas; preparar um ambiente atual pode exigir adaptações.

| Ponto do notebook | O que revisar |
| --- | --- |
| **Profiling** | Verificar a compatibilidade da biblioteca e de sua API antes de executar a instalação e a geração do relatório. |
| **Caminho do HTML** | Substituir o caminho absoluto do Google Drive por um caminho local, por exemplo `dataframe_report.html`. |
| **Seleção de df_full** | Usar uma cópia explícita, como `df[...].copy()`, antes de acrescentar a coluna de clusters. |
| **K-Means** | Definir `random_state` e `n_init` explicitamente para comparar execuções. |
| **Exportação Excel** | Conferir o mecanismo instalado e a necessidade de exportar o índice. |

### 4. Execute e confira os grupos

Execute as células desde o início. Verifique os dados carregados, os valores ausentes nas três entradas, o gráfico do cotovelo e a distribuição das categorias por grupo.

A última etapa grava `Censo2019.xlsx`. Preserve uma cópia do arquivo original se quiser comparar a nova execução com a exportação existente.

A reprodução completa do experimento não foi executada nesta revisão da documentação.

## Cuidados de interpretação

**Os códigos representam categorias.** No experimento, eles entram diretamente no K-Means, que usa distância euclidiana. Isso introduz distâncias e ordenações numéricas que podem não ter significado substantivo: “privada = 4” não significa quatro vezes “federal = 1”.

Esse ponto limita a interpretação dos grupos. Uma evolução do estudo pode comparar codificações e métodos próprios para dados categóricos, além de avaliar estabilidade e utilidade dos perfis.

Também é importante:

- **Interpretar os ausentes pelo dicionário:** ausência pode refletir regras de preenchimento ou campos não aplicáveis; uma taxa elevada, isoladamente, não comprova baixa qualidade.
- **Respeitar o recorte:** três atributos não descrevem infraestrutura, aprendizagem ou todas as condições de oferta.
- **Distinguir período histórico e situação atual:** os dados refletem a edição de 2019.
- **Validar os perfis:** cruzar clusters com frequências das categorias antes de atribuir descrições ou propor ações.

## Possibilidades de evolução

- Comparar diferentes valores de k e métricas de avaliação de agrupamento.
- Testar métodos adequados a categorias e documentar a escolha da distância.
- Separar análises de escolas ativas e demais situações de funcionamento.
- Ampliar as variáveis com base no dicionário e em uma pergunta analítica explícita.
- Construir um painel com tamanho dos grupos, composição categórica e filtros geográficos.
- Registrar versões, sementes e uma execução completa para facilitar a reprodução.

## Leituras e ferramentas

- [**INEP — Microdados do Censo Escolar**](https://www.gov.br/inep/pt-br/acesso-a-informacao/dados-abertos/microdados/censo-escolar) — fonte e documentação dos dados.
- [**scikit-learn — KMeans**](https://scikit-learn.org/stable/modules/generated/sklearn.cluster.KMeans.html) — parâmetros do algoritmo.
- [**scikit-learn — Agrupamento**](https://scikit-learn.org/stable/modules/clustering.html) — métodos e avaliação.
- [**Power BI — materiais de Alex Souza**](https://omestredosdados.com.br/power-bi.html) — apoio à exploração visual dos resultados.
- [**Visualização de dados**](https://omestredosdados.com.br/visualizacao-de-dados.html) — referências para comunicar a análise.

---

**Alex Souza — liderança em dados e professor**

[Portfólio](https://omestredosdados.com.br/portfolio) · [Telegram — Mestre dos Dados](https://t.me/mestredosdados) · [O Mestre dos Dados](https://omestredosdados.com.br/)

[← Voltar ao portfólio no GitHub](../README.md)
