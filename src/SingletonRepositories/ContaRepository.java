package SingletonRepositories;

import Model.Conta;
import Model.Usuario;

import java.util.HashMap;
import java.util.Map;

public class ContaRepository implements Repository<Conta>{
    private static ContaRepository instance;
    private static Map<Usuario, Conta> contasCadastrados;

    private ContaRepository() {
        contasCadastrados = new HashMap<>();
    }

    public static ContaRepository getInstance(){
        if(instance == null) {
            instance = new ContaRepository();
        }

        return instance;
    }

    public Conta acharPorTitular(Usuario titular) {
        return contasCadastrados.get(titular);
    }

    @Override
    public void salvar(Conta conta) {
        contasCadastrados.put(conta.getTitularConta(), conta);
    }

    @Override
    public void deletar(Conta conta) {
        contasCadastrados.remove(conta.getTitularConta());
    }
}
