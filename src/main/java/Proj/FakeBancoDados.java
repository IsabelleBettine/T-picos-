/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author isabe
 */
public class FakeBancoDados {
    
    private static Vector<Produto> produtos;
    
    //leitura das informaçoõs do arquivo excel
    private static void cargaArquivo(){
        
        //Ajuste na criaÃ§Ã£o do vetor de produtos static
        if(produtos == null){
            produtos = new Vector<>();
        } else{
            produtos.clear();
        }
        
        File arquivoCsv = new File("C:\\Users\\isabe\\Downloads\\produtos.csv");
        
        try {
            //estruturas de leitura do arquivo
            FileReader marcaLeitura = new FileReader(arquivoCsv);
            
            BufferedReader bufLeitura = new BufferedReader(marcaLeitura);
            
            //****************Ler cada linha*************
            //Lendo a primeira linha(cabeçalho) - descartar
            bufLeitura.readLine();
            String linha= bufLeitura.readLine();
            
            while(linha != null){
                //Linhas seguintes, até o final do arquivo
                
                //78564213;Carne Churrasco;1.26;178 (split)
                String infos[] = linha.split(";");
                
                int cod = Integer.parseInt(infos[0]);
                String nome = infos[1];
                double preco = Double.parseDouble(infos[2]);
                int quant = Integer.parseInt(infos[3]);
                
                //add dos produtos para o vetor dinamico
                produtos.add(new Produto(cod,nome,preco,quant));
                
                //A próxima linha do arquivo
                linha = bufLeitura.readLine();
            }
            
            //Liberando o arquivo para outros processos
            bufLeitura.close();
            
            
            
        } catch (FileNotFoundException ex) {
            System.err.println("Arquivo espec. não existe");
        } catch (IOException e) {
            System.err.println("Arquivo corrompido");
        }
        
    }
    
    public static Produto consultaProdutoCod(int cod){
        
        //Se o arquivo ainda nÃ£o foi carregado, precisamos carregá-lo
        if(produtos == null){
        cargaArquivo();
        }
        
        for(Produto prodI : produtos){                         //for(int i =0; i<produtos.size();i++)
            if(prodI.getCodigo() == cod){                      //if(produtos.get(i).getCodigo() == cod)
                return prodI;
            }
        }
        
        //não existe produto com cod especificado no parametro
        return null;
    }
    
    
    public static void atualizaArquivo(){
        
        File arquivo= new File("C:\\Users\\isabe\\Downloads\\produtos.csv");
        
        try{
        FileWriter escritor = new FileWriter(arquivo);
        
        BufferedWriter bufEscrita = new BufferedWriter(escritor);
        
        for(int i = 0; i < produtos.size(); i++){
            bufEscrita.write(produtos.get(i)+"\n");
        }
        
        bufEscrita.flush();
        bufEscrita.close();
        
        
        } catch (IOException ex){
            System.err.println("Dispositivo com falha");
        }
        
        
    }
}

