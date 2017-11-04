package textures;
// a class to define an item that is the texture with its
// own properties etc.
public class ModelTexture {
	
	private int textureID;
	
	private boolean useFakeLighting = false;
	
	public boolean isUseFakeLighting() {
		return useFakeLighting;
	}

	public void setUseFakeLighting(boolean useFakeLighting) {
		this.useFakeLighting = useFakeLighting;
	}

	public boolean isHasTransparency() {
		return hasTransparency;
	}

	public void setHasTransparency(boolean hasTransparency) {
		this.hasTransparency = hasTransparency;
	}

	private boolean hasTransparency = false;
	
	private float shineDamper = 1;
	public float getShineDamper() {
		return shineDamper;
	}

	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}

	public float getReflectivity() {
		return reflectivity;
	}

	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}

	private float reflectivity = 0;
	
	public ModelTexture(int id){
		this.textureID = id;
	}
	
	public int getID(){
		return textureID;
	}

}
