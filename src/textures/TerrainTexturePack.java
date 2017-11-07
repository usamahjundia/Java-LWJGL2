package textures;

import terrain.TerrainTexture;

public class TerrainTexturePack {
	
	private TerrainTexture bg;
	private TerrainTexture r;
	private TerrainTexture g;
	private TerrainTexture b;
	//private TerrainTexture blendMap;
	public TerrainTexturePack(TerrainTexture bg, TerrainTexture r, TerrainTexture g, TerrainTexture b) {
		//super();
		this.bg = bg;
		this.r = r;
		this.g = g;
		this.b = b;
		//this.blendMap = blendMap;
	}
	public TerrainTexture getBg() {
		return bg;
	}
	public TerrainTexture getR() {
		return r;
	}
	public TerrainTexture getG() {
		return g;
	}
	public TerrainTexture getB() {
		return b;
	}
	
}
