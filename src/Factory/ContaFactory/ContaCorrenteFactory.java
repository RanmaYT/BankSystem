package Factory.ContaFactory;

import Model.Cliente;
import Model.ContaCorrente;
import Model.UsuarioAbstrato;
import State.ContaPositiva;
import Util.FinancaUtil;

public class ContaCorrenteFactory implements IContaFactory{
    @Override
    public ContaCorrente criarConta(UsuarioAbstrato usuario) {
        // Alto acoplamento com a classe cliente.
        Cliente cliente = (Cliente) usuario;

        return new ContaCorrente(0, usuario.getEmail(), new ContaPositiva(), FinancaUtil.calcularChequeEspecial(cliente.getRendaMensal()));
    }
}
