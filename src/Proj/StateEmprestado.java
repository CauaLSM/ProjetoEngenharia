package Proj;

public class StateEmprestado implements State{

    private Exemplares exemplar;
    private String nome = "Emprestado";

    public StateEmprestado(Exemplares exemplar) {
        this.exemplar = exemplar;
    }

    public void changeState() {
        exemplar.setStatus(new StateLivre(exemplar));
    }

    public String getNome() {
        return nome;
    }

}
