package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
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
	private static final float heightOffset = 1.6f;
	private float distanceFromPlayer = 10;
	private float angleAround =0;
	private float currSpeed= 0;
	private float currTurnX= 0;
	private float currTurnY= 0;
	private float currTurnZ= 0;
	
	private float test1 = 0;
	private float test1prev = -999;
	
	private Player player;
	
	public Camera(Player player){
		//position.z = 1.5f;
		this.pitch = 25f;
		this.yaw = 0f;
		this.roll = 0f;
		this.player = player;
	}
	
	//float speed = 5.0f;
	
	public void move(){
		//UNCOMMENT IF YOU WANNA USE FPS
		/*detectInputs();
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
			currSpeed -= currSpeed*0.025f;
		}*/
		calculateZoom();
		calculatePitch();
		calculateAngle();
		float horizontalD = calculateHorizontalDistance();
		float verticalD = calculateVerticalDistance();
		calculatePosition(horizontalD, verticalD);
		this.yaw = 180 - player.getRotY() - angleAround;

	}
	
	private void calculatePosition(float hori, float verti) {
		position.y = player.getPosition().y + verti + heightOffset;
		float tetha = player.getRotY() + angleAround;
		float xOffset = (float) (hori * Math.sin(Math.toRadians(tetha)));
		float zOffset = (float) (hori * Math.cos(Math.toRadians(tetha)));
		position.x = player.getPosition().x - xOffset;
		position.z = player.getPosition().z - zOffset;
	}
	
	private float calculateHorizontalDistance() {
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}
	
	private float calculateVerticalDistance() {
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}
	
	public void detectInputs() {
		//UNCOMMENT FOR FPS
		/*if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			currSpeed = MOVESPD;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			currSpeed = -MOVESPD /4.0f;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_I)) {
			pitch-=0.25f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_K)) {
			pitch+=0.25f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_J)) {
			yaw -= 0.5f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_L)) {
			yaw += 0.5f;
		}*/
		
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
	
	private void calculateZoom() {
		float zoomLevel = Mouse.getDWheel() * 0.01f;
		distanceFromPlayer -= zoomLevel;
	}
	
	private void calculatePitch() {
		if(Mouse.isButtonDown(1)) {
			float pitchChange = Mouse.getDY() * 0.1f;
			
				pitch -= pitchChange;
			
		}
	}
	
	private void calculateAngle() {
		if(Mouse.isButtonDown(0)) {
			float angleChange = Mouse.getDX() * 0.1f;
			angleAround -= angleChange;
		}
	}
	

}
