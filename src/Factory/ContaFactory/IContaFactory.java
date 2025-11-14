package Factory.ContaFactory;

import Model.ContaAbstrata;
import Model.UsuarioAbstrato;

public interface IContaFactory {
    public ContaAbstrata criarConta(UsuarioAbstrato usuario);
}
