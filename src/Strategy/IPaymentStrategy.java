package Strategy;

import Model.Conta;

public interface IPaymentStrategy {
    public double pagar(Conta conta, double valor);
}
