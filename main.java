import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = 0;
        char ePonderado, eDirecionado;

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

        System.out.print("\nO que deseja fazer, entre com sua opção\n"
                + "Para inserir arestas entre os vertice entre com 'i'\n"
                + "Para remover arestas entre com 'd'\n"
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
                + "Para exibir a matriz de adjacencia entre com 'a'. ");

        menu(matrizAdjacencia, ePonderado, eDirecionado);

        sc.close();
    }

    public static void menu(int matriz[][], char ePonderado, char eDirecionado) {
        Scanner sc = new Scanner(System.in);
        char op;
        char backup = eDirecionado;

        System.out.print("Qual a sua opção: ");
        op = sc.next().charAt(0);

        if (op == 'a') {

            System.out.print("\n\n\n");
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz.length; j++) {
                    System.out.print("  " + matriz[i][j]);
                }
                System.out.println();
            }
            System.out.print("\n\n\n");

            menu(matriz, ePonderado, eDirecionado);
        }

        if (op == 'i' || op == 'd') {
            int v1, v2;

            if (op == 'i') {
                System.out.print("\nEntre com o primeiro vertice que estara ligado: ");
                v1 = sc.nextInt();
                System.out.print("Entre com o segundo vertice que estara ligado: ");
                v2 = sc.nextInt();

                if (v1 > matriz.length || v2 > matriz.length) {
                    System.err.printf("\nErro ao cadastrar nova aresta E(%d,%d)\n"
                            + "Verifique o numero de vertice...\n\n", v1, v2);

                    menu(matriz, ePonderado, backup);
                } else {
                    if (ePonderado == 's' || ePonderado == 'S') {
                        int pond;

                        System.out.printf("Entre com o valor da Aresta E(%d, %d): ", v1, v2);
                        pond = sc.nextInt();

                        if (eDirecionado == 's' || eDirecionado == 'S') {
                            matriz[v1][v2] = pond;
                        } else {
                            matriz[v1][v2] = pond;
                            matriz[v2][v1] = pond;
                        };

                        System.out.printf("\nAresta %d(%d,%d) incluida com sucesso\n\n", pond, v1, v2);

                        menu(matriz, ePonderado, backup);
                    } else {
                        if (eDirecionado == 's' || eDirecionado == 'S') {
                            matriz[v1][v2] = 1;
                        } else {
                            matriz[v1][v2] = 1;
                            matriz[v2][v1] = 1;
                        };

                        System.out.printf("\nAresta E(%d,%d) incluida com sucesso\n\n", v1, v2);

                        menu(matriz, ePonderado, backup);
                    }
                };
            };
            if (op == 'd') {
                int r1, r2;

                System.out.print("\nEntre com o primeiro vertice que estara ligado: ");
                r1 = sc.nextInt();
                System.out.print("Entre com o segundo vertice que estara ligado: ");
                r2 = sc.nextInt();

                if (matriz[r1][r2] != 0) {
                    if (r1 > matriz.length || r2 > matriz.length) {
                        System.err.printf("\nErro ao remover aresta E(%d,%d)\n"
                                + "Verifique o numero de vertice...\n\n", r1, r2);

                        menu(matriz, ePonderado, backup);
                    } else {
                        if (eDirecionado == 's' || eDirecionado == 'S') {
                            matriz[r1][r2] = 0;
                        } else {
                            matriz[r1][r2] = 0;
                            matriz[r2][r1] = 0;
                        }

                        System.out.printf("\nAresta E(%d,%d) removida com sucesso\n\n", r1, r2);

                        menu(matriz, ePonderado, backup);
                    }
                    ;
                } else {
                    System.err.printf("\nNão existe arestas nos V(%d) - V(%d)\n\n", r1, r2);
                    menu(matriz, ePonderado, backup);
                }
                ;
            }
            ;
        } else if (op == 'v' || op == 'g') {
            int v = 0;

            if (op == 'v') {
                System.out.print("\nEntre com o vertice que deseja consultar: ");
                v = sc.nextInt();
            }
            ;
            consultarGrau(matriz, v, op, eDirecionado, ePonderado);

            menu(matriz, ePonderado, backup);

        } else if (op == 't') {
            int no;

            if (eDirecionado == 'n') {
                System.out.print("\nEntre com o no que queria verificar os seus vizinhos: ");
                no = sc.nextInt();

                vizinhoVertice(matriz, no, backup);
            } else if (eDirecionado == 's') {
                List<List<Integer>> listaSucessores = matrizParaListaSucessores(matriz);

                System.out.print("Entre com o índice do vértice (de 0 a " + (matriz.length - 1) + "): ");
                int indiceVertice = sc.nextInt();

                if (indiceVertice >= 0 && indiceVertice < matriz.length) {
                    exibirListaSucessores(listaSucessores, indiceVertice);
                } else {
                    System.out.println(
                            "Índice inválido. Certifique-se de que está entre 0 e " + (matriz.length - 1));
                }

            }

            menu(matriz, ePonderado, backup);
        } else if (op == 'r') {

            if (eDirecionado == 'n') {
                eRegular(matriz, ePonderado);
            } else if (eDirecionado == 's') {
                eRegularDirecionado(matriz, ePonderado);
            }

            menu(matriz, ePonderado, eDirecionado);
        } else if (op == 'e') {
            exportarGrafo(matriz, ePonderado);

            menu(matriz, ePonderado, eDirecionado);
        } else if (op == 'c') {
            boolean verificaSe = eCompleto(matriz, ePonderado, eDirecionado);
            System.out.printf("\nO Grafo e completo: %b", verificaSe);
            System.out.println("\n");
            menu(matriz, ePonderado, eDirecionado);
        } else if (op == 'p') {
            // int origem = 0;

            if (ePonderado == 'n' || ePonderado == 'N') {
                System.out.print("\nEntre com o vertice de origem: ");
                // origem = sc.nextInt();
                // buscaProfundidade(matriz, origem);
            } else if (ePonderado == 's' || ePonderado == 'S') {
                System.out.print("\nEntre com o vertice de origem: ");
                // origem = sc.nextInt();
                // buscaProfundidadePonderada(matriz, origem);
            }

            menu(matriz, ePonderado, eDirecionado);
        } else if (op == 'x') {
            if (eDirecionado == 'n') {
                System.out.println("\nO Grafo e conexo: " + eConexo(matriz));
            } else if (eDirecionado == 's') {
                System.out.println("\nO Grafo e conexo: " + eConexoDirecionado(matriz));
            }
            System.out.println();

            menu(matriz, ePonderado, eDirecionado);
        } else if (op == 'w') {
            int origem, destino;

            System.out.print("\nEntre com o vértice de origem: ");
            origem = sc.nextInt();
            System.out.print("Entre com o vértice de destino: ");
            destino = sc.nextInt();

            temCaminho(matriz, origem, destino, eDirecionado);

            menu(matriz, ePonderado, eDirecionado);
        }

        // else if (op == 'p'){
        // buscaEmProfundidade();
        // // }
        // else if (op == 'l'){
        // buscaEmLargura();
        // // }
    };

    public static void consultarGrau(int matriz[][], int v, char op, char eDirecionado, char ePonderado) {
        int count = 0, gIn = 0, gOut = 0;

        if (op == 'v') {
            if (eDirecionado == 's' || eDirecionado == 'S') {
                if (ePonderado == 'n') {
                    for (int i = 0; i < matriz.length; i++) {
                        if (matriz[i][v] != 0) {
                            gIn++;
                        }
                        if (matriz[v][i] != 0) {
                            gOut++;
                        }
                    };
                } else if (ePonderado == 's') {
                    for (int i = 0; i < matriz.length; i++) {
                        if (matriz[i][v] != 0) {
                            gIn += matriz[i][v];
                        }
                        if (matriz[v][i] != 0) {
                            gOut += matriz[v][i];
                        }
                    };
                };
                System.out.printf("\nO vertice V(%d) tem grau de entrada igual a %d\n\n", v, gIn);
                System.out.printf("\nO vertice V(%d) tem grau de saida igual a %d\n\n\n", v, gOut);
            } else {
                if (ePonderado == 'n') {
                    for (int i = 0; i < matriz.length; i++) {
                        if (matriz[i][v] != 0) {
                            count++;
                        };
                    };
                } else if (ePonderado == 's') {
                    for (int i = 0; i < matriz.length; i++) {
                        if (matriz[i][v] != 0) {
                            count += matriz[i][v];
                        };
                    };
                };

                System.out.printf("\nO vertice V(%d) tem grau %d\n\n", v, count);
            }
        } else if (op == 'g') {
            gIn = 0;
            gOut = 0;

            if (eDirecionado == 's' || eDirecionado == 'S') {
                if (ePonderado == 'n') {
                    for (int i = 0; i < matriz.length; i++) {
                        for (int j = 0; j < matriz.length; j++) {
                            if (matriz[i][j] != 0) {
                                gOut++;
                            }
                        }
                    }

                    for (int i = 0; i < matriz.length; i++) {
                        for (int j = 0; j < matriz.length; j++) {
                            if (matriz[j][i] != 0) {
                                gIn++;
                            }
                        }
                    }
                } else if (ePonderado == 's') {
                    for (int i = 0; i < matriz.length; i++) {
                        for (int j = 0; j < matriz.length; j++) {
                            if (matriz[i][j] != 0) {
                                gOut += matriz[i][j];
                            }
                        }
                    }

                    for (int i = 0; i < matriz.length; i++) {
                        for (int j = 0; j < matriz.length; j++) {
                            if (matriz[j][i] != 0) {
                                gIn += matriz[j][i];
                            }
                        }
                    }
                }

                System.out.printf("\nO Grafo tem grau de saída igual a %d\n", gOut);
                System.out.printf("O Grafo tem grau de entrada igual a %d\n\n", gIn);

            } else {
                count = 0;

                for (int i = 0; i < matriz.length; i++) {
                    for (int j = 0; j < matriz.length; j++) {
                        if (matriz[i][j] != 0) {
                            if (ePonderado == 'n') {
                                count++;
                            } else if (ePonderado == 's') {
                                count += matriz[i][j];
                            }
                        };
                    };
                };

                System.out.printf("\nO Grafo tem grau %d\n\n", count * 2);
            }
        }
    };

    public static void vizinhoVertice(int matriz[][], int no, char eDirecionado) {

        if (no >= 0 && no < matriz.length) {
            System.out.printf("\nV(%d) -> {", no);

            for (int i = 0; i < matriz.length; i++) {
                if (matriz[no][i] != 0) {
                    System.out.printf("- %d -", i);
                }
            };

            System.out.print("}\n\n");
        } else {
            System.out.print("No inexistente");
        };
    };

    public static void eRegular(int matriz[][], char ePonderado) {
        boolean reg = true;
        int count = 0;
        int grau[] = new int[matriz.length + 1];

        for (int i = 0; i < matriz.length; i++) {
            count = 0;

            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] != 0) {
                    if (ePonderado == 'n') {
                        count++;
                    } else if (ePonderado == 's') {
                        count += matriz[i][j];
                    };
                };
            };

            grau[i] = count;
        }

        for (int i = 0; i < matriz.length; i++) {
            if (grau[1] != grau[i]) {
                reg = false;
                break;
            }
        };

        System.out.print("\nO Grafo é regular: " + reg);
        System.out.println("\n");
    };

    public static void eRegularDirecionado(int matriz[][], int ePonderado) {
        boolean reg = true;
        int countIn, countOut;
        int gIn[] = new int[matriz.length];
        int gOut[] = new int[matriz.length];

        for (int i = 0; i < matriz.length; i++) {
            countIn = 0;
            countOut = 0;

            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] != 0) {
                    if (ePonderado == 'n') {
                        countOut++;
                    } else if (ePonderado == 's') {
                        countOut += matriz[i][j];
                    };
                }

                if (matriz[j][i] != 0) {
                    if (ePonderado == 'n') {
                        countIn++;
                    } else if (ePonderado == 's') {
                        countIn += matriz[i][j];
                    };
                }
            }

            gOut[i] = countOut;
            gIn[i] = countIn;
        }

        for (int i = 1; i < matriz.length; i++) {
            if (gIn[0] != gIn[i] || gOut[0] != gOut[i]) {
                reg = false;
                break;
            }
        }

        System.out.println("\n\nO Grafo é regular: " + reg);
        System.out.println();
    };

    public static void exportarGrafo(int matriz[][], char ePonderado) {
        try {
            String nomeArquivo;

            Scanner sc = new Scanner(System.in);
            System.out.print("\nQual o nome do arquivo GEXF: ");
            nomeArquivo = sc.nextLine();

            FileWriter writer = new FileWriter(nomeArquivo + ".gexf");

            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<gexf xmlns=\"http://www.gexf.net/1.3draft\" version=\"1.3\">\n");
            writer.write("  <meta lastmodifieddate=\"2023-10-14\">\n");
            writer.write("    <creator>YourAppName</creator>\n");
            writer.write("    <description>A graph exported from your application</description>\n");
            writer.write("  </meta>\n");
            writer.write("  <graph defaultedgetype=\"directed\">\n");

            writer.write("    <nodes>\n");
            for (int i = 0; i < matriz.length; i++) {
                writer.write("      <node id=\"" + i + "\" label=\"" + (char) ('A' + i) + "\" />\n");
            }
            writer.write("    </nodes>\n");

            writer.write("    <edges>\n");
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    if (matriz[i][j] != 0) {
                        String weightAttribute = (ePonderado == 's' || ePonderado == 'S')
                                ? " weight=\"" + matriz[i][j] + "\""
                                : "";

                        writer.write("      <edge id=\"" + i + "_" + j + "\" source=\"" + i + "\" target=\"" + j +
                                "\"" + weightAttribute + " />\n");
                    }
                }
            }
            writer.write("    </edges>\n");

            writer.write("  </graph>\n");
            writer.write("</gexf>");

            writer.close();
            System.out.println("\nGrafo exportado para " + nomeArquivo + ".gexf com sucesso!\n");

        } catch (IOException e) {
            System.err.println("Erro ao exportar o grafo para GEXF: " + e.getMessage());
            System.out.println();
        }
    };

    public static List<List<Integer>> matrizParaListaSucessores(int[][] matriz) {
        List<List<Integer>> listaSucessores = new ArrayList<>();

        int numVertices = matriz.length;

        for (int i = 0; i < numVertices; i++) {
            listaSucessores.add(new ArrayList<>());

            for (int j = 0; j < numVertices; j++) {
                if (matriz[i][j] != 0) {
                    listaSucessores.get(i).add(j);
                }
            }
        }

        return listaSucessores;
    };

    public static void exibirListaSucessores(List<List<Integer>> listaSucessores, int indiceVertice) {
        System.out.print("\nV(" + indiceVertice + ") -> {");
        List<Integer> sucessores = listaSucessores.get(indiceVertice);

        for (int j = 0; j < sucessores.size(); j++) {
            System.out.print(" - " + sucessores.get(j) + " -");
        }

        System.out.println("}\n");
    };

    public static boolean eCompleto(int matriz[][], char ePonderado, char eDirecionado) {
        int n = matriz.length;

        if (eDirecionado == 'n') {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        if (ePonderado == 'n') {
                            if (matriz[i][j] == 0) {
                                return false;
                            }
                        } else if (ePonderado == 's') {
                            if (matriz[i][j] == 0) {
                                return false;
                            }
                        }
                    }
                }
            }
        } else if (eDirecionado == 's') {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        if (ePonderado == 'n') {
                            if (matriz[i][j] == 0 && matriz[j][i] == 0) {
                                return false;
                            }
                        } else if (ePonderado == 's') {
                            if (matriz[i][j] == 0 && matriz[j][i] == 0) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    };

    private static void dfsRecursivo(int matriz[][], int vertice, boolean[] visitado) {
        visitado[vertice] = true;

        for (int i = 0; i < matriz.length; i++) {
            if (matriz[vertice][i] != 0 && !visitado[i]) {
                dfsRecursivo(matriz, i, visitado);
            }
        }
    }

    public static boolean eConexo(int matriz[][]) {
        int n = matriz.length;
        boolean[] visitados = new boolean[n];

        int verticeInicial = 0;

        dfsRecursivo(matriz, verticeInicial, visitados);

        for (boolean visitado : visitados) {
            if (!visitado) {
                return false;
            }
        }
        return true;
    }

    public static boolean eConexoDirecionado(int matriz[][]) {
        int n = matriz.length;
        boolean[] visitadosIda = new boolean[n];
        boolean[] visitadosVolta = new boolean[n];

        int verticeInicial = 0;

        dfsRecursivo(matriz, verticeInicial, visitadosIda);

        int[][] matrizTransposta = obterMatrizTransposta(matriz);
        dfsRecursivo(matrizTransposta, verticeInicial, visitadosVolta);

        for (int i = 0; i < n; i++) {
            if (!visitadosIda[i] || !visitadosVolta[i]) {
                return false;
            }
        }

        return true;
    }

    private static int[][] obterMatrizTransposta(int matriz[][]) {
        int n = matriz.length;
        int[][] transposta = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                transposta[i][j] = matriz[j][i];
            }
        }

        return transposta;
    }

    public static void temCaminho(int matriz[][], int origem, int destino, char eDirecionado) {
        int n = matriz.length;
        boolean[] visitado = new boolean[n];
        List<Integer> caminho = new ArrayList<>();

        if (verificaExistenciaVertice(origem, n) && verificaExistenciaVertice(destino, n)) {
            if (dfsParaCaminho(matriz, origem, destino, visitado, caminho, eDirecionado)) {
                System.out.println("\nExiste um caminho entre os vértices " + origem + " e " + destino + ":");
                exibirCaminho(caminho);
            } else {
                System.out.println("\nNão existe um caminho entre os vértices " + origem + " e " + destino + "\n");
            }
        } else {
            System.out.println("\nVértice de origem ou destino inválido.");
        }
    }

    private static boolean dfsParaCaminho(int matriz[][], int vertice, int destino, boolean[] visitado, List<Integer> caminho, char eDirecionado) {
        visitado[vertice] = true;
        caminho.add(vertice);

        if (vertice == destino) {
            return true;
        }

        for (int i = 0; i < matriz.length; i++) {
            if (matriz[vertice][i] != 0 && !visitado[i]) {
                if (eDirecionado == 's' || eDirecionado == 'S') {
                    if (dfsParaCaminho(matriz, i, destino, visitado, caminho, eDirecionado)) {
                        return true;
                    }
                } else {
                    if (dfsParaCaminho(matriz, i, destino, visitado, caminho, eDirecionado) || dfsParaCaminho(matriz, i, vertice, visitado, caminho, eDirecionado)) {
                        return true;
                    }
                }
            }
        }

        caminho.remove(caminho.size() - 1);  
        return false;
    }


    private static void exibirCaminho(List<Integer> caminho) {
        for (int i = 0; i < caminho.size(); i++) {
            System.out.print(caminho.get(i));
            if (i < caminho.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println("\n");
    }

    public static boolean verificaExistenciaVertice(int vertice, int n) {
        return vertice >= 0 && vertice < n;
    }
};
