package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position = new Vector3f(50,1.5f,50);
	private float pitch;
	private float yaw;
	private float roll;
	
	public Camera(){
		
	}
	
	float factor = 5.0f;
	
	public void move(){
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.z -= factor * 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.z += factor * 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x += factor * 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x -= factor * 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			position.y +=factor * 0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			position.y -=factor * 0.02f;
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
