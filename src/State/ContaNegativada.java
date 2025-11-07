package State;

import Model.Conta;

public class ContaNegativada implements IContaState{
    private Conta conta;

    public ContaNegativada(Conta conta) {
        this.conta = conta;
    }

    @Override
    public boolean podeCreditar() {
        return true;
    }

    @Override
    public boolean podeDebitar() {
        System.out.println("A conta está com saldo negativo, permissão negada");
        return false;
    }

    @Override
    public boolean podeDeletarConta() {
        System.out.println("A conta está com saldo negativo, permissão negada");
        return false;
    }

    @Override
    public boolean podePegarExtrato() {
        return true;
    }
}
