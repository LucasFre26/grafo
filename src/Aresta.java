import java.util.*;

class Aresta{
  public static void inserirAresta(int matriz[][], int v1, int v2, char ePonderado, char eDirecionado, Scanner sc) {
      if (v1 > matriz.length || v2 > matriz.length) {
          System.err.printf("\nErro ao cadastrar nova aresta E(%d,%d)\n"
                  + "Verifique o numero de vertice...\n\n", v1, v2);

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
              }
              ;

              System.out.printf("\nAresta %d(%d,%d) incluida com sucesso\n\n", pond, v1, v2);

          } else {
              if (eDirecionado == 's' || eDirecionado == 'S') {
                  matriz[v1][v2] = 1;
              } else {
                  matriz[v1][v2] = 1;
                  matriz[v2][v1] = 1;
              }
              ;

              System.out.printf("\nAresta E(%d,%d) incluida com sucesso\n\n", v1, v2);

          }
      }
      ;
  }

  public static void removeAresta(int matriz[][], int r1, int r2, char ePonderado, char eDirecionado) {
      if (matriz[r1][r2] != 0) {
          if (r1 > matriz.length || r2 > matriz.length) {
              System.err.printf("\nErro ao remover aresta E(%d,%d)\n"
                      + "Verifique o numero de vertice...\n\n", r1, r2);

          } else {
              if (eDirecionado == 's' || eDirecionado == 'S') {
                  matriz[r1][r2] = 0;
              } else {
                  matriz[r1][r2] = 0;
                  matriz[r2][r1] = 0;
              }

              System.out.printf("\nAresta E(%d,%d) removida com sucesso\n\n", r1, r2);

          }
          ;
      } else {
          System.err.printf("\nNão existe arestas nos V(%d) - V(%d)\n\n", r1, r2);
      }
      ;
  }
}
