package Proj;

import java.util.ArrayList;
import java.util.List;

public class Livros implements Observable {

    private int codigo;
    private String titulo;
    private String editora;
    private String autores;
    private String edicao;
    private String anoPublicacao;
    public List<Exemplares> listaExemplares = new ArrayList<Exemplares>();
    private List<Observers> observadores = new ArrayList<Observers>();
    private List<Usuarios> reservantes = new ArrayList<Usuarios>();
    private int numReservas = 0;
    private int numEmprestados = 0;

    // Todos os métodos aqui recebem o userID que foi dado na linha de comando
    // Deve ser evitado tratar esses dados utilizando "if" e "switch" - Usuário deve ter métodos que permitam acessar os valores chaves (ex: dias de empréstimo ou limite de empréstimos)
    // Note que o tratamento de valores limites e algumas regras em geral variam em aplicação dependendo do tipo de usuário (Checar seção 3)


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


    //Seção de Empréstimo

    private void emprestimoDeuCerto(Exemplares livro, Usuarios usuario, boolean reservado) { //Shortcut para evitar repetição de código no método emprestar()
        if (reservado) {
            numReservas--;
            int i = reservantes.indexOf(usuario);
            if (i>=0) reservantes.remove(usuario);
        }
        numEmprestados++;
        livro.getStatus().changeState();
        livro.setUsuarioEmprestado(usuario);
        livro.setDataEmprestimo(java.time.LocalDateTime.now());
        livro.setDataDevolucao(java.time.LocalDateTime.now().plusDays(usuario.getTempoDeEmprestimo()));
        usuario.emprestimoBemSucedido(titulo, livro.getDataEmprestimo() , livro.getDataDevolucao());
    }

    public boolean emprestar(Usuarios usuario) { //Seção 3.1

        //Realizando Checagens gerais pertinentes a esta classe antes de especializar:

        //Checagem se há disponibilidade e se o usuário já possui o livro em mãos. (i) e (vi)
        boolean jaEmprestado = false;
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
                jaEmprestado = true;
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


    //Gets da Classe

    public String getTitulo() {
        return titulo;
    }
    public int getNumReservas() {
        return numReservas;
    }
    public int getCodigo() {
        return codigo;
    }



    //Implementação de Observers

    public void registerObserver(Observers o) { //Seção 3.4 - Biblioteca deve buscar o Usuario e tratá-lo como Observers e passar para esse método do livro
        observadores.add(o);
    }

    public void removeObserver(Observers o) { //Seção 3.4 - Biblioteca deve buscar o Usuario e tratá-lo como Observers e passar para esse método do livro
        int i = observadores.indexOf(o);
        if (i>=0) observadores.remove(o);
    }

    public void notifyObservers() {
        for (int i=0 ; i < observadores.size() ; i++) {
            Observers observer = observadores.get(i);
            observer.update(this);
        }
    }

}
