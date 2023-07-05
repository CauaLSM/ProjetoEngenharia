package com.mycompany.app;

public class ComandoConsultaLivro implements Comandos{

	private Sistema sis;
	private int codLiv;

    public ComandoConsultaLivro(Sistema sis) {
    	this.sis = Sistema.obterSistema();
	}


	@Override
	public void execute() {
	    this.codLiv = Integer.parseInt(Invoker.getSecondArg());
	    sis.consultarLivro(this.codLiv);
	}
}
