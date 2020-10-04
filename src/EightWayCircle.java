import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
//import java.lang.Math.*;
import javax.swing.*;
import java.math.*;
import java.util.Scanner;

public class EightWayCircle  implements GLEventListener {
    private GLU glu;
    public void init(GLAutoDrawable gld) {
        Scanner scanner = new Scanner(System.in);
        scanner.close();
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    }
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glPushMatrix();
        drawCircle(gl,100);
        drawInnerCircle(gl,50,0);
        drawInnerCircle(gl,50,1);
        drawInnerCircle(gl,50,2);
        drawInnerCircle(gl,50,3);
        drawInnerCircle(gl,50,4);
        drawInnerCircle(gl,50,5);
        drawInnerCircle(gl,50,6);
        drawInnerCircle(gl,50,7);






    }
    private void drawCircle(GL2 gl, int r) {
        int x= r;
        int y= 0;
        int d=1-r;
        draw8Way(gl,x,y);
        while(y<=x){
            if(d<0){
                d=d+(2*y+3);
                y++;
            }
            else{
                d=d+(2*y-2*x+5);
                x--;
                y++;
            }
            draw8Way(gl,x,y);
        }
    }
    private void draw8Way(GL2 gl, int x, int y) {
        gl.glPointSize(2.0f);
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2d(x, y);
        gl.glVertex2d(y, x);
        gl.glVertex2d(-x, y);
        gl.glVertex2d(-y, x);
        gl.glVertex2d(-x, -y);
        gl.glVertex2d(-y, -x);

        gl.glVertex2d(x, -y);
        gl.glVertex2d(y, -x);
        gl.glEnd();
    }
    private void drawInnerCircle(GL2 gl, int r,int zone) {
        int x= r;
        int y= 0;
        int d=1-r;
        int Z=zone;
        inner8way(gl,x,y,Z);
        while(y<=x){
            if(d<0){
                d=d+(2*y+3);
                y++;
            }
            else{
                d=d+(2*y-2*x+5);
                x--;
                y++;
            }
            inner8way(gl,x,y,Z);
        }
    }
    private void inner8way(GL2 gl, int x, int y,int z) {
        int a=0;
        int b=0;
        switch(z){
            case 0 :
                a=50;
                b=0;
                break;
            case 1 :
                a=35;
                b=35;
                break;
            case 2:
                a=0;
                b=50;
                break;
            case 3:
                a=-35;
                b=35;
                break;
            case 4:
                a=-50;
                b=0;
                break;
            case 5:
                a=-35;
                b=-35;
                break;
            case 6:
                a=0;
                b=-50;
                break;
            case 7:
                a=35;
                b=-35;
                break;
    }



        gl.glPointSize(2.0f);
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2d(x+a, y+b);
        gl.glVertex2d(y+a, x+b);
        gl.glVertex2d(-x+a, y+b);
        gl.glVertex2d(-y+a, x+b);
        gl.glVertex2d(-x+a, -y+b);
        gl.glVertex2d(-y+a, -x+b);

        gl.glVertex2d(x+a, -y+b);
        gl.glVertex2d(y+a, -x+b);
        gl.glEnd();
    }


    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                        int height) {
    }
    public void dispose(GLAutoDrawable arg0) {
    }

    public static void main(String[] args) {

        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        EightWayCircle l = new EightWayCircle();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(600, 400);
        //creating frame
        final JFrame frame = new JFrame ("Circle");
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }

}