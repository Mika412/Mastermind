package objects;

import processing.core.PApplet;
import strategy.Answer;
import strategy.Code;
import utils.Constants;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ItemChooseAnswer implements MouseListener {
    private PApplet parent;
    private float xpos; // rect xposition
    private float ypos ; // rect yposition
    private int width; // single bar width
    private float height; // rect height
    private int nBalls; // number of balls
    private int noneColor = 9;
    private int[] answerColorArray;

    private boolean doOnceButtonPress = false;
    private Code code = null;
    private boolean selectedColor = false;
    private int chosenColor = -1;
    private Button btnSubmit;

    public ItemChooseAnswer(PApplet parent, float xPos, float yPos, int width, float height,  int nBalls, Code code) {
        this.parent = parent;
        this.width = width;
        this.xpos = xPos;
        this.ypos = yPos;
        this.height = height;
        this.nBalls = nBalls;
        btnSubmit = new Button(parent, (int)(Constants.windowWidth - Constants.buttonSubmitWidth * 2), (int) (ypos + Constants.defaultColorBallWidth/2 - Constants.buttonSubmitHeight/2), Constants.buttonGuessText);
        btnSubmit.setReady(true);
        this.answerColorArray = new int[nBalls];
        this.code = code;
        System.out.println(code.toString());
        System.out.println(this.code.toString().charAt(2));
        reset();
    }

    public void display() {
        parent.fill(255,100);
        parent.noStroke();

        parent.rect(xpos,ypos,width,height);

        parent.stroke(Constants.colors[8]);
        for(int i = 0; i < nBalls; i++){
            parent.fill(Constants.colors[Integer.parseInt(code.toString().charAt(i)+"")-1]);
            parent.ellipse(((Constants.windowWidth*3/5) / nBalls)* i + (Constants.windowWidth*3/5) / (2 * nBalls),
                    ypos + Constants.defaultColorBallWidth/2,
                    Constants.defaultColorBallWidth,
                    Constants.defaultColorBallWidth);
        }

        parent.stroke(Constants.colors[8]);
        for(int i = 0; i < nBalls; i++){
            parent.fill(Constants.colors[answerColorArray[i]]);
            parent.ellipse(((Constants.windowWidth*3/5) / nBalls)* i + (Constants.windowWidth*3/5) / (2 * nBalls),
                    ypos + Constants.defaultColorBallWidth*2-20,
                    Constants.defaultColorBallWidth,
                    Constants.defaultColorBallWidth);
        }

        for(int i = 0; i < 3; i++){
            parent.fill(Constants.colors[i+7]);
            parent.ellipse((Constants.windowWidth*3/5) + ((Constants.windowWidth*2/5) / 3)* i  + (Constants.windowWidth*2/5) / (2 * 3),
                    ypos + Constants.defaultColorBallWidth*2-20,
                    Constants.defaultColorBallWidth,
                    Constants.defaultColorBallWidth);
        }

        parent.line(Constants.windowWidth*3/5,
                ypos + Constants.hightChoice/2,
                Constants.windowWidth*3/5,
                Constants.windowHeight);
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
                    float disY = ypos + Constants.defaultColorBallWidth*2-20 - parent.mouseY;
                    if (parent.sqrt(parent.sq(disX) + parent.sq(disY)) < (Constants.defaultColorBallWidth) / 2 && parent.mouseX < (Constants.windowWidth*3/5)) {
                        answerColorArray[parent.mouseX / ((Constants.windowWidth*3/5) / nBalls)] = chosenColor;
                        selectedColor = false;
                    }
                }
                float disX = ((Constants.windowWidth*2/5) / (2 * 3)) -  (parent.mouseX - Constants.windowWidth*3/5) % ((Constants.windowWidth*2/5) / 3);
                float disY = ypos + Constants.defaultColorBallWidth*2-20 - parent.mouseY;
                if (parent.sqrt(parent.sq(disX) + parent.sq(disY)) < (Constants.defaultColorBallWidth) / 2) {
                    chosenColor =(parent.mouseX - Constants.windowWidth*3/5) / ((Constants.windowWidth*2/5) / 3) + 7;
                    selectedColor = true;
                }
            }
        }else{
            selectedColor = false;
        }
    }

    //Created this function because was to lazy to implement a Callback class
    public Answer checkSubmittedGuess(){
        Answer tempAnswer;
        int blacks = 0;
        int whites = 0;
        if(btnSubmit.checkClicked()){
            for (int i = 0; i < nBalls; i++) {
                if(answerColorArray[i] == 8)
                    blacks ++;
                else if(answerColorArray[i] == 7)
                    whites ++;
            }
            tempAnswer = new Answer(blacks, whites);
            parent.delay(500);
            return tempAnswer;
        }
        return null;
    }

    void reset(){
        for(int i = 0; i < nBalls; i++)
            answerColorArray[i] = noneColor;
    }
    public void changeCode(Code code){
        this.code = code;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        doOnceButtonPress = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}