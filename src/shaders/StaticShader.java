package shaders;

public class StaticShader extends shaderProgram{

	private static final String VERTEX_FILE = "src/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/shaders/FragmentShader.txt";
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void bindAttributes(){
		super.bindAttributes(0, "position");
	}

}
