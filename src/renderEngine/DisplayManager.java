package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	
	/*
	 * the following constants
	 * are used as parameters in 
	 * defining a new display
	 */
	
	private static int WIDTH = 720;
	private static int HEIGHT = 720;
	private static int FPS_MAX = 120;
	private static float delta;
	private static long lastFrameTime;
	
	public static void createDisplay(){
		
		ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
		
		try{
		/*
		 * The following lines are used to, respectfully
		 * set a new display with a given width and height,
		 * create it with a certain pixelformat and some
		 * attributes regarding version, forward compatibility.
		 * etc
		 */
			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			Display.create(new PixelFormat(), attribs); 
			Display.setTitle("Some new window");
		}catch(LWJGLException e){
			
			e.printStackTrace();
		
		}
		//Sets the usable portion of the display by setting a left-bottom coordinate to the top-right
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		lastFrameTime = getCurrTime();
	}
	
	public static void updateDisplay(){
		//sync to run at a steady fps
		Display.sync(FPS_MAX);
		Display.update();
		
		long currentFrameTime = getCurrTime();
		delta = (currentFrameTime - lastFrameTime ) / 1000f;
		lastFrameTime = currentFrameTime;
	}
	
	public static float getFrameTimeSecs() {
		return delta;
	}
	
	public static void closeDisplay(){
		//self explanatory
		Display.destroy();
		
	}
	
	private static long getCurrTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

}
