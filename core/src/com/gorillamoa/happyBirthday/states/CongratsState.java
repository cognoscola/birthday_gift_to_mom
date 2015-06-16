package com.gorillamoa.happyBirthday.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.gorillamoa.happyBirthday.Main;
import com.gorillamoa.happyBirthday.sprites.HappyBirthdayText;
import com.gorillamoa.happyBirthday.sprites.Lights;
import com.gorillamoa.happyBirthday.sprites.Star;

public class CongratsState extends State{

    private static final int numStar = 10;

    private Lights lights;
    private HappyBirthdayText hbtext;
    private Array<Star> stars;

    public CongratsState (GSM gsm){
        super(gsm);

        lights = new Lights(Main.WIDTH/2, Main.HEIGHT/2, 920, 920);
        stars = new Array<Star>();
        hbtext = new HappyBirthdayText(
                Main.WIDTH/2,
                Main.HEIGHT/2,
                400,
                400);

        for(int i = 0; i<numStar; i++){
            stars.add(new Star(MathUtils.random(1f)));
        }
    }

    @Override
    public void update(float dt) {
        for(int i= 0; i <stars.size;i++){
            stars.get(i).update(dt);
        }
        lights.update(dt);
        hbtext.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        for(int i= 0; i <stars.size;i++){
            stars.get(i).render(sb);
        }
        lights.render(sb);

        hbtext.render(sb);
        sb.end();
    }

    @Override
    public void handleInput() {

    }
}
