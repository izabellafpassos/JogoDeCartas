import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite o número de jogadores: ");
        int numJogadores = scanner.nextInt();
        
        Jogo jogo = new Jogo(numJogadores);
        
        for (int i = 1; i <= numJogadores; i++) {
            System.out.print("Digite o nome do Jogador " + i + ": ");
            String nome = scanner.next();
            jogo.getJogadores().get(i - 1).setNome(nome);
        }
        
        System.out.println("Mãos dos jogadores:");
        jogo.proximoTurno();
        
        Jogador vencedor = jogo.determinarVencedor();
        System.out.println("O vencedor é: " + vencedor.getNome());
        
        scanner.close();
    }
}
