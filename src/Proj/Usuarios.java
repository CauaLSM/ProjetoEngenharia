package Proj;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Usuarios {

    private int codigo;
    private String nome;
    private String tipo;
    private int tempoDeEmprestimo;
    private State estado;
    private int notificacao;
    private int numReservas;
    private int numEmprestimos;
    private int limiteDeEmprestimo;
    private final ArrayList<Emprestimo> emprestimosCorrentes = new ArrayList<Emprestimo>();
    private final ArrayList<Emprestimo> emprestimosPassados = new ArrayList<Emprestimo>();
    private final ArrayList<Emprestimo> reservas = new ArrayList<Emprestimo>();

    public EmprestimoTeste emprestimoTeste; //todo Implementar nos construtores das classes o EmprestimoTeste correto

    protected Usuarios() {
    }

    public void setEstado(State estado) {
        this.estado = estado;
    }

    public State getEstado() {
        return estado;
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

    public void emprestimoBemSucedido(String tituloLivro, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao) { //Seção 3.5.b parte 1
        //Precisa add um objeto no Array de Histórico de Empréstimos que contenha título, data de empréstimo, status(finalizado ou em curso),
        //data devolucao (realizada ou prevista)

        //Tem que add no numEmprestimos também

        //Tem que lembrar que, quando a DataDevolução passar e o livro ainda não tiver sido devolvido, tem que ativar o Estado.setState("Devedor")
    }

    public void reservaBemSucedida(String tituloLivro) { //Seção 3.5.b parte 2
        //Precisa add um objeto no Array de Reservas que contenha tanto o título que tá sendo passado quanto a data atual
        //a data atual deve vir de um método estático que provavelmente vem da Biblioteca
    }

    public void livroDevolvido(Exemplares livro) {

    }

    public abstract void verificarDatas();

    public abstract void listarEmprestimosEReservas();

    public String getNome() { return this.nome; }

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
