package SingletonRepositories;

import Model.Conta;

public class ContaRepository implements Repository<Conta>{
    private static ContaRepository instance;

    private ContaRepository() {}

    public ContaRepository getInstance(){
        if(instance == null) {
            instance = new ContaRepository();
        }

        return instance;
    }

    @Override
    public Conta acharPorId() {
        return null;
    }

    @Override
    public void salvar(Conta entidade) {

    }

    @Override
    public void deletar(Conta entidade) {

    }
}
