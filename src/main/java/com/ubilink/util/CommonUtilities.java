package com.ubilink.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class CommonUtilities 
{
	public static byte[] getImageByteArray(final InputStream inputStream) {
	      byte images[] = null;
	      try{
	          final BufferedImage image = ImageIO.read(inputStream);
	              final ByteArrayOutputStream baos = new ByteArrayOutputStream();
	          ImageIO.write(image, "jpeg", baos);
	          images = baos.toByteArray();
	         }catch (final Exception e) {
	           e.printStackTrace();
	       }
	      return images;
	    }
	
	public static byte[] scaleImage(final BufferedImage bufferedImage,
	        final int size) {
	        final double boundSize = size;

	        final int origWidth = bufferedImage.getWidth();
	        final int origHeight = bufferedImage.getHeight();

	        double scale;

	        if (origHeight > origWidth)
	            scale = boundSize / origHeight;
	        else
	            scale = boundSize / origWidth;

	        /*
	         * Don't scale up small images.
	         */
	        if (scale > 1.0)
	            return (null);

	        final int scaledWidth = (int) (scale * origWidth);
	        final int scaledHeight = (int) (scale * origHeight);

	        final Image scaledImage =
	            bufferedImage.getScaledInstance(scaledWidth, scaledHeight,
	                Image.SCALE_SMOOTH);

	        // new ImageIcon(image); // load image
	        // scaledWidth = scaledImage.getWidth(null);
	        // scaledHeight = scaledImage.getHeight(null);

	        final BufferedImage scaledBI =
	            new BufferedImage(scaledWidth, scaledHeight,
	                BufferedImage.TYPE_INT_RGB);

	        final Graphics2D g = scaledBI.createGraphics();

	        g.drawImage(scaledImage, 0, 0, null);

	        g.dispose();
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        byte[] imageInByte=null;
	        try {
				ImageIO.write( scaledBI, "jpg", baos );
				 baos.flush();
			        imageInByte = baos.toByteArray();
			        baos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       

	        return imageInByte;
	    }
}
