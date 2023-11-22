# Biblioteca para Criação de um Grafo


Pontifícia Universidade Católica de Minas Gerais - PUC Minas

Disciplina: Algoritmos em Grafos

Grupo: Lucas José de Freitas 

## Para o TP1 o projeto consiste em desenvolver uma biblioteca de grafos
### Permitindo  

```bash
# 1.  A Criação de um grafo com N vértices;
# 2.  Inserção e Remoção arestas;
#       a. Permitindo arestas ser direcionadas ou não;
#       b. Permitindo arestas serem ponderadas ou não;
# 3.  A Consulta o grau de um vértice;
# 4.  A Consulta o grau do grafo;
# 5.  A Consulta os vizinhos de um vértice;
# 6.  A Verificação se o grafo é conexo;
# 7.  A Verificação se o grafo é regular;
# 8.  A Verificação se o grafo é completo;
# 9.  A Realização da busca em profundidade;
# 10. A Realização da busca em largura;
# 11. A Verificação da existência de caminho entre dois vértices;
#       a. Caso exista, exibir o caminho;
# 12. A Visualização do grafo na ferramenta Gephi.
```

## Para o TP2 projeto consistirá na atualização da bibliotece de grafos criada 
### Agora Permitindo   

```bash
# 13. O Calculo da menor distância de uma origem para todos os outros vértices.
#     a. Usando os Algoritmo:
#        Dijkstra
#        Bellman-Ford    
# 14. O Calculo da menor distância de todos para todos
#     a. Usando o Algoritmo:
#        Floyd-Warshall
# 15. A Implementação e explicação do Algoritmo A* (A ESTRELA)
```
```bash
funcao pra receber uma matriz completa{
	receber a matriz 	
	atualizar a lista de adjacencia
}

OBS: fazer tratativa pra nao deixar passar grafos que nao sao ponderados (Feito)

ALGORITMOS

DIJKSTRA FONTE UNICA (FEITO)

DIJKSTRA TODOS PARA TODOS (FEITO)

BELLMAN-FORD FONTE UNICA (FEITO)

BELLMAN-FORD TODOS PARA TODOS (FEITO)

FLOYD-WARSHALL FONTE UNICA 

FLOYD-WARSHALL TODOS PARA TODOS

A* FONTE UNICA 

A* TODOS PARA TODOS

Realizar o teste
	ALGORITMOS QUE CALCULAM A DISTANCIA DE UMA ORIGEM PARA TODOS
		Dijksta (feito)
			matrizes ponderadas (sem pesos negativos)
			2 Grafos [1 direcionado ponderado, 1 n direcionado ponderado]
			G1 e G2 [2 => 100 vertice] | G3 e G4 [2 => 1000 vertices] | G5 e G6 [2 => 10.000 vertices]

		Bellman-Ford (feito)
			matrizes ponderadas (podem ter pesos negativos)
			2 Grafos [1 direcionado ponderado, 1 n direcionado ponderado]
			G1 e G2 [2 => 100 vertice] | G3 e G4 [2 => 1000 vertices] | G5 e G6 [2 => 10.000 vertices]
	
	ALGORITMOS QUE CALCULAM DISTANCIA DE TODOS PRA TODOS
		Floyd-Warshall
			matrizes ponderadas (podem ter pesos negativos)
			2 Grafos [1 direcionado ponderado, 1 n direcionado ponderado]
			G1 e G2 [2 => 100 vertice] | G3 e G4 [2 => 1000 vertices] | G5 e G6 [2 => 10.000 vertices]

	ALGORITMO NAO CONHEÇO 	
		A* (A Estrela)
	
	6 grafos {2 de 100, 2 de 1000, 2 de 10.000}

```
