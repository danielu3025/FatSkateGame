package com.fatsfoodhenkar.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

import static com.fatsfoodhenkar.game.Game.gameHendler;

public class Food {
    Texture sprite;
    float speed = 16;
    float velocity = speed;
    float yPostion;
    float width;
    float height;
    float xPos;
    Rectangle rectangle;

    float rectW,rectH,rectX;

    Texture food1 = new Texture("photos/food1.png");
    Texture food2 = new Texture("photos/food2.png");
    Texture food3 = new Texture("photos/food3.png");
    Texture food4 = new Texture("photos/food4.png");


    public Food() {
        sprite = food3;
        width = Gdx.graphics.getWidth()/7.5f;
        height = Gdx.graphics.getHeight()/11;
        xPos =Gdx.graphics.getWidth();
        yPostion = randY();
        rectangle = new Rectangle(10,10,10,10);
       // gameHendler.food.xPos,gameHendler.food.yPostion,gameHendler.food.width*1/2, gameHendler.food.height*1/2;
        rectW = width/10;
        rectH = height;
        rectX = xPos;
    }


    //calc position of the food in access Y
    public float randY(){
        float minY = 0;
        float maxY = Gdx.graphics.getHeight()/2 - (sprite.getHeight()/2);
        Random rand = new Random();
        float finalX = rand.nextFloat() * (maxY- minY) + minY;
        return  finalX;
    }

    //speed up as the rise
    public void speedUp(){
        if (velocity>70){
            velocity = 70f;
        }
        else {
            velocity = velocity+7f;

        }
    }

    //move the food
    public void foodMovment() {

        if (Game.gameHendler.food.xPos < -(Game.gameHendler.screenWidth)){
            if (Game.gameHendler.boom){
                Game.gameHendler.boom = false;
                Game.gameHendler.level++;
                if (Game.gameHendler.level == 5){
                    Game.gameHendler.food.speedUp();
                    Game.gameHendler.level =0;
                }
            }
            Game.gameHendler.food.xPos = Game.gameHendler.screenWidth;
            yPostion = Game.gameHendler.food.randY();
            spritPicker();


        }
        else {
            Game.gameHendler.food.xPos -= Game.gameHendler.food.velocity;

        }
    }

    //draw the food
    public void drow(){
        gameHendler.batch.draw(sprite, xPos, yPostion, width, height);
    }

    //create rec on the food for the colision
    public void calcReectangle(){
        rectangle.set(Game.gameHendler.food.xPos, Game.gameHendler.food.yPostion,rectW,rectH);
    }

    //choose the food images
    public void spritPicker(){
        Random r = new Random();
        int Low = 1;
        int High = 5;
        int result = r.nextInt(High-Low) + Low;
        if (result == 1){
            sprite = food2;
        }
        else  if (result == 2){
            sprite = food1;
        }
        else if (result == 3){
            sprite = food4;
        }
        else {
            sprite = food3;
        }
    }


}
