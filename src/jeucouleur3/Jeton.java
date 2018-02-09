package jeucouleur3;
import java.awt.*;
import javax.swing.*;


public class Jeton extends JComponent{
		
		private int x = 0;
		private int y = 0;
		private int width = 50;
		private int height = 50;
		
		public Jeton() {}
		public Jeton(int x, int y, Color couleur) {
			super();
			this.setBackground(couleur);
			this.x = x;
			this.y = y;
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawOval(x, y, width, height);
		}
}
