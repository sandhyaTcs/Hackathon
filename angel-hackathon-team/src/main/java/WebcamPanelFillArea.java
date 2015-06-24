	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.IOException;

	import javax.imageio.ImageIO;
	import javax.swing.AbstractAction;
	import javax.swing.JButton;
	import javax.swing.JFrame;

	import com.github.sarxos.webcam.Webcam;
	import com.github.sarxos.webcam.WebcamPanel;


	public class WebcamPanelFillArea  {

	    public static void main(String[] args) throws InterruptedException {

	        final Webcam webcam = Webcam.getDefault();

	        final WebcamPanel panel = new WebcamPanel(webcam);
	        //panel.setFPSDisplayed(true);
	        panel.setFillArea(true);

	        JButton button = new JButton("Capture");
	        button.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {

	                if(e.getActionCommand().equals("Capture")){

	                    System.out.println("done::");
	                    // get image
	                    BufferedImage image = webcam.getImage();

	                    // save image to JPEG file
	                    try {
	                        ImageIO.write(image, "JPEG", new File("test.jpg"));

	                    } catch (IOException e1) {
	                        // TODO Auto-generated catch block
	                        e1.printStackTrace();
	                    }
	                }
	            }
	        });
	        JFrame window = new JFrame("AngelHackathonTeam");
	        window.add(panel);
	        panel.add(button);
	        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        window.pack();
	        window.setVisible(true);

	    }

	}