package DTOs.ContaDTOs;

import Model.ContaAbstrata;

public class ContaDTO {
    private double saldo;
    private String tipoConta;
    private String emailTitular;
    private String estadoConta;

    public ContaDTO(ContaAbstrata conta) {
        this.saldo = conta.getSaldo();
        this.tipoConta = conta.getTipoConta();
        this.emailTitular = conta.getEmailTitular();
        this.estadoConta = conta.getNomeEstado();
    }

    @Override
    public String toString(){
        return String.format("Saldo: %.2f\n" +
                        "Email titular: %s\n" +
                        "Tipo conta: %s\n" +
                        "Estado conta %s\n",
                saldo, emailTitular, tipoConta, estadoConta);
    }
}
