package DTOs.ContaDTOs;

import Model.ContaAbstrata;

public abstract class ContaDTO {
    private double saldo;
    private String tipoConta;
    private String cpfTitular;
    private String estadoConta;

    public ContaDTO(ContaAbstrata conta) {
        this.saldo = conta.getSaldo();
        this.tipoConta = conta.getTipoConta();
        this.cpfTitular = conta.getCpfTitular();
        this.estadoConta = conta.getNomeEstado();
    }

    @Override
    public String toString(){
        return String.format("Saldo: %.2f\n" +
                        "CPF titular: %s\n" +
                        "Tipo conta: %s\n" +
                        "Estado conta: %s",
                saldo, cpfTitular, tipoConta, estadoConta);
    }
}
