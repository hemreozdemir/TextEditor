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
public class dosyaAc implements FileopInterface {
    
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private String textString;

    public dosyaAc(JTextArea textArea, JFileChooser fileChooser, String textString) {
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
        //dosya açma işlemi
        if (fileChooser != null) { // eğer dosya bilgisayarda bir yerde kayıtlı  ise buraya giriyor
            if (!textString.equals(textArea.getText())) { //eğer bu kayıtlı olan dosyanın kayıtlı son halinde değişiklik yapılıp kaydedilmediyse buraya giriyor
                int yesNo = JOptionPane.showConfirmDialog(textArea, "Değişiklikleri kaydetmek istiyor musunuz ?");  //yeni dosya açmadan önce yapılan değişikliğin dosyaya kaydedilmesi soruluyor
                switch (yesNo) {
                    case JOptionPane.YES_OPTION -> {
                        String dosya_konum = fileChooser.getSelectedFile().getPath(); //dosyayı önceden kaydedilen yerin üzerine yazma/kaydetme işlemi
                        File newFile = fileChooser.getSelectedFile(); //yeni
                        try (FileWriter wr = new FileWriter(dosya_konum)) {
                            wr.write(textArea.getText());
                        } catch (IOException ex) {
                            Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        textString = textArea.getText();
                        fileChooser = new JFileChooser(); //dosya açma işlemi
                        int i = fileChooser.showOpenDialog(textArea);
                        if (i == JFileChooser.APPROVE_OPTION) { //dosya açma işleminde open butonuna basılırsa seçilen dosyayı açıyor
                            java.io.File file = fileChooser.getSelectedFile();
                            try (Scanner sc = new Scanner(new BufferedReader(new FileReader(file)))) {
                                String metin = "";
                                while (sc.hasNextLine()) {
                                    metin += sc.nextLine() + "\n";
                                }   //açılan dosyanın içi okunuyor
                                textArea.setText(metin);   //okunan metin textareaya tazdırılıyor

                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            textString = textArea.getText();
                        } else if (i == JFileChooser.CANCEL_OPTION) {   //dosya açma işlemince iptal edilirse açıkta olan dosyaya geri dönülüyor
                            //setFileChooser(null);
                            fileChooser.setSelectedFile(newFile); // açıkta olan dosyanın nerede olduğunu hatırlamak için yapılan işlem
                            textString = textArea.getText();       //dosyada yapılan değişiklikler kaydedildiği için text areamız değişmişti ve textStringin de değişmesi gerekiyor
                            return;
                        }
                    }
                    case JOptionPane.NO_OPTION -> { //açıkta olan dosyada değişiklik olduğu halde değişiklik kaydedilmek istenmezse aşağıdaki işlemler yapılıyor

                        File newFile = fileChooser.getSelectedFile(); //dosya açma işlemi iptal edilir diye hali hazırda açık olan dosyanın tutulduğu yerin bilgisi
                        fileChooser = new JFileChooser();
                        int i = fileChooser.showOpenDialog(textArea);
                        if (i == JFileChooser.APPROVE_OPTION) { //dosya açma işleminde open butonuna basılırsa seçilen dosyayı açıyor
                            java.io.File file = fileChooser.getSelectedFile();
                            try (Scanner sc = new Scanner(new BufferedReader(new FileReader(file)))) {
                                String metin = "";
                                while (sc.hasNextLine()) {
                                    metin += sc.nextLine() + "\n";
                                }
                                textArea.setText(metin);   //okunan metin textareaya tazdırılıyor

                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            textString = textArea.getText();   //açılan dosyamız text areamızı değiştirdiği için textStringin de değişmesi gerekiyor
                        } else if (i == JFileChooser.CANCEL_OPTION) {   // kullanıcı dosya açma işlemini iptal ederse aşağıdaki işlemler yapılacak
                            //setFileChooser(null);
                            fileChooser.setSelectedFile(newFile); // hali hazırda açık olan dosyanın dosya yolunu tekrar atıyoruz yukarıda yeni dosya yolu oluştuğu için
                            return;
                        }
                    }
                    case JOptionPane.CANCEL_OPTION -> { //kullanıcı iptal ederse hiç bir değişiklik yapmadan texteditörüne geri dönüyor
                        // bir şey eklenebilir
                        return;
                    }
                    default -> {
                    }
                }
            } else {    //eğer bu kayıtlı olan dosyanın kayıtlı son halinde değişiklik yapılmadıysa buraya giriyor ve soya açma işlemini yapıyor
                File newFile = fileChooser.getSelectedFile(); //dosya açma işlemi iptal edilir diye hali hazırda açık olan dosyanın tutulduğu yerin bilgisi
                fileChooser = new JFileChooser();
                int i = fileChooser.showOpenDialog(textArea);
                if (i == JFileChooser.APPROVE_OPTION) { //dosya açma işleminde open butonuna basılırsa seçilen dosyayı açıyor
                    java.io.File file = fileChooser.getSelectedFile();
                    try (Scanner sc = new Scanner(new BufferedReader(new FileReader(file)))) {
                        String metin = "";
                        while (sc.hasNextLine()) {
                            metin += sc.nextLine() + "\n";
                        }
                        textArea.setText(metin);   //okunan metin textareaya tazdırılıyor

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    textString = textArea.getText();   //açılan dosyamız text areamızı değiştirdiği için textStringin de değişmesi gerekiyor
                } else if (i == JFileChooser.CANCEL_OPTION) {   // kullanıcı dosya açma işlemini iptal ederse aşağıdaki işlemler yapılacak
                    //setFileChooser(null);
                    fileChooser.setSelectedFile(newFile); // hali hazırda açık olan dosyanın dosya yolunu tekrar atıyoruz yukarıda yeni dosya yolu oluştuğu için
                    return;
                }
            }

            // dosya yolu boş ise yani texteditörü ilk açıldığında ya da dosya oluşturulduktan sonra dosya açma işlemi yapılmak istenirse aşağıdaki işlemler yapılacak
        } else {
            if (textArea.getText().isEmpty()) { //text editörü ilk açtığımızda text boş iken ya da boş dosya açılmış ise dosya açma işlemi direk yapılıyor
                fileChooser = new JFileChooser();
                int i = fileChooser.showOpenDialog(textArea);
                if (i == JFileChooser.APPROVE_OPTION) { //dosya açma işleminde open butonuna basılırsa seçilen dosyayı açıyor 
                    java.io.File file = fileChooser.getSelectedFile();
                    try (Scanner sc = new Scanner(new BufferedReader(new FileReader(file)))) {
                        String metin = "";
                        while (sc.hasNextLine()) {
                            metin += sc.nextLine() + "\n";
                        }
                        textArea.setText(metin);   //okunan metin textareaya tazdırılıyor
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    textString = textArea.getText();   //açılan dosyamız text areamızı değiştirdiği için textStringin de değişmesi gerekiyor
                } else if (i == JFileChooser.CANCEL_OPTION) { // kullanıcı dosya açma işlemini iptal ederse aşağıdaki işlemler yapılacak
                    fileChooser = null;      //dosya açma işlemi yapılırken dosya seçilip sonrasında iptal edilmişse seçilen dosyada kalmamaması için tekrar nulla eşitlenmeli
                    //return;
                }
            } else if (!textArea.getText().isEmpty()) {    //eğer text editörü ilk açtığımızda texte bir şeyler yazılmış ise ya da boş dosya açılmış ve sonrasında yazı yazılmış ise dosya açma işlemi yapılmadan önce yapılan değişikliklerin kaydedilmesini soruyor
                int yesNo = JOptionPane.showConfirmDialog(textArea, "Değişiklikleri kaydetmek istiyor musunuz ?");
                switch (yesNo) {
                    case JOptionPane.YES_OPTION -> {    //kullanıcı evet der ise dosyayı kaydetmemek için dosya kaydetme arayüzünü açıyor ve sonrasında dosya açma işlemine giriyor
                        fileChooser = new JFileChooser();
                        int i = fileChooser.showSaveDialog(textArea);
                        if (i == JFileChooser.APPROVE_OPTION) { //dosya kaydetme işleminde save butonuna basılırsa seçilen dosyayı seçilen yere kaydediyor 
                            String dosya_konum = fileChooser.getSelectedFile().getPath();
                            try (FileWriter writer = new FileWriter(dosya_konum)) {
                                writer.write(textArea.getText());
                            } catch (IOException ex) {
                                Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            textString = textArea.getText();   //açılan dosyamız text areamızı değiştirdiği için textStringin de değişmesi gerekiyor
                        } else if (i == JFileChooser.CANCEL_OPTION) { // kullanıcı dosya kaydetme işlemini iptal ederse aşağıdaki işlemler yapılacak
                            fileChooser = null;   //dosya açma işlemi yapılırken dosya seçilip sonrasında iptal edilmişse seçilen dosyada kalmamaması için tekrar nulla eşitlenmeli
                            return;
                        }
                        File newFile = fileChooser.getSelectedFile(); //dosya açma işlemi iptal edilir diye hali hazırda açık olan dosyanın tutulduğu yerin bilgisi
                        fileChooser = new JFileChooser();
                        int a = fileChooser.showOpenDialog(textArea);
                        if (a == JFileChooser.APPROVE_OPTION) { //dosya açma işleminde open butonuna basılırsa seçilen dosyayı açıyor 
                            java.io.File file = fileChooser.getSelectedFile();
                            try (Scanner sc = new Scanner(new BufferedReader(new FileReader(file)))) {
                                String metin = "";
                                while (sc.hasNextLine()) {
                                    metin += sc.nextLine() + "\n";
                                }
                                textArea.setText(metin);   //okunan metin textareaya tazdırılıyor

                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            textString = textArea.getText();
                        } else if (i == JFileChooser.CANCEL_OPTION) {   // kullanıcı dosya açma işlemini iptal ederse aşağıdaki işlemler yapılacak
                            //setFileChooser(null);
                            fileChooser.setSelectedFile(newFile); // hali hazırda açık olan dosyayı kaydettiğimiz yerin dosya yolunu tekrar atıyoruz yukarıda yeni dosya yolu oluştuğu için
                            return;
                        }
                    }
                    case JOptionPane.NO_OPTION -> { //açıkta olan dosyada değişiklik olduğu halde değişiklik kaydedilmek istenmezse dosya açma işlemi yapılıyor
                        fileChooser = new JFileChooser();
                        int i = fileChooser.showOpenDialog(textArea);
                        if (i == JFileChooser.APPROVE_OPTION) { //dosya açma işleminde open butonuna basılırsa seçilen dosyayı açıyor 
                            java.io.File file = fileChooser.getSelectedFile();
                            try (Scanner sc = new Scanner(new BufferedReader(new FileReader(file)))) {
                                String metin = "";
                                while (sc.hasNextLine()) {
                                    metin += sc.nextLine() + "\n";
                                }
                                textArea.setText(metin);   //okunan metin textareaya tazdırılıyor

                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            textString = textArea.getText();
                        } else if (i == JFileChooser.CANCEL_OPTION) {   // kullanıcı dosya açma işlemini iptal ederse bir şey yapılmayıp texteditöre geri dönülecek
                            //setFileChooser(null);
                        }
                    }
                    case JOptionPane.CANCEL_OPTION -> { //kullanıcı iptal ederse hiç bir değişiklik yapmadan texteditörüne geri dönüyor
                        return;
                    }
                    default -> {
                    }
                }
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
