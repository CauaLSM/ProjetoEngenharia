package Proj;

import java.time.LocalDateTime;


public class Emprestimo {
    private String tituloLivro;
    private State estado;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucaoPrevista;
    private LocalDateTime dataDevolucaoRealizada;

    public Emprestimo (String tituloLivro, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao) {
        this.tituloLivro = tituloLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista= dataDevolucao;
        this.estado = new StateEmCurso(this);
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

    public State getEstado() {
        return estado;
    }

    public void setEstado(State estado) {
        this.estado = estado;
    }

    public void changeState() {
        this.estado.changeState();
    }

    public String getStateName() {
        return this.estado.getNome();
    }

}
