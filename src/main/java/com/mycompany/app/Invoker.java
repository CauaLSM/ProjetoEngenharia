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

    private String firstArg; //Comando. ex: emp, dev, res...
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
        comandos.put("obs", new ComandoObservar(sis) ); //observar, precisamos implementar
        comandos.put("liv", new ComandoConsultaLivro(sis) ); //listar infos do livro
        comandos.put("usu", new ComandoConsultaUsuario(sis) ); //lista de emprestimos/reservas de usuarios
        comandos.put("ntf", new ComandoConsultaNotificacao(sis) ); //notificar observadores, tbm.
        comandos.put("sai", new ComandoSairDoSistema(sis) ); //sair do sistema

       // userInput();, se fosse necessário executar o projeto.
    }

    public static String getSecondArg() {
        return secondArg;
    }

    public static String getThirdArg() {
        return thirdArg;
    }

    public void invoke() {
    }
   
}