package com.gorillamoa.happyBirthday.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.gorillamoa.happyBirthday.Main;
import com.gorillamoa.happyBirthday.ui.Box;

/**
 * Created by alvaregd on 16/06/15.
 */
public class Star extends Box {

    private TextureRegion starTexture;

    public float spinTimer = 0f;
    public float angularFrequency = 1f;
    public float angle = 0f;
    public float MAX_ANGLE = 180f;

    public float appearTimer = 0f;
    public float apearTime = 2f;
    public float MAX_GROWTH_SIZE = 300f;

    public float calculatedDimension = 0f;

    public float delayTimer = 0f;
    public float MAX_DELAY = 0f;

    public Star(float delay){

        this.x = MathUtils.random(Main.WIDTH);
        this.y =MathUtils.random(Main.HEIGHT);
        this.height = 0f;
        this.width = 0f;

        MAX_GROWTH_SIZE = 30f;

        MAX_DELAY = delay;

        starTexture = Main.res.getAtlas("assets").findRegion("path5300");

    }

    public void render(SpriteBatch sb){
//        sb.draw(starTexture, x- width/2, y- height/2,width,height);
        sb.draw(starTexture,
                x- width/2,
                y- height/2,
                width/2,
                height/2,
                width,
                height,
                1,
                1,
                angle);
    }


    public void update(float dt){

        delayTimer+=dt;
        if(delayTimer > MAX_DELAY ){

            delayTimer = MAX_DELAY;
            /** SPIN the object*/
            spinTimer += dt;

            if(angle < MAX_ANGLE){
                angle = spinTimer/angularFrequency * MAX_ANGLE;
            }else{
                spinTimer = 0f;
                angle = 0f;

                this.x = MathUtils.random(Main.WIDTH);
                this.y = MathUtils.random(Main.HEIGHT);
            }

            /** Scale the object */
            appearTimer += dt;
            if(this.height < MAX_GROWTH_SIZE){
                calculatedDimension = Math.abs((float) Math.sin(angle * 3.14 / 180.0) * MAX_GROWTH_SIZE);
                this.height = calculatedDimension;
                this.width = calculatedDimension;

//            this.height =  appearTimer/apearTime * MAX_GROWTH_SIZE;
//            this.width = appearTimer/apearTime * MAX_GROWTH_SIZE;
                if(this.angle > MAX_ANGLE){

                }
            }else{
                appearTimer = 0f;
            }
        }
    }
}
