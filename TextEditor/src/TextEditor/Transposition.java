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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Transposition {

    public List<List<String>> lists = new ArrayList<List<String>>();
    List<String> uctenKucuk = new ArrayList<String>();
    List<String> uc = new ArrayList<String>();
    List<String> dort = new ArrayList<String>();
    List<String> bes = new ArrayList<String>();
    List<String> alti = new ArrayList<String>();
    List<String> yedi = new ArrayList<String>();
    List<String> sekiz = new ArrayList<String>();
    List<String> dokuz = new ArrayList<String>();
    List<String> on = new ArrayList<String>();
    List<String> ondanBüyük = new ArrayList<String>();

    public Transposition() throws FileNotFoundException, IOException {
        File file = new File("words.txt");
        FileReader fileReader = new FileReader(file);
        String line;
        BufferedReader br = new BufferedReader(fileReader);
        while ((line = br.readLine()) != null) {
            if(line.length()<3){
                uctenKucuk.add(line);
            }
            if (line.length() == 3) {

                uc.add(line);
            }
            else if (line.length() == 4) {
                dort.add(line);
            }
            else if (line.length() == 5) {
                bes.add(line);
            }
            else if (line.length() == 6) {
                alti.add(line);
            }
            else if (line.length() == 7) {
                yedi.add(line);
            }
            else if (line.length() == 8) {
                sekiz.add(line);
            }
            else if (line.length() == 9) {
                dokuz.add(line);
            }
            else if (line.length() == 10) {
                on.add(line);
            }
            else if (line.length() > 10) {
                ondanBüyük.add(line);

            }
        }
        
        lists.add(uc);
        lists.add(dort);
        lists.add(bes);
        lists.add(alti);
        lists.add(yedi);
        lists.add(sekiz);
        lists.add(dokuz);
        lists.add(on);
        lists.add(ondanBüyük);
        
        br.close();

    }
}
