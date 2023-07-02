package Proj;

public class ConsultarUsuarioCommand implements Command{

    private Biblioteca biblioteca;
    private int codUsu;

    public ConsultarUsuarioCommand(Biblioteca biblioteca) {
        this.biblioteca = biblioteca.obterBiblioteca();
    }


    @Override
    public void execute() {
        this.codUsu = Integer.parseInt(Invoker.getSecondArg());
        biblioteca.consultarUsuario(codUsu);
    }
}
