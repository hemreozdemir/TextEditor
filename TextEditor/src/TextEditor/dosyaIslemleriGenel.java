/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEditor;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;


public class dosyaIslemleriGenel {

    private FileopInterface fileops;

    public dosyaIslemleriGenel(FileopInterface fileops) {
        this.fileops = fileops;
    }

    public void dosyaIslemleriniYap() {
        fileops.dosyaIslemleri();
    }
    
    public String retString() {
        return fileops.retString();
    }

    public JFileChooser retFileChooser() {
        return fileops.retFileChooser();
    }

    public JTextArea retTextArea() {
        return fileops.retTextArea();
    }
    

}
