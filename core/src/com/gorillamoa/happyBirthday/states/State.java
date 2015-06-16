package com.gorillamoa.happyBirthday.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gorillamoa.happyBirthday.Main;

/**
 * Created by alvaregd on 16/06/15.
 */
public abstract class State {

    protected GSM gsm;
    protected OrthographicCamera cam;

    protected State(GSM gsm){
        this.gsm = gsm;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, Main.WIDTH,Main.HEIGHT);
    }

    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void handleInput();

}
