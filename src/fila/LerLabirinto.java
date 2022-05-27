package fila;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LerLabirinto {

    public String[][] alocaLabirinto(String directory) throws IOException {
      
        int contCol = 0;
        String caminhoArquivo = new File("").getAbsolutePath();
        String caminho = caminhoArquivo +"/"+ directory;
        FileReader dir = new FileReader(caminho);
        Path cam = Paths.get(caminho);

        BufferedReader lerArquivo = new BufferedReader(dir);
        String linha = lerArquivo.readLine();

        String[][] response = new String[(int)Files.lines(cam).count()][linha.length()];

        do {
             for(int i = 0; i < linha.length(); i++)
            {
                response[contCol][i] = String.valueOf(linha.charAt(i));
            }
            contCol += 1;
            linha = lerArquivo.readLine();
        } while (linha != null);
        
        return response;
   
    }
}
