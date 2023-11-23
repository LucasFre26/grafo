import java.util.*;

class algCaminhoMinimo{
  public static void dijkstraFonteUnica(int[][] grafo, int origem) {
      if (temPesoNegativo(grafo)) {
          throw new IllegalArgumentException("O grafo contém arestas com pesos negativos.");
      }

      long inicioAlgoritmo = System.currentTimeMillis();

      int numVertices = grafo.length;
      int[] distancia = new int[numVertices];
      Integer[] pai = new Integer[numVertices];
      boolean[] visitado = new boolean[numVertices];

      Arrays.fill(distancia, Integer.MAX_VALUE);
      Arrays.fill(pai, null);
      distancia[origem] = 0;

      for (int count = 0; count < numVertices - 1; count++) {
          int u = obterVerticeMinimo(distancia, visitado);
          visitado[u] = true;

          for (int v = 0; v < numVertices; v++) {
              if (!visitado[v] && grafo[u][v] != 0 && distancia[u] != Integer.MAX_VALUE &&
                      distancia[u] + grafo[u][v] < distancia[v]) {
                  distancia[v] = distancia[u] + grafo[u][v];
                  pai[v] = u;
              }
          }
      }

      long fimAlgoritmo = System.currentTimeMillis();

      System.out.println("\nDistâncias mínimas a partir do vértice " + origem + ":\n");
      for (int i = 0; i < numVertices; i++) {
          System.out.println("Do vértice " + origem + " para o vértice " + i + ": Distância = " +
                  (distancia[i] != Integer.MAX_VALUE ? distancia[i] : "infinito") +
                  ", Pai = " + (pai[i] != null ? pai[i] : "null"));
      }

      System.out.printf("\nO algoritmo Dijkstra levou %dms\n", fimAlgoritmo - inicioAlgoritmo);
  }

  private static int obterVerticeMinimo(int[] distancia, boolean[] visitado) {
      int minimo = Integer.MAX_VALUE;
      int indiceMinimo = -1;

      for (int v = 0; v < distancia.length; v++) {
          if (!visitado[v] && distancia[v] <= minimo) {
              minimo = distancia[v];
              indiceMinimo = v;
          }
      }

      return indiceMinimo;
  }

  private static boolean temPesoNegativo(int[][] grafo) {
      for (int i = 0; i < grafo.length; i++) {
          for (int j = 0; j < grafo[i].length; j++) {
              if (grafo[i][j] < 0) {
                  return true;
              }
          }
      }
      return false;
  }

  public static void dijkstraPares(int[][] grafo) {
      if (temPesoNegativo(grafo)) {
          throw new IllegalArgumentException("O grafo contém arestas com pesos negativos.");
      }

      long inicioAlgoritmo = System.currentTimeMillis();

      int numVertices = grafo.length;
      int[][] distancia = new int[numVertices][numVertices];
      Integer[][] pai = new Integer[numVertices][numVertices];

      for (int i = 0; i < numVertices; i++) {
          Arrays.fill(distancia[i], Integer.MAX_VALUE);
          Arrays.fill(pai[i], null);
          distancia[i][i] = 0;
      }

      for (int origem = 0; origem < numVertices; origem++) {
          boolean[] visitado = new boolean[numVertices];
          distancia[origem][origem] = 0;

          for (int count = 0; count < numVertices - 1; count++) {
              int u = obterVerticeMinimo(distancia[origem], visitado);
              visitado[u] = true;

              for (int v = 0; v < numVertices; v++) {
                  if (!visitado[v] && grafo[u][v] != 0 &&
                          distancia[origem][u] != Integer.MAX_VALUE &&
                          distancia[origem][u] + grafo[u][v] < distancia[origem][v]) {
                      distancia[origem][v] = distancia[origem][u] + grafo[u][v];
                      pai[origem][v] = u;
                  }
              }
          }
      }

      long fimAlgoritmo = System.currentTimeMillis();

      System.out.println("\nDistâncias mínimas entre todos os pares de vértices:\n");
      for (int i = 0; i < numVertices; i++) {
          for (int j = 0; j < numVertices; j++) {
              System.out.println("Do vértice " + i + " para o vértice " + j + ": Distância = " +
                      (distancia[i][j] != Integer.MAX_VALUE ? distancia[i][j] : "infinito") +
                      ", Pai = " + (pai[i][j] != null ? pai[i][j] : "null"));
          }
      }

      System.out.printf("\nO algoritmo Dijkstra Todos-para-Todos levou %dms\n", fimAlgoritmo - inicioAlgoritmo);
  }

  public static void bellmanFordFonteUnica(int[][] grafo, int origem) {
      int numVertices = grafo.length;
      int[] distancia = new int[numVertices];
      Integer[] pai = new Integer[numVertices];

      long inicioAlgoritmo = System.currentTimeMillis();

      Arrays.fill(distancia, Integer.MAX_VALUE);
      Arrays.fill(pai, null);
      distancia[origem] = 0;

      for (int count = 0; count < numVertices - 1; count++) {
          for (int u = 0; u < numVertices; u++) {
              for (int v = 0; v < numVertices; v++) {
                  if (grafo[u][v] != 0 && distancia[u] != Integer.MAX_VALUE &&
                          distancia[u] + grafo[u][v] < distancia[v]) {
                      distancia[v] = distancia[u] + grafo[u][v];
                      pai[v] = u;
                  }
              }
          }
      }

      for (int u = 0; u < numVertices; u++) {
          for (int v = 0; v < numVertices; v++) {
              if (grafo[u][v] != 0 && distancia[u] != Integer.MAX_VALUE &&
                      distancia[u] + grafo[u][v] < distancia[v]) {
                  throw new IllegalArgumentException("O grafo contém um ciclo de peso negativo.");
              }
          }
      }

      long fimAlgoritmo = System.currentTimeMillis();

      System.out.println("\nDistâncias mínimas a partir do vértice " + origem + ":\n");
      for (int i = 0; i < numVertices; i++) {
          System.out.println("Do vértice " + origem + " para o vértice " + i + ": Distância = " +
                  (distancia[i] != Integer.MAX_VALUE ? distancia[i] : "infinito") +
                  ", Pai = " + (pai[i] != null ? pai[i] : "null"));
      }

      System.out.printf("\nO algoritmo Bellman Ford levou %dms\n", fimAlgoritmo - inicioAlgoritmo);
  }

  public static void bellmanFordPares(int[][] grafo) {
      int numVertices = grafo.length;
      int[][] distancia = new int[numVertices][numVertices];
      Integer[][] pai = new Integer[numVertices][numVertices];

      long inicioAlgoritmo = System.currentTimeMillis();

      for (int i = 0; i < numVertices; i++) {
          Arrays.fill(distancia[i], Integer.MAX_VALUE);
          Arrays.fill(pai[i], null);
          distancia[i][i] = 0;
      }

      for (int count = 0; count < numVertices - 1; count++) {
          for (int origem = 0; origem < numVertices; origem++) {
              for (int u = 0; u < numVertices; u++) {
                  for (int v = 0; v < numVertices; v++) {
                      if (grafo[u][v] != 0 && distancia[origem][u] != Integer.MAX_VALUE &&
                              distancia[origem][u] + grafo[u][v] < distancia[origem][v]) {
                          distancia[origem][v] = distancia[origem][u] + grafo[u][v];
                          pai[origem][v] = u;
                      }
                  }
              }
          }
      }

      for (int origem = 0; origem < numVertices; origem++) {
          for (int u = 0; u < numVertices; u++) {
              for (int v = 0; v < numVertices; v++) {
                  if (grafo[u][v] != 0 && distancia[origem][u] != Integer.MAX_VALUE &&
                          distancia[origem][u] + grafo[u][v] < distancia[origem][v]) {
                      throw new IllegalArgumentException("O grafo contém um ciclo de peso negativo.");
                  }
              }
          }
      }

      long fimAlgoritmo = System.currentTimeMillis();

      System.out.println("\nDistâncias mínimas entre todos os pares de vértices:\n");
      for (int origem = 0; origem < numVertices; origem++) {
          for (int i = 0; i < numVertices; i++) {
              System.out.println("Do vértice " + origem + " para o vértice " + i + ": Distância = " +
                      (distancia[origem][i] != Integer.MAX_VALUE ? distancia[origem][i] : "infinito") +
                      ", Pai = " + (pai[origem][i] != null ? pai[origem][i] : "null"));
          }
      }

      System.out.printf("\nO algoritmo Bellman-Ford Todos-para-Todos levou %dms\n", fimAlgoritmo - inicioAlgoritmo);
  }
}
