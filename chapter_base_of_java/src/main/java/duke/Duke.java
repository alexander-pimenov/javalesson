package duke;

import javax.swing.*;
import java.net.URL;

/******************************************************************************
 * Compilation: javac duke.Duke.java
 * Execution: java duke.Duke
 * Dependencies: duke.StdDraw.java StdIn.java
 * <p>
 * Draw the sequence of images T1.gif to T17.gif. This creates
 * the illusion of motion, where the Java mascot duke.Duke cart-wheels
 * across the screen.
 * <p>
 * Reference: <a href="http://java.sun.com/docs/books/tutorial/uiswing/components/example-1dot4/index.html#TumbleItem">reference</a>
 *
 ******************************************************************************/
public class Duke extends JFrame {

//    private Duke() {
//        int images = 17; // number of images
//        int WIDTH = 150, HEIGHT = 100; // images are 130-by-80
//        StdDraw.setCanvasSize(WIDTH, HEIGHT);
//        StdDraw.setXscale(0, WIDTH);
//        StdDraw.setYscale(0, HEIGHT);
//        StdDraw.setTitle("Duke! Somersault!");
//        StdDraw.enableDoubleBuffering();
//        String path = "/T1-T17/";
//        URL fileUrl = Duke.class.getResource(path);
//        String absolutePath = fileUrl.getPath();
//        // main animation loop
//        for (int t = 0; true; t++) {
//            int i = 1 + (t % images);
//            System.out.println(" i = " + i);
//            String filename = absolutePath + "T" + i + ".gif"; // name of the ith image
//
//            StdDraw.picture(WIDTH / 2.0, HEIGHT / 2.0, filename);
//            StdDraw.show();
//            StdDraw.pause(100);
//        }
//
//    }

    public static void main(String[] args) {
       //new Duke();

        int images = 17; // number of images
//        int WIDTH = 150, HEIGHT = 100; // images are 130-by-80
        int WIDTH = 130, HEIGHT = 80; // images are 130-by-80
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.setTitle("Duke! Somersault!");
        StdDraw.enableDoubleBuffering();
        String path = "/T1-T17/";
        URL fileUrl = Duke.class.getResource(path);
        String absolutePath = fileUrl.getPath();
        // main animation loop
        for (int t = 0; true; t++) {
            int i = 1 + (t % images);
            System.out.println(" i = " + i);
            String filename = absolutePath + "T" + i + ".gif"; // name of the ith image

            StdDraw.picture(WIDTH / 2.0, HEIGHT / 2.0, filename);
            StdDraw.show();
            StdDraw.pause(100);
        }
    }
}
