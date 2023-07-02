package Proj;

import java.util.*;

public class Biblioteca {


    //Caracterizando o Singleton
    private static Biblioteca instancia;

    private Biblioteca() {
        inicializarListas();
    }

    public static Biblioteca obterBiblioteca() {
        if (instancia == null) instancia = new Biblioteca();
        return instancia;
    }

    private List<Livros> listaLivros = new ArrayList<Livros>();

    private List<Usuarios> listaUsuarios = new ArrayList<Usuarios>();

    private void inicializarListas() { //Instancia todos os elementos de exemplo

        listaUsuarios.add(new AlunoGraduacao( 123, "João da Silva"));
        listaUsuarios.add(new AlunoGraduacao(456, "Luiz Fernando Rodrigues"));
        listaUsuarios.add(new AlunoPos(789, "Pedro Paulo"));
        listaUsuarios.add(new Professor(100, "Carlos Lucena"));

        listaLivros.add(new Livros(100, "Engenharia de Software", "AddisonWesley", "Ian Sommervile", "6", "2000"));
        listaLivros.add(new Livros(101, "UML - Guia do Usuário", "Campus", "Grady Booch, James Rumbaugh, Ivar Jacobson", "7", "2000"));
        listaLivros.add(new Livros(200, "Code Complete", "Microsoft Press", "Steve MecConnell", "2", "2014"));
        listaLivros.add(new Livros(201, "Agile Software Development, Principles, Patterns, and Practices", "Prentice Hall", "Robert Martin", "1", "2002"));
        listaLivros.add(new Livros(300, "Refactoring: Improving the Design of Existing Code", "Addison-Wesley Professional", "Martin Fowler", "1", "1999"));
        listaLivros.add(new Livros(301, "Software Metrics: A Rigorous and Practical Approach", "CRC Press", "Norman Fenton, James Bieman", "3", "2014"));
        listaLivros.add(new Livros(400, "Design Patterns: Elements of Reusable Object-Oriented Software", "Addison-Wesley Professional", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "1", "1994"));
        listaLivros.add(new Livros(401, "UML Distilled: A Brief Guide to the Standard Object Modeling Language", "Addison-Wesley Professional", "Martin Fowler", "3", "2003"));

        new Exemplares(findLivro(100), 1);
        new Exemplares(findLivro(100), 2);
        new Exemplares(findLivro(101), 3);
        new Exemplares(findLivro(200), 4);
        new Exemplares(findLivro(201), 5);
        new Exemplares(findLivro(300), 6);
        new Exemplares(findLivro(300), 7);
        new Exemplares(findLivro(400), 8);
        new Exemplares(findLivro(400), 9);

        verificarDatas();
    }



    //Aplicando métodos das diferentes chamadas a serem usadas pelo Command

    private Usuarios findUser(int codUsuario) { //Acha qual o usuário dado seu código
        Usuarios usuarioDesejado=null;
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

    private Livros findLivro(int codLivro) { //Acha qual o livro dado seu código
        Livros livroDesejado=null;
        for (int i=0 ; i < listaLivros.size() ; i++) {
            if (listaLivros.get(i).getCodigo()==codLivro) {
                livroDesejado = listaLivros.get(i);
                break;
            }
        }

        if (livroDesejado==null) {
            System.out.println("Livro de código " + codLivro + " não existe no sistema.\n");
        }

        return livroDesejado;
    }

    public void emprestimo(int codUsuario, int codLivro) { //3.1
        Usuarios usuario = findUser(codUsuario);
        Livros livro = findLivro(codLivro);
        if (!(usuario==null) && !(livro==null)) livro.emprestar(usuario); // !(x||y) === !x && !y
        //Caso não cumpra, o programa devolve controle pro Invoker, note que o erro já foi enviado pelo findUser ou findLivro
    }

    public void devolucao(int codUsuario, int codLivro) { //3.2
        Usuarios usuario = findUser(codUsuario);
        Livros livro = findLivro(codLivro);
        if (!(usuario==null) && !(livro==null)) livro.devolucao(usuario);
    }

    public void reserva(int codUsuario, int codLivro) { //3.3
        Usuarios usuario = findUser(codUsuario);
        Livros livro = findLivro(codLivro);
        if (!(usuario==null) && !(livro==null)) livro.reserva(usuario);
    }

    public void observar(int codUsuario, int codLivro) { //3.4
        Professor usuario = (Professor) findUser(codUsuario); //Aqui será Funcionarios mais pra frente
        Livros livro = findLivro(codLivro);
        if (!(usuario==null) && !(livro==null)) livro.registerObserver(usuario);
    }

    public void consultarLivro(int codLivro) { //3.5.a
        Livros livro = findLivro(codLivro);
        if (!(livro==null)) livro.checarLivro();
    }

    public void consultarUsuario(int codUsuario) { //3.5.b
        Usuarios usuario = findUser(codUsuario);
        if (!(usuario==null)) usuario.listarEmprestimosEReservas();
        //Ativar aqui o método de usuario que vai fazer essa busca descrita na seção 3.5.b do enunciado do trabalho
    }

    public void consultarNotificacao (int codUsuario) { //3.5.c
        Usuarios usuario = findUser(codUsuario);
        if (!(usuario==null)) usuario.getNNotificaoes();
        //Ativar aqui o método de usuário que vai retornar esse dado de quantas vezes ele foi notificado sobre algum livro que observa - descrito na seção 3.5.c do trabalho
    }

    public void sairDoSistema() { //3.6
        System.exit(0);
    }

    public void verificarDatas() {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {

                for(Usuarios usuarios: listaUsuarios){
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
