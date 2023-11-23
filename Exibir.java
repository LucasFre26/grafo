import java.util.*;

class Exibir {
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

  public static void listaAdjacencia(List<List<Integer>> listaAdjacencia) {
      System.out.println("\nLista de Adjacência:\n");

      for (int i = 0; i < listaAdjacencia.size(); i++) {
          System.out.print("V(" + i + ") -> {");

          List<Integer> vizinhos = listaAdjacencia.get(i);

          for (int j = 0; j < vizinhos.size(); j++) {
              System.out.print(" " + vizinhos.get(j));

              if (j < vizinhos.size() - 1) {
                  System.out.print(",");
              }
          }

          System.out.println("}");
      }

      System.out.println();
  };

  public static void matrizAdjacencia(int matriz[][]) {
      System.out.print("\nMatriz de adjacencia:\n");
      for (int i = 0; i < matriz.length; i++) {
          for (int j = 0; j < matriz.length; j++) {
              System.out.print("  " + matriz[i][j]);
          }
          System.out.println();
      }
      System.out.print("\n");
  };
}
