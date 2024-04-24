import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Carta {
    private String valor;
    private String naipe;

    /* construtor */
    public Carta(String valor, String naipe) {
        this.valor = valor;
        this.naipe = naipe;
    }

    /* pega o valor da carta e retorna o valor e o nipe da carta */
    public String getValor() {
        return valor;
    }

    public String getNaipe() {
        return naipe;
    }

    /* retorna no formato de naipe em string */
    @Override
    public String toString() {
        return valor + " de " + naipe;
    }
}

class Baralho {
    private List<Carta> cartas;

    /* construtor do baralho */
    public Baralho() {
        cartas = new ArrayList<>();
        String[] valores = { "A", "2", "3", "4", "5", "6", "7", "Q", "J", "K" };
        String[] naipes = { "Ouro", "Copas", "Espadas", "Paus" };
        for (String valor : valores) {
            for (String naipe : naipes) {
                cartas.add(new Carta(valor, naipe));
            }
        }
    }

    /* recebe as cartas e reorganiza aleotorio */
    public void embaralhar() {
        Collections.shuffle(cartas);
    }

    /* verifica se o baralho esta vazio se não remove a primeira carta da lista */
    public Carta retirarCarta() {
        if (cartas.isEmpty()) {
            return null;
        }
        return cartas.remove(0);
    }
}

/* representa o jogador e armazena os pontos */
class Jogador {
    /* atributos */
    private String nome;
    private List<Carta> mao;
    private int pontuacao = 0;

    /* construtor */
    public Jogador(String nome) {
        this.nome = nome;
        mao = new ArrayList<>();
        pontuacao = 0;
    }

    /* metodos */
    /* adicona carta na mão do jogador */
    public void receberCarta(Carta carta) {
        mao.add(carta);
    }

    public void jogarCarta(Carta carta) {
        mao.remove(carta);
    }

    /*
     * percorre as cartão na mão do jogador e atribui as pontuações e depois retorna
     * a
     * pontuação total
     */
    public int calcularPontuacao() {
        pontuacao = 0;
        for (Carta carta : mao) {
            String valor = carta.getValor();
            switch (valor) {
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                    pontuacao += 0;
                    break;
                case "Q":
                case "J":
                case "K":
                    pontuacao += 10;
                    break;
                case "A":
                    pontuacao += 11;
                    break;
                default:
                    break;
            }
        }
        return pontuacao;
    }

    /* retorna a lista de cartas do jogador */
    public List<Carta> getMao() {
        return mao;
    }

    /* define o nome do jogador */
    public void setNome(String nome) {
        this.nome = nome;

    }

    /* pego o nome */
    public String getNome() {
        return nome;
    }
}

class Jogo {
    private Baralho baralho;
    private List<Jogador> jogadores;

    public Jogo(int numJogadores) {
        baralho = new Baralho();
        baralho.embaralhar();
        jogadores = new ArrayList<>();
        /* Adiciona jogadores */
        for (int i = 1; i <= numJogadores; i++) {
            jogadores.add(new Jogador("Jogador " + i));
        }
        /* distribui as cartas cartas */
        for (Jogador jogador : jogadores) {
            for (int i = 0; i < 6; i++) {
                jogador.receberCarta(baralho.retirarCarta());
            }
        }
    }

    /* obtem a lista de jogadores */
    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void proximoTurno() {
        for (Jogador jogador : jogadores) {
            System.out.println("Mão do " + jogador.getNome() + ": " + jogador.getMao());
        }
    }

    public Jogador determinarVencedor() {
        Jogador vencedor = jogadores.get(0);
        for (Jogador jogador : jogadores) {
            if (jogador.calcularPontuacao() > vencedor.calcularPontuacao()) {
                vencedor = jogador;
            }
        }
        return vencedor;
    }
}
