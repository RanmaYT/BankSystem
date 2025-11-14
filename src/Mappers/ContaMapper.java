package Mappers;

import DTOs.ContaDTOs.ContaCorrenteDTO;
import DTOs.ContaDTOs.ContaDTO;

import Model.ContaAbstrata;
import Model.ContaCorrente;
import Model.ContaPoupanca;

public class ContaMapper {
    public ContaDTO contaToDto(ContaAbstrata conta, String tipoConta){
        switch (tipoConta) {
            case "Corrente":
                ContaCorrente contaCorrente = (ContaCorrente) conta;
                return new ContaCorrenteDTO(contaCorrente);
            case "Poupança":
                ContaPoupanca contaPoupanca = (ContaPoupanca) conta;
                System.out.println("ContaPoupançaDTO");
                return null;
        }

        return null;
    }
}
