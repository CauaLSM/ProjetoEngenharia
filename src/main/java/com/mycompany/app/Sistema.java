package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Sistema {
	//Caracterizando o Singleton
    private static Sistema instancia;

    private Sistema() {
        inicializarListas();
    }

    public static Sistema obterSistema() {
        if (instancia == null) instancia = new Sistema();
        return instancia;
    }

    //private List<Biblioteca> listaLivros = new ArrayList<Biblioteca>();
    
    private static Biblioteca bib;

    private List<Usuario> listaUsuarios = new ArrayList<Usuario>();

    private void inicializarListas() { //Instancia todos os elementos de exemplo

        listaUsuarios.add(new UsuarioAlunoGrad( 123, "João da Silva"));
        listaUsuarios.add(new UsuarioAlunoGrad(456, "Luiz Fernando Rodrigues"));
        listaUsuarios.add(new UsuarioAlunoPos(789, "Pedro Paulo"));
        listaUsuarios.add(new UsuarioProfessor(100, "Carlos Lucena"));

        
        bib.addLivro(100, "Engenharia de Software", "AddisonWesley", "Ian Sommervile", "6", "2000");
        bib.addLivro(101, "UML - Guia do Usuário", "Campus", "Grady Booch, James Rumbaugh, Ivar Jacobson", "7", "2000");
        bib.addLivro(200, "Code Complete", "Microsoft Press", "Steve MecConnell", "2", "2014");
        bib.addLivro(201, "Agile Software Development, Principles, Patterns, and Practices", "Prentice Hall", "Robert Martin", "1", "2002");
        bib.addLivro(300, "Refactoring: Improving the Design of Existing Code", "Addison-Wesley Professional", "Martin Fowler", "1", "1999");
        bib.addLivro(301, "Software Metrics: A Rigorous and Practical Approach", "CRC Press", "Norman Fenton, James Bieman", "3", "2014");
        bib.addLivro(400, "Design Patterns: Elements of Reusable Object-Oriented Software", "Addison-Wesley Professional", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "1", "1994");
        bib.addLivro(401, "UML Distilled: A Brief Guide to the Standard Object Modeling Language", "Addison-Wesley Professional", "Martin Fowler", "3", "2003");

        
        bib.addExemplar(bib.identificaLivroNaLista(100), 1);
        bib.addExemplar(bib.identificaLivroNaLista(100), 2);
        bib.addExemplar(bib.identificaLivroNaLista(101), 3);
        bib.addExemplar(bib.identificaLivroNaLista(200), 4);
        bib.addExemplar(bib.identificaLivroNaLista(201), 5);
        bib.addExemplar(bib.identificaLivroNaLista(300), 6);
        bib.addExemplar(bib.identificaLivroNaLista(300), 7);
        bib.addExemplar(bib.identificaLivroNaLista(400), 8);
        bib.addExemplar(bib.identificaLivroNaLista(400), 9);

        verificarDatas();
    }



    //Aplicando métodos das diferentes chamadas a serem usadas pelo Command

    private Usuario findUser(int codUsuario) { //Acha qual o usuário dado seu código
        Usuario usuarioDesejado=null;
        for (int i=0 ; i < listaUsuarios.size() ; i++) {
            if (listaUsuarios.get(i).getCodigo()==codUsuario) {
                usuarioDesejado = listaUsuarios.get(i);
                break;
            }
        }

        if (usuarioDesejado==null) {
            System.out.println("Usuário de código " + codUsuario + " não existe no sistema.\n");
        }

        return usuarioDesejado;
    }


    public void emprestimo(int codUsuario, int codLivro) { //3.1
        Usuario usuario = findUser(codUsuario);
        Livro livro = bib.identificaLivroNaLista(codLivro);
        if (!(usuario==null) && !(livro==null)) bib.emprestar(usuario, livro); // !(x||y) === !x && !y
        //Caso não cumpra, o programa devolve controle pro Invoker, note que o erro já foi enviado pelo findUser ou findLivro
    }

    public void devolucao(int codUsuario, int codLivro) { //3.2
        Usuario usuario = findUser(codUsuario);
        Livro livro = bib.identificaLivroNaLista(codLivro);
        if (!(usuario==null) && !(livro==null)) bib.devolucao(usuario, livro);
    }

    public void reserva(int codUsuario, int codLivro) { //3.3
        Usuario usuario = findUser(codUsuario);
        Livro livro = bib.identificaLivroNaLista(codLivro);
        if (!(usuario==null) && !(livro==null)) bib.reserva(usuario, livro);
    }

    public void consultarLivro(int codLivro) { //3.5.a
        Livro livro = bib.identificaLivroNaLista(codLivro);
        if (!(livro==null)) bib.checarLivro(livro);
    }

    public void consultarUsuario(int codUsuario) { //3.5.b
        Usuario usuario = findUser(codUsuario);
        if (!(usuario==null)) usuario.listarEmprestimosEReservas();
        //Ativar aqui o método de usuario que vai fazer essa busca descrita na seção 3.5.b do enunciado do trabalho
    }

    public void sairDoSistema() { //3.6
        System.exit(0);
    }

    public void verificarDatas() {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {

                for(Usuario usuarios: listaUsuarios){
                    usuarios.verificarDatas();
                }

            }
        };
        Timer timer = new Timer("Timer");

        long delay = 1000L;
        long period = 1000L * 60L * 60L * 24L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }
}

