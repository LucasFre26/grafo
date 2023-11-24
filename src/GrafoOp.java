class GrafoOp{
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
                  }
                  ;
              }
              ;
          }
          ;

          grau[i] = count;
      }

      for (int i = 0; i < matriz.length; i++) {
          if (grau[1] != grau[i]) {
              reg = false;
              break;
          }
      }
      ;

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
                  }
                  ;
              }

              if (matriz[j][i] != 0) {
                  if (ePonderado == 'n') {
                      countIn++;
                  } else if (ePonderado == 's') {
                      countIn += matriz[i][j];
                  }
                  ;
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

  private static void buscaProfundidadeEConexo(int matriz[][], int vertice, boolean[] visitado) {
      visitado[vertice] = true;

      for (int i = 0; i < matriz.length; i++) {
          if (matriz[vertice][i] != 0 && !visitado[i]) {
              buscaProfundidadeEConexo(matriz, i, visitado);
          }
      }
  }

  public static boolean eConexo(int matriz[][]) {
      int n = matriz.length;
      boolean[] visitados = new boolean[n];

      int verticeInicial = 0;

      buscaProfundidadeEConexo(matriz, verticeInicial, visitados);

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

      buscaProfundidadeEConexo(matriz, verticeInicial, visitadosIda);

      int[][] matrizTransposta = obterMatrizTransposta(matriz);
      buscaProfundidadeEConexo(matrizTransposta, verticeInicial, visitadosVolta);

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

  public static char verificarDirecionado(int[][] matriz) {
    boolean direcionado = false;

    for (int i = 0; i < matriz.length; i++) {
        for (int j = 0; j < matriz[i].length; j++) {
            if (matriz[i][j] != 0 && matriz[j][i] == 0) {
                direcionado = true;
                break;
            }
        }
        if (direcionado) {
            break;
        }
    }
    return direcionado ? 's' : 'n';
}

public static char verificarPonderado(int[][] matriz) {
    boolean ponderado = false;

    for (int i = 0; i < matriz.length; i++) {
        for (int j = 0; j < matriz[i].length; j++) {
            if (matriz[i][j] != 0) {
                ponderado = true;
                break;
            }
        }
        if (ponderado) {
            break;
        }
    }
    return ponderado ? 's' : 'n';
}
}
