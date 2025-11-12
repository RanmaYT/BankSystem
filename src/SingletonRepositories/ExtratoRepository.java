package SingletonRepositories;

import Model.ExtratoBancario;

public class ExtratoRepository extends BaseRepository<ExtratoBancario> {
    private static ExtratoRepository instance;
    private static final String fileName = "Extratos.txt";

    private ExtratoRepository() {};

    public static ExtratoRepository getInstance() {
        if(instance == null) {
            instance = new ExtratoRepository();
        }

        return instance;
    }

    public ExtratoBancario pegarPorConta(int contaId) {
        return null;
    }

    @Override
    public ExtratoBancario carregarEntidade(String textoArmazenado) {
        return null;
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
