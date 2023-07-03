package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Usuario> reservantes = new ArrayList<Usuario>();
    private List<Livro> livros = new ArrayList<Livro>();
    
    //Seção de Empréstimo

    private void emprestimoDeuCerto(Livro livro, Usuario usuario, boolean reservado, int exemplarNaLista) { //Shortcut para evitar repetição de código no método emprestar()
        if (reservado) { // se usuario tiver reservado o exemplar do livro, retira ele da lista de reservados
            livro.setNumReservas(livro.getNumReservas() - 1);
            int i = reservantes.indexOf(usuario);
            if (i>=0) reservantes.remove(usuario);
        }
        livro.setNumEmprestados(livro.getNumEmprestados() + 1);
        livro.setUsuarioEmprestado(usuario);
        livro.listaExemplares.get(exemplarNaLista).setDataEmprestimo(java.time.LocalDateTime.now());
        livro.listaExemplares.get(exemplarNaLista).setDataDevolucao(java.time.LocalDateTime.now().plusDays(usuario.getTempoDeEmprestimo()));
        usuario.emprestimoBemSucedido(titulo, livro.getDataEmprestimo() , livro.getDataDevolucao());
    }
    
    public boolean emprestar(Usuario usuario, Livro livro) { //Seção 3.1

        //Realizando Checagens gerais pertinentes a esta classe antes de especializar:

        //Checagem se há disponibilidade e se o usuário já possui o livro em mãos. (i) e (vi)
        boolean jaEmprestado = false;
    	int disponivel = 0;
    	int exemplarNaLista = 0;
        Exemplar exemplarDisp = null;
        

        for (int i=0 ; i < livro.listaExemplares.size() ; i++ ) {
            if (livro.listaExemplares.get(i).isDisp()) { 
                disponivel++; //Atribuindo o Exemplar, que talvez será emprestado, a uma variável
                
                if (disponivel==1) { //Atribuindo o Exemplar, que talvez será emprestado, a uma variável
                    exemplarDisp = livro.listaExemplares.get(i);
                    exemplarNaLista = i;
                }
            }

            if (livro.listaExemplares.get(i).getUsuarioEmprestado() == usuario) { //Definindo que esse usuário já tem um livro desse emprestado
                jaEmprestado = true;
            }
        }

	        //Condição de falha de Disponibilidade (i)
	        if (disponivel==0) {
	            System.out.println("Livro " + livro.getTitulo() + "não pode ser emprestado para " + usuario.getNome() + " por não haver mais exemplares disponíveis no momento.\n");
	            return false;
	        }
	
	        //Descobrir se o usuário é reservante desse livro ou não
	        boolean reservado = false;
	        for (int i=0 ; i < livro.listaExemplares.size() ; i++) {
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


    //Seção de Devolução

    public boolean devolucao(Usuarios usuario) { //Seção 3.2 - Importante fazer a Biblioteca buscar o Usuário e enviar ele pra essa função

        for (int i=0 ; i < listaExemplares.size() ; i++) { //Busca nos exemplares o usuario em questão

            //Caso de Sucesso da Devolução
            if (listaExemplares.get(i).getUsuarioEmprestado() == usuario) {
                listaExemplares.get(i).getStatus().changeState();
                listaExemplares.get(i).setUsuarioEmprestado(null);
                listaExemplares.get(i).setDataEmprestimo(java.time.LocalDateTime.now());
                listaExemplares.get(i).setDataDevolucao(java.time.LocalDateTime.now().plusDays(usuario.getTempoDeEmprestimo()));
                numEmprestados--;

                usuario.livroDevolvido(listaExemplares.get(i)); //Passar pro usuario, por algum método dele, as informações que precisam para atualizar no array de histórico de empréstimos e tal

                System.out.println("Devolução do livro " + titulo + " por " + usuario.getNome() + " realizada com sucesso.\n");
                return true;

            }

        }

        //Caso de Falha da Devolução
        System.out.println("Devolução do livro " + titulo + " por " + usuario.getNome() + " não pôde ser efetivada por usuário não possuir uma cópia do livro\n");
        return false;

    }



    //Seção de Reserva

    public boolean reserva(Usuarios usuario) { //Seção 3.3 - Importante fazer a Biblioteca buscar o Usuário e enviar ele pra essa função

        //Caso de Falha na Reserva
        if(usuario.getNumReservas()>=3) {
            System.out.println("Livro " + titulo + "não pôde ser reservado por " + usuario.getNome() + " pois o número de reservas simultâneas do usuário já alcançou seu limite.\n");
            return false;
        }
        //Caso de Sucesso na Reserva
        else {
            System.out.println("Livro " + titulo + "foi reservado com sucesso por " + usuario.getNome() + ".\n");
            numReservas++;
            if(numReservas==3) notifyObservers(); //Passou de 2 para 3 reservas
            usuario.reservaBemSucedida(getTitulo());
            return true;
        }

    }

   

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
