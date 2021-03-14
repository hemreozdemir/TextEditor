/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEditor;


public class Kontrol {
    public Command undoCommand ;
    public Command addTextCommand;
    
    public Kontrol(Command undoCommand, Command addTextCommand) {
        this.undoCommand = undoCommand;
        this.addTextCommand = addTextCommand;
    }
    

  
    public void geriAlMetod2(){
        undoCommand.execute();
    }
    public void addText2(){
        addTextCommand.execute();
    }
}
