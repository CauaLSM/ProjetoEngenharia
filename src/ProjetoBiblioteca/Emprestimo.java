package com.mycompany.app;

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
    }
    
    public boolean emprestar(Usuarios usuario, Exemplar exemplar) { //Seção 3.1

        //Realizando Checagens gerais pertinentes a esta classe antes de especializar:


        int disponibilidade = 0;
        Exemplares exemplar = null;

        for ( int i=0 ; i < listaExemplares.size() ; i++ ) {

            if (listaExemplares.get(i).getStatus().getNome().equals("Livre")) {
                disponibilidade++;
                //Atribuindo o Exemplar, que talvez será emprestado, a uma variável
                if (disponibilidade==1) { //Atribuindo o Exemplar, que talvez será emprestado, a uma variável
                    exemplar = listaExemplares.get(i);
                }
            }

            if (listaExemplares.get(i).getUsuarioEmprestado()==usuario) { //Definindo que esse usuário já tem um livro desse emprestado
                exemplar.= false;
            }

        }

        //Condição de falha de Disponibilidade (i)
        if (disponibilidade==0) {
            System.out.println("Livro " + titulo + "não pode ser emprestado para " + usuario.getNome() + " por não haver mais exemplares disponíveis no momento.\n");
            return false;
        }

        //Descobrir se o usuário é reservante desse livro ou não
        boolean reservado = false;
        for ( int i=0 ; i < listaExemplares.size() ; i++ ) {
            if (reservantes.get(i)==usuario) {
                reservado = true;
                break;
            }
        }


        if (usuario.emprestimoTeste.podeEmprestar(usuario, this, exemplar, jaEmprestado, disponibilidade, reservado)) {
            emprestimoDeuCerto(exemplar, usuario, reservado);
            return true;
        }
        return false;

    }

}
