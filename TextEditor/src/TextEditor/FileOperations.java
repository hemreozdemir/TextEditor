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

public class FileOperations {

    private JTextArea textArea;
    private JFileChooser fileChooser;
    private String textString = "";    //textAreadadaki yazılanların en son kaydedilmiş halini tutacağımız String  

    public FileOperations() {
    }

    public FileOperations(JTextArea textArea) {
        this.textArea = textArea;
    }

    //dosya açma işlemi
    public void open() {
        if (getFileChooser() != null) { // eğer dosya bilgisayarda bir yerde kayıtlı  ise buraya giriyor
            if (!textString.equals(getTextArea().getText())) { //eğer bu kayıtlı olan dosyanın kayıtlı son halinde değişiklik yapılıp kaydedilmediyse buraya giriyor
                int yesNo = JOptionPane.showConfirmDialog(textArea, "Değişiklikleri kaydetmek istiyor musunuz ?");  //yeni dosya açmadan önce yapılan değişikliğin dosyaya kaydedilmesi soruluyor
                switch (yesNo) {
                    case JOptionPane.YES_OPTION -> { //kullanıcı evet der ise dosyayı üzerine yazıyor(kaydediyor) ve sonrasında dosya açma işlemine giriyor
                        String dosya_konum = getFileChooser().getSelectedFile().getPath(); //dosyayı önceden kaydedilen yerin üzerine yazma/kaydetme işlemi
                        File newFile = getFileChooser().getSelectedFile(); //yeni
                        try (FileWriter wr = new FileWriter(dosya_konum)) {
                            wr.write(getTextArea().getText());
                        } catch (IOException ex) {
                            Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        textString = getTextArea().getText();
                        setFileChooser(new JFileChooser()); //dosya açma işlemi
                        int i = getFileChooser().showOpenDialog(getTextArea());
                        if (i == JFileChooser.APPROVE_OPTION) { //dosya açma işleminde open butonuna basılırsa seçilen dosyayı açıyor
                            java.io.File file = getFileChooser().getSelectedFile();
                            try (Scanner sc = new Scanner(new BufferedReader(new FileReader(file)))) {
                                String metin = "";
                                while (sc.hasNextLine()) {
                                    metin += sc.nextLine() + "\n";
                                }   //açılan dosyanın içi okunuyor
                                getTextArea().setText(metin);   //okunan metin textareaya tazdırılıyor

                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            textString = getTextArea().getText();
                        } else if (i == JFileChooser.CANCEL_OPTION) {   //dosya açma işlemince iptal edilirse açıkta olan dosyaya geri dönülüyor
                            //setFileChooser(null);
                            getFileChooser().setSelectedFile(newFile); // açıkta olan dosyanın nerede olduğunu hatırlamak için yapılan işlem
                            textString = getTextArea().getText();       //dosyada yapılan değişiklikler kaydedildiği için text areamız değişmişti ve textStringin de değişmesi gerekiyor
                            return;
                        }
                    }
                    case JOptionPane.NO_OPTION -> { //açıkta olan dosyada değişiklik olduğu halde değişiklik kaydedilmek istenmezse aşağıdaki işlemler yapılıyor

                        File newFile = getFileChooser().getSelectedFile(); //dosya açma işlemi iptal edilir diye hali hazırda açık olan dosyanın tutulduğu yerin bilgisi
                        setFileChooser(new JFileChooser());
                        int i = getFileChooser().showOpenDialog(getTextArea());
                        if (i == JFileChooser.APPROVE_OPTION) { //dosya açma işleminde open butonuna basılırsa seçilen dosyayı açıyor
                            java.io.File file = getFileChooser().getSelectedFile();
                            try (Scanner sc = new Scanner(new BufferedReader(new FileReader(file)))) {
                                String metin = "";
                                while (sc.hasNextLine()) {
                                    metin += sc.nextLine() + "\n";
                                }
                                getTextArea().setText(metin);   //okunan metin textareaya tazdırılıyor

                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            textString = getTextArea().getText();   //açılan dosyamız text areamızı değiştirdiği için textStringin de değişmesi gerekiyor
                        } else if (i == JFileChooser.CANCEL_OPTION) {   // kullanıcı dosya açma işlemini iptal ederse aşağıdaki işlemler yapılacak
                            //setFileChooser(null);
                            getFileChooser().setSelectedFile(newFile); // hali hazırda açık olan dosyanın dosya yolunu tekrar atıyoruz yukarıda yeni dosya yolu oluştuğu için
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
            } else {    //eğer bu kayıtlı olan dosyanın kayıtlı son halinde değişiklik yapılmadıysa buraya giriyor ve dosya açma işlemini yapıyor
                File newFile = getFileChooser().getSelectedFile(); //dosya açma işlemi iptal edilir diye hali hazırda açık olan dosyanın tutulduğu yerin bilgisi
                setFileChooser(new JFileChooser());
                int i = getFileChooser().showOpenDialog(getTextArea());
                if (i == JFileChooser.APPROVE_OPTION) { //dosya açma işleminde open butonuna basılırsa seçilen dosyayı açıyor
                    java.io.File file = getFileChooser().getSelectedFile();
                    try (Scanner sc = new Scanner(new BufferedReader(new FileReader(file)))) {
                        String metin = "";
                        while (sc.hasNextLine()) {
                            metin += sc.nextLine() + "\n";
                        }
                        getTextArea().setText(metin);   //okunan metin textareaya tazdırılıyor

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    textString = getTextArea().getText();   //açılan dosyamız text areamızı değiştirdiği için textStringin de değişmesi gerekiyor
                } else if (i == JFileChooser.CANCEL_OPTION) {   // kullanıcı dosya açma işlemini iptal ederse aşağıdaki işlemler yapılacak
                    //setFileChooser(null);
                    getFileChooser().setSelectedFile(newFile); // hali hazırda açık olan dosyanın dosya yolunu tekrar atıyoruz yukarıda yeni dosya yolu oluştuğu için
                    return;
                }
            }

            // dosya yolu boş ise yani texteditörü ilk açıldığında ya da dosya oluşturulduktan sonra dosya açma işlemi yapılmak istenirse aşağıdaki işlemler yapılacak
        } 
        else {
            if (getTextArea().getText().isEmpty()) { //text editörü ilk açtığımızda text boş iken ya da boş dosya açılmış ise dosya açma işlemi direk yapılıyor
                setFileChooser(new JFileChooser());
                int i = getFileChooser().showOpenDialog(getTextArea());
                if (i == JFileChooser.APPROVE_OPTION) { //dosya açma işleminde open butonuna basılırsa seçilen dosyayı açıyor 
                    java.io.File file = getFileChooser().getSelectedFile();
                    try (Scanner sc = new Scanner(new BufferedReader(new FileReader(file)))) {
                        String metin = "";
                        while (sc.hasNextLine()) {
                            metin += sc.nextLine() + "\n";
                        }
                        getTextArea().setText(metin);   //okunan metin textareaya tazdırılıyor
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    textString = getTextArea().getText();   //açılan dosyamız text areamızı değiştirdiği için textStringin de değişmesi gerekiyor
                } else if (i == JFileChooser.CANCEL_OPTION) { // kullanıcı dosya açma işlemini iptal ederse aşağıdaki işlemler yapılacak
                    setFileChooser(null);       //dosya açma işlemi yapılırken dosya seçilip sonrasında iptal edilmişse seçilen dosyada kalmamaması için tekrar nulla eşitlenmeli
                    //return;
                }
            } else if (!getTextArea().getText().isEmpty()) {    //eğer text editörü ilk açtığımızda texte bir şeyler yazılmış ise ya da boş dosya açılmış ve sonrasında yazı yazılmış ise dosya açma işlemi yapılmadan önce yapılan değişikliklerin kaydedilmesini soruyor
                int yesNo = JOptionPane.showConfirmDialog(textArea, "Değişiklikleri kaydetmek istiyor musunuz ?");
                switch (yesNo) {
                    case JOptionPane.YES_OPTION -> {    //kullanıcı evet der ise dosyayı kaydetmemek için dosya kaydetme arayüzünü açıyor ve sonrasında dosya açma işlemine giriyor
                        setFileChooser(new JFileChooser());
                        int i = getFileChooser().showSaveDialog(getTextArea());
                        if (i == JFileChooser.APPROVE_OPTION) { //dosya kaydetme işleminde save butonuna basılırsa seçilen dosyayı seçilen yere kaydediyor 
                            String dosya_konum = getFileChooser().getSelectedFile().getPath();
                            try (FileWriter writer = new FileWriter(dosya_konum)) {
                                writer.write(getTextArea().getText());
                            } catch (IOException ex) {
                                Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            textString = getTextArea().getText();   //açılan dosyamız text areamızı değiştirdiği için textStringin de değişmesi gerekiyor
                        } else if (i == JFileChooser.CANCEL_OPTION) { // kullanıcı dosya kaydetme işlemini iptal ederse aşağıdaki işlemler yapılacak
                            setFileChooser(null);   //dosya açma işlemi yapılırken dosya seçilip sonrasında iptal edilmişse seçilen dosyada kalmamaması için tekrar nulla eşitlenmeli
                            return;
                        }
                        File newFile = getFileChooser().getSelectedFile(); //dosya açma işlemi iptal edilir diye hali hazırda açık olan dosyanın tutulduğu yerin bilgisi
                        setFileChooser(new JFileChooser());
                        int a = getFileChooser().showOpenDialog(getTextArea());
                        if (a == JFileChooser.APPROVE_OPTION) { //dosya açma işleminde open butonuna basılırsa seçilen dosyayı açıyor 
                            java.io.File file = getFileChooser().getSelectedFile();
                            try (Scanner sc = new Scanner(new BufferedReader(new FileReader(file)))) {
                                String metin = "";
                                while (sc.hasNextLine()) {
                                    metin += sc.nextLine() + "\n";
                                }
                                getTextArea().setText(metin);   //okunan metin textareaya tazdırılıyor

                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            textString = getTextArea().getText();
                        } else if (i == JFileChooser.CANCEL_OPTION) {   // kullanıcı dosya açma işlemini iptal ederse aşağıdaki işlemler yapılacak
                            //setFileChooser(null);
                            getFileChooser().setSelectedFile(newFile); // hali hazırda açık olan dosyayı kaydettiğimiz yerin dosya yolunu tekrar atıyoruz yukarıda yeni dosya yolu oluştuğu için
                            return;
                        }
                    }
                    case JOptionPane.NO_OPTION -> { //açıkta olan dosyada değişiklik olduğu halde değişiklik kaydedilmek istenmezse dosya açma işlemi yapılıyor
                        setFileChooser(new JFileChooser());
                        int i = getFileChooser().showOpenDialog(getTextArea());
                        if (i == JFileChooser.APPROVE_OPTION) { //dosya açma işleminde open butonuna basılırsa seçilen dosyayı açıyor 
                            java.io.File file = getFileChooser().getSelectedFile();
                            try (Scanner sc = new Scanner(new BufferedReader(new FileReader(file)))) {
                                String metin = "";
                                while (sc.hasNextLine()) {
                                    metin += sc.nextLine() + "\n";
                                }
                                getTextArea().setText(metin);   //okunan metin textareaya tazdırılıyor

                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            textString = getTextArea().getText();
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

    //dosyayı kaydetme
    public void save() {
        if (getFileChooser() != null) { // eğer dosya bilgisayarda bir yerde kayıtlı ise var olan dosyanın üzerine yapılan değişiklikler yapılıyor
            String dosya_konum = getFileChooser().getSelectedFile().getPath();
            try (FileWriter wr = new FileWriter(dosya_konum)) {
                wr.write(getTextArea().getText());  // var olan dosyanın üzerine yazma/kaydetme işlemi
            } catch (IOException ex) {
                Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {    // eğer dosya bilgisayarda bir yerde kayıtlı değil ise dosya kaydetme arayüzü çıkıyor
            setFileChooser(new JFileChooser());
            int i = getFileChooser().showSaveDialog(getTextArea());
            if (i == JFileChooser.APPROVE_OPTION) { //eğer kullanıcı çıkan arayüzde yer seçip save butonuna basar ise aşağıdaki işlem yapılıyor
                String dosya_konum = getFileChooser().getSelectedFile().getPath();
                try (FileWriter writer = new FileWriter(dosya_konum)) { //seçilen yere dosyayı oluşturuyor
                    writer.write(getTextArea().getText());      // yazılan şeyleri kaydediyor
                } catch (IOException ex) {
                    Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                textString = getTextArea().getText();
            } else if (i == JFileChooser.CANCEL_OPTION) {   // kullanıcı dosya kaydetme işlemini iptal ederse aşağıdaki işlemler yapılacak
                setFileChooser(null);   //dosya kaydetme işlemi yapılırken dosya seçilip sonrasında iptal edilmişse seçilen dosyada kalmamaması için tekrar nulla eşitlenmeli
            }
        }
    }

    //yeni dosya oluşturma 
    public void create() {
        if (getFileChooser() != null) { // eğer dosya bilgisayarda bir yerde kayıtlı ise aiağıdaki işlemleri yapıyor
            if (textString.equals(getTextArea().getText())) {   //açık olan dosya kaydedilen en son hali ile aynı ise direk olarak yeni dosya oluşturuyor
                getTextArea().setText("");
                setFileChooser(null);
                textString = getTextArea().getText();
            } else {    //açık olan dosya kaydedilen en son hali ile aynı değil ise yeni dosya oluşturmadan önce yapılan değişikliği kaydedilmesini soruyor
                int yesNo = JOptionPane.showConfirmDialog(textArea, "Değişiklikleri kaydetmek istiyor musunuz ?");
                switch (yesNo) {
                    case JOptionPane.YES_OPTION -> {    //kullanıcı evet der ise dosyayı son hali ile kaydediyor ve sonrasında yeni dosya açıyor
                        String dosya_konum = getFileChooser().getSelectedFile().getPath();
                        try (FileWriter wr = new FileWriter(dosya_konum)) {
                            wr.write(getTextArea().getText());
                        } catch (IOException ex) {
                            Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        getTextArea().setText("");
                        textString = getTextArea().getText();
                        setFileChooser(null);
                    }
                    case JOptionPane.NO_OPTION -> {  //kullanıcı hayır der ise dosyada yapılan son değişikikleri kaydetmiyor ve yeni dosya açıyor
                        getTextArea().setText("");
                        textString = getTextArea().getText();
                        setFileChooser(null);
                    }
                    case JOptionPane.CANCEL_OPTION -> { //kullanıcı işlemi iptal etmek isterse hiçbir şey yapılmadan geri dönülüyor
                        //getFileChooser().setSelectedFile(newFile); // hali hazırda açık olan dosyayı kaydettiğimiz yerin dosya yolunu tekrar atıyoruz yukarıda yeni dosya yolu oluştuğu için
                        return;
                    }
                    default -> {
                    }
                }
            }
        } else {    // eğer dosya bilgisayarda bir yerde kayıtlı değil ise aiağıdaki işlemleri yapıyor
            if (textString.equals(getTextArea().getText())) {   //textAre boş ise yeni dosya oluşuruyor 
                getTextArea().setText("");
                setFileChooser(null);
                textString = getTextArea().getText();
            } else {    //textArea boş değil ise yeni dosya oluşturulmadan önce hali hazırdaki dosyanın kaydedilip kaydedilmeyeceği soruluyor
                int yesNo = JOptionPane.showConfirmDialog(textArea, "Değişiklikleri kaydetmek istiyor musunuz ?");
                switch (yesNo) {
                    case JOptionPane.YES_OPTION -> {    //kullanıcı evet der ise dosyayı kaydetmek için save arayüzü çıkıyor
                        setFileChooser(new JFileChooser());
                        int i = getFileChooser().showSaveDialog(getTextArea());
                        if (i == JFileChooser.APPROVE_OPTION) { //eğer kullanıcı çıkan arayüzde yer seçip save butonuna basar ise aşağıdaki işlem yapılıyor
                            String dosya_konum = getFileChooser().getSelectedFile().getPath();
                            try (FileWriter writer = new FileWriter(dosya_konum)) { //seçilen yere dosyayı oluşturuyor
                                writer.write(getTextArea().getText());      // yazılan şeyleri kaydediyor
                            } catch (IOException ex) {
                                Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            getTextArea().setText("");  //sonrasında yeni dosya oluşturuluyor/ textArea ve file chooser null'anıyor
                            textString = getTextArea().getText();
                            setFileChooser(null);
                        } else if (i == JFileChooser.CANCEL_OPTION) {   // kullanıcı dosya kaydetme işlemini iptal ederse aşağıdaki işlemler yapılacak
                            setFileChooser(null);   //dosya kaydetme işlemi yapılırken dosya seçilip sonrasında iptal edilmişse seçilen dosyada kalmamaması için tekrar nulla eşitlenmeli
                            return;
                        }
                    }
                    case JOptionPane.NO_OPTION -> {  //kullanıcı hayır der ise dosyada yapılan son değişikikleri kaydetmiyor ve yeni dosya açıyor
                        getTextArea().setText("");
                        textString = getTextArea().getText();
                        setFileChooser(null);
                    }
                    case JOptionPane.CANCEL_OPTION -> { //kullanıcı işlemi iptal etmek isterse hiçbir şey yapılmadan geri dönülüyor
                        return;
                    }
                    default -> {
                    }
                }
            }
        }
    }

    //açılan dosyayı kapatma
    public void close() {
        if (getFileChooser() != null) { // eğer dosya bilgisayarda bir yerde kayıtlı ise aşağıdaki işlemler yapılıyor
            if (!textString.equals(getTextArea().getText())) {  //eğer bu kayıtlı olan dosyanın kayıtlı son halinde değişiklik yapılıp kaydedilmediyse buraya giriyor
                int yesNo = JOptionPane.showConfirmDialog(textArea, "Değişiklikleri kaydetmek istiyor musunuz ?");
                switch (yesNo) {
                    case JOptionPane.YES_OPTION -> {    //kullanıcı evet der ise dosyayı kaydetip sonrasında programdan çıkıyor
                        String dosya_konum = getFileChooser().getSelectedFile().getPath();
                        try (FileWriter wr = new FileWriter(dosya_konum)) {
                            wr.write(getTextArea().getText());
                        } catch (IOException ex) {
                            Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.exit(0);
                    }
                    case JOptionPane.NO_OPTION ->   //kullanıcı hayır der ise son yapılan değişiklikler kaydedilmeden programdan çıkılıyor
                        System.exit(0);
                    //setFileChooser(null);
                    case JOptionPane.CANCEL_OPTION -> { //kullanıcı iptal der ise hiçbirşey yapılmıyor
                        return;
                    }
                    default -> {
                    }
                }
            } else {    //eğer kayıtlı olan dosyada değişiklik yapılmadıysa programı kapatır
                System.exit(0);
            }

        } else {    // eğer dosya bilgisayarda bir yerde kayıtlı değil ise aşağıdaki işlemler yapılıyor
            if (!textString.equals(getTextArea().getText())) { //eğer kayıtlı olmayan hali hazırda açık olan dosya bir şeyler yazılmış ise değişikliklerinin kayıt edilmesini soruyor
                int yesNo = JOptionPane.showConfirmDialog(textArea, "Değişiklikleri kaydetmek istiyor musunuz ?");
                switch (yesNo) {
                    case JOptionPane.YES_OPTION -> {    //kullanıcı evet der ise dosya kaydetme kaydetme arayüzünü açıyor
                        setFileChooser(new JFileChooser());
                        int i = getFileChooser().showSaveDialog(getTextArea());
                        if (i == JFileChooser.APPROVE_OPTION) { //kullanıcı arayüzde save'e tıklarsa seçtiği yere dosyayı kaydeder ardından programı kapatır
                            String dosya_konum = getFileChooser().getSelectedFile().getPath();
                            try (FileWriter writer = new FileWriter(dosya_konum)) {
                                writer.write(getTextArea().getText());
                            } catch (IOException ex) {
                                Logger.getLogger(TextEditorJFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.exit(0);
                        } else if (i == JFileChooser.CANCEL_OPTION) {   //kullanıcı arayüzde cancel'e tıklarsa filechooser'ı sıfırlar ve programa geri döner
                            setFileChooser(null);
                            return;
                        }

                    }
                    case JOptionPane.NO_OPTION ->   //kullanıcı hayır der ise dosyayı kaydetmez ve programı kapatır
                        System.exit(0);
                    //setFileChooser(null);
                    case JOptionPane.CANCEL_OPTION -> { //kullanıcı cancel der ise programa geri döner
                        return;
                    }
                    default -> {
                    }
                }
            } else {    //dosyada değişiklik yok ise programı kapatır
                System.exit(0);
            }
        }
    }

    /**
     * @return the textArea
     */
    public JTextArea getTextArea() {
        return textArea;
    }

    /**
     * @param textArea the textArea to set
     */
    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    /**
     * @return the fileChooser
     */
    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    /**
     * @param fileChooser the fileChooser to set
     */
    public void setFileChooser(JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

}
