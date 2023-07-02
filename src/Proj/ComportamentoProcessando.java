package Proj;

public class ComportamentoProcessando implements Comportamento {

    private Emprestimo emprestimo;
    private final String nome = "Processando";

    public ComportamentoProcessando(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    @Override
    public void mudarComportamento() {
        emprestimo.setEstado(new ComportamentoFinalizado(emprestimo));
    }

    @Override
    public String getNome() {
        return nome;
    }
}
