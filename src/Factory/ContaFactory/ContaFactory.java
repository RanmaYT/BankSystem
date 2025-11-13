package Factory.ContaFactory;

import Model.ContaAbstrata;
import Model.UsuarioAbstrato;

public interface ContaFactory {
    public ContaAbstrata criarConta(UsuarioAbstrato usuario);
}
