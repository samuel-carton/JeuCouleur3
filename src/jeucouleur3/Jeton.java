package jeucouleur3;
import java.awt.*;
import javax.swing.*;

// Swing permet d'avoir un affichage unique, même sur différents systèmes d'exploitation. De plus, on a trouvé qu'il était facile à déssiner par cet intermédiaire
public class Jeton extends JComponent{
		
		//private int x = 0;
		//private int y = 0;
		private int width = 50;
		private int height = 50;
                private Color couleur;
		
                // - Constructeurs
		public Jeton() {}
		public Jeton(Color couleur) {
			super();
			this.couleur = couleur;
			
		}
                
                // - Getters & Setters
                public void setCouleur(Color couleur) {
                    this.couleur = couleur;
                }
                
                
                @Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
                        if ( couleur != null ){
                            g.setColor(couleur);
                            g.fillOval(0, 0, width, height);
                        }
		}
                
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(width, height);
                }
}
