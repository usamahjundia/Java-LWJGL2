package shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public abstract class ShaderProgram {
	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	public ShaderProgram(String vertexFile, String fragmentFile){
		/* loads the shaders
		 * and get the ids
		 */
		vertexShaderID = loadShader(vertexFile,GL20.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentFile,GL20.GL_FRAGMENT_SHADER); 
		/*
		 * creates a program taht will
		 * automatically tie vertexshader and fragmentshader
		 */
		programID = GL20.glCreateProgram();
		//Attaches the shaders into the program
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);
		
		bindAttributes();
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
		getAllUniformLocation();
	}
	
	protected abstract void getAllUniformLocation();
	
	protected int getUniformLocation(String uniformName){
		return GL20.glGetUniformLocation(programID, uniformName);
	}
	
	public void start(){
		GL20.glUseProgram(programID);
	}
	
	public void stop(){
		GL20.glUseProgram(0);
	}
	
	public void cleanUp(){
		stop();
		GL20.glDetachShader(programID, fragmentShaderID);
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDeleteShader(fragmentShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteProgram(programID);
	}
	
	/*
	 * the following abstract method and one of its implementation
	 * is called without parameter in the constructor
	 * because and implementation of it is already used in the staticshader class
	 * which extended this abstract method
	 * 
	 * this took kinda long ish to understand
	 * 
	 * REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
	 */
	
	protected abstract void bindAttributes();
	
	protected void bindAttributes(int attribute, String variableName){
		GL20.glBindAttribLocation(programID, attribute, variableName);
	}
	
	protected void loadInt(int location, int value) {
		GL20.glUniform1i(location, value);
	}
	
	protected void loadFloat(int location, float value){
		GL20.glUniform1f(location, value);
	}
	
	protected void loadVector(int location, Vector3f vector){
		GL20.glUniform3f(location, vector.x, vector.y, vector.z);
	}
	
	protected void loadBoolean(int location, boolean bool){
		float toLoad = 0;
		if(bool){
			toLoad = 1;
		}
		GL20.glUniform1f(location,toLoad);
	}
	
	protected void loadMatrix(int location, Matrix4f matrix){
		matrix.store(matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4(location, false, matrixBuffer);
	}
	/*
	 * loads the shader files into a string
	 * that will be evaluated with the built in glsl
	 * compiler, will then attach it to a x shader
	 * where x is specified by Integer type,
	 * if compilation fails or shader is not found, 
	 * it will terminate the program and show the error as a prompt
	 */
	private static int loadShader(String file, int type){
		  StringBuilder shaderSource = new StringBuilder();
		  try{
		   BufferedReader reader = new BufferedReader(new FileReader(file));
		   String line;
		   while((line = reader.readLine())!=null){
		    shaderSource.append(line).append("//\n");
		   }
		   reader.close();
		  }catch(IOException e){
		   e.printStackTrace();
		   System.exit(-1);
		  }
		  int shaderID = GL20.glCreateShader(type);
		  GL20.glShaderSource(shaderID, shaderSource);
		  GL20.glCompileShader(shaderID);
		  if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS )== GL11.GL_FALSE){
			  System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
			  System.err.println("Could not compile shader!");
			  System.exit(-1);
		  }
		  return shaderID;
	}
}
