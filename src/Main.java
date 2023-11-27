import java.util.*;

class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = 0;
        char ePonderado, eDirecionado, gerarGrafo, lerGrafo;

        System.out.print("\nDeseja ler um grafo já gerado em MatrizesAdjacencia (Sim = 's' | Nao = 'n'): ");
        lerGrafo = sc.next().charAt(0);

        if (lerGrafo == 's' || lerGrafo == 'S') {
            System.out.print("Qual o nome do arquivo: ");
            String arq = sc.next();

            int matrizAdjacencia[][] = Importar.lerMatrizDeArquivo(arq + ".txt");

            ePonderado = GrafoOp.verificarPonderado(matrizAdjacencia);
            eDirecionado = GrafoOp.verificarDirecionado(matrizAdjacencia);

            imprimeMenu();

            menu(matrizAdjacencia, ePonderado, eDirecionado);

        } else {

            System.out.print("\nDeseja gerar um Grafo aleatorio (Sim = 's' | Nao = 'n'): ");
            gerarGrafo = sc.next().charAt(0);

            if (gerarGrafo == 's' || gerarGrafo == 'S') {
                System.out.print("Número de vértices no grafo: ");
                int numVertices = sc.nextInt();

                System.out.print("O grafo é direcionado (Sim = 's' | Nao = 'n'): ");
                eDirecionado = sc.next().charAt(0);
                boolean direcionado = eDirecionado == 's' || eDirecionado == 'S';

                System.out.print("O grafo é ponderado (Sim = 's' | Nao = 'n'): ");
                ePonderado = sc.next().charAt(0);
                boolean ponderado = ePonderado == 's' || ePonderado == 'S';

                boolean ponderacoesNegativas = false;
                if (ponderado) {
                    System.out.print("Permitir ponderações negativas (Sim = 's' | Nao = 'n'): ");
                    ponderacoesNegativas = sc.next().charAt(0) == 's';
                }

                System.out.printf("\n\n...Gerando Grafo com %d vertices...\n\n", numVertices);

                int[][] grafoAleatorio = Importar.gerarGrafoAleatorio(numVertices, direcionado, ponderado,
                        ponderacoesNegativas);

                System.out.print("Deseja salvar o grafo (Entre com s[sim] e n[nao]): ");
                char salvar = sc.next().charAt(0);

                if(salvar == 's' || salvar == 'S'){
                    System.out.print("Nome do arquivo para salvar a matriz: ");
                    String nomeArquivo = sc.next();
    
                    Importar.salvarMatrizEmArquivo(grafoAleatorio, nomeArquivo);
    
                    System.out.println("Matriz salva em " + nomeArquivo + ".txt com sucesso.");
                }

                imprimeMenu();

                menu(grafoAleatorio, ePonderado, eDirecionado);

            } else if (gerarGrafo == 'n' || gerarGrafo == 'N') {

                System.out.print("Quantos vertices seu grafo vai ter: ");
                n = sc.nextInt();

                System.out.printf("\n...Criando um grafo com %d vertices...\n\n", n);

                int matrizAdjacencia[][] = new int[n][n];

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        matrizAdjacencia[i][j] = 0;
                    }
                }

                System.out.print("Esse grafo e um grafo direcionado (Sim = 's' | Nao = 'n'): ");
                eDirecionado = sc.next().charAt(0);

                System.out.print("\nEsse grafo tem arestas ponderadas (Sim = 's' | Nao = 'n'): ");
                ePonderado = sc.next().charAt(0);

                imprimeMenu();

                menu(matrizAdjacencia, ePonderado, eDirecionado);
            }
        }

        sc.close();
    }

    public static void menu(int matriz[][], char ePonderado, char eDirecionado) {
        Scanner sc = new Scanner(System.in);
        char op;
        char backup = eDirecionado;

        // teste pra Dijkstra
        /*
         * int[][] matriz = {
         * { 0, 5, 10, 6, 0, 0, 0 },
         * { 5, 0, 0, 0, 0, 13, 0 },
         * { 10, 0, 0, 3, 4, 0, 5 },
         * { 6, 0, 3, 0, 6, 11, 0 },
         * { 0, 0, 4, 6, 0, 0, 8 },
         * { 0, 13, 0, 11, 0, 0, 3 },
         * { 0, 0, 5, 0, 8, 3, 0 },
         * };
         */

        // teste Bellman-ford
        /*
         * int [][] matriz = {
         * {0, 6, 5, 5, 0, 0, 0},
         * {0, 0, 0, 0, -1, 0, 0},
         * {0, -2, 0, 0, 1, 0, 0},
         * {0, 0, -1, 0, 0, -1, 0},
         * {0, 0, 0, 0, 0, 0, 3},
         * {0, 0, 0, 0, 0, 0, 3},
         * {0, 0, 0, 0, 0, 0, 0},
         * };
         */

        // teste Bellman-ford
        /*
         * int[][] matriz = {
         * { 0, 3, 8, 0, -4 },
         * { 0, 0, 0, 1, 7 },
         * { 0, 4, 0, 0, 0 },
         * { 2, 0, -5, 0, 0 },
         * { 0, 0, 0, 6, 0 },
         * };
         */

        List<List<Integer>> listaAdjacencia = VerticeOp.matrizParaListaAdjacencia(matriz);

        System.out.print("Qual a sua opçao: ");
        op = sc.next().charAt(0);

        if (op == 'a' || op == 'A') {

            Exibir.matrizAdjacencia(matriz);

            menu(matriz, ePonderado, eDirecionado);
        } else if (op == 'b' || op == 'B') {

            Exibir.listaAdjacencia(listaAdjacencia);

            menu(matriz, ePonderado, eDirecionado);
        } else if (op == 'i' || op == 'd' || op == 'I' || op == 'D') {
            int v1, v2;

            if (op == 'i' || op == 'I') {
                System.out.print("\nEntre com o primeiro vertice que estara ligado: ");
                v1 = sc.nextInt();
                System.out.print("Entre com o segundo vertice que estara ligado: ");
                v2 = sc.nextInt();

                Aresta.inserirAresta(matriz, v1, v2, ePonderado, eDirecionado, sc);

                Exibir.matrizParaListaSucessores(matriz);
                menu(matriz, ePonderado, backup);
            }
            ;
            if (op == 'd' || op == 'D') {
                int r1, r2;

                System.out.print("\nEntre com o primeiro vertice da aresta que sera removida: ");
                r1 = sc.nextInt();
                System.out.print("Entre com o segundo vertice da aresta que sera removida: ");
                r2 = sc.nextInt();

                Aresta.removeAresta(matriz, r1, r2, ePonderado, eDirecionado);

                Exibir.matrizParaListaSucessores(matriz);
                menu(matriz, ePonderado, backup);

            }
            ;
        } else if (op == 'v' || op == 'g') {
            int v = 0;

            if (op == 'v') {
                System.out.print("\nEntre com o vertice que deseja consultar: ");
                v = sc.nextInt();
            }
            ;
            VerticeOp.consultarGrau(matriz, v, op, eDirecionado, ePonderado);

            menu(matriz, ePonderado, backup);

        } else if (op == 't' || op == 'T') {
            int no;

            if (eDirecionado == 'n') {
                System.out.print("\nEntre com o no que queria verificar os seus vizinhos: ");
                no = sc.nextInt();

                VerticeOp.vizinhoVertice(matriz, no, backup);
            } else if (eDirecionado == 's') {
                List<List<Integer>> listaSucessores = Exibir.matrizParaListaSucessores(matriz);

                System.out.print("Entre com o índice do vértice (de 0 a " + (matriz.length - 1) + "): ");
                int indiceVertice = sc.nextInt();

                if (indiceVertice >= 0 && indiceVertice < matriz.length) {
                    VerticeOp.exibirListaSucessores(listaSucessores, indiceVertice);
                } else {
                    System.out.println(
                            "Índice inválido. Certifique-se de que está entre 0 e " + (matriz.length - 1));
                }

            }

            menu(matriz, ePonderado, backup);
        } else if (op == 'r' || op == 'R') {

            if (eDirecionado == 'n') {
                GrafoOp.eRegular(matriz, ePonderado);
            } else if (eDirecionado == 's') {
                GrafoOp.eRegularDirecionado(matriz, ePonderado);
            }

            menu(matriz, ePonderado, eDirecionado);
        } else if (op == 'e' || op == 'E') {

            Exportar.grafo(matriz, ePonderado);

            menu(matriz, ePonderado, eDirecionado);
        } else if (op == 'c') {

            boolean verificaSe = GrafoOp.eCompleto(matriz, ePonderado, eDirecionado);

            System.out.printf("\nO Grafo e completo: %b", verificaSe);
            System.out.println("\n");

            menu(matriz, ePonderado, eDirecionado);
        } else if (op == 'x') {
            if (eDirecionado == 'n') {
                System.out.println("\nO Grafo e conexo: " + GrafoOp.eConexo(matriz));
            } else if (eDirecionado == 's') {
                System.out.println("\nO Grafo e conexo: " + GrafoOp.eConexoDirecionado(matriz));
            }
            System.out.println();

            menu(matriz, ePonderado, eDirecionado);
        } else if (op == 'w') {
            int origem, destino;

            System.out.print("\nEntre com o vértice de origem: ");
            origem = sc.nextInt();
            System.out.print("Entre com o vértice de destino: ");
            destino = sc.nextInt();

            VerticeOp.temCaminho(matriz, origem, destino, eDirecionado);

            menu(matriz, ePonderado, eDirecionado);
        } else if (op == 'p' || op == 'P') {
            int origem = 0;

            System.out.print("\nEntre com o vertice de origem: ");
            origem = sc.nextInt();
            Buscas.buscaProfundidade(matriz, origem);

            menu(matriz, ePonderado, eDirecionado);
        } else if (op == 'l') {
            int origem = 0;

            System.out.print("\nEntre com o vertice de origem: ");
            origem = sc.nextInt();
            Buscas.buscaLargura(matriz, origem);

            menu(matriz, ePonderado, eDirecionado);
        } else if (op == 'k') {
            if (ePonderado == 'n') {
                System.err.print(
                        "\nNao e possivel realizar o algortimo de Dijkstra para Grafo com arestas nao Ponderadas");

                menu(matriz, ePonderado, eDirecionado);
            } else {
                int origem;

                System.out.print("\nEntre com o vértice de origem: ");
                origem = sc.nextInt();

                // Algoritmo de Dijkstra pra FONTE UNICA
                try {
                    AlgCaminhoMinimo.dijkstraFonteUnica(matriz, origem);
                    // dijkstraFonteUnica(matriz, origem);
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage());
                }

                System.out.println("\n");

                // Algoritmo Dijkstra pra TODOS PARA TODOS
                System.out.print("Deseja executar o Algoritmo de Todos para Todos? (Entre com s[sim] e n[nao]): ");
                char per = sc.next().charAt(0);

                if (per == 's' || per == 'S') {
                    try {
                        AlgCaminhoMinimo.dijkstraPares(matriz);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                } else {
                }
                System.out.println();

                menu(matriz, ePonderado, eDirecionado);
            }
        } else if (op == 'm') {
            int origem;

            System.out.print("\nEntre com o vertice de origem: ");
            origem = sc.nextInt();

            // Algoritmo de Bellman-Ford pra Fonte Unica
            try {
                AlgCaminhoMinimo.bellmanFordFonteUnica(matriz, origem);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());

                menu(matriz, ePonderado, eDirecionado);
            }

            // Algoritmo de Bellman-Ford de TODOS PARA TODOS
            System.out.print("Deseja executar o Algoritmo de Todos para Todos? (Entre com s[sim] e n[nao]): ");
            char per1 = sc.next().charAt(0);

            if (per1 == 's' || per1 == 'S') {
                try {
                    AlgCaminhoMinimo.bellmanFordPares(matriz);
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage());

                    menu(matriz, ePonderado, eDirecionado);
                }
            }
            System.out.println();

            menu(matriz, ePonderado, eDirecionado);

        } else if (op == 'h') {

            System.out.print("\nEntre com o vertice de origem: ");
            int origem1 = sc.nextInt();

            try {
                AlgCaminhoMinimo.floydWarshallFonteUnica(matriz, origem1);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.print("\nDeseja executar o Algoritmo de Todos para Todos? (Entre com s[sim] e n[nao]): ");
            char per2 = sc.next().charAt(0);

            try {
                if (per2 == 's' || per2 == 'S') {
                    AlgCaminhoMinimo.floydWarshallPares(matriz);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.println("");
            menu(matriz, ePonderado, eDirecionado);
        } else if (op == 's' || op == 'S') {
            System.out.print("\nEntre com o nome do arquivo: ");
            String arq = sc.next();

            Importar.salvarMatrizEmArquivo(matriz, arq);

            menu(matriz, ePonderado, eDirecionado);
        } else if (op == 'q' || op == 'Q'){
            System.out.print("\nEntre com o nome do arquivo do Grafo desejado: ");
            String nomeArq = sc.next();

            int matrizAdjacencia [][] = Importar.lerMatrizDeArquivo(nomeArq);

            menu(matrizAdjacencia, ePonderado, eDirecionado);
        }
    };

    public static void imprimeMenu() {
        System.out.print("\nO que deseja fazer, entre com sua opçao\n"
                + "Para inserir arestas entre os vertice entre com 'i'\n"
                + "Para remover arestas entre com 'd'\n"
                + "Para salvar Matriz de Adjacencia entre com 's'\n"
                + "Para ler Matriz de Adjacencia entre com 'q'\n"
                + "Para verificar se o grafo e completo entre com 'c'\n"
                + "Para consultar o grau de um vertice entre com 'v'\n"
                + "Para consultar o grau do Grafo entre com 'g'\n"
                + "Para os vizinhos de um vertice entre com 't'\n"
                + "Para verificar se o grafo e conexo entre com 'x'\n"
                + "Para verificar se o grafo é regular entre com 'r'\n"
                + "Para fazer a busca em profundidade entre com 'p'\n"
                + "Para fazer a busca em largura entre com 'l'\n"
                + "Para verificar a existencia de caminho entre dois vertices entre com 'w'\n"
                + "Para exportar o grafo entre com 'e'\n"
                + "Para exibir a lista de Adjacencia entre com 'b'\n"
                + "Para exibir a matriz de adjacencia entre com 'a'.\n"
                + "Para exibir o caminho minimo usando o Algoritmo de Dijkstra entre com 'k'\n"
                + "Para exibir o caminho minimo usando o Algoritmo de Bellman Ford entre com 'm'\n"
                + "Para exibir o caminho minimo usando o Algoritmo de Floyd-Warshall entre com 'h'. ");
    }

};
