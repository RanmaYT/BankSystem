package Factory.UserFactory;

import Model.Cliente;

public class ClienteFactory implements IUserFactory {
    private double rendaMensal;

    public ClienteFactory(double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    @Override
    public Cliente criarUsuario(String nome, String senha, String email, String cpf) {
        return new Cliente(nome, senha, email, cpf, rendaMensal);
    }
}
