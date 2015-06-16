package com.gorillamoa.happyBirthday;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gorillamoa.happyBirthday.handler.Content;
import com.gorillamoa.happyBirthday.states.CongratsState;
import com.gorillamoa.happyBirthday.states.GSM;

public class Main extends ApplicationAdapter {

	SpriteBatch sb;
	private GSM gsm;
	public static Content res;

	public static final String TITLE = "Happy Birthday Mom";
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	@Override
	public void create () {

		Gdx.gl.glClearColor(1f,0.6f,0.0f,1);

		res = new Content();
		res.LoadAtlas("hbatlas.pack", "assets");

		sb = new SpriteBatch();
		gsm = new GSM();
		gsm.push(new CongratsState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.render(sb);
		gsm.update(Gdx.graphics.getDeltaTime());
	}
}
