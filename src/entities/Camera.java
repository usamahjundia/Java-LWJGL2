package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;

public class Camera {
	
	private Vector3f position = new Vector3f(400,1.5f,400);
	private float pitch;
	private float yaw;
	private float roll;
	private static final float MOVESPD = 100;
	private static final float CMRSPD = 55;
	private static final float DAMPFCTR = 12;
	private float currSpeed= 0;
	private float currTurnX= 0;
	private float currTurnY= 0;
	private float currTurnZ= 0;
	
	private float test1 = 0;
	private float test1prev = -999;
	
	public Camera(){
		this.pitch = 0f;
		this.yaw = 0f;
		this.roll = 0f;
	}
	
	//float speed = 5.0f;
	
	public void move(){
		detectInputs();
		float dx = (float) (Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)) * currSpeed * DisplayManager.getFrameTimeSecs());
		float dy = (float) (Math.sin(Math.toRadians(pitch)) * -currSpeed * DisplayManager.getFrameTimeSecs());
		float dz = (float) (Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)) * -currSpeed * DisplayManager.getFrameTimeSecs());
		position.x += dx;
		position.y += dy;
		position.z += dz;
		
		test1 = (float) Math.cos(Math.toRadians(yaw));
		
		if(test1 != test1prev) {
			System.out.println("Iter : "+ test1);
			test1prev = test1;
		}
		
		if(!Keyboard.isKeyDown(Keyboard.KEY_W) || !Keyboard.isKeyDown(Keyboard.KEY_S) && currSpeed >0) {
			currSpeed -= currSpeed*0.125f;
		}
		
	}
	
	public void detectInputs() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			currSpeed = MOVESPD;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			currSpeed = -MOVESPD /4.0f;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_I)) {
			pitch-=0.25f;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_K)) {
			pitch+=0.25f;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_J)) {
			yaw -= 0.5f;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_L)) {
			yaw += 0.5f;
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	

}
