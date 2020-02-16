import numpy as np
import os
from flask import Flask, request, render_template, make_response
from sklearn.externals import joblib

app = Flask(__name__, static_url_path='/static')
model = joblib.load('model/model.pkl')


@app.route('/')
def display_gui():
    return render_template('template.html')

@app.route('/verificar', methods=['POST'])
def verificar():
	sexo = request.form['gridRadiosSexo']
	dependentes = request.form['dependentes']
	casado = request.form['gridRadiosCasado']
	trabalho_conta_propria = request.form['gridRadiosTrabalhoProprio']
	rendimento = request.form['rendimento']
	educacao = request.form['educacao']
	valoremprestimo = request.form['valoremprestimo']
	teste = np.array([[sexo,casado,dependentes,educacao,trabalho_conta_propria,rendimento,valoremprestimo]])
	
	print(":::::: Dados de Teste ::::::")
	print("Sexo: {}".format(sexo))
	print("Numero de Dependentes: {}".format(dependentes))
	print("Casado: {}".format(casado))
	print("Educacao: {}".format(educacao))
	print("Trabalha por conta propria: {}".format(trabalho_conta_propria))
	print("Rendimento: {}".format(rendimento))
	print("Valor do emprestimo: {}".format(valoremprestimo))
	print("\n")

	classe = model.predict(teste)[0]
	print("Classe Predita: {}".format(str(classe)))

	return render_template('template.html',classe=str(classe))

if __name__ == "__main__":
        port = int(os.environ.get('PORT', 5500))
       #app.run(host='0.0.0.0', port=port)
        app.run(host='localhost', port=port)

