# Projeto para Portfólio de Analista de Dados (Exemplo)

## Projeto: Gerenciamento de Vendas com Data Warehouse e Dashboards

Este projeto foi desenvolvido para demonstrar as principais etapas do trabalho de um Analista de Dados, desde a criação de um banco de dados até a entrega de insights em forma de dashboards. O foco é em um cenário fictício de uma empresa de vendas. Abaixo estão as fases detalhadas do projeto.

---

## Fase 1: Criação do Banco de Dados

### Estrutura do Banco de Dados
Criamos um banco de dados relacional (utilizamos o SGBD: [PostgreSQL](https://www.postgresql.org/)) com as seguintes tabelas:
- **Cliente:** Contém informações dos clientes.
- **Vendedor:** Contém informações sobre os vendedores.
- **Produto:** Detalha os produtos disponíveis.
- **Venda:** Registra informações sobre cada venda realizada.
- **ItemVenda:** Relaciona os produtos vendidos em cada venda.

> **Nota:** Nesta simulação, criamos o banco de dados para fins didáticos e de demonstração. No cenário real, o Analista de Dados normalmente recebe um banco de dados já existente, podendo ser necessário realizar etapas de limpeza e transformação dos dados antes de começar a análise.

### Script SQL de Criação e População de Tabelas
```sql
-- Criando o Banco de dados
CREATE DATABASE portfolio;

-- Tabela Cliente
CREATE TABLE Cliente (
    ClienteID SERIAL PRIMARY KEY,
    Nome VARCHAR(100),
    CPF VARCHAR(11),
    Email VARCHAR(100),
    Cidade VARCHAR(100)
);

-- Tabela Vendedor
CREATE TABLE Vendedor (
    VendedorID SERIAL PRIMARY KEY,
    Nome VARCHAR(100),
    Email VARCHAR(100),
    MetaMensal NUMERIC
);

-- Tabela Produto
CREATE TABLE Produto (
    ProdutoID SERIAL PRIMARY KEY,
    Nome VARCHAR(100),
    Categoria VARCHAR(50),
    Preco NUMERIC
);

-- Tabela Venda
CREATE TABLE Venda (
    VendaID SERIAL PRIMARY KEY,
    ClienteID INT REFERENCES Cliente(ClienteID),
    VendedorID INT REFERENCES Vendedor(VendedorID),
    DataVenda DATE
);

-- Tabela ItemVenda
CREATE TABLE ItemVenda (
    ItemID SERIAL PRIMARY KEY,
    VendaID INT REFERENCES Venda(VendaID),
    ProdutoID INT REFERENCES Produto(ProdutoID),
    Quantidade INT,
    TotalItem NUMERIC
);

-- População de tabelas
INSERT INTO Cliente (Nome, CPF, Email, Cidade) VALUES
('João Silva', '12345678901', 'joao@gmail.com', 'Fortaleza'),
('Maria Oliveira', '23456789012', 'maria@gmail.com', 'São Paulo'),
('Carlos Santos', '34567890123', 'carlos@gmail.com', 'Rio de Janeiro'),
('Ana Clara', '45678901234', 'ana@gmail.com', 'Belo Horizonte'),
('Pedro Alves', '56789012345', 'pedro@gmail.com', 'Salvador');

INSERT INTO Vendedor (Nome, Email, MetaMensal) VALUES
('Carlos Souza', 'carlos@vendas.com', 50000),
('Ana Lima', 'ana@vendas.com', 70000),
('Marcos Paiva', 'marcos@vendas.com', 60000),
('Fernanda Melo', 'fernanda@vendas.com', 65000);

INSERT INTO Produto (Nome, Categoria, Preco) VALUES
('Notebook', 'Eletrônicos', 3500.00),
('Mouse', 'Acessórios', 50.00),
('Teclado', 'Acessórios', 150.00),
('Monitor', 'Eletrônicos', 1200.00),
('Impressora', 'Eletrônicos', 800.00);

-- Adicionando novas vendas (outros meses e anos)
INSERT INTO Venda (ClienteID, VendedorID, DataVenda) VALUES
(1, 1, '2024-12-20'),
(2, 2, '2024-12-25'),
(3, 3, '2024-11-15'),
(4, 4, '2024-11-20'),
(5, 1, '2024-10-10'),
(1, 2, '2025-01-16'),
(2, 3, '2025-02-05'),
(3, 4, '2025-02-12'),
(4, 1, '2025-03-01'),
(5, 2, '2025-03-15'),
(1, 3, '2026-01-10'),
(2, 4, '2026-01-20'),
(3, 1, '2026-02-14'),
(4, 2, '2026-02-25'),
(5, 3, '2026-03-10');

-- Adicionando novos itens de venda
INSERT INTO ItemVenda (VendaID, ProdutoID, Quantidade, TotalItem) VALUES
(1, 1, 2, 4000.00),
(1, 2, 1, 500.00),
(1, 3, 1, 3500.00),
(2, 4, 3, 7500.00),
(3, 5, 2, 3600.00),
(3, 1, 1, 3000.00),
(4, 2, 2, 3200.00),
(4, 3, 1, 2800.00),
(4, 4, 1, 4000.00),
(4, 5, 1, 4100.00),
(5, 1, 1, 4500.00),
(5, 2, 2, 4600.00),
(5, 3, 1, 3500.00),
(5, 4, 1, 3000.00),
(5, 5, 1, 3800.00);

```

### Desenho da Modelagem Relacional
<img src="https://blogdozouza.wordpress.com/wp-content/uploads/2025/01/screenshot_2.png" alt="Modelagem Relacional - PostgreSQL" width="650"/>

Agora que temos a nossa base de dados de estudo, na próxima fase irei simular uma entrevista com um Gerente de Vendas.

---

## Fase 2: Simulação de Entrevista com o Gerente de Vendas

### Contextualização
Nesta fase, simulamos uma reunião entre o Analista de Dados e o gerente de vendas para compreender as necessidades e desafios enfrentados pelo cliente. Essa etapa é fundamental para o sucesso do projeto, pois define o escopo e os requisitos do Data Warehouse e dos relatórios.

### Perguntas e Respostas
- **Quais problemas o cliente enfrenta?**
  - Dificuldade em monitorar o desempenho dos vendedores.
  - Falta de visibilidade sobre os produtos mais vendidos e análise geográfica.
- **Que tipos de insights são necessários?**
  - Receita total por período.
  - Comparação entre metas e desempenho dos vendedores.
  - Identificação dos produtos mais vendidos por categoria.
  - Análise de vendas por região.

Para antender os tipos de insights necessários, os sequintes **KPIs** (Key Performance Indicators) foram definidos para atender às necessidades do gerente de vendas:

1. **Receita Total por Período:**
   - Descrição: Soma do total de vendas em um período específico.
   - Fórmula: `SUM(ValorTotal)`.

2. **Meta vs. Desempenho dos Vendedores:**
   - Descrição: Comparação entre as vendas realizadas e a meta mensal de cada vendedor.
   - Fórmula: `SUM(ValorTotal) / MetaMensal`.

3. **Produtos Mais Vendidos:**
   - Descrição: Identificação dos produtos mais vendidos com base na quantidade e receita gerada.
   - Fórmulas:
     - Por quantidade: `SUM(Quantidade) GROUP BY ProdutoID`.
     - Por receita: `SUM(ValorTotal) GROUP BY ProdutoID`.

4. **Análise Geográfica de Vendas:**
   - Descrição: Receita total e número de vendas por cidade ou região.
   - Fórmula: `SUM(ValorTotal) GROUP BY Cidade`.

As dimensões abaixo, foram projetadas com base nos KPIs identificados para permitir análises detalhadas e segmentadas! Serão criadas na Fase 4:

1. **DimCliente:**
   - **Campos:** `ClienteID`, `Nome`, `Cidade`.
   - **Justificativa:** Permite a análise das vendas segmentadas por cliente e localização.

2. **DimVendedor:**
   - **Campos:** `VendedorID`, `Nome`.
   - **Justificativa:** Necessária para acompanhar o desempenho dos vendedores.

3. **DimProduto:**
   - **Campos:** `ProdutoID`, `Nome`, `Categoria`.
   - **Justificativa:** Facilita a análise de produtos mais vendidos por categoria.

4. **DimTempo:**
   - **Campos:** `TempoID`, `Data`, `Ano`, `Mes`.
   - **Justificativa:** Essencial para análises temporais, como receita por mês ou ano.

Esses `KPIs` e `dimensões` são usados para modelar o **Star Schema** no Data Warehouse e garantir uma análise eficiente.

Agora, o próximo passo é entender os dados... ver padrões nestes dados, vê anomalias, outliers... vamos lá!!!

---

## Fase 3: Análise Exploratória de Dados (EDA)

### Contextualização
Nesta fase, realizamos uma análise exploratória para entender a estrutura, a qualidade e os padrões dos dados disponíveis. Essa etapa é crucial para identificar possíveis problemas, como valores ausentes ou inconsistências, e obter insights preliminares.

Observação: *Aqui por ser uma demonstração apenas, não pegamos os dados do BD, apenas uma amostra de lá! Mas no dia a dia, sim! Devem vir do BD.*

### Código em Python
```python
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
import psycopg2 #para conectar com bd

#conectando ao BD - exemplo...
#conn = psycopg2.connect(
#    host="localhost",
#    database="portfolio",
#    user="postgres",
#    password="suasenha"
#)

# Simulação de dados extraídos do banco
data = pd.DataFrame({
    'ProdutoID': [1, 2, 3, 4, 5],
    'TotalItem': [3500, 100, 150, 2400, 800],
    'Quantidade': [1, 2, 1, 2, 1]
})

# Estatísticas descritivas
print("Estatísticas descritivas dos dados:")
print(data.describe())

#Resultado da Estatísticas descritivas dos dados:
#       ProdutoID    TotalItem  Quantidade
#count   5.000000     5.000000    5.000000
#mean    3.000000  1390.000000    1.400000
#std     1.581139  1501.832214    0.547723
#min     1.000000   100.000000    1.000000
#25%     2.000000   150.000000    1.000000
#50%     3.000000   800.000000    1.000000
#75%     4.000000  2400.000000    2.000000
#max     5.000000  3500.000000    2.000000

# Gráfico de barras para receita por produto
plt.figure(figsize=(10, 6))
sns.barplot(x='ProdutoID', y='TotalItem', data=data)
plt.title('Receita por Produto')
plt.xlabel('ID do Produto')
plt.ylabel('Receita Total')
plt.show()

# Gráfico de dispersão: Analisa a relação entre a quantidade vendida e a receita total, destacando padrões e produtos com maior impacto financeiro.
plt.figure(figsize=(10, 6))
sns.scatterplot(x='Quantidade', y='TotalItem', hue='ProdutoID', data=data)
plt.title('Relação entre Quantidade e Receita por Produto')
plt.xlabel('Quantidade Vendida')
plt.ylabel('Receita Total')
plt.show()

# Exibe a dispersão e possíveis outliers na receita por produto, ajudando a entender a variação nas vendas.
plt.figure(figsize=(10, 6))
sns.boxplot(x='ProdutoID', y='TotalItem', data=data)
plt.title('Distribuição da Receita por Produto')
plt.xlabel('ID do Produto')
plt.ylabel('Receita Total')
plt.show()
```

Abaixo, um exemplo - Gráfico de barras para receita por produto para entender se tem alguma anomalia de valores
<img src="https://blogdozouza.wordpress.com/wp-content/uploads/2025/01/output.png" alt="Exemplo - Gráfico de barras para receita por produto" width="650"/>

Essa análise fornece *insights* iniciais que auxiliam na modelagem do Data Warehouse e na definição de KPIs relevantes. Agora que já vimos como esta nossa base de dados, entendemos um poucos os dados, vamos para a criação do nosso *Data Warehouse*.

---

## Fase 4: Criação do Data Warehouse

### Contextualização
Nesta etapa, estamos construindo o Data Warehouse (DW) para atender às solicitações do cliente identificadas na Fase 2. O objetivo do DW é consolidar os dados operacionais em um formato otimizado para análise, permitindo a geração de insights valiosos de maneira rápida e eficiente. Utilizamos um modelo dimensional baseado nas necessidades do gerente de vendas, priorizando simplicidade e desempenho.

### Modelo Dimensional
Optamos por um **Star Schema**, que oferece simplicidade e eficiência na consulta de dados analíticos.

#### Tabelas
- **Fato:** FatoVenda (contém medidas como valor e quantidade).
- **Dimensões:**
  - DimCliente
  - DimVendedor
  - DimProduto
  - DimTempo

### Script de Criação e População de Tabelas
```sql
-- Criação das tabelas Dimensionais

-- Criando a dimensão Cliente
CREATE TABLE DimCliente AS
SELECT DISTINCT ClienteID, Nome, Cidade
FROM Cliente;

-- Criando a dimensão vendedor
CREATE TABLE DimVendedor AS
SELECT DISTINCT VendedorID, Nome
FROM Vendedor;

-- Criando a dimensão Produto
CREATE TABLE DimProduto AS
SELECT DISTINCT ProdutoID, Nome, Categoria
FROM Produto;

-- Criando a dimensão Tempo
CREATE TABLE DimTempo AS
SELECT DISTINCT
    ROW_NUMBER() OVER () AS TempoID,
    DataVenda AS Data,
    EXTRACT(YEAR FROM DataVenda) AS Ano,
    EXTRACT(MONTH FROM DataVenda) AS Mes
FROM Venda;

-- Criando a Fato de Vendas
CREATE TABLE FatoVenda AS
SELECT
    i.VendaID AS FatoID,
    v.ClienteID,
    v.VendedorID,
    i.ProdutoID,
    t.TempoID,
    i.Quantidade,
    i.TotalItem AS ValorTotal
FROM ItemVenda i
JOIN Venda v ON i.VendaID = v.VendaID
JOIN DimTempo t ON v.DataVenda = t.Data;
```

Desenho da Modelagem Dimensional:

<img src="https://blogdozouza.wordpress.com/wp-content/uploads/2025/01/dimensional.png" width="650"/>

Uma vez nosso `Data Warehouse` pronto, com nossas tabelas `fato` e `dimensões`, está na hora de transformar estes dados em algo visual para que possamos mostrar ao nosso usuário. Lembrando sempre, que devemos criar visualizações que atendam a demanda do cliente!

Mais informações sobre modelagem dimensional, [confira aqui!](https://medium.com/@aasouzaconsult/aprofundando-em-data-warehouse-65ed2bca9a33) 


---

## Fase 5: Visualização de Dados

### Contextualização
Nesta fase, o objetivo é transformar os dados consolidados no Data Warehouse em insights visuais claros e objetivos. Aqui é onde atendemos diretamente às demandas do cliente, mostrando as análises e métricas de maneira compreensível e interativa. As visualizações permitem identificar tendências, avaliar desempenho e tomar decisões estratégicas com base nos dados processados.

### Ferramentas Utilizadas
Para criar dashboards e relatórios, utilizamos uma combinação de ferramentas modernas, como por exemplo:
- **Power BI**
- **Tableau**
- **Amazon QuickSight**
- **Qlik Sense**
- **Looker**
- **Python** (com bibliotecas como Matplotlib, Seaborn e Plotly)
    - *As visualizações fiz em Python aqui, para facilitar a exposição individual de cada indicados! Mas sugiro sempre usar ferramentas de visualização self-service (como Power BI, Tableau e as demais citadas acima)*
        - No final, tem uma demonstração como ficaria no Power BI.
    - Observação: *Aqui por ser uma demonstração apenas, não pegamos os dados do BD, apenas uma amostra de lá! Mas no dia a dia, sim! Devem vir do BD.*

### Dashboards solicitados pelo cliente
1. **Receita total por mês:** Análise mensal das receitas, permitindo identificar sazonalidades.

<img src="https://blogdozouza.wordpress.com/wp-content/uploads/2025/01/output-1.png" width="650"/>

```python
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

# Dados originais e expandidos simulados
dados_vendas_expandidos = pd.DataFrame({
    'DataVenda': [
        '2025-01-10', '2025-01-12', '2025-01-13', '2025-01-14', '2025-01-15',
        '2024-12-20', '2024-12-25', '2024-11-15', '2024-11-20', '2024-10-10',
        '2025-01-16', '2025-02-05', '2025-02-12', '2025-03-01', '2025-03-15',
        '2026-01-10', '2026-01-20', '2026-02-14', '2026-02-25', '2026-03-10'
    ],
    'TotalItem': [
        3600.00, 2550.00, 4300.00, 300.00, 2800.00,
        4000.00, 3500.00, 2500.00, 2200.00, 1800.00,
        3000.00, 3200.00, 2800.00, 4000.00, 4100.00,
        4500.00, 4600.00, 3500.00, 3000.00, 3800.00
    ]
})

# Convertendo a coluna de datas para o tipo datetime
dados_vendas_expandidos['DataVenda'] = pd.to_datetime(dados_vendas_expandidos['DataVenda'])

# Extraindo o mês/ano para análise mensal
dados_vendas_expandidos['MesAno'] = dados_vendas_expandidos['DataVenda'].dt.to_period('M')

# Agrupando por mês/ano e somando as receitas
receita_mensal_expandidos = dados_vendas_expandidos.groupby('MesAno')['TotalItem'].sum().reset_index()
receita_mensal_expandidos['MesAno'] = receita_mensal_expandidos['MesAno'].astype(str)

# Gerando o gráfico
plt.figure(figsize=(12, 6))
sns.barplot(x='MesAno', y='TotalItem', data=receita_mensal_expandidos, palette='viridis')
plt.title('Receita Total por Mês (Dados Expandidos)', fontsize=14)
plt.xlabel('Mês/Ano', fontsize=12)
plt.ylabel('Receita Total (R$)', fontsize=12)
plt.xticks(rotation=45, fontsize=10)
plt.tight_layout()
plt.show()
```

2. **Desempenho dos vendedores comparado às metas:** Gráficos que destacam vendedores com melhor performance e os que estão abaixo da meta.

<img src="https://blogdozouza.wordpress.com/wp-content/uploads/2025/01/output-2.png" width="650"/>

```python
# Simulando dados de vendas por vendedor e metas
dados_vendedores = pd.DataFrame({
    'VendedorID': [1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4],
    'Nome': ['Carlos', 'Ana', 'Marcos', 'Fernanda'] * 3,
    'MetaMensal': [5000, 7000, 6000, 6500] * 3,
    'TotalVendas': [5200, 6800, 5900, 6400, 4800, 7200, 6100, 6500, 5100, 7300, 6200, 6600],
    'MesAno': [
        '2025-01', '2025-01', '2025-01', '2025-01',
        '2025-02', '2025-02', '2025-02', '2025-02',
        '2025-03', '2025-03', '2025-03', '2025-03'
    ]
})

# Criando coluna para calcular desempenho relativo à meta
dados_vendedores['PercentualMeta'] = (dados_vendedores['TotalVendas'] / dados_vendedores['MetaMensal']) * 100

# Criando o gráfico de barras para desempenho
plt.figure(figsize=(14, 8))
sns.barplot(x='Nome', y='PercentualMeta', hue='MesAno', data=dados_vendedores, palette='coolwarm')
plt.axhline(100, color='gray', linestyle='--', label='Meta Alcançada')
plt.title('Desempenho dos Vendedores Comparado às Metas', fontsize=16)
plt.xlabel('Vendedor', fontsize=12)
plt.ylabel('Desempenho (% da Meta)', fontsize=12)
plt.legend(title='Mês/Ano', fontsize=10, title_fontsize=12, loc='upper left')
plt.xticks(fontsize=10)
plt.yticks(fontsize=10)
plt.tight_layout()
plt.show()
```

3. **Produtos mais vendidos por categoria:** Identificação de produtos e categorias mais lucrativos.

<img src="https://blogdozouza.wordpress.com/wp-content/uploads/2025/01/output-3.png" width="650"/>

```python
# Simulando dados de produtos e categorias
dados_produtos = pd.DataFrame({
    'ProdutoID': [1, 2, 3, 4, 5, 1, 2, 3, 4, 5],
    'Nome': [
        'Notebook', 'Mouse', 'Teclado', 'Monitor', 'Impressora',
        'Notebook', 'Mouse', 'Teclado', 'Monitor', 'Impressora'
    ],
    'Categoria': [
        'Eletrônicos', 'Acessórios', 'Acessórios', 'Eletrônicos', 'Eletrônicos',
        'Eletrônicos', 'Acessórios', 'Acessórios', 'Eletrônicos', 'Eletrônicos'
    ],
    'Quantidade': [10, 25, 20, 15, 18, 12, 30, 22, 17, 19],
    'TotalItem': [35000, 1250, 3000, 18000, 14400, 42000, 1500, 3300, 20400, 15200]
})

# Agrupando por categoria e somando as receitas
produtos_por_categoria = dados_produtos.groupby('Categoria').agg({
    'Quantidade': 'sum',
    'TotalItem': 'sum'
}).reset_index()

# Criando o gráfico
plt.figure(figsize=(10, 6))
sns.barplot(x='Categoria', y='TotalItem', data=produtos_por_categoria, palette='muted')
plt.title('Produtos Mais Vendidos por Categoria (Receita Total)', fontsize=16)
plt.xlabel('Categoria', fontsize=12)
plt.ylabel('Receita Total (R$)', fontsize=12)
plt.xticks(fontsize=10)
plt.yticks(fontsize=10)
plt.tight_layout()
plt.show()
```

4. **Mapa de vendas por cidade:** Visualização geográfica que destaca regiões de maior venda.

<img src="https://blogdozouza.wordpress.com/wp-content/uploads/2025/01/output-4.png" width="650"/>

```python
# Simulando dados de vendas por cidade (tabela de cliente)
import folium

dados_cidades = pd.DataFrame({
    'Cidade': ['Fortaleza', 'São Paulo', 'Rio de Janeiro', 'Belo Horizonte', 'Salvador'],
    'Latitude': [-3.71722, -23.55052, -22.90685, -19.91668, -12.9714],
    'Longitude': [-38.5434, -46.6333, -43.1729, -43.93449, -38.5014],
    'TotalVendas': [15000, 45000, 30000, 20000, 25000]
})

# Criando o mapa base centrado no Brasil
mapa = folium.Map(location=[-14.2350, -51.9253], zoom_start=4)

# Adicionando os pontos das cidades ao mapa
for _, cidade in dados_cidades.iterrows():
    folium.CircleMarker(
        location=(cidade['Latitude'], cidade['Longitude']),
        radius=cidade['TotalVendas'] / 5000,  # Tamanho proporcional às vendas
        color='blue',
        fill=True,
        fill_color='blue',
        fill_opacity=0.6,
        tooltip=f"{cidade['Cidade']}: R$ {cidade['TotalVendas']:,.2f}"
    ).add_to(mapa)

# Exibindo o mapa
mapa.save("mapa_vendas.html")
mapa
```

### Um exemplo usando Power BI
Observem que no Power BI, ou qualquer outra ferramenta especifíca de visualização de dados, a disposição e visual fica bem mais atrativa e interativa. Sempre comento que devemos usar as ferramentas certas na hora certa!!! Fiz acima no Python para demonstrar passo a passo, mas no dia a dia a visualização é feita utilizando ferramentas de visualização de dados, como por exemplo, o [Power BI](https://www.microsoft.com/pt-br/power-platform/products/power-bi).


- *Sugestão de material: [Storytelling em Análise de Dados](https://medium.com/blog-do-zouza/storytelling-em-an%C3%A1lise-de-dados-f708cca115bb)*
- *Sugestão de livro: [Storytelling com Dados](https://www.amazon.com.br/Storytelling-com-Dados-Visualiza%C3%A7%C3%A3o-Profissionais/dp/8550804681)*


---

## Fase 6: Entrega ao Cliente

### Contextualização
Esta é a etapa final do projeto, onde o trabalho desenvolvido é apresentado ao cliente em um formato acessível e impactante. Os dashboards, relatórios e insights gerados são entregues para que o cliente possa utilizá-los em suas decisões estratégicas. Durante essa fase, é essencial garantir que o cliente compreenda as análises realizadas e como usá-las para resolver os problemas inicialmente identificados.

### Ciclo de Feedback e Ajustes
A entrega também envolve uma possível iteração com o cliente, onde:
- Feedback é coletado sobre as análises e ferramentas entregues.
- Ajustes podem ser realizados para refinar os dashboards ou incluir novos indicadores.

### Relação com o CRISP-DM
Nesta fase, encerramos o ciclo do **CRISP-DM (Cross-Industry Standard Process for Data Mining)**, que estrutura projetos de análise de dados em seis etapas:
1. **Entendimento do Negócio:** Conduzido na Fase 2.
2. **Entendimento dos Dados:** Abordado na Fase 3.
3. **Preparação dos Dados:** Realizado na construção do Data Warehouse (Fase 4).
4. **Modelagem:** Incorporado durante as análises e desenvolvimento de KPIs.
5. **Avaliação:** Envolvendo validação dos resultados junto ao cliente nesta fase.
6. **Implantação:** A entrega e integração dos dashboards na rotina do cliente.

Essa metodologia assegura que os objetivos de negócios sejam atendidos de forma estruturada e iterativa.

### Aplicação do MVP
Adotamos o conceito de **MVP (Minimum Viable Product)** para entregar uma solução inicial que já resolve os problemas principais do cliente. Isso garante agilidade na entrega e permite melhorias incrementais baseadas no feedback do cliente. Por exemplo:
- Entrega inicial de dashboards com os KPIs prioritários.
- Iteração posterior para incluir segmentações ou novos relatórios conforme necessidade.

### Finalização
O sucesso dessa fase é medido pelo impacto das análises no processo decisório do cliente. Um acompanhamento contínuo pode ser sugerido para garantir que os insights gerados mantenham relevância ao longo do tempo.

---

Este portfólio demonstra como um Analista de Dados pode transformar dados brutos em insights valiosos por meio de práticas estruturadas e ferramentas avançadas. Os scripts e códigos aqui apresentados estão disponíveis no [GitHub](https://github.com/aasouzaconsult/portfolio-dados/tree/master/Projeto%20de%20An%C3%A1lise%20de%20Dados%20-%20Exemplo).
