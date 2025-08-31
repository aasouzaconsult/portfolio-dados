# 🚗 Projeto Multi-Agente com MCP – Zouza Motors

## Introdução do Problema  
O desafio proposto foi criar um sistema de atendimento inteligente para uma concessionária fictícia, a **Zouza Motors**, que pudesse automatizar interações típicas de clientes em três áreas fundamentais:  
1. **Recepção** – o primeiro contato, apresentando a empresa e direcionando o atendimento.  
2. **Vendas** – suporte ao processo de compra de veículos e agendamento de visitas/test drives.  
3. **Manutenção** – agendamento de revisões e suporte pós-venda.  

A ideia central foi mostrar como **IA Generativa + MCP (Model Context Protocol)** podem ser usados em conjunto para orquestrar diferentes agentes especializados, cada um responsável por uma parte da jornada do cliente.

---

## Solução: Arquitetura Multi-Agente  
O projeto utilizou **três agentes que trabalham em parceria**:  

- **Agente de Recepção**  
  - Responsável por dar boas-vindas, apresentar a empresa e encaminhar o cliente.  
  - Pode transferir o atendimento para o agente de vendas ou de manutenção.  

- **Agente de Vendas**  
  - Consulta os veículos disponíveis (`get_veiculos_disponiveis`).  
  - Ajuda a entender as preferências do cliente.  
  - Consulta concessionárias (`get_concessionarias`) e vendedores (`get_vendedores_por_concessionaria`).  
  - Agenda visitas para compra com a ferramenta `agenda_visita_para_compra`.  

- **Agente de Manutenção**  
  - Identifica o cliente e seus veículos (`get_info_cliente`).  
  - Coleta informações sobre a necessidade de revisão/manutenção.  
  - Agenda o serviço na concessionária onde o cliente comprou o carro (`agenda_visita_para_assistencia`).  

Esses agentes foram implementados em **Python**, utilizando:  
- `FastMCP` para disponibilizar ferramentas conectadas ao banco de dados.  
- `Streamlit` para interface de chat, simulando um atendimento em tempo real.  
- **Orquestração multi-agente** com handoffs, onde um agente pode repassar a conversa a outro.  
- Conexão com **PostgreSQL online**, permitindo acesso em **tempo real** às informações de veículos, concessionárias, vendedores e clientes.  

---

## Passo a Passo da Implementação  

1. **Criação das ferramentas (MCP Server)**  
   - Foram definidos métodos como `get_veiculos_disponiveis`, `get_concessionarias`, `get_vendedores_por_concessionaria` e `get_info_cliente`.  
   - Esses métodos acessam o banco de dados PostgreSQL online em **tempo real**, trazendo informações atualizadas.  

2. **Configuração dos Agentes**  
   - Cada agente recebeu **instruções específicas** sobre seu papel.  
   - Foram definidos os **handoffs**, permitindo que a recepção encaminhasse o cliente para vendas ou manutenção.  

3. **Interface de Usuário (Chat)**  
   - Criada com **Streamlit**, exibindo histórico de mensagens.  
   - Usuário interage como se fosse um chat com a Zouza Motors.  
   - Cada mensagem pode ser respondida por agentes diferentes, dependendo da necessidade.  

4. **Orquestração com MCP**  
   - O `Runner` gerencia a execução e mantém o contexto da conversa.  
   - O servidor MCP é iniciado para permitir que os agentes chamem as ferramentas quando necessário.  

---

## Conclusão  
O projeto demonstra como **IA Generativa + MCP** podem ser aplicados em cenários reais para:  
- Melhorar a experiência do cliente.  
- Automatizar processos de vendas e manutenção.  
- Criar uma arquitetura escalável de agentes especializados.  
- Garantir acesso a dados **em tempo real**, integrando diretamente um banco PostgreSQL online.  

Essa solução pode ser adaptada para qualquer setor que precise de múltiplos agentes colaborando em tempo real (ex.: turismo, saúde, educação, bancos).  

---

## Próximos Passos  
- Expandir o número de agentes (ex.: **Financeiro** para simular financiamento de veículos).  
- Adicionar análises de sentimentos e personalização da experiência.  
- Integrar com sistemas externos de CRM para completar a jornada do cliente.  
