package models;

/*
 * This is a class Representing a single model which
 * is basically just a bucnh of vertex stored
 * as a VBO in a slot in VAO. The actual buffer
 * is not stored here, rather, it is stored
 * somewhere else with a certain unique ID
 * which is stored here along with the number of vertex
 */

public class RawModel {
	private int VAOID;
	private int vertexCount;
	
	public RawModel(int VAOID, int vertexCount){
		
		this.VAOID = VAOID;
		this.vertexCount = vertexCount;
		
	}

	public int getVAOID() {
		return VAOID;
	}

	public int getVertexCount() {
		return vertexCount;
	}
}