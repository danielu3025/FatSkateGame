package com.fatsfoodhenkar.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

class BottonImg {

    boolean state = false;
    float x,y,widt,hight;
    Texture texture;
    public BottonImg(String img,float xi, float yi, float widthi, float heighti) {
        texture = new Texture(img);
        x= xi;y=yi;widt = widthi;hight = heighti;
    }


//    public void update (SpriteBatch batch, float input_x, float input_y) {
//        checkIfClicked();
//    }
    //chack if button was pressed
    boolean  checkIfClicked () {
        if (Gdx.input.justTouched()){
            float ix = Gdx.input.getX();float iy = Gdx.input.getY();
            System.out.println("\n\n");
//            System.out.println("x:"+x);
//            System.out.println("ix:"+ix);
//            System.out.println("x+width:"+(x+widt));

            float s =(Gdx.graphics.getHeight());

//            System.out.println("---------------\n\n");
//            System.out.println("s:"+s);
//            System.out.println("s-y:"+(s-y));
//            System.out.println("iy:"+iy);
//            System.out.println("s-(y+heigt)+:"+(s-(y+hight)));
//            System.out.println("y:"+y);
////
////
//             System.out.println("*******************");
            if (ix > x && ix < x + widt) {
                if (iy>s-(y+hight) && (s-y)>iy) {
                    // the button was clicked, perform an action
                    System.out.println("Button clicked !");
                    return true;
                }
            }
        }
        return false;
    }

}