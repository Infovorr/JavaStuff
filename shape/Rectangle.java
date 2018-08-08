public class Rectangle extends Shape {
	
	private Shape rectangle;
	
	public Rectangle(double dimension1, double dimension2) {
		//Stores width in firstDimension and length in secondDimension
		super(dimension1, dimension2, 0);
		//Stores area in firstStatistic
		super.setFirstStatistic(dimension1 * dimension2);
		//Stores perimeter in secondStatistic
		super.setSecondStatistic((dimension1 * 2) + (dimension2 * 2));
	}
	
	public double getWidth() {
		return super.getFirstDimension();
	}
	
	public double getLength() {
		return super.getSecondDimension();
	}
	
	public double getArea() {
		return super.getFirstStatistic();
	}
	
	public double getPerimeter() {
		return super.getSecondStatistic();
	}
	
	public void setLength(double dimension) {
		super.setSecondDimension(dimension);
		super.setFirstStatistic(super.getFirstDimension() * dimension);
		super.setSecondStatistic((super.getFirstDimension() * 2) + (dimension * 2));
	}
	
	public void setWidth(double dimension) {
		super.setFirstDimension(dimension);
		super.setFirstStatistic(super.getSecondDimension() * dimension);
		super.setSecondStatistic((super.getSecondDimension() * 2) + (dimension * 2));
	}
}