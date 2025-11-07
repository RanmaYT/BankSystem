package Model;

public abstract class Conta {
    private double saldo;
    private Usuario titularConta;
    private ExtratoBancario extrato;
    private IContaState estadoConta;

    public void mudarEstado(IContaState novoEstado){

    }

    public void creditar(double quantidade){

    }

    public void debitar(double quantidade){

    }

    public void deletarConta(){

    }

}
