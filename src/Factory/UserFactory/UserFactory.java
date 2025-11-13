package Factory.UserFactory;

import Model.UsuarioAbstrato;

public interface UserFactory {
    public UsuarioAbstrato criarUsuario(String nome, String senha, String email, String cpf);
}
