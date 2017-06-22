package pObjects;

import processing.core.PApplet;
import utils.Constants;

/**
 * Created by mykha on 12/04/2017.
 */
class Button{
    int x,y;
    String label;
    PApplet parent;

    boolean isReady = false;
    int colorReady = Constants.colors[4];
    int colorNotReady = 200;
    Button(PApplet parent, int x, int y, String label){
        this.x = x;
        this.y = y;
        this.label = label;
        this.parent = parent;
    }
    void draw(){
        if(isReady)
            parent.fill(colorReady);
        else
            parent.fill(colorNotReady);
        if(over() && isReady){
            parent.fill(255);
        }
        parent.rect(x, y, Constants.buttonSubmitWidth, Constants.buttonSubmitHeight);
        parent.fill(0);
        parent.text(label, x + Constants.buttonSubmitWidth/4, y + 20);
    }
    boolean over(){
        if(parent.mouseX >= x && parent.mouseY >= y && parent.mouseX <= x + Constants.buttonSubmitWidth && parent.mouseY <= y + 22){
            return true;
        }
        return false;
    }

    void setReady(boolean ready){
        isReady = ready;
    }
    boolean checkClicked(){
        if(over() && parent.mousePressed){
            return true;
        }else{
            return false;
        }
    }

}