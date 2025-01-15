Crud


Frases:


Get: http://localhost:8080/api/frases
lista de criação


Post: http://localhost:8080/api/frases
{
  "conteudo": "Só se vive uma vez",
  "autor": "Enzho Pedro"
}

Put: http://localhost:8080/api/frases/(coloque o id)
{
  "conteudo": "Só sei que nada sei ",
  "autor": "Enzho Soares"
}


Delete:
http://localhost:8080/api/frases/(coloque o id)



Biscoitos:

Get: http://localhost:8080/api/biscoitos
lista de criação

Post: http://localhost:8080/api/biscoitos

{
  "nome": "Biscoito da chocolate",
  "phraseId": 8
}

Put: não é permitido 


Delete: http://localhost:8080/api/biscoitos/2