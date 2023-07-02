package Proj;

public class StateEmCurso implements State{

    private Emprestimo emprestimo;
    private final String nome = "Em Curso";

    public StateEmCurso(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    @Override
    public void changeState() {
        emprestimo.setEstado(new StateFinalizado(emprestimo));
    }

    @Override
    public String getNome() {
        return nome;
    }


}
