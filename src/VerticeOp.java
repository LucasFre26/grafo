import java.util.*;

class VerticeOp{
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
                  }
                  ;
              } else if (ePonderado == 's') {
                  for (int i = 0; i < matriz.length; i++) {
                      if (matriz[i][v] != 0) {
                          gIn += matriz[i][v];
                      }
                      if (matriz[v][i] != 0) {
                          gOut += matriz[v][i];
                      }
                  }
                  ;
              }
              ;
              System.out.printf("\nO vertice V(%d) tem grau de entrada igual a %d\n\n", v, gIn);
              System.out.printf("\nO vertice V(%d) tem grau de saida igual a %d\n\n\n", v, gOut);
          } else {
              if (ePonderado == 'n') {
                  for (int i = 0; i < matriz.length; i++) {
                      if (matriz[i][v] != 0) {
                          count++;
                      }
                      ;
                  }
                  ;
              } else if (ePonderado == 's') {
                  for (int i = 0; i < matriz.length; i++) {
                      if (matriz[i][v] != 0) {
                          count += matriz[i][v];
                      }
                      ;
                  }
                  ;
              }
              ;

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
                      }
                      ;
                  }
                  ;
              }
              ;

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
          }
          ;

          System.out.print("}\n\n");
      } else {
          System.out.print("No inexistente");
      }
      ;
  };

  public static void exibirListaSucessores(List<List<Integer>> listaSucessores, int indiceVertice) {
      System.out.print("\nV(" + indiceVertice + ") -> {");
      List<Integer> sucessores = listaSucessores.get(indiceVertice);

      for (int j = 0; j < sucessores.size(); j++) {
          System.out.print(" - " + sucessores.get(j) + " -");
      }

      System.out.println("}\n");
  };

  public static List<List<Integer>> matrizParaListaAdjacencia(int[][] matriz) {
      List<List<Integer>> listaAdjacencia = new ArrayList<>();

      int numVertices = matriz.length;

      for (int i = 0; i < numVertices; i++) {
          listaAdjacencia.add(new ArrayList<>());

          for (int j = 0; j < numVertices; j++) {
              if (matriz[i][j] != 0) {
                  listaAdjacencia.get(i).add(j);
              }
          }
      }

      return listaAdjacencia;
  };

  public static void temCaminho(int matriz[][], int origem, int destino, char eDirecionado) {
      int n = matriz.length;
      boolean[] visitado = new boolean[n];
      List<Integer> caminho = new ArrayList<>();

      if (verificaExistenciaVertice(origem, n) && verificaExistenciaVertice(destino, n)) {
          if (buscaProfundidadeTemCaminho(matriz, origem, destino, visitado, caminho, eDirecionado)) {
              System.out.println("\nExiste um caminho entre os vértices " + origem + " e " + destino + ":");
              exibirCaminho(caminho);
          } else {
              System.out.println("\nNão existe um caminho entre os vértices " + origem + " e " + destino + "\n");
          }
      } else {
          System.out.println("\nVértice de origem ou destino inválido.");
      }
  }

  private static boolean buscaProfundidadeTemCaminho(int matriz[][], int vertice, int destino, boolean[] visitado,
          List<Integer> caminho, char eDirecionado) {
      visitado[vertice] = true;
      caminho.add(vertice);

      if (vertice == destino) {
          return true;
      }

      for (int i = 0; i < matriz.length; i++) {
          if (matriz[vertice][i] != 0 && !visitado[i]) {
              if (eDirecionado == 's' || eDirecionado == 'S') {
                  if (buscaProfundidadeTemCaminho(matriz, i, destino, visitado, caminho, eDirecionado)) {
                      return true;
                  }
              } else {
                  if (buscaProfundidadeTemCaminho(matriz, i, destino, visitado, caminho, eDirecionado)
                          || buscaProfundidadeTemCaminho(matriz, i, vertice, visitado, caminho, eDirecionado)) {
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
}
