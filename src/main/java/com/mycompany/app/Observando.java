package com.mycompany.app;

public interface Observando {
	void registrarObserver(Observadores ob);
    void removerObserver(Observadores ob);
    void notificarObservers();
}
