from flask import Flask, render_template, request, redirect, url_for, jsonify, session
from flask_sqlalchemy import SQLAlchemy
from flask_mail import Mail, Message
import google.generativeai as genai
from collections import Counter
from datetime import datetime, timedelta
import random
import logging
import os
import markdown  # Adicione esta importação para converter Markdown em HTML

# Inicializa a aplicação Flask
app = Flask(__name__)

app.secret_key = "chave_secreta_super_segura"  # Necessário para sessões

# Usuário e senha fictícios (pode conectar ao banco de dados)
USUARIOS = {
    "admin": "admin",
    "alex": "alex"
}

# Rota da tela de login
@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']

        if username in USUARIOS and USUARIOS[username] == password:
            session['usuario'] = username  # Salva na sessão
            return redirect(url_for('dashboard'))  # Redireciona para a tela principal
        else:
            return render_template('index.html', error="Usuário ou senha incorretos!")

    return render_template('index.html')

# Rota protegida
@app.route('/dashboard')
def dashboard():
    if 'usuario' not in session:
        return redirect(url_for('login'))  # Se não estiver logado, volta para login

    leads = Lead.query.all()  # Busca todos os leads no banco de dados
    return render_template('dashboard.html', usuario=session['usuario'], leads=leads, etapas=ETAPAS_FUNIL)  # Passa os leads para o HTML


# Rota para logout
@app.route('/logout')
def logout():
    session.pop('usuario', None)
    return redirect(url_for('login'))  # Redireciona para login após logout


# Configuração do banco de dados PostgreSQL
app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://postgres:Aula123@localhost:5432/leads_db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

# Inicializa a extensão SQLAlchemy
db = SQLAlchemy(app)

# Configuração do Flask-Mail para envio de e-mails
app.config['MAIL_SERVER'] = 'smtp.gmail.com'
app.config['MAIL_PORT'] = 587
app.config['MAIL_USE_TLS'] = True
app.config['MAIL_USERNAME'] = 'aasouzaconsult@gmail.com'
app.config['MAIL_PASSWORD'] = 'adke uhga knty totl'
app.config['MAIL_DEFAULT_SENDER'] = 'aasouzaconsult@gmail.com'

#https://myaccount.google.com/apppasswords

mail = Mail(app)

# Configuração da API Gemini (Google Generative AI)
genai.configure(api_key="AIzaSyBGosvQoL-VeIQgB0PA3VYOx9jDtZI1Y2U")

# Definição das etapas do funil de vendas
ETAPAS_FUNIL = [
    "Novo Lead",
    "Contato Inicial",
    "Interesse Demonstrado",
    "Proposta Enviada",
    "Negociação",
    "Fechamento"
]

# Modelo do banco de dados para armazenar leads
class Lead(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    nome = db.Column(db.String(100), nullable=False)
    email = db.Column(db.String(100), unique=True, nullable=False)
    telefone = db.Column(db.String(20), nullable=False)
    escola = db.Column(db.String(100), nullable=False)
    cidade = db.Column(db.String(100), nullable=False)
    estado = db.Column(db.String(50), nullable=False)
    series_trabalhadas = db.Column(db.String(200), nullable=False)
    nome_diretor = db.Column(db.String(100), nullable=False)
    nome_mantenedor = db.Column(db.String(100), nullable=False)
    nome_responsavel_financeiro = db.Column(db.String(100), nullable=False)
    necessidade = db.Column(db.Text, nullable=False)
    orçamento_estimado = db.Column(db.String(50), nullable=False)
    status = db.Column(db.String(50), nullable=False, default="Novo Lead")
    data_atualizacao = db.Column(db.DateTime, default=datetime.utcnow)
    consultor = db.Column(db.String(100), nullable=True)  # Nova coluna

# Criar as tabelas no banco de dados
with app.app_context():
    db.create_all()

# Rota principal para exibir os leads cadastrados
@app.route('/')
def index():
    leads = Lead.query.all()
    return render_template('index.html', leads=leads, etapas=ETAPAS_FUNIL)

# Rota para visualizar detalhes do lead
@app.route('/lead/<int:lead_id>')
def lead_detail(lead_id):
    lead = Lead.query.get_or_404(lead_id)
    return render_template('lead_detail.html', lead=lead, etapas=ETAPAS_FUNIL)

# Rota para editar um lead
@app.route('/edit/<int:lead_id>', methods=['GET', 'POST'])
def edit_lead(lead_id):
    lead = Lead.query.get_or_404(lead_id)
    if request.method == 'POST':
        lead.nome = request.form['nome']
        lead.email = request.form['email']
        lead.telefone = request.form['telefone']
        lead.escola = request.form['escola']
        lead.cidade = request.form['cidade']
        lead.estado = request.form['estado']
        lead.series_trabalhadas = request.form['series_trabalhadas']
        lead.nome_diretor = request.form['nome_diretor']
        lead.nome_mantenedor = request.form['nome_mantenedor']
        lead.nome_responsavel_financeiro = request.form['nome_responsavel_financeiro']
        lead.necessidade = request.form['necessidade']
        lead.orçamento_estimado = request.form['orçamento_estimado']
        lead.status = request.form.get('status', lead.status)
        lead.data_atualizacao = datetime.utcnow()
        lead.consultor = request.form['consultor']
        db.session.commit()
        return redirect(url_for('dashboard'))
    return render_template('edit_lead.html', lead=lead, etapas=ETAPAS_FUNIL)

# Rota para fornecer dados do dashboard
@app.route('/dashboard_data')
def dashboard_data():
    leads = Lead.query.all()
    status_contagem = Counter(lead.status for lead in leads)
    return jsonify({
        'labels': list(status_contagem.keys()),
        'data': list(status_contagem.values())
    })

# Testar a API
@app.route('/test_gemini')
def test_gemini():
    try:
        model = genai.GenerativeModel("gemini-1.5-flash")
        response = model.generate_content("Diga Olá, Gemini!")
        return response.text if response and hasattr(response, 'text') else "Falha na resposta"
    except Exception as e:
        return f"Erro ao testar Gemini: {str(e)}"

# Função para gerar resumo do lead usando IA
def gerar_resumo_lead(lead):
    try:
        model = genai.GenerativeModel("gemini-1.5-flash")  # Define explicitamente o modelo
        prompt = f"""
        Gere um breve resumo profissional para um lead de educação socioemocional.
        Nome: {lead.nome}
        Escola: {lead.escola}, {lead.cidade} - {lead.estado}
        Diretor: {lead.nome_diretor}
        Mantenedor: {lead.nome_mantenedor}
        Responsável Financeiro: {lead.nome_responsavel_financeiro}
        Necessidade: {lead.necessidade}
        Orçamento Estimado: {lead.orçamento_estimado}
        Status Atual: {lead.status}
        
        Dependendo do novo Status, comente um pouco o que é o Status (descrição abaixo) e quais as próximas fases até o fechamento... coloque até uma estimativa de tempo entre as fases...
        Status do Funil de Vendas
        1 Novo Lead - O lead foi cadastrado no sistema.
        Ainda não houve nenhum contato.
        
        2 Contato Inicial - A equipe entrou em contato pela primeira vez.
        O objetivo é validar se o lead está interessado.
        
        3 Interesse Demonstrado - O lead respondeu positivamente ao contato inicial.
        Ele demonstrou curiosidade ou intenção de seguir com o serviço.

        4 Proposta Enviada - Uma proposta comercial foi enviada com valores e detalhes.
        Aguarda-se uma resposta do lead.

        5 Negociação - O lead está avaliando a proposta e pode fazer contrapropostas.
        Pode estar buscando aprovação interna antes de fechar o contrato.

        6 Fechamento - O lead aceitou a proposta e fechou negócio.
        Agora, ele se torna um cliente oficial.
        
        """
        response = model.generate_content(prompt)
        if response and hasattr(response, 'text') and response.text:
            return response.text.strip()
        logging.error("Erro: A resposta da API Gemini está vazia ou inválida.")
        return "Resumo não disponível no momento."
    except Exception as e:
        logging.error(f"Erro na API Gemini: {str(e)}")
        return f"Erro ao gerar resumo: {str(e)}"

# Função para enviar e-mail
def enviar_email_resumo(lead):
    resumo = gerar_resumo_lead(lead)  # Texto gerado pelo Gemini
    
    if "Erro ao gerar resumo" in resumo:
        return  # Evita enviar e-mail com erro

    # Converter Markdown para HTML
    resumo_html = markdown.markdown(resumo)

    # Definir o corpo do e-mail com HTML formatado
    msg = Message(f"Atualização de Status: {lead.escola}", recipients=[lead.consultor])
    msg.html = f"""
    <p>Olá Consultor, em relação à <strong>{lead.escola}</strong>,</p>

    <p>Aqui está um resumo atualizado do seu cadastro:</p>

    {resumo_html}  <!-- Inserindo o texto do Gemini convertido para HTML -->

    <p>Atenciosamente,<br><strong>Equipe de Atendimento</strong></p>
    """

    mail.send(msg)

# Rota para atualizar o status do lead e enviar e-mail
@app.route('/update_status/<int:lead_id>', methods=['POST'])
def update_status(lead_id):
    lead = Lead.query.get_or_404(lead_id)
    novo_status = request.form.get('status', lead.status)
    if novo_status in ETAPAS_FUNIL:
        lead.status = novo_status
        lead.data_atualizacao = datetime.utcnow()
        db.session.commit()
        enviar_email_resumo(lead)  # Enviar e-mail após atualização do status
    return redirect(url_for('dashboard'))

# Rota para adicionar um novo lead
@app.route('/add_lead', methods=['GET', 'POST'])
def add_lead():
    if request.method == 'POST':
        ultimo_lead = Lead.query.order_by(Lead.id.desc()).first()  # Busca o último ID cadastrado
        novo_id = ultimo_lead.id + 1 if ultimo_lead else 1  # Se não houver leads, começa do 1

        novo_lead = Lead(
            id=novo_id,  # Define o novo ID corretamente
            nome=request.form['nome'],
            email=request.form['email'],
            telefone=request.form['telefone'],
            escola=request.form['escola'],
            cidade=request.form['cidade'],
            estado=request.form['estado'],
            series_trabalhadas=request.form['series_trabalhadas'],
            nome_diretor=request.form['nome_diretor'],
            nome_mantenedor=request.form['nome_mantenedor'],
            nome_responsavel_financeiro=request.form['nome_responsavel_financeiro'],
            necessidade=request.form['necessidade'],
            orçamento_estimado=request.form['orçamento_estimado'],
            consultor=request.form.get('consultor', None),
            status="Novo Lead"
        )

        db.session.add(novo_lead)
        db.session.commit()
        return redirect(url_for('dashboard'))
    
    return render_template('add_lead.html', etapas=ETAPAS_FUNIL)

# Rota para a página de funcionalidades
@app.route('/funcionalidades')
def funcionalidades():
    return render_template('funcionalidades.html')

# Rota para deletar um lead
@app.route('/delete_lead/<int:lead_id>', methods=['POST'])
def delete_lead(lead_id):
    lead = Lead.query.get_or_404(lead_id)
    db.session.delete(lead)
    db.session.commit()
    return redirect(url_for('dashboard'))

# Rota para gerar o relatório de tempo no status atual
@app.route('/relatorio_tempo_status')
def relatorio_tempo_status():
    leads = Lead.query.all()
    relatorio = []

    for lead in leads:
        if lead.data_atualizacao:
            tempo_no_status = (datetime.utcnow() - lead.data_atualizacao).days  # Calcula a diferença em dias
        else:
            tempo_no_status = "Desconhecido"  # Caso algum lead não tenha data_atualizacao

        relatorio.append({
            "escola": lead.escola,
            "status": lead.status,
            "tempo_status": tempo_no_status
        })

    return jsonify(relatorio)

# Lista de apelidos divertidos para os consultores
APELIDOS_CONSULTORES = ["Mestre dos Leads", "Guru das Vendas", "Encantador de Clientes", "Ninja do Fechamento", "O Caçador de Negócios"]

# Rota para gerar um resumo criativo usando IA Generativa
@app.route('/gerar_resumo_gemini')
def gerar_resumo_gemini():
    leads_movimentados = Lead.query.filter(Lead.data_atualizacao >= datetime.utcnow().replace(hour=0, minute=0, second=0)).all()

    if not leads_movimentados:
        return jsonify({"resumo": "Hoje não tivemos movimentações significativas no sistema. Vamos em frente!"})

    resumo_dados = []
    for lead in leads_movimentados:
        apelido = random.choice(APELIDOS_CONSULTORES)
        resumo_dados.append(f"A escola {lead.escola} avançou para '{lead.status}', sob a orientação do consultor {lead.consultor}.")

    prompt = f"""
    Gere um resumo envolvente e criativo sobre as movimentações no sistema hoje:

    {'. '.join(resumo_dados)}

    Crie uma história conectando essas mudanças, descrevendo de forma inspiradora como cada escola está progredindo em sua jornada.
    
    E sempre associe a movimentação ao consultor, se possível, colocando só o que vem até antes do @! E se possível, dá um apelido por e-mail. Exemplo: aasouzaconsult@gmail, poderia ser o Souza...
    
    No final, coloque um agradecimento ao esforço de todos e até amanhã!
    
    Que o retorno na tela seja bem formatado e com emotions
    """

    try:
        model = genai.GenerativeModel("gemini-1.5-flash")
        response = model.generate_content(prompt)

        if response and hasattr(response, 'text') and response.text:
            return jsonify({"resumo": response.text.strip()})
        
        return jsonify({"resumo": "Não foi possível gerar um resumo no momento. Tente novamente mais tarde."})

    except Exception as e:
        return jsonify({"resumo": f"Erro ao gerar resumo: {str(e)}"})


# Inicia a aplicação Flask em modo debug
if __name__ == '__main__':
    app.run(debug=True)
