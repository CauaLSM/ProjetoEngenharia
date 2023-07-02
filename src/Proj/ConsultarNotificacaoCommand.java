package Proj;

public class ConsultarNotificacaoCommand implements Command{

    private Biblioteca biblioteca;
    private int codUsu;

    public ConsultarNotificacaoCommand(Biblioteca biblioteca) {
        this.biblioteca = biblioteca.obterBiblioteca();
    }


    @Override
    public void execute() {
        this.codUsu = Integer.parseInt(Invoker.getSecondArg());
        biblioteca.consultarNotificacao(codUsu);
    }
}
