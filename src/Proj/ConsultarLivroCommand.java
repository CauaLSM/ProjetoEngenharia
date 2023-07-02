package Proj;

public class ConsultarLivroCommand implements Command{

    private Biblioteca biblioteca;
    private int codLiv;

    public ConsultarLivroCommand(Biblioteca biblioteca) {
        this.biblioteca = biblioteca.obterBiblioteca();
    }


    @Override
    public void execute() {
        this.codLiv = Integer.parseInt(Invoker.getSecondArg());
        biblioteca.consultarLivro(codLiv);
    }
}
