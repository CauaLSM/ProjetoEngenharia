package com.mycompany.app;

import java.time.LocalDateTime;


public class Emprestimo {
    private String tituloLivro;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucaoPrevista;
    private LocalDateTime dataDevolucaoRealizada;

    public Emprestimo (String tituloLivro, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao) {
        this.tituloLivro = tituloLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista= dataDevolucao;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDateTime getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public LocalDateTime getDataDevolucaoRealizada() {
        return dataDevolucaoRealizada;
    }

    public void setDataDevolucaoRealizada() {
        this.dataDevolucaoRealizada = LocalDateTime.now();
    }
}