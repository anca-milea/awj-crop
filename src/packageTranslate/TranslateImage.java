package packageTranslate;

import packageSuper.ConvertClass;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TranslateImage extends ConvertClass {
    
    public String sourceFile;
    public String outputFile;

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }
    
    public void Graphic() {
        //afisarea celor doua poze pe ecran
        File sourceFile1;
        sourceFile1 = new File(this.sourceFile);
        DisplayImage vc1 = new DisplayImage(sourceFile1);
        vc1.setVisible(true);
        vc1.setLocation(300, 200);

        File sourceFile2;
        sourceFile2 = new File(this.outputFile);
        DisplayImage vc2 = new DisplayImage(sourceFile2);
        vc2.setVisible(true);
        vc2.setLocation(400 + 720, 200);
    }

    public TranslateImage() {
    }

    public static void main(String[] args) throws IOException {
        //input de la tastatura pentru valorile translatiei si a fisierelor
        Scanner keyboardInput = new Scanner(System.in);
        System.out.println("Introduceti numele fisierului de intrare: ");
        String source = keyboardInput.nextLine();
        System.out.println("Introduceti numele fisierului de iesire: ");
        String output = keyboardInput.nextLine();
        System.out.println("Introduceti valoarea pentru translatia pe axa X: ");
        int translateX = keyboardInput.nextInt();
        System.out.println("Introduceti valoarea pentru translatia pe axa Y: ");
        int translateY = keyboardInput.nextInt();
        
        //un nou obiect de translatie imagine
        TranslateImage TI = new TranslateImage();

        //retinem numele fisierelor de input si output
        TI.sourceFile = source;
        TI.outputFile = output;
        //un nou fisier sursa
        File sourceFile = new File(source);
        
        //calculam timpurile de executie
        int startTime = (int) System.currentTimeMillis();
        
        convertArrayPixelsIntoImage(convertAnImageToPixelsArray(sourceFile), 
                translateX, translateY, output);
        
        int endTime = (int) System.currentTimeMillis();
        int finalTime = (int) (endTime - startTime);
        System.out.println();
        timer("Timpul total de executie: ", finalTime);
        
        //afisarea imaginilor
        TI.Graphic();

    }
}
