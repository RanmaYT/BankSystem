package SingletonRepositories;

import Model.Conta;

import java.util.HashMap;
import java.util.Map;


public class ContaRepository implements Repository<Conta>{
    private static ContaRepository instance;
    private static Map<Integer, Conta> contasCadastrados;

    private ContaRepository() {
        contasCadastrados = new HashMap<>();
    }

    public static ContaRepository getInstance(){
        if(instance == null) {
            instance = new ContaRepository();
        }

        return instance;
    }

    @Override
    public Conta acharPorId(int id) {
        return contasCadastrados.get(id);
    }

    @Override
    public void salvar(Conta conta) {
        contasCadastrados.put(conta.getId(), conta);
    }

    @Override
    public void deletar(Conta conta) {
        contasCadastrados.remove(conta.getId());
    }
}
