package com.mycompany.app;

public class ComandoConsultaUsuario implements Comandos{
	private Sistema sis;
	private int codUsu;

	public ComandoConsultaUsuario(Sistema sis) {
	        this.sis = Sistema.obterSistema();
	}


	@Override
	public void execute() {
	this.codUsu = Integer.parseInt(Invoker.getSecondArg());
		sis.consultarUsuario(this.codUsu);
	}
}
