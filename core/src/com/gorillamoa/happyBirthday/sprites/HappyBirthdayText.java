package com.gorillamoa.happyBirthday.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gorillamoa.happyBirthday.Main;
import com.gorillamoa.happyBirthday.ui.Box;


public class HappyBirthdayText extends Box {

    private TextureRegion birthdayText;

    public float spinTimer = 0f;
    public float angularFrequency = 5f;
    public float angle = 0f;
    public float MAX_ANGLE = 360;

    public float appearTimer = 0f;
    public float apearTime = 2f;
    public float MAX_GROWTH_SIZE = 300f;

    public HappyBirthdayText(float x,float y, float width, float height){

        this.x = x;
        this.y =y;
        this.height = 0f;
        this.width = 0f;

        MAX_GROWTH_SIZE = width;

        birthdayText = Main.res.getAtlas("assets").findRegion("happybirthday");

    }

    public void render(SpriteBatch sb){
//        sb.draw(birthdayText, x- width/2, y- height/2,width,height);
        sb.draw(birthdayText,
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

        /** SPIN the object*/
        spinTimer += dt;

        /** Scale the object */
        appearTimer += dt;
        if(this.height < MAX_GROWTH_SIZE){
            this.height =  appearTimer/apearTime * MAX_GROWTH_SIZE;
            this.width = appearTimer/apearTime * MAX_GROWTH_SIZE;
            if(this.height > MAX_GROWTH_SIZE){
                this.height = MAX_GROWTH_SIZE;
                this.width = MAX_GROWTH_SIZE;
            }
        }
    }
}