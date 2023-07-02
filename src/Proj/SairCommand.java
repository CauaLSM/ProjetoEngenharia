package Proj;

public class SairCommand implements Command{

    private Biblioteca biblioteca;

    public SairCommand(Biblioteca biblioteca) {
        this.biblioteca = biblioteca.obterBiblioteca();
    }

    @Override
    public void execute() {
        biblioteca.sairDoSistema();
    }
}
