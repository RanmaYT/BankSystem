package Model;

import State.IContaState;

public class ContaPoupanca extends ContaAbstrata{
    public ContaPoupanca(double saldo, String cpfTitular, IContaState state) {
        super(saldo, cpfTitular, state, "Poupança");
    }
}
