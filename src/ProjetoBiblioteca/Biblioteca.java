package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private int numReservas = 0;
    private int numEmprestados = 0;

    private List<Usuarios> reservantes = new ArrayList<Usuarios>();
    
    //Checar Livro pelo comando "liv"

    public void checarLivro() { //Seção 3.5.a

        //Listar o título e o número de reservas
        System.out.println("Título: " + titulo + "\nNúmero de Reservas: " + numReservas + "\n");

        //Listar o nome de todos reservantes
        for (int i=0 ; i < reservantes.size() ; i++) {
            System.out.println("Nome reservante: " + reservantes.get(i).getNome() + "\n");
        }

        //Listando informações sobre cada exemplar e finalizando a computação do comando
        for (int i=0 ; i < listaExemplares.size() ; i++) {
            System.out.println("Informações de Exemplar: " + listaExemplares.get(i).getCodigo() + " - " + listaExemplares.get(i).getStatus());
            if (listaExemplares.get(i).getStatus().getNome().equals("Emprestado")) System.out.println(" - " + listaExemplares.get(i).getEmprestado() + listaExemplares.get(i).getDataEmprestimo() + listaExemplares.get(i).getDataDevolucao() +"\n");
            else System.out.println("\n");
        }

    }
}
