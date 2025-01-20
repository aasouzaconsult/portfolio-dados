# Integração de Python com a API do Gemini usando Flask

## Contextualização

Este projeto demonstra a integração do framework Flask com a API do [Gemini](https://gemini.google.com/) para criar uma aplicação web interativa. A aplicação permite que o usuário faça perguntas, que serão processadas e respondidas pela API do Gemini. Além disso, o histórico completo da conversa é exibido na interface, tornando o processo transparente e fácil de acompanhar.

A ideia é oferecer uma aplicação funcional para profissionais, estudantes ou interessados em **Inteligencia Artificial Generativa**, utilizando ferramentas modernas e práticas. 

---

## Estrutura do Projeto

O projeto é composto por dois arquivos principais:

1. **`app.py`**: Este arquivo contém a lógica da aplicação, desde a configuração do Flask até a integração com a API do Gemini. Ele gerencia as rotas para exibir a interface e processar as solicitações do usuário.
2. **`templates/index.html`**: Este arquivo contém a interface da aplicação, permitindo que o usuário insira perguntas e visualize as respostas e o histórico.

### Configuração da API do Gemini
Para usar a API do Gemini, você precisará de uma chave de acesso. Ela pode ser obtida [aqui](https://aistudio.google.com/app/apikey). 
Após adquirir a chave, configure-a no código substituindo o valor em `GOOGLE_API_KEY` ou use uma variável de ambiente (mais seguro, aqui utilizei no código apenas para se tornar mais didático).

---

### Código-Fonte

#### Arquivo `app.py`
```python
from flask import Flask, render_template, request, jsonify
import google.generativeai as genai
import os

# Configuração inicial do Flask
app = Flask(__name__)

# Configuração da API do Gemini
# GOOGLE_API_KEY = os.getenv("GEMINI_API_KEY")
GOOGLE_API_KEY = "ZouzaSfdufuyufurioturioutioruturituroi"  # Substitua pelo valor da sua chave
if not GOOGLE_API_KEY:
    raise ValueError("Erro: Chave da API não encontrada. Configure a chave no código.")
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
```

#### Arquivo `templates/index.html`
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Professor de Dados usando Gemini (Alex Souza)</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f9;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        textarea {
            width: 100%;
            height: 100px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        button {
            padding: 10px 20px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        .response {
            margin-top: 20px;
            padding: 15px;
            background: #e9ecef;
            border-radius: 8px;
            border: 1px solid #ddd;
            line-height: 1.6;
        }
        ul {
            padding: 0;
            margin-left: 20px;
        }
        li {
            margin-bottom: 10px;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .author {
            font-size: 0.9em;
            color: #d63384;
            font-style: italic;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Dados GPT</h1>
            <span class="author">por Alex Souza</span>
        </div>
        <p>Digite sua dúvida ou comando SQL abaixo e receba uma explicação:</p>
        <textarea id="userInput" placeholder="Digite aqui..."></textarea>
        <button onclick="sendQuery()">Enviar</button>

        <div class="response" id="responseBox" style="display: none;"></div>

        <div id="history" style="margin-top: 20px;">
            <h3>Histórico</h3>
            <ul id="historyList" style="list-style-type: none; padding: 0;"></ul>
        </div>
    </div>

    <script>
        async function sendQuery() {
            const userInput = document.getElementById("userInput");
            const responseBox = document.getElementById("responseBox");
            const historyList = document.getElementById("historyList");

            const userQuestion = userInput.value;
            if (!userQuestion) {
                console.error("Campo de entrada vazio!");
                return;
            }

            try {
                const response = await fetch("/query", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ user_input: userQuestion }),
                });

                const data = await response.json();

                if (data.error) {
                    responseBox.innerHTML = `<strong>Erro:</strong> ${data.error}`;
                } else {
                    responseBox.innerHTML = `<strong>Resposta:</strong><br>${data.response}`;

                    const listItem = document.createElement("li");
                    listItem.innerHTML = `<strong>Pergunta:</strong> ${userQuestion}<br><strong>Resposta:</strong><br>${data.response}`;
                    historyList.appendChild(listItem);
                }
                responseBox.style.display = "block";
            } catch (err) {
                console.error("Erro ao enviar a solicitação:", err);
            }

            userInput.value = "";
        }
    </script>
</body>
</html>
```

---

### Execução e visualização

Ao executar a aplicação, a interface terá um campo para digitar a pergunta, um botão para enviar, e uma seção exibindo a resposta e o histórico completo das interações. 

O projeto pode ser executado localmente ao instalar as dependências e rodar `app.py`. No meu caso, a pasta que tem o projeto é a pasta: **py_gemini**.

```
py_gemini/
├── app.py                    # Arquivo principal da aplicação Flask
├── templates/                # Diretório para os templates HTML
│   └── index.html            # Interface do usuário
```

No **Python**, instale as dependências (`google-generativeai` e `flask`) e depois rode a aplicação.

**Exemplo:**
- (base) C:\py_gemini>**pip install flask google-generativeai**
- Agora rode a aplicação
    - (base) C:\py_gemini>**python app.py**
        * Serving Flask app 'app'
        * Debug mode: on
        WARNING: This is a development server. Do not use it in a production deployment. Use a production WSGI server instead.
        * Running on http://127.0.0.1:5000
        Press CTRL+C to quit
        * Restarting with watchdog (windowsapi)
        * Debugger is active!
        * Debugger PIN: 274-952-926

Abra no navegador o endereço: http://127.0.0.1:5000 e aparecerá algo assim:
![](https://blogdozouza.wordpress.com/wp-content/uploads/2025/01/1.png)

Vamos testar... vamos perguntar: **O que é SQL?** e clique em **Enviar**...
A resposta é algo assim:

![](https://blogdozouza.wordpress.com/wp-content/uploads/2025/01/6.png)

Vamos fazer outra interação: **Me dê um exemplo de um SELECT simples**.

![](https://blogdozouza.wordpress.com/wp-content/uploads/2025/01/5.png)

E todo o **Histórico** da conversa fica guardado durante a seção...
![](https://blogdozouza.wordpress.com/wp-content/uploads/2025/01/4.png)

---

### Publicar

Caso deseje publicar esta aplicação para acesso remoto, uma opção recomendada é o uso da plataforma [PythonAnywhere](https://www.pythonanywhere.com/). Essa plataforma permite hospedar aplicações Flask de forma simples e com configurações acessíveis, ideal para demonstrações ou uso contínuo. 

A etapa de publicação é crucial no ciclo de desenvolvimento, pois amplia a acessibilidade do projeto, permitindo que outros usuários ou colaboradores interajam com a aplicação. Além disso, torna o projeto mais visível e facilita feedbacks que podem contribuir para melhorias contínuas. 

Siga as instruções da documentação do PythonAnywhere para configurar seu ambiente e fazer o deploy da aplicação.

Quer ver funcionando? [Só conferir aqui!](https://alexsouza.pythonanywhere.com/)

---

### Conclusão

Este projeto demonstra como o Flask e a API do Gemini podem ser combinados para criar uma aplicação interativa e útil. Através deste exemplo, é possível compreender melhor os processos de desenvolvimento, integração com APIs e publicação de aplicações web. 

Incentivo que você personalize este projeto para atender às suas necessidades específicas e explore os recursos avançados da API do Gemini para expandir suas funcionalidades.

---

### Sugestão de melhorias
- Opção de enviar o histórico por e-mail
- Guardar histórico em um banco de dados
- Aplicar multimodalidade