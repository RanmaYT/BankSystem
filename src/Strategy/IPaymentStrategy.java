package Strategy;

import Model.Conta;

public interface IPaymentStrategy {
    public void pagar(Conta conta, double valor);
}
