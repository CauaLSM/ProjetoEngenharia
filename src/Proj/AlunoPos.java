package Proj;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class AlunoPos extends Usuarios {

    private int codigo;
    private String nome;
    private String tipo = "Aluno Pós-Graduação";
    private int tempoDeEmprestimo = 4;
    private int limiteDeEmprestimo = 4;
    private Comportamento comportamento;
    private int numReservas;
    private int numEmprestimos;
    private final ArrayList<Emprestimo> emprestimosCorrentes = new ArrayList<>();
    private final ArrayList<Emprestimo> emprestimosPassados = new ArrayList<>();
    private final ArrayList<Reserva> reservas = new ArrayList<>();

    public AlunoPos(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.comportamento = new ComportamentoNormal(this);
    }

    public int getTempoDeEmprestimo() {
        return tempoDeEmprestimo;
    }

    public int getLimiteDeEmprestimo() {
        return limiteDeEmprestimo;
    }

    public void setLimiteDeEmprestimo(int limiteDeEmprestimo) {
        this.limiteDeEmprestimo = limiteDeEmprestimo;
    }

    public Comportamento getComportamento() {
        return comportamento;
    }

    public void setComportamento(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    public void mudarComportamento() {
        comportamento.mudarComportamento();
    }

    public String getComportamentoNome() {
        return this.comportamento.getNome();
    }

    public void emprestimoBemSucedido(String tituloLivro, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao) {
        emprestimosCorrentes.add(new Emprestimo(tituloLivro, dataEmprestimo, dataDevolucao));
        numEmprestimos++;

        if (Objects.equals(getComportamentoNome(), "Devedor")) {
            mudarComportamento();
        }
    }

    public void reservaBemSucedida(String tituloLivro) {
        reservas.add(new Reserva(tituloLivro));
        numReservas++;
    }

    public void livroDevolvido(Exemplares livro) {
        String titulo = livro.getDono().getTitulo();

        for (Emprestimo e : emprestimosCorrentes) {
            if (Objects.equals(titulo, e.getTituloLivro())) {
                if (Objects.equals(getComportamentoNome(), "Devedor")) {
                    mudarComportamento();
                }

                e.mudarComportamento();
                emprestimosPassados.add(e);
                emprestimosCorrentes.remove(e);
                break;
            }
        }

        verificarDatas();
    }

    public void verificarDatas() {
        if (!Objects.equals(getComportamentoNome(), "Devedor")) {
            for (Emprestimo e : emprestimosCorrentes) {
                if (e.getDataDevolucaoPrevista().isAfter(LocalDateTime.now())) {
                    mudarComportamento();
                    break;
                }
            }
        }
    }

    public void listarEmprestimosEReservas() {
        System.out.println("EMPRESTIMOS\n");
        listarEmprestimosAtuais();
        listarEmprestimosPassados();
        System.out.println("Reservas\n");
        listarReservas();
    }

    public void listarEmprestimosAtuais() {
        for (Emprestimo e : emprestimosCorrentes) {
            System.out.println("Titulo: " + e.getTituloLivro());
            System.out.println("Data de Emprestimo: " + e.getDataEmprestimo());
            System.out.println("Data de Devolução Prevista: " + e.getDataDevolucaoPrevista());
            System.out.println("Status: " + e.getComportamentoNome());
            if (e.getDataDevolucaoRealizada() == null) {
                System.out.println("Data de Devolução Realizada: Ainda não Realizada");
            } else {
                System.out.println("Data de Devolução Realizada: " + e.getDataDevolucaoRealizada() + "\n");
            }
        }
    }

    public void listarEmprestimosPassados() {
        for (Emprestimo e : emprestimosPassados) {
            System.out.println("Titulo: " + e.getTituloLivro());
            System.out.println("Data de Emprestimo: " + e.getDataEmprestimo());
            System.out.println("Data de Devolução Prevista: " + e.getDataDevolucaoPrevista());
            System.out.println("Status: " + e.getComportamentoNome());
            if (e.getDataDevolucaoRealizada() == null) {
                System.out.println("Data de Devolução Realizada: Ainda não Realizada");
            } else {
                System.out.println("Data de Devolução Realizada: " + e.getDataDevolucaoRealizada() + "\n");
            }
        }
    }

    public void listarReservas() {
        for (Reserva r : reservas) {
            System.out.println("Titulo: " + r.getTituloLivro());
            System.out.println("Data de Emprestimo: " + r.getDataEmprestimo() + "\n");
        }
    }
}
