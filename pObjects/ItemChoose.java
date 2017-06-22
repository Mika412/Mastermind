package pObjects;

import processing.core.PApplet;
import utils.Constants;

/**
 * Created by mykha on 12/04/2017.
 */
public class ItemChoose {
    private PApplet parent;
    private float xpos; // rect xposition
    private float ypos ; // rect yposition
    private int width; // single bar width
    private float height; // rect height
    private int nBalls; // number of balls
    private int noneColor = 9;
    private int[] colorArray;

    private boolean selectedColor = false;
    private int chosenColor = -1;
    private Button btnSubmit;

    public ItemChoose(PApplet parent, float xPos, float yPos, int width, float height,  int nBalls) {
        this.parent = parent;
        this.width = width;
        this.xpos = xPos;
        this.ypos = yPos;
        this.height = height;
        this.nBalls = nBalls;
        btnSubmit = new Button(parent, (int)(Constants.windowWidth - Constants.buttonSubmitWidth * 2), (int) (ypos + Constants.defaultColorBallWidth/2 - Constants.buttonSubmitHeight/2), Constants.buttonGuessText);
        this.colorArray = new int[nBalls];
        reset();
    }

    public void display() {
        parent.fill(255,100);
        parent.noStroke();

        parent.rect(xpos,ypos,width,height);

        parent.stroke(Constants.colors[8]);
        for(int i = 0; i < nBalls; i++){
            parent.fill(Constants.colors[colorArray[i]]);
            parent.ellipse(((Constants.windowWidth*3/5) / nBalls)* i + (Constants.windowWidth*3/5) / (2 * nBalls),
                    ypos + Constants.defaultColorBallWidth/2,
                    Constants.defaultColorBallWidth,
                    Constants.defaultColorBallWidth);
        }
        parent.noStroke();
        for(int i = 0; i < Constants.numberColors; i++){
            parent.fill(Constants.colors[i]);
                    parent.ellipse((Constants.windowWidth / Constants.numberColors)* i + Constants.windowWidth / (2 * Constants.numberColors),
                    ypos + Constants.defaultColorBallWidth*2-20,
                    Constants.defaultColorBallWidth,
                    Constants.defaultColorBallWidth);
        }
        btnSubmit.draw();
    }

    public void checkMouse(){
        if((parent.mouseX > 0 && parent.mouseX < Constants.windowWidth) && (parent.mouseY < Constants.windowHeight && parent.mouseY >= Constants.windowHeight - Constants.hightChoice)){
            if(selectedColor){
                parent.fill(Constants.colors[chosenColor]);
                parent.ellipse(parent.mouseX, parent.mouseY, Constants.defaultAnswerBallWidth, Constants.defaultAnswerBallWidth);
            }
            if(parent.mousePressed){
                if(selectedColor){
                    float disX = ((Constants.windowWidth*3/5) / nBalls) - (Constants.windowWidth*3/5) / (2 * nBalls) - parent.mouseX % ((Constants.windowWidth*3/5) / nBalls);
                    float disY = ypos + Constants.defaultColorBallWidth/2 - parent.mouseY;
                    if (parent.sqrt(parent.sq(disX) + parent.sq(disY)) < (Constants.defaultColorBallWidth) / 2 && parent.mouseX < (Constants.windowWidth*3/5)) {
                        //StdOut.println(parent.mouseX / ((Constants.windowWidth*3/5) / nBalls) + " " +chosenColor);
                        colorArray[parent.mouseX / ((Constants.windowWidth*3/5) / nBalls)] = chosenColor;
                        selectedColor = false;
                        if(areAllChosen())
                            btnSubmit.setReady(true);
                    }
                }
                float disX = (Constants.windowWidth / (4 * Constants.numberColors) + Constants.defaultColorBallWidth / 2) - parent.mouseX % (Constants.windowWidth / Constants.numberColors);
                float disY = ypos + Constants.defaultColorBallWidth * 2 - 20 - parent.mouseY;
                if (parent.sqrt(parent.sq(disX) + parent.sq(disY)) < (Constants.defaultColorBallWidth) / 2) {
                    chosenColor = parent.mouseX / (Constants.windowWidth / Constants.numberColors);
                    selectedColor = true;
                }
            }
        }else{
            selectedColor = false;
        }
    }

    public boolean areAllChosen(){
        int count = 0;
        for (int i = 0; i < nBalls; i++) {
            if(colorArray[i] != 9){
                count++;
            }
        }
        return count == nBalls ? true : false;
    }

    //Created this function because was to lazy to implement a Callback class
    public String checkSubmittedGuess(){
        String sub = "";
        if(btnSubmit.checkClicked()){
            for (int i = 0; i < nBalls; i++) {
                sub += colorArray[i]+1;
            }
            reset();
        }
//        StdOut.println(sub);
        return sub;
    }

    void reset(){
        for(int i = 0; i < nBalls; i++)
            colorArray[i] = noneColor;
        btnSubmit.setReady(false);
    }
}