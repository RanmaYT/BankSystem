package Model;

import State.IContaState;
import Util.InputUtil;

public class ContaCorrente extends ContaAbstrata {
    private double chequeEspecial;

    public ContaCorrente(double saldo, String emailTitular, IContaState state, double chequeEspecial) {
        super(saldo, emailTitular, state, "Corrente");
        this.chequeEspecial = chequeEspecial;
    }

    public void usarChequeEspecial(double valor){
        // Calcular o valor necessitado
        double quantiaNecessariaDoCheque = valor - getSaldo();

        // Aumentar o saldo, baseado no valor necessitado
        creditar(quantiaNecessariaDoCheque);

        // Debita a nova dívida
        double novoDebito = valor + quantiaNecessariaDoCheque * 1.1;
        super.debitar(novoDebito);
    }

    @Override
    public void debitar(double valor){
        // Verifica se o valor é suficiente para o débito
        double valorPosDebito = getSaldo() - valor;

        if(!(valorPosDebito < 0)) {
            // Caso o valor seja suficiente, só fala pro pai debitar.
            super.debitar(valor);
        }
        else if((valorPosDebito + chequeEspecial) >= 0) {
            InputUtil input = new InputUtil();
            String escolha = "";

            // Oferecer a possibilidade de utilizar o cheque especial, repete enquanto uma entrada válida não for colocada
            while(escolha.isEmpty()) {
                // conta não deveria receber entrada, gambiarra!
                System.out.println("O saldo atual é insuficiente para a compra, é possível utilizar o cheque especial, seu saldo ficará negativado com um adicional de 10% do valor do cheque");
                escolha = input.getAlphaInput("Usar (s/n): ");
            }

            // Trata a entrada
            escolha = escolha.trim().toLowerCase().substring(0,1);

            // Valida o que o usuário escolheu
            if(escolha.equals("s")) { usarChequeEspecial(valor); }
            else { System.out.println("Operação cancelada: negou cheque especial!"); }
        }
        else { System.out.println("Saldo insuficiente para realizar a operação"); }
    }

    // Getters e Setters

    public double getChequeEspecial(){ return chequeEspecial; }
}
