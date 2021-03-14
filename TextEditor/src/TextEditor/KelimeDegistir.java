/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEditor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
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
public class KelimeDegistir implements KelimeIslemDurumu{
    private JTextArea textArea;
    String bulunacak, degistir;
    Stack<String> undoStack;

    public KelimeDegistir(JTextArea textArea, Stack<String> undoStack, String bulunacak, String degistir) {
        this.textArea = textArea;
        this.bulunacak = bulunacak;
        this.degistir = degistir;
        this.undoStack = undoStack;
    }
    
    @Override
    public void islemiUygula() {
        
        String kelimeBulStr = bulunacak.toUpperCase();
        String kelimeDegistirStr = degistir.toUpperCase();
        String textAreaStr = textArea.getText().toUpperCase();
        int length = kelimeDegistirStr.length();
        int index = 0; //bulunacak kelimenin başlangıç indexleri
        ArrayList<Integer> degistirIndex = new ArrayList<>();
        Highlighter hl = textArea.getHighlighter();
        Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
        hl.removeAllHighlights();

        if (textAreaStr.contains(kelimeBulStr)) {
            undoStack.push(textArea.getText()); //undo işlemi için, kelime değiştirilmeden önce stack'e push edilir
            textArea.setText(textArea.getText().replaceAll("(?i)" + bulunacak, degistir)); //kelime değiştirilme işlemi
            //değiştirilen kelimenin highlight edilmesi işlemi
            while (true) {
                index = textAreaStr.indexOf(kelimeBulStr, index); //kelimenin başlangıç indexi bulunur
                if (index < 0) {
                    break; //
                }
                degistirIndex.add(index); //kelime var ise başlangıç indexi arrayliste atılır
                index++;
            }

            //değiştirlen kelimenin highlight edilmesi
            int startIndex; //kelime degistirildikten sonra degistirilen kelimenini başlangıç indexini tutar
            try {
//değiştirilen kelime ve yeni kelimenin boyuları farklı olabileceğinden dolayı kelimelerin indeksleri değişmektedir
//ilk kelimeden sonraki kelimelerin başlangıç indeksleri boyut farkıdan dolayı değişmektedir. idenks aşağıdaki formül ile bulnur.
//değiştirlen kelime ile yeni kelimenin boyut farkının, kaçıncı kelime değiştirildi ise 1 eksiği ile çarpılarak bulunur

                Iterator<Integer> degistirIndexIterator = degistirIndex.iterator();
                int counter = 0;
                while (degistirIndexIterator.hasNext()) {
                    int currentDegistirIndex = degistirIndexIterator.next();
                    if (counter == 0) {
                        hl.addHighlight(currentDegistirIndex, currentDegistirIndex + length, painter);
                    } else if (counter > 0) {
                        startIndex = currentDegistirIndex + counter * (length - kelimeBulStr.length());
                        hl.addHighlight(startIndex, startIndex + length, painter);
                    }
                    counter++;
                }
                /*for (int i = 0; i < degistirIndex.size(); i++) {
                    if (i == 0) {
                        hl.addHighlight(degistirIndex.get(i), degistirIndex.get(i) + length, painter); //denk gelen ilk kelime ise
                    } //ilk kelimeden sonraki kelimeler değiştirilirken başlangıç indeksinin atanması.
                    else if (i > 0) {
                        //yukarıda bahsedilen formül ile indeksin bulunması
                        startIndex = degistirIndex.get(i) + i * (length - kelimeBulStr.length());
                        hl.addHighlight(startIndex, startIndex + length, painter);
                    }
                }*/
            } catch (BadLocationException ex) {
                Logger.getLogger(KelimeDegistir.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
