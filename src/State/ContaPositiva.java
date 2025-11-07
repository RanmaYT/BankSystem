package State;

import Model.Conta;

public class ContaPositiva implements IContaState{
    private Conta conta;

    public ContaPositiva(Conta conta) {
        this.conta = conta;
    }

    @Override
    public boolean podeCreditar() {
        return true;
    }

    @Override
    public boolean podeDebitar() {
        return true;
    }

    @Override
    public boolean podeDeletarConta() {
        return true;
    }

    @Override
    public boolean podePegarExtrato() {
        return true;
    }
}
