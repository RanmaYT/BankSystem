package Model;

import State.IContaState;

public class ContaCorrente extends Conta {
    private double chequeEspecial;

    public ContaCorrente(double saldo, Usuario titularConta, ExtratoBancario extrato, IContaState estadoInicial, double chequeEspecial) {
        super(saldo, titularConta, extrato, estadoInicial);
        this.chequeEspecial = chequeEspecial;
    }

    public void usarChequeEspecial(){

    }

    @Override
    public void debitar(double quantidade){

    }

    @Override
    public String converterParaStringArmazenavel() {
        return "";
    }
}
