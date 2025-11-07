package SingletonRepositories;

import Model.Usuario;

public class UserRepository implements Repository<Usuario>{
    private static UserRepository instance;

    private UserRepository() {}

    public UserRepository getInstance(){
        if(instance == null) {
            instance = new UserRepository();
        }

        return instance;
    }

    @Override
    public Usuario acharPorId() {
        return null;
    }

    @Override
    public void salvar(Usuario entidade) {

    }

    @Override
    public void deletar(Usuario entidade) {

    }
}
