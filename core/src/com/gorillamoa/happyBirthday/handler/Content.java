package com.gorillamoa.happyBirthday.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

/**
 * Created by alvaregd on 16/06/15.
 */
public class Content {

    HashMap<String, TextureAtlas> atlases;

    public Content(){
        atlases = new HashMap<String,TextureAtlas>();
    }

    public void LoadAtlas(String path, String key){
        atlases.put(key, new TextureAtlas(Gdx.files.internal(path)));
    }

    public TextureAtlas getAtlas(String key){
        return atlases.get(key);
    }


}
