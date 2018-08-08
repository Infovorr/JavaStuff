public class Rhombus extends Parallelogram {
	
	public Rhombus(double dimension1, double dimension2) {
		//Stores the base in firstDimension and height in secondDimension
		super(dimension1, dimension1, dimension2);
	}
	
	public double getBase() {
		return super.getBase();
	}
	
	public double getHeight() {
		return super.getHeight();
	}
	
	public double getArea() {
		return super.getArea();
	}
	
	public double getPerimeter() {
		return super.getPerimeter();
	}
	
	public void setBase(double dimension) {
		super.setBase(dimension);
		super.setLength(dimension);
	}
	
	public void setHeight(double dimension) {
		super.setHeight(dimension);
	}
}