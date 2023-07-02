package Proj;

public class StateLivre implements State{
    private Exemplares exemplar;
    private String nome = "Livre";

    public StateLivre(Exemplares exemplar) {
        this.exemplar = exemplar;
    }

    public void changeState() {
        exemplar.setStatus(new StateEmprestado(exemplar));
    }

    public String getNome() {
        return nome;
    }

}
