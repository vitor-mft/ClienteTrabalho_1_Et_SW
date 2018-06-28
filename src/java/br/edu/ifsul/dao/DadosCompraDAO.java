
package br.edu.ifsul.dao;

import br.edu.ifsul.servicos.DadosCompra;
import br.edu.ifsul.servicos.ServicoDadosCompraService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author V_M_FT
 */
@Stateless
public class DadosCompraDAO implements Serializable{
    
        private ServicoDadosCompraService dados;

    public DadosCompraDAO(){
        dados = new ServicoDadosCompraService();
        
    }
    
    public List<DadosCompra> getListaDadosCompra(){
        return dados.getServicoDadosCompraPort().listaDadosCompra();
    }
    
    public void inserir(DadosCompra obj){
        dados.getServicoDadosCompraPort().inserir(obj);
    }
    
    public void alterar(DadosCompra obj){
        dados.getServicoDadosCompraPort().alterar(obj);
    }
    
    public void remover(Integer id){
        dados.getServicoDadosCompraPort().remover(id);
    }

    public ServicoDadosCompraService getDadoCompra() {
        return dados;
    }

    public void setCliente(ServicoDadosCompraService dados) {
        this.dados = dados;
    }
    
    
}
