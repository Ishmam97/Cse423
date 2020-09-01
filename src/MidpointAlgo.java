import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;
import java.util.*;


public class MidpointAlgo implements GLEventListener{

    private GLU glu;

    public int findZone(double dx , double dy){
        int zone = 0;
        if (Math.abs(dx)>Math.abs(dy)){if (dx > 0){zone = (dy > 0)?0:7;}else{zone = (dy > 0) ? 3 : 4;}}
        else {if (dx > 0){zone = (dy > 0)?1:6;}else{zone = (dy > 0)?2:5;}}
        return zone;
        }
    public double[] convertzone(double x1 ,double y1 ,double x2 ,double y2 ,int zone){
        double[] out = new double[4];
        switch (zone){
            case(0):
                out[0] = x1;
                out[1] = y1;
                out[2] = x2;
                out[3] = y2;
                break;
            case(1):
                out[0] = y1;
                out[2] = y2;
                out[1] = x1;
                out[3] = x2;
                break;
            case(2):
                out[0] = y1;
                out[2] = y2;
                out[1] = -x1;
                out[3] = -x2;
                break;
            case(3):
                out[0] = -x1;
                out[2] = -x2;
                out[1] = y1;
                out[3] = y2;
                break;
            case(4):
                out[0] = -x1;
                out[2] = -x2;
                out[1] = -y1;
                out[3] = -y2;
                break;
            case(5):
                out[0] = -y1;
                out[2] = -y2;
                out[1] = -x1;
                out[3] = -x2;
                break;
            case(6):
                out[0] = -y1;
                out[2] = -y2;
                out[1] = x1;
                out[3] = x2;
                break;
            case(7):
                out[0] = x1;
                out[2] = x2;
                out[1] = -y1;
                out[3] = -y2;
                break;
        }
     return out;
    }
    public double[] originalzone(double x , double y, int zone){
         double[] out = new double[2];
         switch(zone){
             case(0):
                 out[0] = x;
                 out[1] = y;
                 break;
             case(1):
                 out[0] = y;
                 out[1] = x;
                 break;
             case(2):
                 out[0] = -y;
                 out[1] = x;
                 break;
             case(3):
                 out[0] = -x;
                 out[1] = y;
                 break;
             case(4):
                 out[0] = -x;
                 out[1] = -y;
                 break;
             case(5):
                 out[0] = -y;
                 out[1] = -x;
                 break;
             case(6):
                 out[0] = y;
                 out[1] = -x;
                 break;
             case(7):
                 out[0] = x;
                 out[1] = -y;
                 break;
         }
         return out;
    }



    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        Random r = new Random();
        System.out.println("Generating random points");
        double inputs[] = new double[4];
        for (int i = 0 ; i < inputs.length ; ++i) {
            double random = -1 + r.nextFloat() * (2);
            inputs[i] = random;
            switch(i) {
                case 0:
                    System.out.println("X0 = " + inputs[i]);
                    break;
                case 1:
                    System.out.println("y0 = " + inputs[i]);
                    break;
                case 2:
                    System.out.println("X1 = " + inputs[i]);
                    break;
                case 3:
                    System.out.println("y1 = " + inputs[i]);
                    break;
                default:
                    System.out.println("Some oopsie happened");
            }

        }

        double dx = inputs[0] - inputs[2];
        double dy = inputs[1] - inputs[3];
        int zone = findZone(dx , dy);
        double[] converted = convertzone(inputs[0],inputs[1],inputs[2],inputs[3],zone);
        dx = converted[0]-converted[2] ;
        dy = converted[1] - converted[3];
        double d = ( 2 * dy ) - dx;
        double ie = 2 * dy;
        double ine = 2*(dy-dx);
        System.out.println("d: " + d + "; X0_ = "+converted[0] + "; Y0_ = " + converted[1]+ "; X1_ = "+converted[2] + "; Y1_ = " + converted[3]);
        double[] original;
        double x0 = converted[0];
        double y0 = converted[1];
        double x1 = converted[2];
        double y1 = converted[3];
        gl.glBegin (GL2.GL_POINTS);
        gl.glVertex2d(inputs[0],inputs[1]);

        while(x0 > x1){
            d += (d<=0)?ie:ine;
            y1 += (d<=0)?0:0.01;
            x1 += 0.01;
            System.out.println("d: " + d + "; X_ = "+x1 + "; Y_ = " + y1);
            original = originalzone(x1 , y1 , zone);
            System.out.println("Drawing points : X = "+original[0] + "; Y = " + original[1]);
            gl.glVertex2d(original[0],original[1]);
        }

        gl.glVertex2d(inputs[2],inputs[3]);
        gl.glEnd();
    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        //method body
    }

    @Override
    public void init(GLAutoDrawable arg0) {
        // method body
    }

    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        // method body
    }

    public static void main(String[] args) {
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        MidpointAlgo l = new MidpointAlgo();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(600, 400);
        //creating frame
        final JFrame frame = new JFrame ("straight Line");
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }
}