/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj;

import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author isabe
 */
public class ModeloTabelaCompra extends AbstractTableModel{

    
    private Vector<Produto> carrinhoCompra;
    private CompraGUI painel;
    
    public ModeloTabelaCompra(CompraGUI painel){
        this.carrinhoCompra = new Vector<>();
        this.painel = painel;
    }
    
    
    @Override
    public int getRowCount() {
        return carrinhoCompra.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        
        
       Produto temp = carrinhoCompra.get(linha);
               
               
        switch(coluna){
            case 0: return carrinhoCompra.get(linha).getNome();
            case 1: return carrinhoCompra.get(linha).getPreco();
            case 2: return carrinhoCompra.get(linha).getQuantidade();
            case 3: return carrinhoCompra.get(linha).getQuantidade() * temp.getPreco();
            default: return null;
        }
    }
 
    public void addNovoProduto(Produto vendido){
        this.carrinhoCompra.add(vendido);
    }
    
    public void removeProdutoCarrinho(int indice){
        this.carrinhoCompra.remove(indice);
    }

    @Override
    public String getColumnName(int coluna) {
        switch(coluna){
            case 0: 
                return "NOME";
            case 1: 
                return "PREÃ‡O UNI.";
            case 2: 
                return "QUANTIDADE";
            case 3: 
                return "PREÃ‡O PARC";
            default: 
                return null;
        
        }
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        if(coluna == 2){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void setValueAt(Object novoValor, int linha, int coluna) {
        if(coluna == 2){
            
            //Requisitando a senha do gerente para permitir a modificação de quantidades
            String senha = JOptionPane.showInputDialog(null, "Informe a senha do gerente", "Operação restrita", 
                    JOptionPane.INFORMATION_MESSAGE);
            
            if(senha != null && senha.equals("ifmg")){
                
                carrinhoCompra.get(linha).setQuantidade((int) novoValor);
            
            
                //a tabela e o valor da compra são atualizados
                this.painel.atualizaQuantidade();
            }
            
            
        }
            
    }

    @Override
    public Class<?> getColumnClass(int coluna) {
        switch(coluna){
            case 0: 
                return String.class;
            case 1: 
                return Double.class;
            case 2: 
                return Integer.class;
            case 3: 
                return Double.class;
            default: 
                return null;
        }
    }
    
    public double calculaPrecoParcialCompra(){
        double valor = 0.0;
        
        //recalculando o valor do carrinho com os atuais produtos
        for(Produto p: carrinhoCompra){                    //for(int i = 0; i<carrinhocompra.size(); i++)
            valor += p.getQuantidade() * p.getPreco();       //carrinhocompra.get(i)
        }
        return valor;
    }

    public Vector<Produto> produtosCarrinho() {
        return carrinhoCompra;
    }

    public void limpaCarrinho(){
        this.carrinhoCompra.clear();
    }
    
}
