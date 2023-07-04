package com.mycompany.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Invoker {

    private Sistema sis;

    {
       sis = Sistema.obterSistema();
    }

    private String firstArg; //Comando em si, ex: emp, dev, res...
    private static String secondArg; // Primeiro argumento seguindo o comando
    private static String thirdArg; // Segundo argumento seguindo o comando
    Scanner scanner = new Scanner(System.in);
    public Map<String, Comandos> comandos = new HashMap<String, Comandos>();
    public List<String> inputs = new ArrayList<String>();
    public List<Usuario> reservantes = new ArrayList<Usuario>();

    public Invoker () {

        comandos.put("emp", new ComandoEmprestar(sis) ); //emprestimo
        comandos.put("dev", new ComandoDevolver(sis) ); //devolução
        comandos.put("res", new ComandoReservar(sis) ); //reserva
        //comandos.put("obs", new ObservarCommand(biblioteca) ); //observar, precisamos implementar
        comandos.put("liv", new ComandoConsultaLivro(sis) ); //listar infos do livro
        comandos.put("usu", new ComandoConsultaUsuario(sis) ); //lista de emprestimos/reservas de usuarios
        //comandos.put("ntf", new ConsultarNotificacaoCommand(biblioteca) ); //notificar observadores, tbm.
        comandos.put("sai", new ComandoSairDoSistema(sis) ); //sair do sistema

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

            System.out.println("Comando Inválido, por favor, insira o comando correto \n");
            userInput();

        } else if((firstArg.equals("emp") || firstArg.equals("dev") || firstArg.equals("res") || firstArg.equals("obs")) && (secondArg == null || thirdArg == null)) {

            System.out.println("Ausência de argumentos, por favor insira os argumentos correspondentes \n");
            userInput();

        } else if((firstArg.equals("liv") || firstArg.equals("usu") || firstArg.equals("ntf")) && secondArg == null) {

            System.out.println("Ausência de argumentos, por favor insira os argumentos correspondentes \n");
            userInput();

        } else {

            comandos.get(firstArg).execute(); //Executa primeiro argumento

            if (!Objects.equals(firstArg, "sai")) { //Se o argumento não for "sai", chama de novo - Assim, outros métodos não precisam se preocupar em passar o controle de volta pro Invoker
                userInput();
            }

        }
    }
    public void userInput() {
        System.out.println("myProgram> ");
        ArrayList<String> inputs = new ArrayList<String>();
        String msg = scanner.nextLine();
        String[] palavras = msg.split("\n");

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
    //demonstrar de alguma forma q o usuario pode dar input novamente
    //Usar essa função como a "Static" mencionada em outros métodos sempre que
    //ou argumentos falhem (aqui no Invoker) ou quando Usuário/Livro não são encontrados lá na Biblioteca
    
}