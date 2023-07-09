package com.mycompany.app;

public class CondicoesEmprestimoProf implements CondicoesEmprestimo{
	
	public boolean podeEmprestar(Usuario usuario, Livro livro, Exemplar exemplar, boolean jaEmprestado, int disponibilidade, boolean reservado) {

        //Se chegou aqui, há disponibilidade

        //Checagem de é devedor (ii)
        if (usuario.isDevedor()) {
            System.out.println("Livro " + livro.getTitulo() + "não pode ser emprestado para " + usuario.getNome() + " pois o usuário está devendo a Biblioteca!\n");
            return false;
        }

        //Se chegou aqui, passou pelas checagens determinadas para Professor

        //Caso tenha sido reservado
        if (reservado) System.out.println("Livro " + livro.getTitulo() + "emprestado para " + usuario.getNome() + " e reserva excluída do sistema.\n");
        //Caso não tenha sido reservado
        else System.out.println("Livro " + livro.getTitulo() + "emprestado para " + usuario.getNome() + "\n");

        return true;


    }

}
