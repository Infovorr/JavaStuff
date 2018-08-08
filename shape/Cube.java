public class Cube extends Cuboid {
	
	public Cube(double dimension) {
		//Stores width in all three dimensions
		super(dimension, dimension, dimension);
	}
	
	public double getWidth() {
		return super.getWidth();
	}
	
	public double getSurfaceArea() {
		return super.getSurfaceArea();
	}
	
	public double getVolume() {
		return super.getVolume();
	}
	
	public void setWidth(double dimension) {
		super.setWidth(dimension);
	}
}