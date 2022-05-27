package fila;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import java.util.Scanner;

public class Labirinto {

    Scanner scan = new Scanner(System.in);

    Fila q = new Fila(50);
    Fila moveFila = new Fila(50);

    int aumentaComando = 0;
    private boolean ganhouJogo = false;
    private String[][] guardaLabirinto;
    private int[] posicaoAtual = new int[2];
    public Labirinto(String[][] guardaLabirinto) {
        this.guardaLabirinto = guardaLabirinto;
    }

    public void imprimeLabirinto() {

        for (int l = 0; l < guardaLabirinto.length; l++) {
            for (int c = 0; c < guardaLabirinto[0].length; c++) {
                System.out.print(guardaLabirinto[l][c] + " "); //imprime caracter a caracter
            }
            System.out.println(" "); //muda de linha
        }
    }

    public void preenchePosicao(int linha, int coluna) throws InterruptedException {
        
        if (!(guardaLabirinto[linha][coluna].equals("#")) && !(guardaLabirinto[linha][coluna].equals("E"))) {
            if (guardaLabirinto[linha][coluna].equals("S"))
                System.out.println("Voce venceu!");
            else
                guardaLabirinto[linha][coluna] = "?";
            imprimeLabirinto();
            MILLISECONDS.sleep(1500);
            
        } else if (guardaLabirinto[linha][coluna].equals("E")) {
            System.out.println("Movimento inválido, tente novamente");
        } 
          else {
            System.err.println("Movimento invalido, programa encerrado!");
        }
    }

    public void limpaLabirinto() {
        for (int l = 0; l < guardaLabirinto.length; l++) {
            for (int c = 0; c < guardaLabirinto[0].length; c++) {
                if (guardaLabirinto[l][c].equals("?")) {
                    guardaLabirinto[l][c] = " ";
                }
            }
        }

        q.desenfileira();
    }

    public void imprimeComandos() {

        imprimeLabirinto();
        System.out.println("");
        System.out.println("Insira seus passos ate encontar o 'S'");
        System.out.println("Comandos enfileirados: " + q.tamanho());
        System.out.println("Comandos disponiveis:");
        System.out.println("direita");
        System.out.println("esquerda");
        System.out.println("cima");
        System.out.println("baixo");
        System.out.println("comecar");
        System.out.println("reiniciar");

    }

    public void comecaJogada() throws InterruptedException {

        // linha, posicao 0 é linha
        this.posicaoAtual[0] = 1;

        //coluna, posicao 1 é coluna
        this.posicaoAtual[1] = 1;
        do{
            imprimeComandos();
            System.out.println("");
            String jogada = scan.next();
            movimenta(jogada);
        
        } while (ganhouJogo == false);
        
        if (ganhouJogo == true)
            System.out.println("Voce venceu!");
    }

    public void movimenta(String jogada) throws InterruptedException {

        switch (jogada) {
            case "direita":
                posicaoAtual[1] = posicaoAtual[1] + 1;
                q.enfileira(Integer.toString(posicaoAtual[0]) + "," + Integer.toString(posicaoAtual[1]));
                moveFila.enfileira("direita");
                break;
                
            case "esquerda":
                posicaoAtual[1] = posicaoAtual[1] - 1;
                q.enfileira(Integer.toString(posicaoAtual[0]) + "," + Integer.toString(posicaoAtual[1]));
                moveFila.enfileira("esquerda");
                break;
            
            case "cima":
                posicaoAtual[0] = posicaoAtual[0] - 1;
                q.enfileira(Integer.toString(posicaoAtual[0]) + "," + Integer.toString(posicaoAtual[1]));
                moveFila.enfileira("cima");
                break;
            
            case "baixo":
                posicaoAtual[0] = posicaoAtual[0] + 1;
                q.enfileira(Integer.toString(posicaoAtual[0]) + "," + Integer.toString(posicaoAtual[1]));
                moveFila.enfileira("baixo");
                break;

            case "comecar":
                while (!q.empty()) {
                    String[] auxValores = q.espiar().split(",");
                    int linha = Integer.parseInt(auxValores[0]);
                    int coluna = Integer.parseInt(auxValores[1]);
                    aumentaComando++;
                    System.out.println("Comando " + aumentaComando + ": " + moveFila.espiar());
                    preenchePosicao(linha, coluna);
                    moveFila.desenfileira();
                    q.desenfileira();
                }
                break;
            case "reiniciar":

                while (!q.empty()) {
                    limpaLabirinto();
                }

                break;
            default:
                System.err.println("Movimento invalido, tente novamente!");
        }
    }

}
