import java.util.*;

public class AEstrela {
    
    static Scanner sc = new Scanner(System.in);

    public static List<int[]> buscar(int[][] matriz, int[] inicio, int[] fim) {
        // Cria a lista de abertos, que contém os nós que ainda não foram explorados.
        PriorityQueue<No> abertos = new PriorityQueue<>(Comparator.comparingInt(No::getCusto));

        // Cria a lista de fechados, que contém os nós que já foram explorados.
        List<No> fechados = new ArrayList<>();

        // Adiciona o nó inicial à lista de abertos.
        abertos.add(new No(inicio, 0, 0, null));

        while (!abertos.isEmpty()) {
            // Remove o nó com o menor custo da lista de abertos.
            No atual = abertos.poll();

            // Se o nó atual for o objetivo, retorna o caminho.
            if (Arrays.equals(atual.posicao, fim)) {
                return atual.caminho;
            }

            // Adiciona o nó atual à lista de fechados.
            fechados.add(atual);

            // Para cada vizinho do nó atual:
            for (int[] vizinho : getVizinhos(matriz, atual.posicao)) {
                // Se o vizinho não estiver na lista de fechados:
                if (!fechados.contains(vizinho)) {
                    // Calcula o custo estimado do vizinho.
                    int custoEstimativo = atual.custo + getCustoMovimentacao(atual.posicao, vizinho);

                    // Se o vizinho não estiver na lista de abertos ou se seu custo for menor que o custo atual:
                    if (!abertos.contains(vizinho) || custoEstimativo < abertos.peek().custo) {
                        // Atualiza o custo do vizinho.
                        abertos.add(new No(vizinho, custoEstimativo, getCustoHeuristica(vizinho, fim), atual));
                    }
                }
            }
        }

        // Se o objetivo não for encontrado, retorna null.
        return null;
    }

    private static List<int[]> getVizinhos(int[][] matriz, int[] posicao) {
        List<int[]> vizinhos = new ArrayList<>();

        int altura = matriz.length;
        int largura = matriz[0].length;

        // Para cada direção possível:
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // Calcula a posição do vizinho.
                int novoX = posicao[0] + i;
                int novoY = posicao[1] + j;

                // Se a posição for válida e não for um obstáculo:
                if (novoX >= 0 && novoX < altura && novoY >= 0 && novoY < largura && matriz[novoX][novoY] != 1) {
                    // Adiciona o vizinho à lista.
                    vizinhos.add(new int[]{novoX, novoY});
                }
            }
        }

        return vizinhos;
    }

    private static int getCustoMovimentacao(int[] origem, int[] destino) {
        return Math.abs(origem[0] - destino[0]) + Math.abs(origem[1] - destino[1]);
    }

    private static int getCustoHeuristica(int[] posicao, int[] fim) {
        return Math.abs(posicao[0] - fim[0]) + Math.abs(posicao[1] - fim[1]);
    }

    private static class No {

        public int[] posicao;
        public int custo;
        public int custoHeuristica;
        public No pai;
        public List<int[]> caminho;

        public No(int[] posicao, int custo, int custoHeuristica, No pai) {
            this.posicao = posicao;
            this.custo = custo;
            this.custoHeuristica = custoHeuristica;
            this.pai = pai;

            // Atualiza o caminho com base no pai
            if (pai != null) {
                this.caminho = new ArrayList<>(pai.caminho);
                this.caminho.add(posicao);
            } else {
                this.caminho = new ArrayList<>();
                this.caminho.add(posicao);
            }
        }

        public int getCusto() {
            return custo + custoHeuristica;
        }
    }

    public static void iniciaAEstrela(int matriz[][], int inicio[], int fim[]) {
        // Chamando o algoritmo A* para encontrar o caminho mínimo
        long inicioAlgoritmo = System.currentTimeMillis();

        List<int[]> caminho = AEstrela.buscar(matriz, inicio, fim);

        long fimAlgoritmo = System.currentTimeMillis();

        System.out.printf("\nO algoritmo A* (A Estrela) levou %dms\n", fimAlgoritmo - inicioAlgoritmo);

        System.out.print("\nDeseja imprimir os caminhos minimos (Entre com s[sim] e n[nao]): ");
        boolean op = sc.next().charAt(0) == 's' || sc.next().charAt(0) == 'S';

        if(op){
            // Imprimindo o resultado
            if (caminho != null) {
                System.out.println("\nCaminho mínimo encontrado:");
                for (int[] ponto : caminho) {
                    System.out.println("(" + ponto[0] + ", " + ponto[1] + ")");
                }
                System.out.println();
            } else {
                System.out.println("\nCaminho não encontrado.");
            }
        }
    }
}
