package com.mycompany.app;

public class ComandoDevolver implements Comandos{
	
    private Sistema sis;
    private int codUsu;
    private int codLiv;

    public ComandoDevolver(Sistema sis) {
        this.sis = Sistema.obterSistema();
    }


    @Override
    public void execute() {
        this.codUsu = Integer.parseInt(Invoker.getSecondArg());
        this.codLiv = Integer.parseInt(Invoker.getThirdArg());
        sis.devolucao(this.codUsu, this.codLiv);
    }
}

