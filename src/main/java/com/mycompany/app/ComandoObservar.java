package com.mycompany.app;

public class ComandoObservar implements Comandos{
	
	 private Sistema sis;
	    private int codUsu;
	    private int codLiv;

	    public ComandoObservar(Sistema sis) {
	        this.sis = Sistema.obterSistema();
	    }


	    @Override
	    public void execute() {
	        this.codUsu = Integer.parseInt(Invoker.getSecondArg());
	        this.codLiv = Integer.parseInt(Invoker.getThirdArg());
	        sis.observar(this.codUsu, this.codLiv);
	    }
	}