import java.util.*;

class Buscas {
  private static int tempo;
  
  public static void buscaProfundidade(int matriz[][], int origem) {
      int n = matriz.length;
      boolean[] visitado = new boolean[n];
      int[] tempoDescobrimento = new int[n];
      int[] tempoTermino = new int[n];
      int[] pai = new int[n];

      tempo = 0;

      for (int i = 0; i < n; i++) {
          if (!visitado[i]) {
              pai[i] = -1;
              buscaProfundidadeVisit(matriz, i, visitado, tempoDescobrimento, tempoTermino, pai);
          }
      }

      exibirBuscaProfundidade(origem, tempoDescobrimento, tempoTermino, pai);
  }

  private static void buscaProfundidadeVisit(int matriz[][], int vertice, boolean[] visitado,
          int[] tempoDescobrimento, int[] tempoTermino, int[] pai) {
      visitado[vertice] = true;
      tempoDescobrimento[vertice] = ++tempo;

      for (int i = 0; i < matriz.length; i++) {
          if (matriz[vertice][i] != 0 && !visitado[i]) {
              pai[i] = vertice;
              buscaProfundidadeVisit(matriz, i, visitado, tempoDescobrimento, tempoTermino, pai);
          }
      }

      tempoTermino[vertice] = ++tempo;
  }

  private static void exibirBuscaProfundidade(int origem, int[] tempoDescobrimento, int[] tempoTermino, int[] pai) {
      System.out.println("\n");
      System.out.println("Vertice | TD | TT | Pai");
      for (int i = 0; i < tempoDescobrimento.length; i++) {
          System.out.printf("   %d    | %d  | %d  |  ", i, tempoDescobrimento[i], tempoTermino[i]);
          if (pai[i] == -1) {
              System.out.print("null");
          } else {
              System.out.print(pai[i]);
          }
          System.out.println();
      }
      System.out.println("\n");
  }

  public static void buscaLargura(int matriz[][], int origem) {
      int n = matriz.length;
      int[] distancia = new int[n];
      int[] pai = new int[n];
      boolean[] visitado = new boolean[n];

      for (int i = 0; i < n; i++) {
          distancia[i] = Integer.MAX_VALUE;
          pai[i] = -1;
          visitado[i] = false;
      }

      distancia[origem] = 0;

      Queue<Integer> fila = new LinkedList<>();
      fila.add(origem);

      while (!fila.isEmpty()) {
          int u = fila.poll();

          for (int v = 0; v < n; v++) {
              if (matriz[u][v] != 0 && !visitado[v]) {
                  fila.add(v);
                  visitado[v] = true;

                  if (distancia[u] + matriz[u][v] < distancia[v]) {
                      distancia[v] = distancia[u] + matriz[u][v];
                      pai[v] = u;
                  }
              }
          }
      }

      exibirBuscaLargura(origem, distancia, pai);
  }

  private static void exibirBuscaLargura(int origem, int[] distancia, int[] pai) {
      System.out.println("\n");
      System.out.println("Vertice | Distancia | Pai");
      for (int i = 0; i < distancia.length; i++) {
          System.out.printf("   %d    | ", i);

          if (distancia[i] == Integer.MAX_VALUE) {
              System.out.print("Infinito");
          } else {
              System.out.print(distancia[i] + "       ");
          }

          System.out.printf("  |  ");

          if (pai[i] == -1) {
              System.out.print("null");
          } else {
              System.out.print(pai[i]);
          }

          System.out.println();
      }
      System.out.println("\n");
  }
}
