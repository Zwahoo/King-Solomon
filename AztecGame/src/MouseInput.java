import java.awt.Component; 
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
        /** 
         * Assigns the newly created InputHandler to a Component 
         * @param c Component to get input from 
         */ 
        public MouseInput(Component c) 
        { 
                c.addMouseListener(this); 
                c.addMouseMotionListener(this);
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
			
        } 
		
		@Override
		public void mouseMoved(MouseEvent m) { 
			xpos = m.getX();
			ypos = m.getY();
        }
		

		@Override
		public void mouseEntered(MouseEvent m) {
			inwindow = true;
			xpos = m.getX();
			ypos = m.getY();
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			inwindow = false;
		}

		@Override
		public void mousePressed(MouseEvent m) {
			// TODO Auto-generated method stub
            if (m.getButton() > 0 && m.getButton() < 4) 
            { 
                    mbutton[m.getButton()] = true; 
            } 
		}

		@Override
		public void mouseReleased(MouseEvent m) {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			 if (m.getButton() > 0 && m.getButton() < 4) 
            { 
                    mbutton[m.getButton()] = false; 
            } 
		} 
		public boolean checkmouseinwindow(){
			return inwindow;
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		
} 