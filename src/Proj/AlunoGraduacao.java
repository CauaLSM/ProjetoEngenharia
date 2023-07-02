package Proj;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class AlunoGraduacao extends Usuarios {

    private int codigo;
    private String nome;
    private String tipo = "Aluno Graduação";
    private int tempoDeEmprestimo = 3;
    private int limiteDeEmprestimo = 3;
    private State estado;
    private int numReservas;
    private int numEmprestimos;
    private final ArrayList<Emprestimo> emprestimosCorrentes = new ArrayList<Emprestimo>();
    private final ArrayList<Emprestimo> emprestimosPassados = new ArrayList<Emprestimo>();
    private final ArrayList<Reserva> reservas = new ArrayList<Reserva>();

    public AlunoGraduacao(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.estado = new StateNormal(this);
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

    public State getEstado() {
        return estado;
    }

    @Override
    public void setEstado(State estado) {
        this.estado = estado;
    }

    public void changeStatus() {
        estado.changeState();
    }

    public String getStateName() {
       return this.estado.getNome();
    }

    public void emprestimoBemSucedido(String tituloLivro, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao) { //Seção 3.5.b parte 1
        //Precisa add um objeto no Array de Histórico de Empréstimos que contenha título, data de empréstimo, status(finalizado ou em curso),
        //data devolucao (realizada ou prevista)
        emprestimosCorrentes.add(new Emprestimo(tituloLivro, dataEmprestimo, dataDevolucao));
        numEmprestimos++;

        //Tem que add no numEmprestimos também

        //Tem que lembrar que, quando a DataDevolução passar e o livro ainda não tiver sido devolvido, tem que ativar o Estado.setState("Devedor")
    }

    public void reservaBemSucedida(String tituloLivro) { //Seção 3.5.b parte 2
        //Precisa add um objeto no Array de Reservas que contenha tanto o título que tá sendo passado quanto a data atual
        //a data atual deve vir de um método estático que provavelmente vem da
        reservas.add(new Reserva(tituloLivro));
        numReservas++;
    }

    public void livroDevolvido(Exemplares livro) {
        String titulo = livro.getDono().getTitulo();

        for(Emprestimo e : emprestimosCorrentes) {
            if(Objects.equals(titulo, e.getTituloLivro())) {

                if(Objects.equals(getStateName(), "Devedor")) {
                    changeStatus();
                }

                e.changeState();
                emprestimosPassados.add(e);
                emprestimosCorrentes.remove(e);

            }
        }

        verificarDatas();
    }

    public void verificarDatas() {
        if(!Objects.equals(getStateName(), "Devedor")) {
            for(Emprestimo e : emprestimosCorrentes) {
                if(e.getDataDevolucaoPrevista().isAfter(LocalDateTime.now())) {
                    changeStatus();
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

        for(Emprestimo e : emprestimosCorrentes) {
            System.out.println("Titulo: " + e.getTituloLivro());
            System.out.println("Data de Emprestimo: " + e.getDataEmprestimo());
            System.out.println("Data de Devolução Prevista: " + e.getDataDevolucaoPrevista());
            System.out.println("Status: " + e.getStateName());
            if(e.getDataDevolucaoRealizada() == null){
                System.out.println("Data de Devolução Realizada: Ainda não Realizada");
            } else {
                System.out.println("Data de Devolução Realizada: " + e.getDataDevolucaoRealizada() + "\n");
            }
        }
    }

    public void listarEmprestimosPassados() {

        for(Emprestimo e : emprestimosPassados) {
            System.out.println("Titulo: " + e.getTituloLivro());
            System.out.println("Data de Emprestimo: " + e.getDataEmprestimo());
            System.out.println("Data de Devolução Prevista: " + e.getDataDevolucaoPrevista());
            System.out.println("Status: " + e.getStateName());
            if(e.getDataDevolucaoRealizada() == null){
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
