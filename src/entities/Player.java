package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;
import renderEngine.DisplayManager;

public class Player extends Entity{
	
	private static final float RUN_SPEED = 20;
	private static final float TURN_RATE = 160;
	private static final float GRAVITY = -50;
	private static final float JUMPSTR = 10;
	
	private float currentSpeed = 0;
	private float currentTurnSPeed = 0;
	private float jumpSpeed = 0;
	
	private static final float TERR_HEIGHT = 0;

	public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
		// TODO Auto-generated constructor stub
	}
	
	public void move() {
		checkInputs();
		super.increaseRotation(0, currentTurnSPeed*DisplayManager.getFrameTimeSecs(), 0);
		float distance = currentSpeed * DisplayManager.getFrameTimeSecs();
		float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
		float dz = (float) ( distance * Math.cos(Math.toRadians(super.getRotY())));
		super.increasePosition(dx, 0, dz);
		jumpSpeed += GRAVITY*DisplayManager.getFrameTimeSecs();
		this.getPosition().y += jumpSpeed * DisplayManager.getFrameTimeSecs();
		if(this.getPosition().y< TERR_HEIGHT) {
			jumpSpeed = 0;
			this.getPosition().y = 0;
		}
	}
	
	private void jump() {
		jumpSpeed = JUMPSTR;
	}
	
	private void checkInputs() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.currentSpeed = RUN_SPEED;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.currentSpeed = -RUN_SPEED;
		}else {
			this.currentSpeed = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.currentTurnSPeed = -TURN_RATE;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.currentTurnSPeed = TURN_RATE;
		}else {
			this.currentTurnSPeed = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			jump();
		}
		
	}
	
}
