/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;


import br.edu.ifsul.dao.DadosCompraDAO;
import br.edu.ifsul.servicos.DadosCompra;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.inject.Named;
import javax.rmi.CORBA.Util;
import org.tempuri.CResultado;
import org.tempuri.CalcPrecoPrazoWS;

/**
 *
 * @author V_M_FT
 */
@Named (value = "controleDadosCompra")
@SessionScoped
public class controleDadosCompra implements Serializable{
    
    private DadosCompra objeto;
   
    @EJB
    private DadosCompraDAO dao;
 
    private CalcPrecoPrazoWS freteCorreios;

        private Boolean editando;
    
    
    public controleDadosCompra(){
         editando = false;
         freteCorreios = new CalcPrecoPrazoWS();
    }
    
    public void novo(){
         editando = true;
        objeto = new DadosCompra();
        objeto.setId(0);
       // System.out.println("Chegou no NOVO");
      //  return "form?faces-redirect=true";
    }
    
    public void alterar(DadosCompra obj){
        objeto = obj;
       // return "form?faces-redirect=true";
       editando = true;
    }
    
    public void salvar(){
        if (objeto.getId() == 0){
            dao.inserir(objeto);
        }else{
            dao.alterar(objeto);
        }
        editando = false;
       // return "index?faces-redirect=true";
    }
    public void voltar(){
        editando = false;
                
    }
    
    public void remover (Integer id){
        dao.remover(id);
    }
    
    public DadosCompra getObjeto() {
        return objeto;
    }

    public void setObjeto(DadosCompra objeto) {
        this.objeto = objeto;
    }

    public DadosCompraDAO getDao() {
        return dao;
    }

    public void setDao(DadosCompraDAO dao) {
        this.dao = dao;
    }
    
     public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
    //Parte do Correio
    
    //Função que vai ser acionada no botao no form
    public void calcularFrete(){
         System.out.println("Chegou aqui no Calcula Frere RESULTADO : " );
        CResultado resultado = freteCorreios.getCalcPrecoPrazoWSSoap().calcPrecoPrazo(                
               "",
                "", 
               objeto.getFrete(),//Tipo do Frete (pac,Sedex) 
                objeto.getCepOrigem(),
             objeto.getCepDestino(),
                 objeto.getPeso(),
                 objeto.getFormato(),
                 new BigDecimal(objeto.getComprimento()), //comprimento
                new BigDecimal(objeto.getAltura()), //altura
                new BigDecimal(objeto.getLargura()), //largura
                new BigDecimal(objeto.getDiametro()), //diametro
                objeto.getMaoPropria(),//Mao Propria
               new BigDecimal(objeto.getValorDeclarado()),//valor declarado
               objeto.getAvisoRecebimento());//aviso de recebimento
        

       // System.out.println(resultado.getServicos().getCServico().get(0).getPrazoEntrega());
       if(!resultado.getServicos().getCServico().get(0).getMsgErro().isEmpty()){
           FacesMessage msg3 = new FacesMessage(resultado.getServicos().getCServico().get(0).getMsgErro());
           FacesContext.getCurrentInstance().addMessage(null, msg3);
       }else{
        objeto.setDataEntrega(resultado.getServicos().getCServico().get(0).getPrazoEntrega());
        objeto.setValorFrete(Double.parseDouble(resultado.getServicos().getCServico().get(0).getValor().replace(",", ".")));
       } 
        
        
    }    
}
