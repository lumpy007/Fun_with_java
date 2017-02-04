package code_optim;

public class tree_element {
	
	private String element = null;
	private int debth = 0;
	private int up_index = 0;
	private String funcionName = null;
	private String filename = null;
	
	public String getFuncionName() {
		return funcionName;
	}
	public void setFuncionName(String funcionName) {
		this.funcionName = funcionName;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	public int getDebth() {
		return debth;
	}
	public void setDebth(int debth) {
		this.debth = debth;
	}
	public int getUp_index() {
		return up_index;
	}
	public void setUp_index(int up_index) {
		this.up_index = up_index;
	}
	
	
	

}
