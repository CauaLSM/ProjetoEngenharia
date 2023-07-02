package Proj;

public class EmprestimoCommand implements Command{

    private Biblioteca biblioteca;
    private int codUsu;
    private int codLiv;

    public EmprestimoCommand(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }


    @Override
    public void execute() {
        this.codUsu = Integer.parseInt(Invoker.getSecondArg());
        this.codLiv = Integer.parseInt(Invoker.getThirdArg());
        biblioteca.emprestimo(this.codUsu, this.codLiv);
    }
}
