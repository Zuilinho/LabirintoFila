package fila;

import java.io.IOException;

/**
 *
 * @author Luan Magalhaes e Luiz Baldao
 */

public class Main {
    public static void main(String[] args) throws IOException{
        
        
        LerLabirinto lerLabirinto = new LerLabirinto();
        String[][] aux = lerLabirinto.alocaLabirinto("/src/labirintos/lab03.txt");
        Labirinto labirinto = new Labirinto(aux);
       
        labirinto.comecaJogada();   
    }
}
