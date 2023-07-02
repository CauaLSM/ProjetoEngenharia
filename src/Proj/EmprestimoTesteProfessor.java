package Proj;

public class EmprestimoTesteProfessor implements EmprestimoTeste {

    public boolean podeEmprestar(Usuarios usuario, Livros livro, Exemplares exemplar, boolean jaEmprestado, int disponibilidade, boolean reservado) {

        //Se chegou aqui, há disponibilidade

        //Checagem de é devedor (ii)
        if (usuario.getEstado().getNome().equals("Devedor")) {
            System.out.println("Livro " + livro.getTitulo() + "não pode ser emprestado para " + usuario.getNome() + " por usuário ter status de devedor.\n");
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
