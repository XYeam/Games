package com.x.game2048;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.x.game2048.bus.constant.ResConstants;
import com.x.game2048.screen.GameScreen;

public class MainGame extends Game {

	public static final String TAG = "Game-2048";

	/**
	 * world width
	 */
	private float worldWidth;

	/**
	 * world height
	 */
	private float worldHeight;

	/**
	 * source manager
	 */
	private AssetManager assetManager;

	/**
	 * texture book
	 */
	private TextureAtlas textureAtlas;

	/**
	 * bitmap fonts
	 */
	private BitmapFont bitmapFont;

	/**
	 * main game scene
	 */
	private GameScreen gameScreen;

	/**
	 * 纹理画布
	 */
	SpriteBatch batch;

	/**
	 *纹理
	 */
	Texture img;
	
	@Override
	public void create () {
		//set log level
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		//In order not to flatten or stretch the image, calculate the width and height of the world at the actual screen scale
		worldWidth = ResConstants.FIX_WORLD_WIDTH;
		worldHeight = Gdx.graphics.getHeight() * worldWidth / Gdx.graphics.getWidth();

		Gdx.app.log(TAG, "World size:" + worldWidth + " * " + worldHeight);

		//create
		assetManager = new AssetManager();

		//load resource
		assetManager.load(ResConstants.ATLAS_PATH, TextureAtlas.class);
		assetManager.load(ResConstants.BITMAP_FONT_PATH, BitmapFont.class);
		assetManager.load(ResConstants.Audios.MOVE, Sound.class);
		assetManager.load(ResConstants.Audios.MERGE, Sound.class);
		//Wait for the resource to complete loading
		assetManager.finishLoading();
		//get resources
		textureAtlas = assetManager.get(ResConstants.ATLAS_PATH, TextureAtlas.class);
		bitmapFont = assetManager.get(ResConstants.BITMAP_FONT_PATH, BitmapFont.class);

		//set main game scene
		gameScreen = new GameScreen();

		//set current scene
		setScreen(gameScreen);

		//Capture the back key to manually handle the exit of the application (prevent pressing the back key to exit
		//the application when "pop-up" the help screen or dialog box)
		Gdx.input.setCatchBackKey(true);

		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		if (gameScreen != null) {
			gameScreen.dispose();
		}
		if (assetManager != null) {
			assetManager.dispose();
		}
		batch.dispose();
		img.dispose();
	}

	public float getWorldWidth() {
		return worldWidth;
	}

	public float getWorldHeight() {
		return worldHeight;
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}

	public BitmapFont getBitmapFont() {
		return bitmapFont;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public Texture getImg() {
		return img;
	}
}
