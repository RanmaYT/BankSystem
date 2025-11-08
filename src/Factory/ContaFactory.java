package Factory;

import Model.Conta;
import Model.Usuario;

public interface ContaFactory {
    public Conta criarConta(Usuario usuario);
}
