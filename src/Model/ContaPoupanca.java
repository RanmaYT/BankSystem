package Model;

import State.IContaState;
import Exceptions.OperacaoNaoConcluidaException;

public class ContaPoupanca extends ContaAbstrata{
    public ContaPoupanca(double saldo, String cpfTitular, IContaState state) {
        super(saldo, cpfTitular, state, "Poupança");

    }
    @Override
    public void debitar(double valor) {
        if(valor > getSaldo()) {
            throw new OperacaoNaoConcluidaException("Valor insuficiente!");
        }

        super.debitar(valor);
    }
}
