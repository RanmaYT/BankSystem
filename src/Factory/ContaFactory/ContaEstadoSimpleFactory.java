package Factory.ContaFactory;

import State.*;

public class ContaEstadoSimpleFactory {
    public IContaState criarEstadoPorNome(String nomeEstado){
        return switch (nomeEstado) {
            case "positiva" -> new ContaPositiva();
            case "negativada" -> new ContaNegativada();
            case "bloqueada" -> new ContaBloqueada();
            case "encerrada" -> new ContaEncerrada();
            default -> null;
        };
    }
}
