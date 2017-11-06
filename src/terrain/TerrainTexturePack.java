package terrain;

import textures.TerrainTexture;

public class TerrainTexturePack {
	
	private TerrainTexture bgTex;
	private TerrainTexture rTex;
	private TerrainTexture gTex;
	private TerrainTexture bTex;
	public TerrainTexturePack(TerrainTexture bgTex, TerrainTexture rTex, TerrainTexture gTex, TerrainTexture bTex) {
		//super();
		this.bgTex = bgTex;
		this.rTex = rTex;
		this.gTex = gTex;
		this.bTex = bTex;
	}
	public TerrainTexture getBgTex() {
		return bgTex;
	}
	public TerrainTexture getrTex() {
		return rTex;
	}
	public TerrainTexture getgTex() {
		return gTex;
	}
	public TerrainTexture getbTex() {
		return bTex;
	}
	
	
	
}
