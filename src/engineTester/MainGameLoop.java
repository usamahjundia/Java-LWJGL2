package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import renderEngine.EntityRenderer;
import shaders.StaticShader;
import terrain.Terrain;
import textures.ModelTexture;

public class MainGameLoop {
	
	public static void main(String[] args){
		/**
		 * @param args
		 */
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
//		StaticShader shader = new StaticShader();
//		Renderer renderer = new Renderer(shader);
		
		Camera camera = new Camera();
		
		
		
		RawModel model = OBJLoader.loadOBJModel("dragon", loader);
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("white")));
		ModelTexture texture = staticModel.getTexture();
		texture.setReflectivity(0);
		texture.setShineDamper(1);
		
		Entity entity = new Entity(staticModel,new Vector3f(50,0,50),0,90,0,1);
		Light light = new Light(new Vector3f(50,25,50),new Vector3f(0,1,1));
		
		Terrain terrain = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain2 = new Terrain(1,0,loader,new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain3 = new Terrain(-1,0,loader,new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain4 = new Terrain(0,1,loader,new ModelTexture(loader.loadTexture("grass")));

		MasterRenderer renderer = new MasterRenderer();
				
		while(!Display.isCloseRequested()){
			entity.increaseRotation(0, 1, 0);
			//entity.increaseRotation(0, 1, 0);
			camera.move();
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
			renderer.processTerrain(terrain3);
			renderer.processTerrain(terrain4);
//			renderer.prepare();
//			shader.start();
//			shader.loadLight(light);
//			shader.loadViewMatrix(camera);
//			renderer.render(entity,shader);
//			shader.stop();
			renderer.processEntity(entity);
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
			
		}
		renderer.cleanUp();
//		shader.cleanUp();
		loader.cleanup();
		DisplayManager.closeDisplay();
	}
}
