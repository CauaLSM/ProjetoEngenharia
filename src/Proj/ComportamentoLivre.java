package Proj;

public class ComportamentoLivre implements Comportamento {

    private Exemplares exemplar;
    private final String nome = "Livre";

    public ComportamentoLivre(Exemplares exemplar) {
        this.exemplar = exemplar;
    }

    @Override
    public void mudarComportamento() {
        exemplar.setComportamento(new ComportamentoEmprestado(exemplar));
    }

    @Override
    public String getNome() {
        return nome;
    }
}

