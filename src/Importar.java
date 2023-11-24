import java.util.*;
import java.io.*;

class Importar {

    public static int[][] gerarGrafoAleatorio(int numVertices, boolean direcionado, boolean ponderado, boolean ponderacoesNegativas) {
        Random random = new Random();
        int[][] grafo = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i != j) {
                    if (direcionado || (i < j)) { 
                        int peso = ponderado ? (ponderacoesNegativas ? random.nextInt(201) - 100 : random.nextInt(101)) : 1;
                        grafo[i][j] = peso;
                        if (!direcionado) {
                            grafo[j][i] = peso;
                        }
                    }
                }
            }
        }

        return grafo;
    }

    public static void salvarMatrizEmArquivo(int[][] matriz, String nomeArquivo) {
        // Cria o diretório "graphs" se não existir
        File directory = new File("MatrizesAdjacencia");
        if (!directory.exists()) {
            directory.mkdir();
        }
        
        try (PrintWriter writer = new PrintWriter(new FileWriter("MatrizesAdjacencia/" + nomeArquivo))) {
            int numVertices = matriz.length;

            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    writer.print(matriz[i][j] + " ");
                }
                writer.println();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar a matriz em arquivo: " + e.getMessage());
        }
    }

    public static int[][] lerMatrizDeArquivo(String nomeArquivo) {
        try (Scanner scanner = new Scanner(new File("MatrizesAdjacencia/" + nomeArquivo))) {
            List<int[]> linhas = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String[] valores = scanner.nextLine().trim().split("\\s+");
                int[] linha = Arrays.stream(valores)
                        .mapToInt(Integer::parseInt)
                        .toArray();
                linhas.add(linha);
            }

            int numLinhas = linhas.size();
            int numColunas = numLinhas > 0 ? linhas.get(0).length : 0;

            int[][] matriz = new int[numLinhas][numColunas];

            for (int i = 0; i < numLinhas; i++) {
                matriz[i] = linhas.get(i);
            }

            return matriz;
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
    }
}