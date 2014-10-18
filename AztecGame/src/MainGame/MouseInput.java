package MainGame;
import java.awt.Component; 
import java.awt.Point;
import java.awt.event.*; 

/** 
 * Makes handling input a lot simpler 
 */ 
public class MouseInput implements MouseListener, MouseMotionListener 

{        
	private boolean[] mbutton = new boolean[4];
	private boolean inwindow = false;
	private int xpos;
	private int ypos;
	InputManager input;
	
        /** 
         * Assigns the newly created InputHandler to a Component 
         * @param c Component to get input from 
         */ 
        public MouseInput(Component c, InputManager input) 
        { 
                c.addMouseListener(this); 
                c.addMouseMotionListener(this);
                this.input = input;
        } 

        /** 
         * Not used 
         */ 
        public int getX(){
        	return xpos;
        }
        
        public int getY(){
        	return ypos;
        }
        
        public boolean isDown(int mCode) 
        { 
                if (mCode > 0 && mCode < 4) 
                { 
                        return mbutton[mCode]; 
                } 
                
                return false; 
        } 
        
		@Override
		public void mouseClicked(MouseEvent m) { 
            input.mouseClicked(m.getButton());
        }
		
		public void setXYPos(int xLoc, int yLoc) {
			xpos = xLoc - gameframe.rightinset + gameframe.leftinset;
			ypos = yLoc - gameframe.topinset + gameframe.bottominset;
		}
		
		@Override
		public void mouseMoved(MouseEvent m) { 
			setXYPos(m.getX(), m.getY());
            input.mouseMoved(new Point(m.getX(), m.getY()));
        }
		
		@Override
		public void mouseEntered(MouseEvent m) {
			inwindow = true;
			setXYPos(m.getX(), m.getY());
            input.mouseEntered();
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			inwindow = false;
            input.mouseExited();
		}

		@Override
		public void mousePressed(MouseEvent m) {
			// TODO Auto-generated method stub
            if (m.getButton() > 0 && m.getButton() < 4) 
            { 
                    mbutton[m.getButton()] = true; 
            } 
            input.mousePressed(m.getButton());
		}

		@Override
		public void mouseReleased(MouseEvent m) {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			 if (m.getButton() > 0 && m.getButton() < 4) 
            { 
                    mbutton[m.getButton()] = false; 
            } 
	        input.mouseReleased(m.getButton());
		}

		@Override
		public void mouseDragged(MouseEvent m) {
			setXYPos(m.getX(), m.getY());
            input.mouseDragged();
		}
		
		 
		public boolean checkmouseinwindow(){
			return inwindow;
		}
} 