package packageSuper;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ConvertClass {
    
    public static void timer(String msg, int... a) {
        System.out.println(msg);
        int ok = 0;
        for (int i : a) {
            System.out.print(i + " ms");
                if (a.length != 1 && ok == 0) {
                System.out.print(", ");
                ok = 1;
                }  
        }    
    }
    
    protected static byte[] convertAnImageToPixelsArray(File file) throws FileNotFoundException {
        //convertim imaginea intr un pixel array
        int startImageToPixel = (int) System.currentTimeMillis();
        //filestream din fisier
        FileInputStream fileStream = new FileInputStream(file);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //noul buffer
        byte[] buffer = new byte[1024];
        try {
            for (int readValue; (readValue = fileStream.read(buffer)) != -1;) {
                outputStream.write(buffer, 0, readValue);
            }
        } catch (IOException exception) {
        }
        int endImageToPixel = (int) System.currentTimeMillis();
        
        System.out.println();
        timer("Timpul de convertire al imaginii in pixeli: ", (endImageToPixel - startImageToPixel));
        return outputStream.toByteArray();
    }

    protected static void convertArrayPixelsIntoImage(byte[] bytes, int translateX, 
            int translateY, String output) throws IOException {
        //convertim array pixels into image
        int startPixelToImage = (int) System.currentTimeMillis();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        Iterator<?> index = ImageIO.getImageReadersByFormatName("bmp");
        ImageReader reader = (ImageReader) index.next();
        Object source = inputStream;
        ImageInputStream imageStream = ImageIO.createImageInputStream(source);
        reader.setInput(imageStream, true);
        ImageReadParam imageParam = reader.getDefaultReadParam();
        Image image = reader.read(0, imageParam);
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), 
                image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        //imagine de tip graphics2d pentru a putea aplica translatia 
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, null, null);

        int endPixelToImage = (int) System.currentTimeMillis();
        
        //efect translatie
        int startTransform = (int) System.currentTimeMillis();

        AffineTransform tx = AffineTransform.getTranslateInstance(translateX, translateY);
        //aplicare translatie
        g2.drawImage(image, tx, null);       
        //creare a noului fisier
        File imageFile = new File(output);
        ImageIO.write(bufferedImage, "bmp", imageFile);

        int endTransform = (int) System.currentTimeMillis();
        
        //print timpuri de convertire si translatie
        System.out.println();
        timer("Timpul de convertire al pixelilor in imagine si cel de translatie: ", 
                (endPixelToImage - startPixelToImage), (endTransform - startTransform));

    }

}
