package com.mycompany.app;

public interface CondicoesEmprestimo {
	
	 public boolean podeEmprestar(Usuario usuario, Livro livro, Exemplar exemplar, boolean jaEmprestado, int disponivel, boolean reservado);

}
