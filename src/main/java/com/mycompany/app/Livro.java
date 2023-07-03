package Projeto;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private int codigo;
    private int numReservas = 0;
    private int numEmprestados = 0;
    private String titulo;
    private String editora;
    private String autores;
    private String edicao;
    private String anoPublicacao;
    public List<Exemplar> listaExemplares = new ArrayList<Exemplar>();
    private Usuario usuarioEmprestado;

    public Livro(int codigo, String titulo, String editora, String autores, String edicao, String anoPublicacao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
    }
    
    public void addExemplar(Exemplar exemplar) {
        listaExemplares.add(exemplar);
    }
    
    public boolean temExemplarDisp(){
    	for(Exemplar exemplar : listaExemplares) {
    		if(exemplar.isDisp()) {
    			return true;
    		}
    	}
		return false;
    }

    public String getTitulo() {
        return titulo;
    }
 
    public int getCodigo() {
        return codigo;
    }

	public int getNumReservas() {
		return numReservas;
	}

	public void setNumReservas(int numReservas) {
		this.numReservas = numReservas;
	}

	public int getNumEmprestados() {
		return numEmprestados;
	}

	public void setNumEmprestados(int numEmprestados) {
		this.numEmprestados = numEmprestados;
	}

	public void setUsuarioEmprestado(Usuario usuarioEmprestado) {
		this.usuarioEmprestado = usuarioEmprestado;
		
	}

}
