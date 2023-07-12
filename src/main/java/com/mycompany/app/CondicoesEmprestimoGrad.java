package com.mycompany.app;

public class CondicoesEmprestimoGrad implements CondicoesEmprestimo{
	
	@Override
	public boolean podeEmprestar(Usuario usuario, Livro livro,Exemplar exemplar, boolean jaEmprestado, int disponibilidade, boolean reservado) {

        if (usuario.isDevedor()) {
            System.out.println("Livro " + livro.getTitulo() + "não pode ser emprestado para " + usuario.getNome() + " pois o usuário está devendo a Biblioteca!.\n");
            return false;
        }

        if (usuario.getNumEmprestimos() >= usuario.getLimiteDeEmprestimo()) {
            System.out.println("Livro " + livro.getTitulo() + "não pode ser emprestado para " + usuario.getNome() + " pois o usuário atingiu o número limite de livros emprestados simultâneamente.\n");
            return false;
        }

        if (livro.getNumReservas()>=disponibilidade && !reservado) {
            System.out.println("Livro " + livro.getTitulo() + "não pode ser emprestado para " + usuario.getNome() + " pois o número de reservas existentes atingiu o valor do número de exemplares disponíveis do livro e o usuário em questão não tem reserva.\n");
            return false;
        }

        if (jaEmprestado) {
            System.out.println("Livro " + livro.getTitulo() + "não pode ser emprestado para " + usuario.getNome() + " pois o usuário tem uma cópia desse livro em mãos.\n");
            return false;
        }


        if (reservado) System.out.println("Livro " + livro.getTitulo() + "emprestado para " + usuario.getNome() + " e reserva excluída do sistema.\n");
        else System.out.println("Livro " + livro.getTitulo() + "emprestado para " + usuario.getNome() + ".\n");

        return true;

    }

}
