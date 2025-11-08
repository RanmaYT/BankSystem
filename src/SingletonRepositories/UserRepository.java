package SingletonRepositories;

import Model.Usuario;

import java.util.HashMap;
import java.util.Map;

public class UserRepository implements Repository<Usuario>{
    private static UserRepository instance;
    private static Map<Integer, Usuario> usuariosCadastrados;

    private UserRepository() { usuariosCadastrados = new HashMap<>();  }

    public static UserRepository getInstance(){
        if(instance == null) {
            instance = new UserRepository();
        }

        return instance;
    }

    @Override
    public Usuario acharPorId(int id) {
        return usuariosCadastrados.get(id);
    }

    @Override
    public void salvar(Usuario usuario) {
        usuariosCadastrados.put(usuario.getId(), usuario);
    }

    @Override
    public void deletar(Usuario usuario) {
        usuariosCadastrados.remove(usuario.getId());
    }
}
