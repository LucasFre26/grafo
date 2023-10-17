## Estrutura Geral do Programa

O programa consiste em uma série de operações que podem ser realizadas em um grafo. O usuário é solicitado a fornecer detalhes sobre o grafo, como o número de vértices, se é direcionado e se as arestas são ponderadas. Em seguida, o usuário pode escolher entre várias operações, como inserir ou remover arestas, verificar a completude do grafo, consultar o grau de um vértice, realizar buscas em profundidade e largura, verificar a existência de caminhos, exportar o grafo para um formato GEXF, entre outras.

## Função Principal (`main`)

A função principal `main` é responsável por interagir com o usuário, obter informações sobre o grafo e oferecer um menu para realizar várias operações. Ele chama a função `menu` para processar as escolhas do usuário.

## Função `menu`

A função `menu` implementa um menu de operações que podem ser realizadas no grafo. As opções incluem inserção e remoção de arestas, consultas de grau, verificação de propriedades do grafo (como ser regular, completo ou conexo), exportação do grafo e execução de algoritmos de busca em profundidade e largura.

## Operações no Grafo

### Inserção e Remoção de Arestas (`inserirAresta` e `removeAresta`)

As funções `inserirAresta` e `removeAresta` permitem a inserção e remoção de arestas no grafo, com suporte para grafos direcionados e não direcionados, bem como arestas ponderadas.

### Consulta de Grau (`consultarGrau`)

A função `consultarGrau` é responsável por calcular e exibir o grau de um vértice ou o grau total do grafo. Ela considera se o grafo é direcionado, não direcionado, ponderado ou não ponderado.

### Consulta de Grau (`vizinhoVertice`)

A função `consultarGrau` é responsável por exibir os respectivos vizinhos de um determinado vértice, usando a busca em Profundidade.

### Verificação de Propriedades do Grafo

- **É Regular (`eRegular`):** Verifica se o grafo é regular.
- **É Regular Direcionado (`eRegularDirecionado`):** Verifica se o grafo direcionado é regular.
- **É Completo (`eCompleto`):** Verifica se o grafo é completo.

### Exportação do Grafo (`exportarGrafo`)

A função `exportarGrafo` exporta o grafo para um arquivo GEXF, um formato de arquivo para representação de grafos.

### Verificação de Conexidade (`eConexo` e `eConexoDirecionado`)

As funções `eConexo` e `eConexoDirecionado` usam a busca em profundidade para verificar se o grafo é conexo, levando em consideração a direção das arestas.

### Verificação da Existência de Caminho (`temCaminho`)

A função `temCaminho` verifica se há um caminho entre dois vértices usando busca em profundidade.

## Outras Funções Auxiliares

- **`matrizParaListaSucessores` e `exibirListaSucessores`:** Convertem a matriz de adjacência em uma lista de sucessores;

- **`verificaExistenciaVertice`:** Verifica se um vértice existe no grafo.

- **`obterMatrizTransposta`:** Obtém a matriz transposta do grafo.
