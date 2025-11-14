package Model;

import State.IContaState;

public class ContaPoupanca extends ContaAbstrata{
    public ContaPoupanca(double saldo, String emailTitular, IContaState state) {
        super(saldo, emailTitular, state, "Poupança");
    }
}
