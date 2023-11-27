import java.util.*;

public class AEstrela {

    static Scanner sc = new Scanner(System.in);

    static class Node {
        int x, y;
        int g, h, f;
        Node pai;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Função de heurística para estimar o custo do caminho restante
    static int heuristica(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    // Algoritmo A*
    static List<Node> aEstrela(int[][] grade, Node inicio, Node objetivo) {
        Set<Node> conjuntoAberto = new HashSet<>();
        Set<Node> conjuntoFechado = new HashSet<>();

        Map<Node, Integer> custoG = new HashMap<>();
        custoG.put(inicio, 0);

        Map<Node, Integer> custoF = new HashMap<>();
        custoF.put(inicio, heuristica(inicio, objetivo));

        conjuntoAberto.add(inicio);

        while (!conjuntoAberto.isEmpty()) {
            // Seleciona o nó com menor custo total (f)
            Node atual = conjuntoAberto.stream().min(Comparator.comparingInt(custoF::get)).get();

            // Verifica se o nó atual é o objetivo
            if (atual.equals(objetivo)) {
                return reconstruirCaminho(atual);
            }

            conjuntoAberto.remove(atual);
            conjuntoFechado.add(atual);

            // Gera vizinhos do nó atual
            for (Node vizinho : obterVizinhos(grade, atual)) {
                if (conjuntoFechado.contains(vizinho)) {
                    continue;
                }

                // Calcula o custo G tentativo
                int custoGTentativo = custoG.get(atual) + 1;

                // Verifica se o vizinho não está no conjunto aberto ou o novo custo G é menor
                if (!conjuntoAberto.contains(vizinho) || custoGTentativo < custoG.get(vizinho)) {
                    custoG.put(vizinho, custoGTentativo);
                    custoF.put(vizinho, custoGTentativo + heuristica(vizinho, objetivo));
                    vizinho.pai = atual;

                    if (!conjuntoAberto.contains(vizinho)) {
                        conjuntoAberto.add(vizinho);
                    }
                }
            }
        }

        return null; // Se o conjunto aberto ficar vazio e o objetivo não for alcançado, retorna null
    }

    // Gera a lista de vizinhos válidos para um nó
    static List<Node> obterVizinhos(int[][] grade, Node no) {
        List<Node> vizinhos = new ArrayList<>();
        int linhas = grade.length;
        int colunas = grade[0].length;

        int[][] movimentos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] movimento : movimentos) {
            int novoX = no.x + movimento[0];
            int novoY = no.y + movimento[1];

            // Verifica se as coordenadas estão dentro dos limites e a célula é válida (0)
            if (novoX >= 0 && novoX < linhas && novoY >= 0 && novoY < colunas && grade[novoX][novoY] == 0) {
                vizinhos.add(new Node(novoX, novoY));
            }
        }

        return vizinhos;
    }

    // Reconstrói o caminho a partir do nó final até o início
    static List<Node> reconstruirCaminho(Node atual) {
        List<Node> caminho = new ArrayList<>();
        while (atual != null) {
            caminho.add(atual);
            atual = atual.pai;
        }
        Collections.reverse(caminho);
        return caminho;
    }

    // Função principal para iniciar o algoritmo A*
    public static void iniciaAEstrela(int matriz[][]) {

        // Obtenção das coordenadas do nó de origem
        System.out.print("Informe a coordenada x do nó de origem: ");
        int inicioX = sc.nextInt();

        System.out.print("Informe a coordenada y do nó de origem: ");
        int inicioY = sc.nextInt();

        Node inicio = new Node(inicioX, inicioY);

        // Obtenção das coordenadas do nó de destino
        System.out.print("Informe a coordenada x do nó de destino: ");
        int objetivoX = sc.nextInt();

        System.out.print("Informe a coordenada y do nó de destino: ");
        int objetivoY = sc.nextInt();

        Node objetivo = new Node(objetivoX, objetivoY);

        // Chamada da função A* e obtenção do caminho mínimo
        List<Node> caminho = aEstrela(matriz, inicio, objetivo);

        // Impressão do caminho mínimo
        System.out.println("Caminho mínimo de (" + inicio.x + ", " + inicio.y + ") para (" + objetivo.x + ", " + objetivo.y + "):");

        if (caminho != null) {
            for (Node no : caminho) {
                System.out.println("(" + no.x + ", " + no.y + ")");
            }
        } else {
            System.out.println("Caminho não encontrado.");
        }
    }
}
