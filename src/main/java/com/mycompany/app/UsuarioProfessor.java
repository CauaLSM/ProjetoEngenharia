package com.mycompany.app;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class UsuarioProfessor extends Usuario implements Observadores{
	private int codigo;
    private String nome;
    private final String tipo = "Professor";
    private int tempoDeEmprestimo = 7;
    private boolean devedor = false;
    private int numReservas;
    private int numEmprestimos;
    private final ArrayList<Emprestimo> emprestimosCorrentes = new ArrayList<>();
    private final ArrayList<Emprestimo> emprestimosPassados = new ArrayList<>();
    private final ArrayList<Reserva> reservas = new ArrayList<>();

    public UsuarioProfessor(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
    
    public String getTipo() {
        return tipo;
    }

    public int getCodigo() {
        return codigo;
    }


    public int getNumEmprestimos() {
        return numEmprestimos;
    }

    
    public int getNumReservas() {
        return numReservas;
    }

	public String getNome() {
		return nome;
	}

    public int getTempoDeEmprestimo() {
        return tempoDeEmprestimo;
    }
    
	public boolean isDevedor() {
		return devedor;
	}

	public void setDevedor(boolean devedor) {
		this.devedor = devedor;
	}

    public void emprestimoBemSucedido(String tituloLivro, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao) {
        emprestimosCorrentes.add(new Emprestimo(tituloLivro, dataEmprestimo, dataDevolucao));
        numEmprestimos++;
        setDevedor(false);
    }

    public void reservaBemSucedida(String tituloLivro) {
        reservas.add(new Reserva(tituloLivro));
        numReservas++;
    }

    public void livroDevolvido(Exemplar livro) {
        String titulo = livro.getTitulo();

        for (Emprestimo e : emprestimosCorrentes) {
            if (Objects.equals(titulo, e.getTituloLivro())) {
            	
                setDevedor(false);
                e.setEmProcesso(false);
                emprestimosPassados.add(e);
                emprestimosCorrentes.remove(e);
                break;
            }
        }

        verificarDatas();
    }

    public void verificarDatas() {
        if (!isDevedor()) {
            for (Emprestimo e : emprestimosCorrentes) {
                if (e.getDataDevolucaoPrevista().isAfter(LocalDateTime.now())) {
                    setDevedor(true);
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
            if(e.isEmProcesso()) {
            	System.out.println("Status: Em processo");
            } else {
            	System.out.println("Status: Finalizado");
            }
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
            if(e.isEmProcesso()) {
            	System.out.println("Status: Em processo");
            } else {
            	System.out.println("Status: Finalizado");
            }
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
	public int getLimiteDeEmprestimo() {
		return 0;
	}

	@Override
    public void update(Biblioteca livros) {
        setNotificacao(getNotificacao() + 1);
    }





}
