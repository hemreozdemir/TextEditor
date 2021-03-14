/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEditor;
import java.util.Stack;
import javax.swing.JTextArea;

public class Undo {
    private JTextArea textArea;
    private Stack<String> undoStack;
    public Undo(JTextArea textArea, Stack<String> undoStack) {
        this.textArea = textArea;
        this.undoStack =undoStack;
    }
    public void addTextMethod() {
        undoStack.push(textArea.getText());
    }
    
    public void undoMethod(){
        
        if (textArea.getText().isEmpty()) {
            //metin boş ise geri alma işlemi yapılmıyor.
        } else {
            getTextArea().setText(getUndoStack().pop());
        }
    }
     public JTextArea getTextArea() {
        return textArea;
    }

    

    public Stack<String> getUndoStack() {
        return undoStack;
    }

    public void setUndoStack(Stack<String> undoStack) {
        this.undoStack = undoStack;
    }
}

