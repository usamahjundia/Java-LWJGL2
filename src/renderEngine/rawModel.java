package renderEngine;

public class rawModel {
	private int VAOID;
	private int vertexCount;
	
	public rawModel(int VAOID, int vertexCount){
		
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