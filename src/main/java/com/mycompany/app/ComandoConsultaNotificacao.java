package com.mycompany.app;

public class ComandoConsultaNotificacao implements Comandos{
	
	private Sistema sis;
    private int codUsu;

    public ComandoConsultaNotificacao(Sistema sis) {
        this.sis = Sistema.obterSistema();
    }


    @Override
    public void execute() {
        this.codUsu = Integer.parseInt(Invoker.getSecondArg());
        sis.consultarNotificacao(codUsu);
    }
}

