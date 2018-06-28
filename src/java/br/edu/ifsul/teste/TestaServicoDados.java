/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.teste;

import br.edu.ifsul.servicos.DadosCompra;
import br.edu.ifsul.servicos.ServicoDadosCompraService;
import javax.xml.datatype.DatatypeConfigurationException;


/**
 *
 * @author V_M_FT
 */
public class TestaServicoDados {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DatatypeConfigurationException {
        ServicoDadosCompraService compra = new ServicoDadosCompraService();
        DadosCompra dados = new DadosCompra();
        dados.setId(10);
        dados.setCliente("Teste1");
        dados.setCepOrigem("95985000");
        dados.setCepDestino("34333414");
        dados.setEndereco("Rua: Não sei ");
        dados.setValorCompra(500.00);
        dados.setValorFrete(10.00);
        dados.setDataEntrega("12/12/2018");
        
        dados.setFrete("41106");
        dados.setAltura("12");
        dados.setLargura("12");
        dados.setComprimento("12");
        dados.setDiametro("10");
        dados.setAvisoRecebimento("N");
        dados.setFormato(1);//1 caixa
        dados.setMaoPropria("Não");
        dados.setAvisoRecebimento("N");
        dados.setValorDeclarado("500.00");
        dados.setPeso("100");
        
      
        compra.getServicoDadosCompraPort().inserir(dados);
      
        
    }
    
}
