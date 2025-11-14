package Factory.ContaFactory;

import Model.ContaPoupanca;
import Model.UsuarioAbstrato;
import State.ContaPositiva;

public class ContaPoupancaFactory implements IContaFactory{
    @Override
    public ContaPoupanca criarConta(UsuarioAbstrato usuario) {
        return new ContaPoupanca(0, usuario.getEmail(), new ContaPositiva());
    }
}
