package engineTester;

import java.util.ArrayList;
import java.util.List;

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
import terrain.TerrainTexturePack;
import textures.ModelTexture;
import textures.TerrainTexture;

public class MainGameLoop {
	
	public static void main(String[] args){
		/**
		 * @param args
		 */
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		
		TerrainTexture bg = new TerrainTexture(loader.loadTexture("grassy"));
		TerrainTexture r = new TerrainTexture(loader.loadTexture("dirt"));
		TerrainTexture g = new TerrainTexture(loader.loadTexture("pinkFlowers"));
		TerrainTexture b = new TerrainTexture(loader.loadTexture("path"));
		
		TerrainTexturePack terrainTexPack = new TerrainTexturePack(bg,r,g,b);
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
				
		
//		StaticShader shader = new StaticShader();
//		Renderer renderer = new Renderer(shader);
		
		Camera camera = new Camera();
		
		
		
		RawModel model = OBJLoader.loadOBJModel("tree", loader);
<<<<<<< HEAD
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("grassy2")));
=======
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("tree")));
>>>>>>> 4c81eedc9f74daf48b3aea4ae6008a5d86c2d507
		RawModel gmodel = OBJLoader.loadOBJModel("grassModel", loader);
		TexturedModel gstaticModel = new TexturedModel(gmodel,new ModelTexture(loader.loadTexture("grassTexture")));
		gstaticModel.getTexture().setHasTransparency(true);
		gstaticModel.getTexture().setUseFakeLighting(true);
		ModelTexture texture = staticModel.getTexture();
		texture.setReflectivity(0);
		texture.setShineDamper(1);
		List<Entity> entityList = new ArrayList<Entity>();
		for(int i = 0; i < 1000; i++) {
			int x = (int) (Math.random() * 1000);
			int z = (int) (Math.random() * 1000);
			entityList.add(new Entity(staticModel,new Vector3f(x,0,z),0,90,0,3));
<<<<<<< HEAD
			//entityList.add(new Entity(bunnyTex,new Vector3f(z,0,x),0,0,0,1));
=======
>>>>>>> 4c81eedc9f74daf48b3aea4ae6008a5d86c2d507
			entityList.add(new Entity(gstaticModel,new Vector3f(z,0,x),0,90,0,1));
			entityList.add(new Entity(gstaticModel,new Vector3f(z+10,0,x),0,90,0,1));
			entityList.add(new Entity(gstaticModel,new Vector3f(z,0,x+10),0,90,0,1));
			entityList.add(new Entity(gstaticModel,new Vector3f(z+10,0,x+10),0,90,0,1));

		}
		
<<<<<<< HEAD
=======
		
		Light light = new Light(new Vector3f(2000,2000,2000),new Vector3f(0.5f,0.75f,0.93f));
>>>>>>> 4c81eedc9f74daf48b3aea4ae6008a5d86c2d507
		
		Light light = new Light(new Vector3f(2000,2000,2000),new Vector3f(0.5f,0.75f,0.93f));
		
		Terrain terrain = new Terrain(0,0,loader, terrainTexPack, blendMap);
		
		MasterRenderer renderer = new MasterRenderer();
				
		while(!Display.isCloseRequested()){
			//entity.increaseRotation(0, 1, 0);
			//entity.increaseRotation(0, 1, 0);
			
			camera.move();
			renderer.processTerrain(terrain);
//			renderer.prepare();
//			shader.start();
//			shader.loadLight(light);
//			shader.loadViewMatrix(camera);
//			renderer.render(entity,shader);
//			shader.stop();
			for(Entity entity:entityList) {
				renderer.processEntity(entity);
			}
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
			
		}
		renderer.cleanUp();
//		shader.cleanUp();
		loader.cleanup();
		DisplayManager.closeDisplay();
	}
}
