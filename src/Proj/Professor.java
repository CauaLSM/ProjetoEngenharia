package Proj;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Professor extends Usuarios implements Observers {

    private int codigo;
    private String nome;
    private String tipo = "Professor";
    private int tempoDeEmprestimo = 7;
    private Comportamento comportamento;
    private int numReservas = 0;
    private int numEmprestimos = 0;

    private final ArrayList<Emprestimo> emprestimosCorrentes = new ArrayList<>();
    private final ArrayList<Emprestimo> emprestimosPassados = new ArrayList<>();
    private final ArrayList<Reserva> reservas = new ArrayList<>();

    public Professor(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.comportamento = new ComportamentoNormal(this);
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTempoDeEmprestimo() {
        return tempoDeEmprestimo;
    }

    public int getNumReservas() {
        return numReservas;
    }

    public int getNumEmprestimos() {
        return numEmprestimos;
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

    @Override
    public void update(Livros livros) {
        setNotificacao(getNotificacao() + 1);
    }
}

