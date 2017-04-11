/*Author Nawaf Alsufiani
4/11/2017
Computer Graphics Cal Poly Pomona
HW1
011601213
Import the libraries
*/
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.Scanner;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

//Main method
 public class Basic {
 public static void main(String[] args) {
     
 //starting point    
 Basic basic = new Basic();
 basic.start();
}
//Load creating window,initial the projector and load the render methods
 public void start() {
       try{
         createWindow();
         initGL();
         render();
        } 
       catch (Exception e){
         System.out.print(e.getMessage());
        }


 }
    //initial the projector
 private void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 0, 480, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
 }
 // windows attribute
 private void createWindow() throws Exception{
        Display.setFullscreen(false);
        Display.setDisplayMode(new DisplayMode(640, 480));
        Display.setTitle("Nawaf Alsufiani");
        Display.create();
 }
 
 
 private void render() throws FileNotFoundException {
         while (!Keyboard.isKeyDown(Keyboard.KEY_Q)) 
        {
            try{
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                glLoadIdentity();
                shape ();
                Display.update();
                Display.sync(60);
            }
            catch(Exception e){
            }
        }
         
        Display.destroy();
}
 //Draw a line on the screen
 public void drawLine(int x1, int y1, int x2, int y2){
        glColor3f(1.0f,0.0f,0.0f);
        glPointSize(1.0f);
        glBegin(GL_POINTS);
       
        float dx = (x2 - x1) / (float)100;
        float dy = (y2 - y1) / (float)100;
          for( int i = 0; i < 100; i++ )
          {
            glVertex2f( i * dx, i * dy );
          } 
        glEnd();
 //end drawing of points
}
 //creating and draw circle on the screen
 public void drawcircle (int k, int r, int h) {
        glColor3f(0.0f,0.0f,1.0f);
        glPointSize(1.0f);
        glBegin(GL_LINES);
        float x,y;
        glColor3f(0.0f,0.0f,1.0f);
         for (int i = 0; i < 180; i++)
        {
        x = (float) (r * cos(i) - h);
        y = (float) (r * sin(i) + k);
        glVertex3f(x + k,y - h,0);
    
        x = (float) (r * cos(i + 0.1) - h);
        y = (float) (r * sin(i + 0.1) + k);
        glVertex3f(x + k,y - h,0);
    }
    glEnd();
}
 //creating and draw circle on the screen 
 public void drawEllipse (int rx,int ry, int h,int k) {
 
        glColor3f(0.0f,1.0f,0.0f);
        glBegin(GL_LINES);
        float x,y;
        for (int i = 0; i < 180; i++)
       {
        x = (float) (rx * cos(i) - h);
        y = (float) (ry * sin(i) + k);
        glVertex3f(x + k,y - h,0);
        x = (float) (rx * cos(i + 0.1) - h);
        y = (float) (ry * sin(i + 0.1) + k);
        glVertex3f(x + k,y - h,0);
       }
       glEnd();
}
 /*method shape to call circle, drawLine and creatEllipse based on the user 
 selection */
 public void shape () throws FileNotFoundException{ 
        Scanner scanner = new Scanner(new File("coordinates.txt"));
        String menu;
        String first=null;
     while (scanner.hasNext())
     {
      menu=scanner.nextLine();
      first=menu.substring(0, 1);
        if(first.equals("c"))
        {
          String xs = menu.substring(2, menu.indexOf(","));
          String ys = menu.substring(menu.indexOf(",")+1, menu.length());
          String rs[] = ys.split(" ");
          int x,y,r;
          x=Integer.parseInt(xs);
          y=Integer.parseInt(rs[0]);
          r=Integer.parseInt(rs[1]);
          System.out.println(x+"  "+y+"  "+r);
          drawcircle(y,r,x);
        }
        
        else if(first.equals("l"))
        {
         System.out.println("l");
         System.out.print("e::");
         String spliter[] = menu.split(",|\\s+");
         int x1,x2,y1,y2;
         x1= Integer.parseInt(spliter[1]);
         y1= Integer.parseInt(spliter[2]);
         x2= Integer.parseInt(spliter[3]);
         y2= Integer.parseInt(spliter[4]);   
        System.out.println(" Line::"+x1+" "+y1+" "+x2+" "+y2);
        drawLine(x1, y1, x2, y2);
        }
        
        else if(first.equals("e"))
       {
         System.out.print("e::");
         String spliter[] = menu.split(",|\\s+");
         int x,y,w,h;
         x= Integer.parseInt(spliter[1]);
         y= Integer.parseInt(spliter[2]);
         w= Integer.parseInt(spliter[3]);
         h= Integer.parseInt(spliter[4]);   
         System.out.println(" Ellipse::"+x+" "+y+" "+w+" "+h);
         drawEllipse(x,y,w,h);
       }
     
     
     
 
     
 } 
 }
 
 
}
