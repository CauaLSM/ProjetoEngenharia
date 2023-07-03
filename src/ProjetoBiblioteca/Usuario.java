package Projeto;
import java.util.ArrayList;

public class Usuario {
    private int codigo;
    private String nome;
    private String tipo;
    private int tempoDeEmprestimo;
    private int numReservas;
    private int numEmprestimos;
    private int limiteDeEmprestimo;
    private final ArrayList<Emprestimo> emprestimosCorrentes = new ArrayList<>();
    private final ArrayList<Emprestimo> emprestimosPassados = new ArrayList<>();
    private final ArrayList<Reserva> reservas = new ArrayList<>();
    
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
    
    public int getNumReservas() {
        return numReservas;
    }
}
