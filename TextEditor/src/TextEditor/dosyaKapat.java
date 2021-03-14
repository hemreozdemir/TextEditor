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

/**
 *
 * @author Furkan Babacan
 */
public class dosyaKapat implements FileopInterface {
    
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private String textString;

    public dosyaKapat(JTextArea textArea, JFileChooser fileChooser, String textString) {
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
        if (fileChooser != null) { // eğer dosya bilgisayarda bir yerde kayıtlı ise aşağıdaki işlemler yapılıyor
            if (!textString.equals(textArea.getText())) {  //eğer bu kayıtlı olan dosyanın kayıtlı son halinde değişiklik yapılıp kaydedilmediyse buraya giriyor
                int yesNo = JOptionPane.showConfirmDialog(textArea, "Değişiklikleri kaydetmek istiyor musunuz ?");
                switch (yesNo) {
                    case JOptionPane.YES_OPTION -> {    //kullanıcı evet der ise dosyayı kaydetip sonrasında dosyayı kapatıyor
                        String dosya_konum = fileChooser.getSelectedFile().getPath();
                        try (FileWriter wr = new FileWriter(dosya_konum)) {
                            wr.write(textArea.getText());
                        } catch (IOException ex) {
                            Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        fileChooser = null;
                        textArea.setText(null);
                        textString = textArea.getText();
                    }
                    case JOptionPane.NO_OPTION -> {
                        //kullanıcı hayır der ise son yapılan değişiklikler kaydedilmeden dosyayı kapatıyor
                        fileChooser = null;
                        textArea.setText(null);
                        textString = textArea.getText();
                    }
                    case JOptionPane.CANCEL_OPTION -> { //kullanıcı iptal der ise hiçbirşey yapılmıyor
                        return;
                    }
                    default -> {
                    }
                }
            } else {    //eğer kayıtlı olan dosyada değişiklik yapılmadıysa programı kapatır
                fileChooser = null;
                textArea.setText(null);
                textString = textArea.getText();
            }

        } else {    // eğer dosya bilgisayarda bir yerde kayıtlı değil ise aşağıdaki işlemler yapılıyor
            if (!textArea.getText().isEmpty()) { //eğer kayıtlı olmayan hali hazırda açık olan dosya bir şeyler yazılmış ise değişikliklerinin kayıt edilmesini soruyor
                int yesNo = JOptionPane.showConfirmDialog(textArea, "Değişiklikleri kaydetmek istiyor musunuz ?");
                switch (yesNo) {
                    case JOptionPane.YES_OPTION -> {    //kullanıcı evet der ise dosya kaydetme kaydetme arayüzünü açıyor
                        fileChooser = new JFileChooser();
                        int i = fileChooser.showSaveDialog(textArea);
                        if (i == JFileChooser.APPROVE_OPTION) { //kullanıcı arayüzde save'e tıklarsa seçtiği yere dosyayı kaydeder ardından programı kapatır
                            String dosya_konum = fileChooser.getSelectedFile().getPath();
                            try (FileWriter writer = new FileWriter(dosya_konum)) {
                                writer.write(textArea.getText());
                            } catch (IOException ex) {
                                Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            fileChooser = null;
                            textArea.setText(null);
                            textString = textArea.getText();
                        } else if (i == JFileChooser.CANCEL_OPTION) {   //kullanıcı arayüzde cancel'e tıklarsa filechooser'ı sıfırlar ve programa geri döner
                            fileChooser = null;
                            return;
                        }

                    }
                    case JOptionPane.NO_OPTION -> {
                        //kullanıcı hayır der ise dosyayı kaydetmez ve dosyayı kapatır
                        fileChooser = null;
                        textArea.setText(null);
                        textString = textArea.getText();
                    }
                    case JOptionPane.CANCEL_OPTION -> { //kullanıcı cancel der ise programa geri döner
                        return;
                    }
                    default -> {
                    }
                }
            } else {    //dosyada değişiklik yok ise programı kapatır
                fileChooser = null;
                textArea.setText(null);
                textString = textArea.getText();
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
