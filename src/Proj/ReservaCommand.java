package Proj;

public class ReservaCommand implements Command{

    private Biblioteca biblioteca;
    private int codUsu;
    private int codLiv;

    public ReservaCommand(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }


    @Override
    public void execute() {
        this.codUsu = Integer.parseInt(Invoker.getSecondArg());
        this.codLiv = Integer.parseInt(Invoker.getThirdArg());
        biblioteca.reserva(this.codUsu, this.codLiv);
    }
}
