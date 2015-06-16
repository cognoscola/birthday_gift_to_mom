package com.gorillamoa.happyBirthday.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/** manages the different screens in our game,
 * (our single screen game */

public class GSM {

    private Stack<State> states;

    public GSM(){
        states = new Stack<State>();
    }

    public void push(State s){
        states.push(s);
    }

    public void set (State s){

        states.pop();
        states.push(s);

    }

    public void update(float dt){
        states.peek().update(dt);

    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);

    }
}
