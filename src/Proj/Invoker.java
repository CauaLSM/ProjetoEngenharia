package Proj;

import java.util.*;

public class Invoker {

    private Biblioteca biblioteca;

    {
        biblioteca = Biblioteca.obterBiblioteca();
    }

    private String firstArg; //Comando em si, ex: emp, dev, res...
    private static String secondArg; // Primeiro argumento seguindo o comando
    private static String thirdArg; // Segundo argumento seguindo o comando
    Scanner scanner = new Scanner(System.in);
    public Map<String, Command> comandos = new HashMap<String, Command>();

    public Invoker () {

        comandos.put("emp", new EmprestimoCommand(biblioteca) ); //emprestimo
        comandos.put("dev", new DevolucaoCommand(biblioteca) ); //devolução
        comandos.put("res", new ReservaCommand(biblioteca) ); //reserva
        comandos.put("obs", new ObservarCommand(biblioteca) ); //obersvar
        comandos.put("liv", new ConsultarLivroCommand(biblioteca) ); //listar infos do livro
        comandos.put("usu", new ConsultarUsuarioCommand(biblioteca) ); //lista de emprestimos/reservas de usuarios
        comandos.put("ntf", new ConsultarNotificacaoCommand(biblioteca) ); //notificar observadorres
        comandos.put("sai", new SairCommand(biblioteca) ); //sair do sistema

        userInput();
    }

    public static String getSecondArg() {
        return secondArg;
    }

    public static String getThirdArg() {
        return thirdArg;
    }

    public void invoke() {


        if(!comandos.containsKey(firstArg) || firstArg == null) {

            System.out.println("Comando Inválido, insira o comando correto por gentileza \n");
            userInput();

        } else if((firstArg.equals("emp") || firstArg.equals("dev") || firstArg.equals("res") || firstArg.equals("obs")) && (secondArg == null || thirdArg == null)) {

            System.out.println("Ausência de argumentos, insira os argumentos correspondentes por gentileza \n");
            userInput();

        } else if((firstArg.equals("liv") || firstArg.equals("usu") || firstArg.equals("ntf")) && secondArg == null) {

            System.out.println("Ausência de argumentos, insira os argumentos correspondentes por gentileza \n");
            userInput();

        } else {

            comandos.get(firstArg).execute(); //Executa primeiro argumento

            if (!Objects.equals(firstArg, "sai")) { //Se o argumento não for "sai", chama de novo - Assim, outros métodos não precisam se preocupar em passar o controle de volta pro Invoker
                userInput();
            }

        }
    }

    //demonstrar de alguma forma q o usuario pode dar input novamente
    //Usar essa função como a "Static" mencionada em outros métodos sempre que
    //ou argumentos falhem (aqui no Invoker) ou quando Usuário/Livro não são encontrados lá na Biblioteca
    public void userInput() {
        System.out.println("myProgram> ");
        ArrayList<String> inputs = new ArrayList<String>();
        String msg = scanner.nextLine();
        String[] palavras = msg.split("\s");

        for(int i = 0; i< palavras.length; i++ ){
            inputs.add(palavras[i]);
        }

        if(inputs.size() == 1) {
            firstArg = inputs.get(0);
        } else if(inputs.size() == 2) {
            firstArg = inputs.get(0);
            secondArg = inputs.get(1);
        } else {
            firstArg = inputs.get(0);
            secondArg = inputs.get(1);
            thirdArg = inputs.get(2);
        }

        invoke();
    }
}


//todo Adicionar a funcionalidade de ter o Invoker "conversando" com o usuário, tipo myProgram> e tudo mais.
//Também ter um método para voltar ao myProgram> para os diferentes comandos chamarem caso o comando esteja digitado erroneamente, preferencialmente com uma mensagem
//Ex: Caso alguém faça emp 100, tá faltando um argumento, então não deve ser aceito e EmprestimoCommand deve ser capaz de retornar o erro
//Caso o Usuário ou Livro não exista lá na Biblioteca, então a Biblioteca também deve ser capaz de chamar esse método quando detectar essa falha
//Ou seja, criar um método Static em Invoker para voltar esse início