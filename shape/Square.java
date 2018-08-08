public class Square extends Rectangle {
	
	public Square(double dimension) {
		//Stores the width in firstDimension
		super(dimension, dimension);
	}
	
	public double getWidth() {
		return super.getWidth();
	}
	
	public double getArea() {
		//Stores the area in firstStatistic
		return super.getArea();
	}
	
	public double getPerimeter() {
		//Stores the perimeter in secondStatistic
		return super.getPerimeter();
	}
	
	public void setWidth(double dimension) {
		super.setWidth(dimension);
		super.setLength(dimension);
	}
}