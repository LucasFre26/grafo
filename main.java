import java.util.Scanner;

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
               + "Para consultar o grau de um vertice entre com 'v'\n"
               + "Para consultar o grau do Grafo entre com 'g'\n"
               + "Para os vizinhos de um vertice entre com 't'\n"
               + "Para verificar se o grafo e conexo entre com 'x'\n"
               + "Para verificar se o grafo é regular entre com 'r'\n"
               + "Para fazer a busca em profundidade entre com 'p'\n"
               + "Para fazer a busca em largura entre com 'l'\n"
               + "Para verificar a existencia de caminho entre dois vertices entre com 'c'\n"
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
                    if (ePonderado == 's' || op == 'S') {
                        int pond;

                        System.out.printf("Entre com o valor da Aresta E(%d, %d)", v1, v2);
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
                        }
                        ;

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
            consultarGrau(matriz, v, op, eDirecionado);

            menu(matriz, ePonderado, backup);
        } else if (op == 't') {
            int no;

            System.out.print("\nEntre com o no que queria verificar os seus vizinhos: ");
            no = sc.nextInt();

            vizinhoVertice(matriz, no, backup);

            menu(matriz, ePonderado, backup);
        }
        else if (op == 'r'){
          boolean reg = true;
          int count = 0;
          int grau [] = new int[matriz.length + 1];

          for (int i = 0; i < matriz.length; i++) {
            count = 0; 
        
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] != 0) {
                    count++;
                }
            }
            grau[i] = count;  
         }
          
          for(int i=0; i<matriz.length; i++){
            if(grau[1] != grau[i]){
              reg = false;
              break;
            }
          };
      
          System.out.print("\nO Grafo é regular: " +reg);
          System.out.println("\n");
          
          menu(matriz, ePonderado, eDirecionado);
        };
        // else if (op == 'p' || op == 'l'){
        // executaBuscas();
        // };
        // else if (op == 'c'){
        // temCaminho();
        // };
    };

    public static void consultarGrau(int matriz[][], int v, char op, char eDirecionado) {
        int count = 0, gIn = 0, gOut = 0;

        if (op == 'v') {
            if (eDirecionado == 's' || eDirecionado == 'S') {
                for (int i = 0; i < matriz.length; i++) {
                    if (matriz[i][v] != 0) {
                        gIn++;
                    }
                    if (matriz[v][i] != 0) {
                        gOut++;
                    }
                }
                ;
                System.out.printf("\n\nO vertice V(%d) tem grau de entrada igual a %d\n\n", v, gIn);
                System.out.printf("\nO vertice V(%d) tem grau de saida igual a %d\n\n\n", v, gOut);
            } else {
                for (int i = 0; i < matriz.length; i++) {
                    if (matriz[i][v] != 0) {
                        count++;
                    };
                };
                System.out.printf("\n\nO vertice V(%d) tem grau %d\n\n", v, count);
            }
        } else if (op == 'g') {
            count = 0;

            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz.length; j++) {
                    if (matriz[i][j] != 0) {
                        count++;
                    }
                    ;
                }
                ;
            }
            ;
            System.out.printf("\nO Grafo tem grau %d\n\n", count * 2);
        }
    };

    public static void vizinhoVertice(int matriz[][], int no, char eDirecionado) {

        if (no >= 0 && no < matriz.length) {
            System.out.printf("\nOs vizinhos do %d sao: {", no);

            for (int i = 0; i < matriz.length; i++) {
                if (matriz[no][i] != 0) {
                    System.out.printf("- %d -", i);
                }
            }
            ;

            System.out.print("}\n\n");
        } else {
            System.out.print("No inexistente");
        }
        ;
    };
}
