package jeucouleur3;
import java.awt.*;
import javax.swing.*;


public class Jeton extends JComponent{
		
		//private int x = 0;
		//private int y = 0;
		private int width = 50;
		private int height = 50;
                private Color couleur;
		
		public Jeton() {}
		public Jeton(Color couleur) {
			super();
			this.couleur = couleur;
			
		}

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
