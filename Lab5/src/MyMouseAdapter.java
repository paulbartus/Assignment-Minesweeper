import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
    int flags = 0;
	private Random generator = new Random();
    
    public void mousePressed(MouseEvent e) {    
    switch (e.getButton()) {
        case 1:		//Left mouse button
        Component c = e.getComponent();
        while (!(c instanceof JFrame)) {
            c = c.getParent();
            if (c == null) {
                return;
            }
        }
      
        JFrame myFrame = (JFrame) c;
        MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
        Insets myInsets = myFrame.getInsets();
        int x1 = myInsets.left;
        int y1 = myInsets.top;
        e.translatePoint(-x1, -y1);
        int x = e.getX();
        int y = e.getY();
        myPanel.x = x;
        myPanel.y = y;
        myPanel.mouseDownGridX = myPanel.getGridX(x, y);
        myPanel.mouseDownGridY = myPanel.getGridY(x, y);
        myPanel.repaint();
        break;
        
    case 3:	//Right mouse button
    	Component d = e.getComponent();
        while (!(d instanceof JFrame)) {
            d = d.getParent();
            if (d == null) {
                return;
            }
        }
        JFrame myFrame2 = (JFrame) d;
        MyPanel myPanel2 = (MyPanel) myFrame2.getContentPane().getComponent(0);  
        Insets myInsets2 = myFrame2.getInsets();
        int x2 = myInsets2.left;
        int y2 = myInsets2.top;
        e.translatePoint(-x2, -y2);
        int x3 = e.getX();
        int y3 = e.getY();
        myPanel2.x = x3;
        myPanel2.y = y3;
        myPanel2.mouseDownGridX = myPanel2.getGridX(x3, y3);
        myPanel2.mouseDownGridY = myPanel2.getGridY(x3, y3);
        myPanel2.repaint();
        break;
    default:	//Some other button (2 = Middle mouse button, etc.)
        //Do nothing
        break;
    }
}

public void mouseReleased(MouseEvent e) {
    switch (e.getButton()) {
    case 1:        //Left mouse button
        Component c = e.getComponent();
        while (!(c instanceof JFrame)) {
            c = c.getParent();
            if (c == null) {
                return;
            }
        }
        JFrame myFrame = (JFrame)c;
        MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
        Insets myInsets = myFrame.getInsets();
        int x1 = myInsets.left;
        int y1 = myInsets.top;
        e.translatePoint(-x1, -y1);
        int x = e.getX();
        int y = e.getY();
        myPanel.x = x;
        myPanel.y = y;
        int gridX = myPanel.getGridX(x, y);
        int gridY = myPanel.getGridY(x, y);
       
        if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
            //Had pressed outside
            //Do nothing
        } else {
            if ((gridX == -1) || (gridY == -1)) {
                //Is releasing outside
                //Do nothing,
            } else {               
                if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
                    //Released the mouse button on a different cell where it was pressed
                    //Do nothing
                } else {
                    //Released on the grid
                    System.out.println(myPanel.mouseDownGridX);
                    System.out.println(myPanel.mouseDownGridY);

                    if(myPanel.minesArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == 1){
                    	for(int j = 0; j < 9; j++){
                    		for(int k = 0; k < 10; k++){
    	                     if(myPanel.minesArray[j][k] == 1){
    	                    	 myPanel.colorArray[j][k] = Color.BLACK;
    	                    	 myPanel.repaint();
    	                      }
                    	   }
                    	}                 
                    } else{
                    	myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.GRAY;
                    }
                    myPanel.repaint();
                    }
               }
         }
        myPanel.repaint();
        break;
        
     case 3:        //Right mouse button
    	Component d = e.getComponent();
        while (!(d instanceof JFrame)) {
            d = d.getParent();
            if (d == null) {
                return;
            }
        }
        JFrame myFrame2 = (JFrame)d;
        MyPanel myPanel2 = (MyPanel) myFrame2.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
        Insets myInsets2 = myFrame2.getInsets();
        int x2 = myInsets2.left;
        int y2 = myInsets2.top;
        e.translatePoint(-x2, -y2);
        int x3 = e.getX();
        int y3 = e.getY();
        myPanel2.x = x3;
        myPanel2.y = y3;
        int grid_X = myPanel2.getGridX(x3, y3);
        int grid_Y = myPanel2.getGridY(x3, y3);
        if ((myPanel2.mouseDownGridX == -1) || (myPanel2.mouseDownGridY == -1)) {
            //Had pressed outside
            //Do nothing
        } else {
               if ((grid_X == -1) || (grid_Y == -1)) {
               //Is releasing outside
               //Do nothing
               } else  {
                        Color newColor = null;
                        switch(generator.nextInt(1)) {
                        case 0:
                            if(myPanel2.colorArray[myPanel2.mouseDownGridX][myPanel2.mouseDownGridY].equals(Color.RED)) {
                            newColor = Color.WHITE;
                            flags = flags - 1;
                            }else {
                            if (flags > 9){
                            newColor = Color.WHITE;
                            }
                            else{
                            	newColor = Color.RED;
                            	flags = flags + 1;
                            }
                            } 
                        case 1:
                       	    if(myPanel2.colorArray[myPanel2.mouseDownGridX][myPanel2.mouseDownGridY].equals(Color.GRAY))   {
                            newColor = Color.GRAY;
                        }
                        break;
               }
                        myPanel2.colorArray[myPanel2.mouseDownGridX][myPanel2.mouseDownGridY] = newColor;
                        myPanel2.repaint();
           
                        }
               }
            
        myPanel2.repaint();
        break;
    default:    //Some other button (2 = Middle mouse button, etc.)
        //Do nothing
        break;
        }
    }
}