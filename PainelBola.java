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
    int x= 0;
    int altura, largura;
    
    private double aceleracao = (double) -9.8;
    private double vInicialY = 0;
    private double vFinalY = 0;
    private double vFinalYAntesdaQueda = 0;
    private double tempo = 0;
    private double espacoY=0;
    private double y =0 ,velocidadeY = 9.8;
    

    /**
     *
     * @param g
     */
    
    public PainelBola(){
    
        thread = new Thread(this);
        thread.start();
        calcularVars();
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
    
    public void calcularVars()
    {
        
       espacoY = -larguraDimensaoDaTela()/2;
       vFinalYAntesdaQueda = 2*aceleracao*espacoY;
       vFinalYAntesdaQueda = - java.lang.Math.sqrt(vFinalYAntesdaQueda);
       tempo = vFinalYAntesdaQueda/aceleracao;
       
        System.out.println("Altura"+espacoY+ "\n" + "Velocidade Final: "+ vFinalYAntesdaQueda+ "\n" + 
        "tempo: " + tempo);
        
        
    }
    
   
    // Função responsável por movimentar a bola
    public void movimentar() {
    
       // Subtraí 60 na altura para garantir que a bola não "afunda-se" ou desaparecesse da tela.
       largura = larguraDimensaoDaTela()/2-25;
       altura = alturaDimensaoDaTela()/2 -70;
       
       x = largura/2;
      
       System.out.println("Y: "+y);
       
      if(y < 0){
            velocidadeY*=-1;  
       }
       
     else if(y > altura){
            velocidadeY*=-1;
           
            System.out.println("Y: "+y);
      }
       y +=velocidadeY;
    
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
                super.repaint();
                this.movimentar();
                Thread.sleep(22);
        
            }
        } catch (InterruptedException ex) {
            System.out.println("ERRO");
        }
                
    }
   
}
