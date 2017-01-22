package com.fatsfoodhenkar.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class main_activity extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Fabric.with(this, new Crashlytics());
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useWakelock = true;
		GLProfiler.enable();
		//RUN THE GAME
		initialize(new Game(), config);
	}
}
