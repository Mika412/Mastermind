package pObjects;

import edu.princeton.cs.algs4.StdOut;
import processing.core.PApplet;
import strategy.Answer;
import strategy.Code;
import utils.Constants;

public class ItemGuessed {
    PApplet parent;
    float xpos; // rect xposition
    float ypos ; // rect yposition
    int width; // single bar width
    float height; // rect height
    int nBalls; // number of balls
    Code code;
    Answer answer;

    public ItemGuessed(PApplet parent, float xPos, float yPos, int width, float height,  int nBalls, Code code, Answer answer) {
        this.parent = parent;
        this.width = width;
        this.xpos = xPos;
        this.ypos = yPos;
        this.height = height;
        this.nBalls = nBalls;
        this.code = code;
        this.answer = answer;
    }

    public void updateYpos(float yPos){
        this.ypos = yPos;
    }

    public void display() {
        parent.fill(255,100);
        parent.noStroke();
        parent.rect(xpos,ypos,width,height);
        for(int i = 0; i < nBalls; i++){
            parent.fill(Constants.colors[code.getCode(i)-1]);
            parent.ellipse(((Constants.windowWidth*3/5) / nBalls)* i + (Constants.windowWidth*3/5) / (2 * nBalls),
                    ypos + Constants.defaultColorBallWidth/2,
                    Constants.defaultColorBallWidth,
                    Constants.defaultColorBallWidth);
        }

        for(int i = 0; i < nBalls; i++){
            if(i < answer.blacks){
                parent.fill(Constants.colors[8]);
            }else if(i < answer.blacks + answer.whites){
                parent.fill(Constants.colors[7]);
            }else{
                parent.fill(Constants.colors[9]);
            }
            parent.ellipse(Constants.defaultColorBallWidth * 11 + (Constants.defaultAnswerBallWidth + 4*(10-nBalls))* i,
                    ypos + Constants.defaultColorBallWidth/2,
                    Constants.defaultAnswerBallWidth,
                    Constants.defaultAnswerBallWidth);
        }
    }

}