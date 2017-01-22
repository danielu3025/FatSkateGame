package com.fatsfoodhenkar.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

import static com.fatsfoodhenkar.game.Game.gameHendler;

public class GameHendler {
    Preferences prefs;
    int hightScore =0;
    FatBoy fatBoy;
    Food food;
    SpriteBatch batch;
    FreeTypeFontGenerator.FreeTypeFontParameter params, params2;
    FreeTypeFontGenerator generator;
    Texture sky, road, skyLineClose, skyLineFar, trees, bar, head, stop, stop2, play, st, resume, startbg, gameover, settingsPage, timeOut,
            backIcon,sounOn,soundOff,fxOn,fxOff,about,best,aboutBg,guide;
    int gameState = 0;
    Random randdomGenerator;
    boolean isPaused = false;
    public int level = 0;
    int score = 0;
    BitmapFont font;
    BitmapFont font2;
    float screenHight = Gdx.graphics.getHeight();
    float screenWidth = Gdx.graphics.getWidth();
    boolean boom = false;
    float skyX = 0, roadX = 0, treesX = 0, closeX = 0, farX = 0;
    float skyV = 2, roadV = 4, treesV = 3, closeV = 2, farV = 1f;
    float barLen, headPos;
    Rectangle fatboyRect;
    float timeStete = 0f;
    int scoreHit = 5;
    int record = 0;
    String musicIsOn = "1";
    String fxIsOn = "1'";
    MusicGame musicGame;
    boolean aboutIsOpen = false;
    boolean isFirst = true;

    float halfScreenHight;

    BottonImg sounBt,fxBt,aboutBt,startPlayBt,settingsButton,backButton,pauseButton,resumeButton,retryBotton,quitButton;


    public GameHendler() {
        batch = new SpriteBatch();
        sky = new Texture("photos/sky.png");
        skyLineClose = new Texture("photos/skyLineCloser.png");
        skyLineFar = new Texture("photos/skyLineFar.png");
        road = new Texture("photos/road.png");
        trees = new Texture("photos/trees.png");
        bar = new Texture("photos/bar.png");
        head = new Texture("photos/head.png");
        stop = new Texture("photos/stop.png");
        stop2 = new Texture("photos/stopbt.png");
        play = new Texture("photos/play.png");
        resume = new Texture("photos/resume.png");
        startbg = new Texture("photos/startBg.png");
        gameover = new Texture("photos/gameover.png");
        st = new Texture("photos/setting.png");
        settingsPage = new Texture("photos/settingsPage.png");
        timeOut = new Texture("photos/timeOut.png");
        backIcon = new Texture("photos/backIcon.png");
        sounOn  = new Texture("photos/soundOn.png");
        soundOff  = new Texture("photos/soundOff.png");
        fxOn = new Texture("photos/fxOn.png");
        fxOff = new Texture("photos/fxOff.png");
        about = new Texture("photos/fxOff.png");
        best = new Texture("photos/best.png");
        aboutBg = new Texture("photos/aboutBG.png");
        guide = new Texture("photos/guide.png");

        sounBt = new BottonImg("photos/soundOn.png",screenWidth/4,screenHight/3f,screenWidth/5,screenHight/6);
        fxBt = new BottonImg("photos/fxOn.png",screenWidth/1.7f,screenHight/3f,screenWidth/5,screenHight/6);
        aboutBt = new BottonImg("photos/about.png",screenWidth/1.2f,screenHight/1.2f,screenWidth/6,screenHight/7);
        startPlayBt = new BottonImg("photos/playbutton.png",screenWidth/2.4f,screenHight/3.5f,screenWidth/4,screenHight/5);
        settingsButton = new BottonImg("photos/setting.png",0,screenHight/1.2f,screenHight/7,screenHight/7);
        backButton= new BottonImg("photos/backIcon.png",0,screenHight/1.2f,screenHight/7,screenHight/7);
        pauseButton= new BottonImg("photos/stop.png",screenWidth/1.2f,screenHight/1.3f,screenWidth/8.3f,screenHight/6.5f);

        resumeButton = new BottonImg("photos/resume.png",screenWidth/1.65f,screenHight/3f,screenWidth/5,screenHight/6);
        retryBotton = new BottonImg("photos/retry.png",screenWidth/2.5f,screenHight/3f,screenWidth/5,screenHight/6);
        quitButton = new BottonImg("photos/quit.png",screenWidth/5.2f,screenHight/3f,screenWidth/5,screenHight/6);

        randdomGenerator = new Random();
        fatBoy = new FatBoy();
        food = new Food();
        fatboyRect = new Rectangle(70 + fatBoy.width / 2, fatBoy.yPosition, fatBoy.width, fatBoy.hieght * 2.5f);

        barLen = screenWidth / 2;
        headPos = screenWidth / 4f + screenWidth / 5;

        musicGame = new MusicGame();
        // check if thre is a seved preferance allredy
        try {
            prefs = Gdx.app.getPreferences("gamePreferences");
            musicIsOn =  prefs.getString("music");
            fxIsOn =  prefs.getString("fx");
            if (musicIsOn == ""){
                musicIsOn = "1";
            }
            if (fxIsOn == ""){
                fxIsOn = "1";
            }

        }catch (Exception e){
            System.out.println("no saved prefernces");
            musicIsOn = "1";
            fxIsOn = "1";
        }
        System.out.println("sund:"+ musicIsOn);
        System.out.println("fx:"+ fxIsOn);

        halfScreenHight = screenHight / 2;

    }

    //sets all game parameters for a new game
    public void initGame() {
        isPaused = false;
        level = 0;
        score = 0;
        boom = false;
        skyX = 0;
        roadX = 0;
        treesX = 0;
        closeX = 0;
        farX = 0;
        skyV = 2;
        roadV = 4;
        treesV = 3;
        closeV = 2;
        farV = 1f;
        timeStete = 0f;
        scoreHit = 5;
        //init boy
        fatBoy.yPosition = 0;
        fatBoy.status = 0;
        fatBoy.stateTime = 0f;
        headPos = screenWidth / 4f + screenWidth / 5;
        gameHendler.fatBoy.dt = 0;


    }

    //return the batch of the game
    public SpriteBatch getBatch() {
        return batch;
    }

    //check the input method swipe down or up
    public void inputChaking() {
        if (Gdx.input.getDeltaY() < 0) {
            if (gameHendler.fatBoy.yPosition <= 0) {
                gameHendler.fatBoy.status = 1;
                gameHendler.fatBoy.jump = true;

            }
        } else if (Gdx.input.getDeltaY() > 0) {
            if (gameHendler.fatBoy.yPosition == 0) {
                gameHendler.fatBoy.status = 2;
            }
            if (gameHendler.fatBoy.jump) {
                gameHendler.fatBoy.gravity = gameHendler.fatBoy.superGarvity;
            }
        }
    }

    //check if there is a colison between the boy and the food
    public void colison() {
        if (Intersector.overlaps(gameHendler.fatBoy.rectangle, gameHendler.food.rectangle)) {
            biteSound();
            food.xPos = screenWidth + fatBoy.startingX + fatBoy.getWidth();
            food.yPostion = food.randY();
            food.spritPicker();
            if (headPos >= screenWidth / 2 + screenWidth / 5.8f) {
                gameState = 3;

            } else {
                headPos += 30;
            }
        }
    }

    //sets the fonts
    public void initFont() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/font.ttf"));
        params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params2 = new FreeTypeFontGenerator.FreeTypeFontParameter();

        params.size = 100;
        params2.size = 100;
        params.color = Color.DARK_GRAY;
        params2.color = Color.YELLOW;
        font = generator.generateFont(params);
        font2 = generator.generateFont(params2);
    }

    //count the score of the boy
    public void ScoreCounter() {
        timeStete += Gdx.graphics.getDeltaTime();
        if (timeStete >= 1f) {
            gameHendler.score++;
            headPos -= 5;
            if (headPos <= screenWidth / 4) {
                headPos = screenWidth / 4;
            }
            timeStete = 0f;
        }
    }

    //draw shade
    public void drowFont() {
        font.draw(gameHendler.batch, String.valueOf(gameHendler.score) , screenWidth / 23, gameHendler.screenHight - (gameHendler.screenHight * 1 / 10));
    }

    //draw score shade
    public void drowFont2() {
        font2.draw(gameHendler.batch, String.valueOf(gameHendler.score), screenWidth / 25, gameHendler.screenHight - (gameHendler.screenHight * 1 / 12f));

    }

    //draw game's background
    public void backgroundDrow() {
        gameHendler.getBatch().draw(sky, skyX, 0, gameHendler.screenWidth, gameHendler.screenHight);
        gameHendler.getBatch().draw(sky, gameHendler.skyX + gameHendler.screenWidth, 0, gameHendler.screenWidth, gameHendler.screenHight);

        gameHendler.getBatch().draw(skyLineFar, farX, screenHight / 6, gameHendler.screenWidth, halfScreenHight);
        gameHendler.getBatch().draw(skyLineFar, farX + gameHendler.screenWidth, screenHight / 6, gameHendler.screenWidth, halfScreenHight);

        gameHendler.getBatch().draw(skyLineClose, closeX, screenHight / 6, gameHendler.screenWidth, halfScreenHight - screenHight / 5);
        gameHendler.getBatch().draw(skyLineClose, closeX + gameHendler.screenWidth, screenHight / 6, gameHendler.screenWidth, halfScreenHight - screenHight / 5);

        gameHendler.getBatch().draw(trees, roadX, screenHight / 6, gameHendler.screenWidth, gameHendler.screenHight / 10);
        gameHendler.getBatch().draw(trees, roadX + gameHendler.screenWidth, screenHight / 6, gameHendler.screenWidth, gameHendler.screenHight / 10);

        gameHendler.getBatch().draw(road, roadX, 0, gameHendler.screenWidth, gameHendler.screenHight / 6);
        gameHendler.getBatch().draw(road, roadX + gameHendler.screenWidth, 0, gameHendler.screenWidth, gameHendler.screenHight / 6);

        gameHendler.getBatch().draw(bar, screenWidth / 4f, gameHendler.screenHight - (gameHendler.screenHight * 1 / 5f), barLen, screenHight / 8);
        gameHendler.getBatch().draw(head, headPos, gameHendler.screenHight - (gameHendler.screenHight * 1 / 5f), screenWidth / 13, screenHight / 10);


    }

    //calc the position of all the objects on the background
    public void backgroundCalcPostion() {
        if (gameHendler.skyX == -(gameHendler.screenWidth)) {
            gameHendler.skyX = 0;
            roadX = 0;
        }
        if (roadX == -(screenWidth)) {
            roadX = 0;
            treesX = 0;
        }
//        if (treesX == -(screenWidth)) {
//            treesX = 0;
//        }
        if (closeX == -(screenWidth)) {
            closeX = 0;
        }
        if (farX == -(screenWidth)) {
            farX = 0;
        }
    }

    //game promoter
    public void gameRuner() {
        //gameing state
        if (gameState == 0) {
        }
        if (gameHendler.gameState == 1) {
            gameHendler.skyX -= gameHendler.skyV;
            roadX -= roadV;
            //treesX -= treesV;
            closeX -= closeV;
            farX -= farV;
            gameHendler.ScoreCounter();
            //input checking
            gameHendler.inputChaking();

            //food logic
            gameHendler.food.foodMovment();
            gameHendler.food.calcReectangle();
//           gameHendler.food.drow();
            //jump
            gameHendler.fatBoy.jumpLogic();

        }
        //state pause
//        else if (gameHendler.gameState == 2) {
//
//
//        }
//        //game over
//        else if (gameHendler.gameState == 3) {
//
//        }
//        //game menu
//        else if (gameState == 4) {
//
//        }
    }

    //calc food's speed
    public void speedGame() {
        float l1 = food.speed, l2 = food.speed * 1.5f, l3 = food.speed * 2, l4 = food.speed * 3,l5 = food.speed*3.5f ,l6 =food.speed *4 , l7 = food.speed*4.5f;
        if (score <= 10) {
            food.velocity = l1;

        } else if (score <= 30) {
            food.velocity = l2;
        } else if (score <= 60) {
            food.velocity = l3;
        } else if (score <=90){
            food.velocity = l5;
        } else if (score <=130){
        food.velocity = l6;
        }
        else {
            food.velocity = l7;
        }
    }

    //manage the draw on the screen
    public void drowRuner() {
        //start page
        if (gameState == 0) {
            gameHendler.getBatch().draw(startbg, 0, 0, gameHendler.screenWidth, gameHendler.screenHight);
            startPlayAction();
            batch.draw(startPlayBt.texture,startPlayBt.x,startPlayBt.y,startPlayBt.widt,startPlayBt.hight);
            goToSettings();
            batch.draw(settingsButton.texture,settingsButton.x,settingsButton.y,settingsButton.widt,settingsButton.hight);
            try {
                int temp  =prefs.getInteger("hightScore");
                if (temp>0){
                    font.draw(gameHendler.batch,String.valueOf(temp)+" M", screenWidth /1.7f,screenHight/9f);
                    font2.draw(gameHendler.batch,String.valueOf(temp)+" M", screenWidth / 1.68f,screenHight/8.5f);
                    batch.draw(best,screenWidth/1.2f,screenHight/20,screenWidth/6,screenHight/5);
                }

            }catch (Exception e){

            }

        }
        //game
       else if (gameState == 1) {
            backgroundDrow();
            food.drow();
            fatBoy.drow();
            drowFont();
            drowFont2();
            goToPause();
            batch.draw(pauseButton.texture,pauseButton.x,pauseButton.y,pauseButton.widt,pauseButton.hight);


        }
        //pause
         else if (gameState == 2) {
            gameHendler.getBatch().draw(timeOut, 0, 0, gameHendler.screenWidth, gameHendler.screenHight);
            resumeGame();
            batch.draw(resumeButton.texture,resumeButton.x,resumeButton.y,resumeButton.widt,resumeButton.hight);
            quitGame();
            batch.draw(quitButton.texture,quitButton.x,quitButton.y,quitButton.widt,quitButton.hight);
            retryGame();
            batch.draw(retryBotton.texture,retryBotton.x,retryBotton.y,retryBotton.widt,retryBotton.hight);


        }
        //game
       else if (gameState == 3) {
            gameHendler.getBatch().draw(gameover, 0, 0, gameHendler.screenWidth, gameHendler.screenHight);
            quitGame();
            batch.draw(quitButton.texture,quitButton.x,quitButton.y,quitButton.widt,quitButton.hight);
            retryGame();
            batch.draw(retryBotton.texture,retryBotton.x,retryBotton.y,retryBotton.widt,retryBotton.hight);

            saveScore(score);

            if (record == 1){
                score = prefs.getInteger("hightScore");
                batch.draw(best,screenWidth/1.39f,screenHight/5,screenWidth/6,screenHight/5);
            }


            font.draw(gameHendler.batch, String.valueOf(gameHendler.score) + " M" , screenWidth / 1.4f, gameHendler.screenHight - (gameHendler.screenHight * 1 / 1.96f));
            font2.draw(gameHendler.batch, String.valueOf(gameHendler.score) +" M" , screenWidth / 1.422f, gameHendler.screenHight - (gameHendler.screenHight * 1 / 2f));

        }
        //settings
       else if (gameState == 4) {

            gameHendler.getBatch().draw(settingsPage, 0, 0, gameHendler.screenWidth, gameHendler.screenHight);
            backToMenu();
            batch.draw(backButton.texture,backButton.x,backButton.y,backButton.widt,backButton.hight);
            musicButton();
            if (Integer.parseInt(musicIsOn) == 1){
                sounBt.texture = sounOn;
            }
            else if (Integer.parseInt(musicIsOn) == 0){
                sounBt.texture = soundOff;
            }

            if (Integer.parseInt(fxIsOn) == 1){
                fxBt.texture = fxOn;
            }
            else if (Integer.parseInt(fxIsOn) == 0){
                fxBt.texture = fxOff;
            }
            batch.draw(sounBt.texture,sounBt.x,sounBt.y,sounBt.widt,sounBt.hight);
            fxButton();
            batch.draw(fxBt.texture, fxBt.x, fxBt.y, fxBt.widt, fxBt.hight);
            goToAbout();
            batch.draw(aboutBt.texture, aboutBt.x, aboutBt.y, aboutBt.widt, aboutBt.hight);

        }
        //about
        else if (gameState == 5){
            gameHendler.getBatch().draw(aboutBg, 0, 0, gameHendler.screenWidth, gameHendler.screenHight);
            backToSettings();
            batch.draw(backButton.texture,backButton.x,backButton.y,backButton.widt,backButton.hight);
        }
        //tutorial
        else  if (gameState ==6){
            gameHendler.getBatch().draw(guide, 0, 0, gameHendler.screenWidth, gameHendler.screenHight);
            if (Gdx.input.justTouched()){
                isFirst = false;
                gameState = 1;
            }
        }
    }

    //music button action
    public void musicButton(){
        if (sounBt.checkIfClicked()){
            if (Integer.parseInt(musicIsOn) == 1 ){
                musicIsOn = "0";
                //sounBt.texture = soundOff;
                prefs.putString("music",musicIsOn);
                prefs.flush();

            }
            else if (Integer.parseInt(musicIsOn) == 0){
                musicIsOn = "1";
                //sounBt.texture = sounOn;
                prefs.putString("music",musicIsOn);
                prefs.flush();
            }
            System.out.println(musicIsOn);

        }
    }

    //sound effects button action
    public void fxButton(){
        if (fxBt.checkIfClicked()){
            if (Integer.parseInt(fxIsOn) == 1 ){
                fxIsOn = "0";
                //sounBt.texture = soundOff;
                prefs.putString("fx",fxIsOn);
                prefs.flush();

            }
            else if (Integer.parseInt(fxIsOn) == 0){
                fxIsOn = "1";
                //sounBt.texture = sounOn;
                prefs.putString("fx",fxIsOn);
                prefs.flush();
            }
            System.out.println(fxIsOn);
        }
    }

    //start the game
    public void startPlayAction(){
        if (startPlayBt.checkIfClicked()){
            initGame();
            if (isFirst){
                gameState = 6;
            }
            else {
                gameState = 1;
            }
        }
    }

    //buttons
    public  void goToSettings(){
        if (settingsButton.checkIfClicked()){
            initGame();
            gameState = 4;

        }
    }
    public  void backToMenu(){
        if (backButton.checkIfClicked()){
            initGame();
            gameState = 0;
        }
    }
    public  void goToPause(){
        if (pauseButton.checkIfClicked()){
            gameState = 2;
        }
    }
    public  void quitGame(){
        if (quitButton.checkIfClicked()){
            initGame();
            gameState = 0;
            record = 0;
        }
    }
    public  void retryGame(){
        if (retryBotton.checkIfClicked()){
            initGame();
            gameState = 1;
            record = 0;
        }
    }
    public  void resumeGame(){
        if (resumeButton.checkIfClicked()){
            gameState = 1;
        }
    }

    public void backToSettings(){
        if (backButton.checkIfClicked()){
            gameState = 4;
        }
    }
    public void goToAbout(){
        if (aboutBt.checkIfClicked()){
            gameState = 5;
        }
    }

    //save the highest score
    public void   saveScore( int s){
        int temp;
        try {
            temp = prefs.getInteger("hightScore");
        }catch (Exception e){
            temp = 0;
        }
        if (s > temp){
            prefs = Gdx.app.getPreferences("gamePreferences");
            record = 1;
            prefs.putInteger("hightScore",score);
            prefs.flush();
        }
    }

    //play jump sound
    public void playJumpSound(){
        if ((fatBoy.yPosition >10 && fatBoy.yPosition <14) && fatBoy.isLending == false && Integer.parseInt(fxIsOn) ==1){
            musicGame.jumpSound.play();
        }
    }

    //play dodge sound
    public void dodheJumpSound(){
        if (fatBoy.dodgeTime ==1 && Integer.parseInt(fxIsOn) ==1){
            musicGame.bendSound.play();
        }
    }

    //play bite sound
    public void biteSound(){
        if (Integer.parseInt(fxIsOn) ==1){
            musicGame.bite.play();
        }
    }

    //play background music
    public void bgMusicPlayer(){
        if (Integer.parseInt(musicIsOn)==1){
            if (!musicGame.bgMusic.isPlaying()){
                musicGame.bgMusic.play();
            }
        }
        if (Integer.parseInt(musicIsOn)==0){
            if (musicGame.bgMusic.isPlaying()){
                musicGame.bgMusic.stop();
            }
        }

    }
}




