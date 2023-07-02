package Proj;

public class ObservarCommand implements Command {

    private Biblioteca biblioteca;
    private int codUsu;
    private int codLiv;

    public ObservarCommand(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }


    @Override
    public void execute() {
        this.codUsu = Integer.parseInt(Invoker.getSecondArg());
        this.codLiv = Integer.parseInt(Invoker.getThirdArg());
        biblioteca.observar(this.codUsu, this.codLiv);
    }
}
