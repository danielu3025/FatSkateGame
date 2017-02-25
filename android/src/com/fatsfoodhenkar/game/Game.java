package com.fatsfoodhenkar.game;

import com.badlogic.gdx.ApplicationAdapter;

import static com.fatsfoodhenkar.game.Game.gameHendler;


public class Game extends ApplicationAdapter {
	static GameHendler gameHendler;
    Thread gameThread   = new Thread(new GameRunnable());
    Thread musicThread = new Thread( new SoundRunnable());

	@Override
	public void create() {
        gameHendler = new GameHendler();
        gameHendler.fatBoy = new FatBoy();
        gameHendler.food = new Food();
		gameHendler.initFont();
        gameThread.start();
        musicThread.start();
    }
	@Override
	 public void render() {
        gameThread.run();
        musicThread.run();
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
        gameHendler.batch.begin();
        gameHendler.drowRuner();
        gameHendler.batch.end();
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
    }
}
class  SoundRunnable implements  Runnable{

    @Override
    public void run() {
        gameHendler.bgMusicPlayer();
    }
}
