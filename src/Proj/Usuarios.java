package Proj;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Usuarios {

    private int codigo;
    private String nome;
    private String tipo;
    private int tempoDeEmprestimo;
    private Comportamento comportamento;
    private int notificacao;
    private int numReservas;
    private int numEmprestimos;
    private int limiteDeEmprestimo;
    private final ArrayList<Emprestimo> emprestimosCorrentes = new ArrayList<>();
    private final ArrayList<Emprestimo> emprestimosPassados = new ArrayList<>();
    private final ArrayList<Reserva> reservas = new ArrayList<>();

    public EmprestimoTeste emprestimoTeste; //todo Implementar nos construtores das classes o EmprestimoTeste correto

    protected Usuarios() {
    }

    public void setComportamento(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    public Comportamento getComportamento() {
        return comportamento;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getLimiteDeEmprestimo() {
        return limiteDeEmprestimo;
    }

    public int getNumEmprestimos() {
        return numEmprestimos;
    }

    public int getTempoDeEmprestimo() {
        return tempoDeEmprestimo;
    }

    public void emprestimoBemSucedido(String tituloLivro, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao) {
        // Precisa adicionar um objeto no Array de Histórico de Empréstimos que contenha título, data de empréstimo,
        // status (finalizado ou em curso) e data de devolução (realizada ou prevista)

        // É necessário incrementar numEmprestimos também

        // Lembrar que quando a dataDevolucao passar e o livro ainda não tiver sido devolvido,
        // o comportamento deve ser mudado para "Devedor"
    }

    public void reservaBemSucedida(String tituloLivro) {
        // Precisa adicionar um objeto no Array de Reservas que contenha o título do livro e a data atual
        // A data atual pode ser obtida de um método estático da Biblioteca
    }

    public void livroDevolvido(Exemplares livro) {

    }

    public abstract void verificarDatas();

    public abstract void listarEmprestimosEReservas();

    public String getNome() {
        return this.nome;
    }

    public int getNumReservas() {
        return numReservas;
    }

    public int getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(int notificacao) {
        this.notificacao = notificacao;
    }

    public void getNNotificaoes() {
        System.out.println("Usuário " + getNome() + " foi notificado um total de " + getNotificacao() + " vezes.\n");
    }
}

