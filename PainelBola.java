/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bola.gravidade;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

/**
 *
 * @author creuma kuzola
 */
public class PainelBola extends JPanel implements Runnable{
    
    private final Thread thread;
    int x= 0, y = 0, velocidadeX = 10, velocidadeY = 10;
    int altura, largura;

    
    
    /**
     *
     * @param g
     */
    
    public PainelBola(){
    
        thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        this.setBackground(new Color(255,255,255));
        Ellipse2D bola = new Ellipse2D.Double(x,y,50,50);
        g2d.setPaint(new Color(255,165,0));
        g2d.fill(bola);
    }
    
    
    // Função responsável por movimentar a bola
    public void movimentar() {
    
       // Subtraí 60 na altura para garantir que a bola não "afunda-se" ou desaparecesse da tela.
       largura = larguraDimensaoDaTela()/2;
       altura = alturaDimensaoDaTela()/2 -70;
       
       x+=velocidadeX;
       y+=velocidadeY;
       
        if (x > largura) {
            
            velocidadeX*=-1;
        } 
        else if( x < 0) {
            velocidadeX*=-1;
        }
        

        if(y > altura)
            velocidadeY*=-1;
        else if( y < 0){
            velocidadeY*=-1;
        }
    
    
    }
    
    // Funções para pegar a largura e a altura do screen 
     public int larguraDimensaoDaTela(){
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension tamanhoTela = kit.getScreenSize();   
        return tamanhoTela.width;   
    }
    
    public int alturaDimensaoDaTela(){
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension tamanhoTela = kit.getScreenSize();
        return tamanhoTela.height;
     
    }
    
    // Função que permite executar a thread 
    @Override
    public void run() {
        
        try {
            
            while(true)
            {

                this.movimentar();
                Thread.sleep(22);
                super.repaint();
            
            }
        } catch (InterruptedException ex) {
            System.out.println("ERRO");
        }
                
    }
   
}
