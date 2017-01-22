package com.fatsfoodhenkar.game;

import com.badlogic.gdx.ApplicationAdapter;

import static com.fatsfoodhenkar.game.Game.gameHendler;


public class Game extends ApplicationAdapter {
	static GameHendler gameHendler;

    int count  = 0;
    float avg = 0;

	@Override
	public void create() {
		gameHendler = new GameHendler();
        gameHendler.fatBoy = new FatBoy();
        gameHendler.food = new Food();
		gameHendler.initFont();

    }
	@Override
	 public void render() {

        gameHendler.batch.begin();
        Thread gameThread   = new Thread(new GameRunnable());
        Thread musicThread = new Thread( new SoundRunnable());
        gameHendler.drowRuner();
        gameHendler.batch.end();
        gameThread.start();
        musicThread.start();
    }

	@Override
	public void dispose() {
		gameHendler.batch.dispose();
		gameHendler.font.dispose();

	}
}

class  GameRunnable implements Runnable{

    @Override
    public void run() {

        if (gameHendler.gameState == 1) {
            gameHendler.backgroundCalcPostion();
            gameHendler.gameRuner();
            gameHendler.fatBoy.rectanglePicker();
            gameHendler.colison();
            gameHendler.speedGame();
            gameHendler.fatBoy.spriteChanger();
            gameHendler.playJumpSound();
            gameHendler.dodheJumpSound();

        }
       // System.out.println("GameRunablbe");
//        try {
//            Thread.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}
class  SoundRunnable implements  Runnable{

    @Override
    public void run() {
            gameHendler.bgMusicPlayer();
    }
}
