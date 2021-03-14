/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEditor;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author Hemre
 */
public class KelimeBul implements KelimeIslemDurumu {
    private JTextArea textArea;
    String bulunacak;

    public KelimeBul(JTextArea textArea, String bulunacak) {
        this.textArea = textArea;
        this.bulunacak = bulunacak;
    }
    
    @Override
    public void islemiUygula() {
        String kelimeBulStr = bulunacak.toUpperCase();
        int length = kelimeBulStr.length(); //bulunacak kelimenin uzunluğu
        String textAreaStr = textArea.getText().toUpperCase();
        Highlighter hl = textArea.getHighlighter();
        Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
        hl.removeAllHighlights();
        try {
            if (!textAreaStr.contains(kelimeBulStr)) {
                //kelime metinde yoksa
            }
            int index = 0;
            while (index >= 0) {
                index = textAreaStr.indexOf(kelimeBulStr, index); //kelime varsa kelimenin başlangıç indeksi index'e atanır   
                if (index >= 0) {
                    hl.addHighlight(index, index + length, painter); //kelimenin highlight edilmesi
                }
                if (index < 0) {
                    break;
                } else {
                    index++;
                }
            }
        } catch (BadLocationException ex) {
            Logger.getLogger(KelimeBul.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
