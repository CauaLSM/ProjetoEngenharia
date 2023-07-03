package com.mycompany.app;

import java.time.LocalDateTime;

public class Reserva {

    private String tituloLivro;
    private LocalDateTime dataEmprestimo;
    
    public Reserva(String tituloLivro) {
        this.tituloLivro = tituloLivro;
        this.dataEmprestimo = LocalDateTime.now();
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }
}
