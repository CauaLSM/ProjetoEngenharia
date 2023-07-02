package Proj;

public class ComportamentoEmprestado implements Comportamento {

    private Exemplares exemplar;
    private final String nome = "Emprestado";

    public ComportamentoEmprestado(Exemplares exemplar) {
        this.exemplar = exemplar;
    }

    public void mudarComportamento() {
        exemplar.setStatus(new ComportamentoLivre(exemplar));
    }

    public String getNome() {
        return nome;
    }
}

