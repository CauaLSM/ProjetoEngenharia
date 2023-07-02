package Proj;

public class ComportamentoFinalizado implements Comportamento {

    private Emprestimo emprestimo;
    private final String nome = "Finalizado";

    public ComportamentoFinalizado(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    @Override
    public void mudarComportamento() {
        emprestimo.setComportamento(new ComportamentoProcessando(emprestimo));
    }

    @Override
    public String getNome() {
        return nome;
    }
}

