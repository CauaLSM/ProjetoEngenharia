package Proj;

public class StateFinalizado implements State{

    private Emprestimo emprestimo;
    private final String nome = "Em Curso";

    public StateFinalizado(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    @Override
    public void changeState() {
        emprestimo.setEstado(new StateEmCurso(emprestimo));
    }

    @Override
    public String getNome() {
        return nome;
    }
}
