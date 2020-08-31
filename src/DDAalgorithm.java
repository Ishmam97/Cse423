import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;
import static java.lang.Math.*;
public class DDAalgorithm implements GLEventListener{

    private GLU glu;
    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        DDA(gl, -10,3,30,40);

    }
    @Override
    public void dispose(GLAutoDrawable arg0) {
        //method body
    }

    @Override
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glViewport(-100, -50, 50, 100);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-100.0, 100.0, -100.0, 100.0);
    }



    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        // method body
    }


    public void DDA(GL2 gl, float x1, float y1, float x2, float y2) {

        gl.glPointSize(3.0f);
        gl.glColor3d(1, 1, 1);

        //write your own code
        float dx = x2-x1;

        float dy = y2-y1;
        //convert negative to positive
        dx = ( dx < 0 )? dx*(-1) : dx;
        dy = ( dy < 0 )? dy*(-1) : dy;
        int steps = (dx>dy)? Math.round(dx) : Math.round(dy);
        float m = dy/dx;
        float Xinc = dx/steps;
        float Yinc = dy/steps;
        float x = x1;
        float y = y1;
        for (int i = 0; i<steps ; i++){

            gl.glBegin(GL2.GL_POINTS);

            gl.glVertex2d(x , y);

            gl.glEnd();
            x+=Xinc;
            y+=Yinc;
        }





    }






    public static void main(String[] args) {
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        DDAalgorithm l = new DDAalgorithm();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(400, 400);
        //creating frame
        final JFrame frame = new JFrame ("straight Line");
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }//end of main
}//end of classimport javax.media.opengl.GL2;