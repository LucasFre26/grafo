# Explicação Algoritmo A* (A ESTRELA)
O algoritmo A* é um algoritmo de busca que encontra o caminho mais curto entre dois pontos em um grafo, usando uma heurística para otimizar a busca. Aqui está uma explicação passo a passo do código:

### Estrutura do Nó

- A classe `Node` representa um nó no grafo, com coordenadas `(x, y)` e informações necessárias para o A* como `g`, `h`, `f` (custos).

### Função de Heurística

- `heuristica(a, b)`: Calcula a distância Manhattan entre dois nós `a` e `b`. A distância Manhattan é a soma das diferenças absolutas das coordenadas.

### Algoritmo A*

1. **Inicialização**:
   - Conjuntos `conjuntoAberto` e `conjuntoFechado`.
   - Mapas `custoG` e `custoF` para rastrear os custos.

2. **Adição do Nó Inicial**:
   - O nó inicial é adicionado ao `conjuntoAberto`.

3. **Loop Principal**:
   - Enquanto o `conjuntoAberto` não estiver vazio:
     - Seleciona o nó atual com o menor custo `f`.
     - Se o nó atual é o objetivo, reconstrói o caminho e retorna.

4. **Exploração dos Vizinhos**:
   - Para cada vizinho do nó atual:
     - Calcula o custo tentativo `g`.
     - Atualiza se o custo tentativo é menor ou se o vizinho não está no `conjuntoAberto`.
     - Adiciona o vizinho ao `conjuntoAberto`.

5. **Reconstrução do Caminho**:
   - Retorna o caminho reconstruído do nó inicial ao objetivo.

### Função Principal `iniciaAEstrela`

- Solicita ao usuário as coordenadas de origem e destino.
- Chama a função A* (`aEstrela`) para encontrar o caminho mínimo no grafo.

- ---

# Resultados
## 1. Dijkstra com caminho de uma origem para todos os vértices
  -   a. Grafo com 100 vértices Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/afa21829-86f3-4110-bd07-6316618a7d83)

  -   b. Grafo com 100 vértices Não Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/924ed83a-69ea-4447-a0a4-735ed45c7f24)

  -   c. Grafo com 1.000 vértices Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/9a2a3106-2c54-4e98-a20c-fc89e37c146c)

  -   d. Grafo com 1.000 vértices Não Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/c1669bac-519b-4585-8834-242470d67ac4)

  -   d. Grafo com 10.000 vértices Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/77e966f7-a1fb-48e4-ae7d-c21c4ca36713)

  -   e. Grafo com 10.000 vértices Não Direcionado
  
      ![image](https://github.com/LucasFre26/grafo/assets/99023129/dc007af3-841f-4af6-9e69-ac9bb7a59d45)

## 2. Dijkstra com caminho de todos para todos os vértices
  -   a. Grafo com 100 vértices Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/53a58c1d-3687-4e8a-b7fc-2d0cfdc5c75c)
    
  -   b. Grafo com 100 vértices Não Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/fcb6ab7d-53b3-4a5f-939c-be3365e5826c)

  -   c. Grafo com 1.000 vértices Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/40768c64-e2e9-4203-9e98-fd69e9a38c5a)

  -   d. Grafo com 1.000 vértices Não Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/f8f052bf-a6f8-4772-a7f8-7761be8db4b8)

  -   d. Grafo com 10.000 vértices Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/6fc12fd3-40a1-4b93-8a30-c971c96b76b6)

  -   e. Grafo com 10.000 vértices Não Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/71f59a75-7d26-4516-a0b6-5ae0a273f2c5)

## 2. Bellman-Ford com caminho de uma origem para todos os vértices
  -   a. Grafo com 100 vértices Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/923e9b41-59de-42ce-91b2-7e43a41375df)
    
  -   b. Grafo com 100 vértices Não Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/c2d43dde-5761-4993-9ffb-68f796bad3d6)

  -   c. Grafo com 1.000 vértices Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/4f8dfdcf-8bf4-4bc0-b5a1-08d07b8da48a)

  -   d. Grafo com 1.000 vértices Não Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/0ae91d6e-46f5-4c81-ac18-29f746d88149)

  -   d. Grafo com 10.000 vértices Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/d63a6944-dff1-4d05-8b63-251272801879)

  -   e. Grafo com 10.000 vértices Não Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/a5428707-7af0-4bde-8422-a2e0b76968f6)

## 3. Bellman-Ford com caminho de todos para todos os vértices
  -   a. Grafo com 100 vértices Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/0e8f6488-b06d-4303-afbf-956380cb7703)
    
  -   b. Grafo com 100 vértices Não Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/0efe2786-2194-4e69-8b20-8bc57fb6f7fe)

  -   c. Grafo com 1.000 vértices Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/8118f210-1033-4471-95a5-467e3f731454)

  -   d. Grafo com 1.000 vértices Não Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/bee9b302-e9a9-4448-b459-542ebe6a0b63)

  -   d. Grafo com 10.000 vértices Direcionado

  -   e. Grafo com 10.000 vértices Não Direcionado
    
## 4. Floyd-Warshal com caminho de uma origem para todos os vértices
  -   a. Grafo com 100 vértices Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/8385dc24-8c00-41af-9330-9677768ae934)
    
  -   b. Grafo com 100 vértices Não Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/770b9c1f-fe46-4e5c-b64e-f15a82741c80)

  -   c. Grafo com 1.000 vértices Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/ca5c2c72-57e6-477b-984b-be8944623bee)

  -   d. Grafo com 1.000 vértices Não Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/204c34a6-a208-4efe-8f69-d2e71001abbd)

  -   d. Grafo com 10.000 vértices Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/7fd0ed9a-d983-4583-884d-fd6b9ca1e304)

  -   e. Grafo com 10.000 vértices Não Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/2968100c-a1a8-4c99-bb13-e89fac0a264f)
    
## 5. Floyd-Warshal com caminho de todos para todos os vértices
  -   a. Grafo com 100 vértices Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/fb1e4d99-63d1-4229-a081-8d56b3d84143)
    
  -   b. Grafo com 100 vértices Não Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/79188b2d-5072-49ec-a3a0-dc194fbc6528)

  -   c. Grafo com 1.000 vértices Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/a9204df4-2536-4a51-91fe-9b6f65911fd1)

  -   d. Grafo com 1.000 vértices Não Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/22463767-0bda-47f0-a958-76e0aa53cd0d)

  -   d. Grafo com 10.000 vértices Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/70013a13-7f09-4753-983d-02e4bf6da127)

  -   e. Grafo com 10.000 vértices Não Direcionado

      ![image](https://github.com/LucasFre26/grafo/assets/99023129/b111b255-f982-4e84-9b51-ef56d1750a29)
      
## 6. A* com caminho de uma origem para todos os vértices
  -   a. Grafo com 100 vértices Direcionado
    
  -   b. Grafo com 100 vértices Não Direcionado

  -   c. Grafo com 1.000 vértices Direcionado

  -   d. Grafo com 1.000 vértices Não Direcionado

  -   d. Grafo com 10.000 vértices Direcionado

  -   e. Grafo com 10.000 vértices Não Direcionado

  -   f. Grafo com 15.000 vértices Direcionado

  -   g. Grafo com 15.000 vértices Não Direcionado
  
## 7. A* com caminho de todos para todos os vértices
  -   a. Grafo com 100 vértices Direcionado
    
  -   b. Grafo com 100 vértices Não Direcionado

  -   c. Grafo com 1.000 vértices Direcionado

  -   d. Grafo com 1.000 vértices Não Direcionado

  -   d. Grafo com 10.000 vértices Direcionado

  -   e. Grafo com 10.000 vértices Não Direcionado

  -   f. Grafo com 15.000 vértices Direcionado

  -   g. Grafo com 15.000 vértices Não Direcionado
