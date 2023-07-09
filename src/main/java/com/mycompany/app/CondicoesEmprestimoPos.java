package com.mycompany.app;

public class CondicoesEmprestimoPos implements CondicoesEmprestimo{
	
	 public boolean podeEmprestar(Usuario usuario, Livro livro, Exemplar exemplar, boolean jaEmprestado, int disponivel, boolean reservado) {

	        //Condição de falha de Devedor (ii)
	        if (usuario.isDevedor()) {
	            System.out.println("Livro " + livro.getTitulo() + "não pode ser emprestado para " + usuario.getNome() + " pois o usuário está devendo a Biblioteca!\n");
	            return false;
	        }

	        //Condição de falha de Quantidade Máxima de Empréstimos (iii)
	        if (usuario.getNumEmprestimos() >= usuario.getLimiteDeEmprestimo()) {
	            System.out.println("Livro " + livro.getTitulo() + "não pode ser emprestado para " + usuario.getNome() + " pois o usuário atingiu o número limite de livros emprestados simultâneamente.\n");
	            return false;
	        }

	        //Caso já tenha reservas demais pro livro e o usuário não tem reserva (iv) e (v)
	        if (livro.getNumReservas() >= disponivel && !reservado) {
	            System.out.println("Livro " + livro.getTitulo() + "não pode ser emprestado para " + usuario.getNome() + " pois o número de reservas existentes atingiu o valor do número de exemplares disponíveis do livro e o usuário em questão não tem reserva.\n");
	            return false;
	        }

	        //Condição de falha de Usuario já tem um empréstimo daquele livro (vi)
	        if (jaEmprestado) {
	            System.out.println("Livro " + livro.getTitulo() + "não pode ser emprestado para " + usuario.getNome() + " pois o usuário tem uma cópia desse livro em mãos.\n");
	            return false;
	        }

	        //Se chegou aqui, passou pelas checagens determinadas para Aluno Graduação

	        if (reservado)
	            System.out.println("Livro " + livro.getTitulo() + "emprestado para " + usuario.getNome() + " e reserva excluída do sistema.\n");
	        else System.out.println("Livro " + livro.getTitulo() + "emprestado para " + usuario.getNome() + ".\n");

	        return true;
	    }

}
