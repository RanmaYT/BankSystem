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
        hasOnlyLetters = input.matches("[a-zA-Zá-úÁ-ÚÇç ]+");
        if(!hasOnlyLetters) {
            System.out.println(TextColor.RED_BOLD + "Esse campo aceita apenas letras" + TextColor.ANSI_RESET);
            return "";
        }

        return input;
    }

    public String getNameInput(String campoEntrada) {
        while (true) {
            System.out.print(TextColor.WHITE_BOLD + campoEntrada);
            String name = sc.nextLine().trim();

            //Verificação de letras maiúsculas e minúsculas
            String[] partesFormato = name.split("\\s+");
            boolean formatoInvalido = false;

            for (String p : partesFormato) {
                String correto = p.substring(0,1).toUpperCase() + p.substring(1).toLowerCase();

                //Só irá pegar se for escrito o nome inteiro maiusculo ou certo
                if (!p.equals(p.toLowerCase()) && !p.equals(correto)) {
                    formatoInvalido = true;
                    break;
                }
            }

            if (formatoInvalido) {
                System.out.println(TextColor.RED_BOLD + "Formato inválido.");
                continue;
            }

            // aqui faz a validação de apenas letras e espaços
            if (!name.matches("[a-zA-Zá-úÁ-ÚçÇ ]+")) {
                System.out.println(TextColor.RED_BOLD + "Use apenas letras e espaços.");
                continue;
            }

            // quebra em partes
            String[] partes = name.split("\\s+");
            boolean valido = true;

            for (String p : partes) {

                // nomes com mais de 3 letras
                if (p.length() < 3) {
                    System.out.println(TextColor.RED_BOLD + "Cada nome deve ter pelo menos 3 letras.");
                    valido = false;
                    break;
                }

                // impede os nomes colados
                for (int i = 1; i < p.length(); i++) {
                    if (Character.isUpperCase(p.charAt(i))) {
                        System.out.println(TextColor.RED_BOLD + "Separe nome e sobrenome com espaço.");
                        valido = false;
                        break;
                    }
                }
            }

            if (valido) {
                return name;
            }
        }
    }

    public String getCPFInput(String campoEntrada){
        while (true) {
            System.out.print(TextColor.WHITE_BOLD + campoEntrada);
            String entrada = sc.nextLine().trim();

            // aqui aceita só números e 11 dígitos
            if (entrada.matches("\\d{11}")) {
                return entrada;
            }

            // apenas no formato XXX.XXX.XXX-XX
            if (entrada.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
                // remove . e -
                String cpfLimpo = entrada.replace(".", "").replace("-", "");
                return cpfLimpo;
            }

            System.out.println(TextColor.RED_BOLD + "CPF inválido. Use 11 dígitos e nos formatos: XXXXXXXXXXX ou XXX.XXX.XXX-XX");
        }
    }

    public String getStringInput(String campoEntrada){
        System.out.print(campoEntrada);
        String input = sc.nextLine();
        return  input;
    }

    public String getPasswordInput(String campoEntrada) {
        String senha;

        while (true) {
            System.out.print(campoEntrada);
            senha = sc.nextLine().trim();

            // Tem que ter exatamente 6 dígitos
            if (!senha.matches("\\d{6}")) {
                System.out.println(TextColor.RED_BOLD + "Senha inválida. Digite exatamente 6 números.");
                continue;
            }

            // Não pode ser todos os números iguais
            if (senha.chars().distinct().count() == 1) {
                System.out.println(TextColor.RED_BOLD + "Senha inválida. Não use todos os dígitos iguais.");
                continue;
            }

            // Não pode ter mais que 2 dígitos iguais seguidos
            boolean repeticaoExcessiva = false;
            int contador = 1;

            for (int i = 1; i < senha.length(); i++) {
                if (senha.charAt(i) == senha.charAt(i - 1)) {
                    contador++;
                    if (contador > 2) {
                        repeticaoExcessiva = true;
                        break;
                    }
                } else {
                    contador = 1;
                }
            }

            if (repeticaoExcessiva) {
                System.out.println(TextColor.RED_BOLD + "Senha inválida! Não use mais que dois dígitos repetidos seguidos.");
                continue;
            }

            return senha;
        }
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
            } catch (InputMismatchException e) {
                // Limpa o input em caso de erro, e printa uma mensagem
                sc.nextLine();
                System.out.println(TextColor.RED_BOLD + "Esse campo só aceita valores inteiros" + TextColor.ANSI_RESET);

                continue;
            }
        }
    }

    public double getDoubleInput(String campoEntrada) {
        while(true) {
            try {
                String input;
                // Pegar o input
                System.out.print(campoEntrada);
                input = sc.nextLine();

                input = input.replace(",", ".");

                double valor = Double.parseDouble(input);

                return valor;
            } catch(InputMismatchException e) {
                // Limpa o input em caso de erro, e printa uma mensagem
                sc.nextLine();
                System.out.println(TextColor.RED_BOLD + "Esse campo só aceita valores flutuantes" + TextColor.ANSI_RESET);
            }
        }
    }

}
