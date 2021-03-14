/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEditor;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;


public interface FileopInterface {

    public void dosyaIslemleri();
    public String retString();
    public JFileChooser retFileChooser();
    public JTextArea retTextArea();
    
}
