from flask import Flask, render_template, request, jsonify
import google.generativeai as genai
import os

# Configuração inicial do Flask
app = Flask(__name__)

# Configuração da API do Gemini
GOOGLE_API_KEY = os.getenv("GEMINI_API_KEY")
if not GOOGLE_API_KEY:
    raise ValueError("Erro: Chave da API não encontrada. Configure a variável GEMINI_API_KEY.")
genai.configure(api_key=GOOGLE_API_KEY)

# Inicializando o modelo
model = genai.GenerativeModel("gemini-1.5-flash")
chat = model.start_chat(history=[])

# Rota principal para exibir a interface
@app.route("/")
def home():
    return render_template("index.html")

# Rota para processar as solicitações do usuário
@app.route("/query", methods=["POST"])
def query():
    user_input = request.json.get("user_input")
    if not user_input:
        return jsonify({"error": "Comando SQL ou dúvida não fornecido"}), 400

    try:
        # Enviar a consulta para a API do Gemini
        response = chat.send_message(user_input)
        return jsonify({"response": response.text})
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == "__main__":
    app.run(debug=True)