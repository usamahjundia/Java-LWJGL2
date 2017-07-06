package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import renderEngine.rawModel;
import shaders.StaticShader;

public class MainGameLoop {
	
	public static void main(String[] args){
		/**
		 * @param args
		 */
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		float[] vertices = {
				  -0.5f, 0.5f, 0,
				  -0.5f, -0.5f, 0,
				  0.5f, -0.5f, 0,
				  0.5f, 0.5f, 0f
				};
				  
				int[] indices = {
				  0,1,3,
				  3,1,2
				};
		rawModel model = loader.loadToVAO(vertices,indices);
		
		while(!Display.isCloseRequested()){
			renderer.prepare();
			shader.start();
			renderer.render(model);
			shader.stop();
			DisplayManager.updateDisplay();
			
		}
		shader.cleanUp();
		loader.cleanup();
		DisplayManager.closeDisplay();
	}
}
