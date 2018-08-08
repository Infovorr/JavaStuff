public class Triangle extends Shape {
	
	public Triangle(double dimension1, double dimension2) {
		//Stores the base in firstDimension, and height in secondDimension
		super(dimension1, dimension2, 0);
		//Stores the area in FirstStatistic
		super.setFirstStatistic((dimension1 * dimension2) / 2);
	}
	
	public double getBase() {
		return super.getFirstDimension();
	}
	
	public double getHeight() {
		return super.getSecondDimension();
	}
	
	public double getArea() {
		return super.getFirstDimension();
	}
	
	public void setBase(double dimension) {
		super.setFirstDimension(dimension);
		super.setFirstStatistic((dimension * super.getSecondDimension()) / 2);
	}
	
	public void setHeight(double dimension) {
		super.setSecondDimension(dimension);
		super.setFirstStatistic((dimension * super.getFirstDimension()) / 2);
	}
}