package Proj;

public class DevolucaoCommand implements Command{
    private Biblioteca biblioteca;
    private int codUsu;
    private int codLiv;

    public DevolucaoCommand(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }


    @Override
    public void execute() {
        this.codUsu = Integer.parseInt(Invoker.getSecondArg());
        this.codLiv = Integer.parseInt(Invoker.getThirdArg());
        biblioteca.devolucao(this.codUsu, this.codLiv);
    }
}
