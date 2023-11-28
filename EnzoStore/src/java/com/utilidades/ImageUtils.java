/*
* ImageUtils.java
*
* Created on 20 de julio de 2005, 11:47
* Resizes jpeg image files on your file system.
* Uses the com.sun.image.codec.jpeg package shipped
* by Sun with Java 2 Standard Edition.
*
* @author Randy Belknap
* @revision Alejandro S�nchez Marcos
* se aprovecha la nueva clase ImageIO de 1.4
* y se a�aden algunos m�todos nuevos
*/
 
package com.utilidades;
 
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
 
/**

*/
public class ImageUtils {


 public static void copyFile(String fileName, InputStream in,String path) {
           try {  
               
               System.out.println(path); 
              OutputStream out = new FileOutputStream(path + fileName);
             
                int read = 0;
                byte[] bytes = new byte[1024];
             
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
             
                in.close();
                out.flush();
                out.close();
             
                System.out.println("New file created!");
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }


 
}