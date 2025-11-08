package Factory;

import Model.*;
import Util.FinancaUtil;

public class ContaCorrenteFactory implements ContaFactory{

    @Override
    public Conta criarConta(Usuario usuario) {
        Cliente cliente = (Cliente) usuario;
        double chequeEspecial = FinancaUtil.calcularChequeEspecial(cliente.getRendaMensal());
        return new ContaCorrente(0, cliente, new ExtratoBancario(), chequeEspecial);
    }
}
