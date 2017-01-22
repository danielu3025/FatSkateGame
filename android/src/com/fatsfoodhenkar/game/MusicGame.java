package com.fatsfoodhenkar.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class MusicGame {

    public Music bgMusic, gameOverMusic;
    Sound jumpSound, bendSound,bite;
    float volume = 0.3f;

    public MusicGame() {
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/background.mp3"));
        gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/gameover.mp3"));
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("sounds/jumppp11.ogg"));
        bendSound = Gdx.audio.newSound(Gdx.files.internal("sounds/jumppp11.ogg"));
        bite = Gdx.audio.newSound(Gdx.files.internal("sounds/bite.mp3"));
        long x = (long) 0.7f;
        jumpSound.setVolume(x,0.7f);
        bendSound.setVolume(x,0.7f);

        bgMusic.setVolume(0.2f);
        bgMusic.setLooping(true);
    }

    public Music getBgMusic() {
        return bgMusic;
    }

    public float getVolume() {
        return volume;
    }

    public Music getGameOverMusic() {
        return gameOverMusic;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }


}



