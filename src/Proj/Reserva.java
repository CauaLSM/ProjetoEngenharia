package Proj;

import java.time.LocalDateTime;

public class Reserva {

    private String tituloLivro;
    private LocalDateTime dataEmprestimo;
    
    private aleatorio;

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
