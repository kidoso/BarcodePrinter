package test;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.CanvasProvider;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.output.java2d.Java2DCanvasProvider;

public class Gen {
	public static void main(String[] args) throws Exception {
		
		
		Code128Bean bean = new Code128Bean();
		
		final int dpi = 72;
//		
//		//Open output file
//		File outputFile = new File("out.png");
//		OutputStream out = new FileOutputStream(outputFile);
//		try {
//		    //Set up the canvas provider for monochrome PNG output 
//		    BitmapCanvasProvider canvas = new BitmapCanvasProvider(
//		            out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
//
//		    //Generate the barcode
//		    bean.generateBarcode(canvas, "L00001");
//
//		    //Signal end of generation
//		    canvas.finish();
//		} finally {
//		    out.close();
//		}
		
		JFrame window = new JFrame("test");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(200, 100, 800, 600);
		window.setVisible(true);
		window.setLayout(new BorderLayout());
		
		
		
		@SuppressWarnings("serial")
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g1) {
				Graphics2D g = (Graphics2D) g1;
				g.drawLine(0, 0, 100, 10);
				CanvasProvider provider = new Java2DCanvasProvider(g, 0);
				bean.generateBarcode(provider, "L00001");
				
			}
		};
		window.getContentPane().add(panel, BorderLayout.CENTER);
		
		
		
		panel.repaint();
		
	}

}
