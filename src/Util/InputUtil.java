package Util;

import View.TextColor;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {
    private Scanner sc = new Scanner(System.in);

    public String getAlphaInput(String campoEntrada){
        // Obs: essa função só coleta inputs formados apenas por letras e espaços
        boolean hasOnlyLetters = false;
        String input = "";

        // Pegar o input
        System.out.print(campoEntrada);
        input = sc.nextLine();

        // Valida se é composto apenas por letras
        hasOnlyLetters = input.matches("[a-zA-Zá-ú ]+");
        if(!hasOnlyLetters) {
            System.out.println(TextColor.RED_BOLD + "Esse campo aceita apenas letras");
            return "";
        }

        return input;
    }

    public String getStringInput(String campoEntrada){
        System.out.print(campoEntrada);
        String input = sc.nextLine();
        return  input;
    }

    public int getIntegerInput(String campoEntrada){
        while(true) {
            try {
                int input;
                // Pegar o input
                System.out.print(campoEntrada);
                input = sc.nextInt();

                // Limpar o input
                sc.nextLine();

                return input;
            } catch(InputMismatchException e) {
                // Limpa o input em caso de erro, e printa uma mensagem
                sc.nextLine();
                System.out.println(TextColor.RED_BOLD + "Esse campo só aceita valores inteiros");

                // Retorna um valor inválido para os contextos
                continue;
            }
        }
    }

    public double getDoubleInput(String campoEntrada) {
        while(true) {
            try {
                double input;
                // Pegar o input
                System.out.print(campoEntrada);
                input = sc.nextDouble();

                // Limpar o input
                sc.nextLine();

                return input;
            } catch(InputMismatchException e) {
                // Limpa o input em caso de erro, e printa uma mensagem
                sc.nextLine();
                System.out.println(TextColor.RED_BOLD + "Esse campo só aceita valores inteiros");
            }
        }
    }
}
