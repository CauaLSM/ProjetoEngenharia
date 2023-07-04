package com.mycompany.app;


public class ComandoEmprestar implements Comandos{
	
    private Sistema sis;
    private int codUsu;
    private int codLiv;

    public ComandoEmprestar(Sistema sis) {
        this.sis = Sistema.obterSistema();
    }


    @Override
    public void execute() {
        this.codUsu = Integer.parseInt(Invoker.getSecondArg());
        this.codLiv = Integer.parseInt(Invoker.getThirdArg());
        sis.emprestimo(this.codUsu, this.codLiv);
    }
}

