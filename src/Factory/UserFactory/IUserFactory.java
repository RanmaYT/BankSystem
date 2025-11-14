package Factory.UserFactory;

import Model.UsuarioAbstrato;

public interface IUserFactory {
    public UsuarioAbstrato criarUsuario(String nome, String senha, String email, String cpf);
}
