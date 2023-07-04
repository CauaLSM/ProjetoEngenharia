package com.mycompany.app;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Usuario {
    private String nome;
    private String tipo;
    private int codigo;
    private int tempoDeEmprestimo;
    private int numReservas;
    private int numEmprestimos;
    private int limiteDeEmprestimo;
    private boolean devedor = false;
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

	public String getNome() {
		return nome;
	}
	
	public abstract void emprestimoBemSucedido(String tituloLivro, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao);

	public abstract void reservaBemSucedida(String tituloLivro);

    public abstract void livroDevolvido(Exemplar livro);

	public abstract void verificarDatas();

    public abstract void listarEmprestimosEReservas();

	public boolean isDevedor() {
		return devedor;
	}

	public void setDevedor(boolean devedor) {
		this.devedor = devedor;
	}

	public ArrayList<Emprestimo> getEmprestimosCorrentes() {
		return emprestimosCorrentes;
	}

	public ArrayList<Emprestimo> getEmprestimosPassados() {
		return emprestimosPassados;
	}

	public ArrayList<Reserva> getReservas() {
		return reservas;
	}
    
}

