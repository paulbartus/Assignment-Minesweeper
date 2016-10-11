import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
    private Random generator = new Random();
    
    public void mousePressed(MouseEvent e) {    
    switch (e.getButton()) {
        case 1:        //Left mouse button
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
    case 3:        //Right mouse button
        //Do nothing
        break;
    default:    //Some other button (2 = Middle mouse button, etc.)
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
        Color whiteColor = Color.WHITE;
        Color grayColor = Color.LIGHT_GRAY;
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
        	if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(grayColor)  && ((myPanel.mouseDownGridY != 0))){
                //Clicked on leftmost column that is not the top-left cell nor the bottom-left cell.
             	for(int i = 1; i < 10; i++){
                     Color newColor = myPanel.colorArray[i][myPanel.mouseDownGridY];
                     while(myPanel.colorArray[i][myPanel.mouseDownGridY].equals(newColor)){
                         switch (generator.nextInt(5)) {
                         case 0:
                             newColor = Color.YELLOW;
                             break;
                         case 1:
                             newColor = Color.MAGENTA;
                             break;
                         case 2:
                             newColor = Color.BLACK;
                             break;
                         case 3:
                             newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
                             break;
                         case 4:
                             newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
                             break;
                         }
                     }                    
                     myPanel.colorArray[i][myPanel.mouseDownGridY] = newColor;
                     myPanel.repaint();
                 }

            }
        	if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(grayColor)  && (myPanel.mouseDownGridX != 0) ){
               //Had pressed on top grey row.
                for(int i = 1; i < 10; i++){
                    //change the column's color from top row to bottom row.      
                    Color newColor = myPanel.colorArray[myPanel.mouseDownGridX][i];
                    while(myPanel.colorArray[myPanel.mouseDownGridX][i].equals(newColor)){
                        switch (generator.nextInt(5)) {
                        case 0:
                            newColor = Color.YELLOW;
                            break;
                        case 1:
                            newColor = Color.MAGENTA;
                            break;
                        case 2:
                            newColor = Color.BLACK;
                            break;
                        case 3:
                            newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
                            break;
                        case 4:
                            newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
                            break;
                        }

                    }

                    

                    myPanel.colorArray[myPanel.mouseDownGridX][i] = newColor;

                    myPanel.repaint();

                }

            }

        	if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(grayColor)  && (myPanel.mouseDownGridX == 0) && (myPanel.mouseDownGridY == 0) ){

                //Had pressed top left grey cell. 

                for(int i = 1; i < 10; i++){

                            

                            Color newColor = myPanel.colorArray[i][i];

                            

                            while(myPanel.colorArray[i][i].equals(newColor)){

                                switch (generator.nextInt(5)) {

                                case 0:

                                    newColor = Color.YELLOW;

                                    break;

                                case 1:

                                    newColor = Color.MAGENTA;

                                    break;

                                case 2:

                                    newColor = Color.BLACK;

                                    break;

                                case 3:

                                    newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)

                                    break;

                                case 4:

                                    newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)

                                    break;

                                }

                            }

                            

                            myPanel.colorArray[i][i] = newColor;

                            myPanel.repaint();

                        }

            }

        	if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(grayColor)  && (myPanel.mouseDownGridX == 0) && (myPanel.mouseDownGridY == 10) ){

                //Had pressed a bottom left grey cell. 

                for(int i = 4; i <= 6; i++){
                            

                            for(int j = 4; j <=6; j++){

                                Color newColor = myPanel.colorArray[i][j];                           

                                while(myPanel.colorArray[i][j].equals(newColor)){

                                    switch (generator.nextInt(5)) {

                                    case 0:

                                        newColor = Color.YELLOW;

                                        break;

                                    case 1:

                                        newColor = Color.MAGENTA;

                                        break;

                                    case 2:

                                        newColor = Color.BLACK;

                                        break;

                                    case 3:

                                        newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)

                                        break;

                                    case 4:

                                        newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)

                                        break;

                                    }

                                }

                                myPanel.colorArray[i][j] = newColor;

                                myPanel.repaint();

                            }                          

                }

        	}

            if ((gridX == -1) || (gridY == -1)) {

                //Is releasing outside

                //Do nothing

            } else {               

                if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {

                    //Released the mouse button on a different cell where it was pressed

                    //Do nothing

                } else {

                    //Released the mouse button on the same cell where it was pressed

                    if ((gridX == 0) || (gridY == 0)) {

                        //do nothing

                    } else {

                        //On the grid other than on the left column and on the top row:

                        System.out.println(myPanel.mouseDownGridX);

                        System.out.println(myPanel.mouseDownGridY);

                        Color newColor = null;

                        switch (generator.nextInt(5)) {

                        case 0:

                            newColor = Color.YELLOW;

                            break;

                        case 1:

                            newColor = Color.MAGENTA;

                            break;

                        case 2:

                            newColor = Color.BLACK;

                            break;

                        case 3:

                            newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)

                            break;

                        case 4:

                            newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)

                            break;

                        }
                        myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
                        myPanel.repaint();
                    }
                }
            }
        }
        myPanel.repaint();
        break;
    case 3:        //Right mouse button
        //Do nothing
        break;
    default:    //Some other button (2 = Middle mouse button, etc.)
        //Do nothing
            break;
        }
    }
}
