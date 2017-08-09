package renderEngine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import models.RawModel;

public class Loader {
	/*
	 * arraylists to store any vao, vbo, or texture and grab the id for cleanup later
	 */
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();

	public RawModel loadToVAO(float[] positions, float[] textureCoords, int[] indices, float[] normals){
		
		//creates a VAO, get the ID of it
		int vaoID = createVAO();
		//bind the indices to the VBO
		bindIndicesBuffer(indices);
		//storing the datas in VAO attrib list
		//the said VAO is already Bound on createVAO()
		storeDataInAttributeList(0,3,positions);
		storeDataInAttributeList(1,2,textureCoords);
		storeDataInAttributeList(2,3,normals);
		//storing data complete, unbinds the vao
		unbindVAO();
		/*
		 * returns the rawmodel containing the ID of the model's vao
		 * and the number of vertex
		 */
		return new RawModel(vaoID,indices.length);
		
	}
	
	/*
	 * loads a texture of a certain filename in a specific folder,
	 * the texture is a type provided by the Texture class
	 * that is provided by the SlickUtils library
	 * and returns the textureID of the said texture
	 * that is already loaded using the TextureLoader method
	 * 
	 */
	
	public int loadTexture(String fileName){
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG",new FileInputStream("res/"+fileName+".png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int textureID = texture.getTextureID();
		return textureID;
	}
	
	
	/*
	 * cleans up all vao, vbo and texture that is
	 * already registered for memory purposes
	 */
	public void cleanup(){
		
		for(int vao:vaos){
			GL30.glDeleteVertexArrays(vao);
		}
		for(int vbo:vbos){
			GL15.glDeleteBuffers(vbo);
		}
		for(int texture:textures){
			GL11.glDeleteTextures(texture);
		}
		
	}
	
	
	//Method to create a new VAO with its own ID
	private int createVAO(){
		
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		//this binds the specific VAO for use
		//in a sense, actvating it
		GL30.glBindVertexArray(vaoID);
		return vaoID;
		
	}
	
	/*
	 * this method fills the attrib list of the VAO
	 * by creating a VBO first to store the data
	 */
	
	private void storeDataInAttributeList(int attributeNumber,int coordsSize, float[] data){
		//creates an empty VBO, get ID
		
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		
		//Binds  the VBO array for later use
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID); 
		
		/*
		 * readies the data by storing it into a buffer
		 * using the predefined methd below
		 * v
		 * v
		 * v
		 */
		
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		
		/*
		 * this following line is used to store
		 * the data into the VBO
		 * with the type GL_ARRAY_BUFFER
		 * using data buffer
		 * and use it for static draw 
		 */
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		
		/*
		 * the following line is used to store the VBO we just made
		 * into one of the attribs list of the VAO
		 * with the index of attributenumber,
		 * the length of each data stored in the VAO,
		 * the data type,
		 * normalized or not,
		 * distance between each vertice,
		 * and the offset of the data
		 */
		
		GL20.glVertexAttribPointer(attributeNumber, coordsSize, GL11.GL_FLOAT, false, 0, 0);
		
		/*
		 * unbinds the VBO of the type
		 * GL_ARRAY_BUFFER by set the
		 * current bound buffer to
		 * the bufferID 0
		 */
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
	}
	
	/*
	 * the method bound a 'null' array, 
	 * hence unbinding the other VAO it is currently binding
	 */
	
	private void unbindVAO(){
		GL30.glBindVertexArray(0);
	}
	
	
	/* more or less, readying a data
	 * containing indices of a model,
	 * loading it into a VBO, with first
	 * storing it inside a buffer
	 */
	private void bindIndicesBuffer(int[] indices){
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		/* the type of the data is different
		 * from the normal vertex data which is
		 * GL_Element_Array_Buffer as opposed to
		 * GL_ARRAY_BUFFER which indicates it is
		 * used as indices
		 */
		
		
		//binds a VBO to store the data in
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		//stores the buffered data in the current bound VBO
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	
	/* more or less the same
	 * with the storedatainfloatbuffer
	 */
	private IntBuffer storeDataInIntBuffer(int[] data){
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	/*
	 * this method takes an array of float and turn it to
	 * a float buffer that is compatible to be bound
	 * to the VBO before being loaded into the attrib list
	 */
	
	private FloatBuffer storeDataInFloatBuffer(float[] data){
		
		//creates a float buffer with the same size data has
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		//puts the data inside the buffer
		buffer.put(data);
		/*
		 * since buffers have different behavior when read and written,
		 * flipping the buffer readies the buffer to be read after
		 * being written
		 */
		buffer.flip();
		return buffer;
		
	}
	
}
