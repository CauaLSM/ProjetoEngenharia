package Proj;

public class EmprestimoTesteAlunoGraduacao implements EmprestimoTeste {

    public boolean podeEmprestar(Usuarios usuario, Livros livro, Exemplares exemplar, boolean jaEmprestado, int disponibilidade, boolean reservado) {

        //Condição de falha de Devedor (ii)
        if (usuario.getEstado().getNome().equals("Devedor")) {
            System.out.println("Livro " + livro.getTitulo() + "não pode ser emprestado para " + usuario.getNome() + " por usuário ter status de devedor.\n");
            return false;
        }

        //Condição de falha de Quantidade Máxima de Empréstimos (iii)
        if (usuario.getNumEmprestimos() >= usuario.getLimiteDeEmprestimo()) {
            System.out.println("Livro " + livro.getTitulo() + "não pode ser emprestado para " + usuario.getNome() + " por usuário ter atingido o número limite de livros emprestados simultâneamente.\n");
            return false;
        }

        //Caso já tenha reservas demais pro livro e o usuário não tem reserva (iv) e (v)
        if (livro.getNumReservas()>=disponibilidade && !reservado) {
            System.out.println("Livro " + livro.getTitulo() + "não pode ser emprestado para " + usuario.getNome() + " pelo número de reservas existentes já ter atingido o valor do número de exemplares disponíveis do livro e o usuário em questão não tem reserva.\n");
            return false;
        }

        //Condição de falha de Usuario já tem um empréstimo daquele livro (vi)
        if (jaEmprestado) {
            System.out.println("Livro " + livro.getTitulo() + "não pode ser emprestado para " + usuario.getNome() + " pelo por usuário já ter uma cópia desse livro em mãos.\n");
            return false;
        }

        //Se chegou aqui, passou pelas checagens determinadas para Aluno Graduação

        if (reservado) System.out.println("Livro " + livro.getTitulo() + "emprestado para " + usuario.getNome() + " e reserva excluída do sistema.\n");
        else System.out.println("Livro " + livro.getTitulo() + "emprestado para " + usuario.getNome() + ".\n");

        return true;

    }

}
