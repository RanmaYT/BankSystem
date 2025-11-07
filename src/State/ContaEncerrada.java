package State;

import Model.Conta;

public class ContaEncerrada implements IContaState{
    private Conta conta;

    public ContaEncerrada(Conta conta) {
        this.conta = conta;
    }

    @Override
    public boolean podeCreditar() {
        System.out.println("Essa conta já foi encerrada, impossível realizar operações!");
        return false;
    }

    @Override
    public boolean podeDebitar() {
        System.out.println("Essa conta já foi encerrada, impossível realizar operações!");
        return false;
    }

    @Override
    public boolean podeDeletarConta() {
        System.out.println("Essa conta já foi encerrada, impossível realizar operações!");
        return false;
    }

    @Override
    public boolean podePegarExtrato() {
        System.out.println("Essa conta já foi encerrada, impossível realizar operações!");
        return false;
    }
}
