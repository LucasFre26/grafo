import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Exportar {
    static Scanner sc = new Scanner(System.in);

    public static void grafo(int matriz[][], char ePonderado) {
        try {

            String nomeArquivo;

            System.out.print("\nQual o nome do arquivo GEXF: ");
            nomeArquivo = sc.nextLine();

            // olhar o caminho ta exportanto errado
            File directory = new File("graphs");
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Cria o arquivo no diretório "graphs"
            FileWriter writer = new FileWriter("graphs/" + nomeArquivo + ".gexf");

            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<gexf xmlns=\"http://www.gexf.net/1.3draft\" version=\"1.3\">\n");
            writer.write("  <meta lastmodifieddate=\"2023-10-14\">\n");
            writer.write("    <creator>YourAppName</creator>\n");
            writer.write("    <description>A graph exported from your application</description>\n");
            writer.write("  </meta>\n");
            writer.write("  <graph defaultedgetype=\"directed\">\n");

            writer.write("    <nodes>\n");
            for (int i = 0; i < matriz.length; i++) {
                writer.write("      <node id=\"" + i + "\" label=\"" + (char) ('A' + i) + "\" />\n");
            }
            writer.write("    </nodes>\n");

            writer.write("    <edges>\n");
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    if (matriz[i][j] != 0) {
                        String weightAttribute = (ePonderado == 's' || ePonderado == 'S')
                                ? " weight=\"" + matriz[i][j] + "\""
                                : "";

                        writer.write("      <edge id=\"" + i + "_" + j + "\" source=\"" + i + "\" target=\"" + j +
                                "\"" + weightAttribute + " />\n");
                    }
                }
            }
            writer.write("    </edges>\n");

            writer.write("  </graph>\n");
            writer.write("</gexf>");

            writer.close();
            System.out.println("\nGrafo exportado para graphs/" + nomeArquivo + ".gexf com sucesso!\n");

        } catch (IOException e) {
            System.err.println("Erro ao exportar o grafo para GEXF: " + e.getMessage());
            System.out.println();
        }
    }

}
