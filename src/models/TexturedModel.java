package models;

import textures.ModelTexture;

/*
 * represents a TexturedModel that has a RawModel component and a Texture Component utilizing the ModelTexture and the RawModel class
 * nothingfancy
 */

public class TexturedModel {
	
	private RawModel rawModel;
	private ModelTexture texture;
	
	public TexturedModel(RawModel model, ModelTexture texture){
		this.rawModel = model;
		this.texture = texture;
	}

	public RawModel getRawModel() {
		return rawModel;
	}

	public ModelTexture getTexture() {
		return texture;
	}
	
	

}
