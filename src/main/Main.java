package main;

import javax.swing.JFrame;

public class Main {

    static JFrame frame;

    public static final int WIDTH = 700;
    public static final int HEIGHT = 600;
    
    public static void main(String[] args) {
        frame = new JFrame("Aksara Brick");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Controller());
        frame.pack();
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
    }
}