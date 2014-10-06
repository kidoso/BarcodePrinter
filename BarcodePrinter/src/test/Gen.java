package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

public class Gen {
	public static void main(String[] args) throws Exception {
		
		
		Code128Bean bean = new Code128Bean();
		
		final int dpi = 150;
		
		//Open output file
		File outputFile = new File("out.png");
		OutputStream out = new FileOutputStream(outputFile);
		try {
		    //Set up the canvas provider for monochrome PNG output 
		    BitmapCanvasProvider canvas = new BitmapCanvasProvider(
		            out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

		    //Generate the barcode
		    bean.generateBarcode(canvas, "L00001");

		    //Signal end of generation
		    canvas.finish();
		} finally {
		    out.close();
		}
	}

}
