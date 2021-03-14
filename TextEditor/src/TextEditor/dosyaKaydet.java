/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class dosyaKaydet implements FileopInterface {

    private JTextArea textArea;
    private JFileChooser fileChooser;
    private String textString;

    public dosyaKaydet(JTextArea textArea, JFileChooser fileChooser, String textString) {
        this.textArea = textArea;
        this.fileChooser = fileChooser;
        this.textString = textString;
    }
    public JTextArea getTextArea() {
        return textArea;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    public String getTextString() {
        return textString;
    }
    
    
    @Override
    public void dosyaIslemleri() {
        if (fileChooser != null) { // eğer dosya bilgisayarda bir yerde kayıtlı ise var olan dosyanın üzerine yapılan değişiklikler yapılıyor
            String dosya_konum = fileChooser.getSelectedFile().getPath();
            try (FileWriter wr = new FileWriter(dosya_konum)) {
                wr.write(textArea.getText());  // var olan dosyanın üzerine yazma/kaydetme işlemi
            } catch (IOException ex) {
                Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            textString = textArea.getText();
        } else {    // eğer dosya bilgisayarda bir yerde kayıtlı değil ise dosya kaydetme arayüzü çıkıyor
            fileChooser = new JFileChooser();
            int i = fileChooser.showSaveDialog(textArea);
            if (i == JFileChooser.APPROVE_OPTION) { //eğer kullanıcı çıkan arayüzde yer seçip save butonuna basar ise aşağıdaki işlem yapılıyor
                String dosya_konum = fileChooser.getSelectedFile().getPath();
                try (FileWriter writer = new FileWriter(dosya_konum)) { //seçilen yere dosyayı oluşturuyor
                    writer.write(textArea.getText());      // yazılan şeyleri kaydediyor
                } catch (IOException ex) {
                    Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                textString = textArea.getText();
            } else if (i == JFileChooser.CANCEL_OPTION) {   // kullanıcı dosya kaydetme işlemini iptal ederse aşağıdaki işlemler yapılacak
                fileChooser = null;   //dosya kaydetme işlemi yapılırken dosya seçilip sonrasında iptal edilmişse seçilen dosyada kalmamaması için tekrar nulla eşitlenmeli
            }
        }
    }
    
    @Override
    public String retString() {
        return getTextString();
    }

    @Override
    public JFileChooser retFileChooser() {
        return getFileChooser();
    }

    @Override
    public JTextArea retTextArea() {
        return getTextArea();
    }

}
