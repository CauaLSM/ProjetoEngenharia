package com.mycompany.app;

import java.time.LocalDateTime;

public class Exemplar {

    private int codigo;
    private boolean isDisp = true;
    private Usuario usuarioEmprestado;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;

    public Exemplar(int codigo) {
        this.setCodigo(codigo);
    }

   public String getEmprestado() {
        return usuarioEmprestado.getNome();
   } 

  

	public boolean isDisp() {
		return isDisp;
	}

	public void setDisp(boolean isDisp) {
		this.isDisp = isDisp;
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

	public Usuario getUsuarioEmprestado() {
		return usuarioEmprestado;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setUsuarioEmprestado(Usuario usuarioEmprestado) {
		this.usuarioEmprestado = usuarioEmprestado;
	}

 
}