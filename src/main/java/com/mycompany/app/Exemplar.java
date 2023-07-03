package com.mycompany.app;

import java.time.LocalDateTime;

public class Exemplar {

    private int codigo;
    private boolean isDisp = true;
    private Usuario usuarioEmprestado;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;

    public Exemplar(int codigo) {
        this.codigo = codigo;
    }

   public String getEmprestado() {
        return usuarioEmprestado.getNome();
   }

    public int getCodigo() {
        return codigo;
    }

	public boolean isDisp() {
		return isDisp;
	}

	public void setDisp(boolean isDisp) {
		this.isDisp = isDisp;
	}
	
    public Usuario getUsuarioEmprestado() {
        return usuarioEmprestado;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

 
}