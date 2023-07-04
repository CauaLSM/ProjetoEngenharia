package com.mycompany.app;

public class ComandoReservar implements Comandos{
    private Sistema sis;
    private int codUsu;
    private int codLiv;

    public ComandoReservar(Sistema sis) {
        this.sis = Sistema.obterSistema();
    }


    @Override
    public void execute() {
        this.codUsu = Integer.parseInt(Invoker.getSecondArg());
        this.codLiv = Integer.parseInt(Invoker.getThirdArg());
        sis.reserva(this.codUsu, this.codLiv);
    }
}

