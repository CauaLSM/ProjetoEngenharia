package com.mycompany.app;

public class ComandoSairDoSistema implements Comandos{
    private Sistema sis;

    public ComandoSairDoSistema(Sistema sis) {
        this.sis = Sistema.obterSistema();
    }

    @Override
    public void execute() {
        sis.sairDoSistema();
    }
}

