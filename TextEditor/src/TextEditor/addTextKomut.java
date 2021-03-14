/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEditor;


public class addTextKomut implements Command{
    private Undo undo ;

    public addTextKomut(Undo undo) {
        this.undo = undo ;
    }

    @Override
    public void execute() {
        undo.addTextMethod();
    }
    
    
    
}