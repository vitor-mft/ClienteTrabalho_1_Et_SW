/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.teste;

import java.math.BigDecimal;
import org.tempuri.CResultado;
import org.tempuri.CalcPrecoPrazoWS;

/**
 *
 * @author V_M_FT
 */
public class TestaServicoCorreio {

    public static void main(String[] args) {
        
        
        CalcPrecoPrazoWS servico = new CalcPrecoPrazoWS();

        CResultado resultado = servico.getCalcPrecoPrazoWSSoap().calcPrecoPrazo( 
                "", "", "04510", "99025530", "88010020", "0.4", 1, new BigDecimal(20.0), new BigDecimal(20.0), new BigDecimal(20.0), new BigDecimal(20.0), "N", new BigDecimal(0.0), "N");

        try {

            System.out.println("data"+resultado.getServicos().getCServico().get(0).getPrazoEntrega());
            System.out.println("valor"+resultado.getServicos().getCServico().get(0).getValor());

        } catch (Exception e) {
            System.out.println("erro" + e);
        }
    }
}