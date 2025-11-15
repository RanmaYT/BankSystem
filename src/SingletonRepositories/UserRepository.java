package SingletonRepositories;

import Model.Admin;
import Model.Cliente;
import Model.UsuarioAbstrato;

public class UserRepository extends BaseRepositoryAbstract<UsuarioAbstrato> {
    private static UserRepository instance;
    private static final String fileName = "Usuarios.txt";

    private UserRepository() {}

    public static UserRepository getInstance(){
        if(instance == null) {
            instance = new UserRepository();
        }

        return instance;
    }

    public UsuarioAbstrato pegarPorCpf(String cpf){
        String linha = buscarLinhaComItem(cpf);

        if(linha == null) {
            System.out.println("Nenhum usuário foi encontrado");
            return null;
        }

        return carregarEntidade(linha);
    }

    @Override
    public UsuarioAbstrato carregarEntidade(String json) {
        // ALERTA/GAMBIARRA: Código altamente acoplado

        if (json.contains("\"tipo\":\"Cliente\"")) {
            return gson.fromJson(json, Cliente.class);
        } else if (json.contains("\"tipo\":\"Admin\"")) {
            return gson.fromJson(json, Admin.class);
        }

        return null;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

}
