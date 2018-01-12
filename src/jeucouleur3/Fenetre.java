/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeucouleur3;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Loris
 */
public class Fenetre extends JFrame{
    private JLabel placeholdingy;
    
    
    public Fenetre(){
        setSize(500,500);
        setTitle("Puissance 3 !! Alignez-en trois, et c'est gagné !");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        instanciation();
    }
    
    public void instanciation(){
        placeholdingy = new JLabel("Je ne sers à rien !");
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        
        c.add(placeholdingy);
        
        setContentPane(c);
    }
}
