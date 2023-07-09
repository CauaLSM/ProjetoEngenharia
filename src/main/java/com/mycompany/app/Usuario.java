package com.mycompany.app;

import java.time.LocalDateTime;



public abstract class Usuario {
	
	public CondicoesEmprestimo condicoes;
	 
	public abstract String getTipo();

	public abstract int getCodigo();

    public abstract int getNumEmprestimos();

    public abstract int getTempoDeEmprestimo();
    
    public abstract int getNumReservas();

	public abstract String getNome();
	
    public abstract int getLimiteDeEmprestimo();
	
	public abstract void emprestimoBemSucedido(String tituloLivro, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao);

	public abstract void reservaBemSucedida(String tituloLivro);

    public abstract void livroDevolvido(Exemplar exemplar);

	public abstract void verificarDatas();

    public abstract void listarEmprestimosEReservas();

	public abstract boolean isDevedor();

	public abstract void setDevedor(boolean devedor);

}

