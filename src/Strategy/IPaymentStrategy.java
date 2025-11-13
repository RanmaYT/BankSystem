package Strategy;

import Model.ContaAbstrata;

public interface IPaymentStrategy {
    public double pagar(ContaAbstrata conta, double valor);
}
