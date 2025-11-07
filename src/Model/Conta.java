package Model;

import SingletonRepositories.IStorable;
import State.IContaState;

public abstract class Conta implements IStorable {
    private int id;
    private double saldo;
    private Usuario titularConta;
    private ExtratoBancario extrato;
    private IContaState estadoConta;

    public Conta(double saldo, Usuario titularConta, ExtratoBancario extrato, IContaState estadoInicial) {
        this.saldo = saldo;
        this.titularConta = titularConta;
        this.extrato = extrato;
        this.estadoConta = estadoInicial;
    }

    public void mudarEstado(IContaState novoEstado){

    }

    public void creditar(double quantidade){

    }

    public void debitar(double quantidade){

    }

    public void deletarConta(){

    }

    public abstract String converterParaStringArmazenavel();
    public int pegarId() { return id; }
}
