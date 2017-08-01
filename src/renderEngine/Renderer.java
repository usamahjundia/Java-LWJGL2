package renderEngine;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import shaders.StaticShader;
import textures.ModelTexture;
import tools.Maths;

/**
 * @author USER
 *
 */
/**
 * @author USER
 *
 */
public class Renderer {
	
	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;
	
	private Matrix4f projectionMatrix;
	
	public Renderer(StaticShader shader){
		/**
		 * The pieces of code below made it so that
		 * faces or surfaces that is behind another
		 * surface will not be rendered, saving some
		 * working power to be used for something else
		 */
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		
		
		
		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	/*
	 * prepares the display with a certain color
	 * for later use
	 */
	public void prepare(){
		
		/*
		 * enables depth test so which face is on the fornt is clear
		 */
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(1, 0, 0, 1);
		//GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}
	
	public void render(Entity entity, StaticShader shader){
		
		TexturedModel texturedModel = entity.getModel();
		
		RawModel model = texturedModel.getRawModel();
		
		/*
		 * the following line binds the VAO
		 * of the model to be rendered,
		 * because binding it is the first step
		 * before it being usable
		 */
		
		GL30.glBindVertexArray(model.getVAOID());
		
		/*
		 * the following lines enables
		 * each of the attrib list
		 * for use
		 */
		
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
		ModelTexture texture = texturedModel.getTexture();
		shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getID());
		
		/*
		 * this method below draws the model using the Elements Array Buffer
		 */
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT,0);
		
		//disabling the VAO attrib list when finished using
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		
		//unbinds the VAO to use
		GL30.glBindVertexArray(0);
		
	}
	
	
	
	
	/*
	 * this method generates a projection matrix based on
	 * some self defined constants, and turn it into
	 * a matrix to later use to for projection effects
	 * */
	private void createProjectionMatrix(){
       float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV/2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;
        
        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
    }
}
