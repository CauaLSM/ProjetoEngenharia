package com.mycompany.app;

import java.time.LocalDateTime;

public interface Usuario {
	
	public String getTipo();

	public int getCodigo();

    public int getNumEmprestimos();

    public int getTempoDeEmprestimo();
    
    public int getNumReservas();

	public String getNome();
	
	public void emprestimoBemSucedido(String tituloLivro, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao);

	public void reservaBemSucedida(String tituloLivro);

    public void livroDevolvido(Exemplar exemplar);

	public void verificarDatas();

    public void listarEmprestimosEReservas();

	public boolean isDevedor();

	public void setDevedor(boolean devedor);

}

