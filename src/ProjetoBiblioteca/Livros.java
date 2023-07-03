package Projeto;

import java.util.ArrayList;
import java.util.List;

public class Livros {
    private int codigo;
    private String titulo;
    private String editora;
    private String autores;
    private String edicao;
    private String anoPublicacao;
    public List<Exemplares> listaExemplares = new ArrayList<Exemplares>();

    public Livros(int codigo, String titulo, String editora, String autores, String edicao, String anoPublicacao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
    }
    
    public void addExemplar(Exemplares exemplar) {
        listaExemplares.add(exemplar);
    }

    public String getTitulo() {
        return titulo;
    }
    public int getNumReservas() {
        return numReservas;
    }
    public int getCodigo() {
        return codigo;
    }

}
