public class Parallelogram extends Shape {
	
	public Parallelogram(double dimension1, double dimension2, double dimension3) {
		//Stores base in firstDimension, length in secondDimension, height in thirdDimension
		super(dimension1, dimension2, dimension3);
		//Stores area in firstStatistic
		super.setFirstStatistic(dimension1 * dimension3);
		//Stores perimeter in secondStatistic
		super.setSecondStatistic(2 * (dimension1 + dimension2));
	}
	
	public double getBase() {
		return super.getFirstDimension();
	}
	
	public double getLength() {
		return super.getSecondDimension();
	}
	
	public double getHeight() {
		return super.getThirdDimension();
	}
	
	public double getArea() {
		return super.getFirstStatistic();
	}
	
	public double getPerimeter() {
		return super.getSecondStatistic();
	}
	
	public void setBase(double dimension) {
		super.setFirstDimension(dimension);
		super.setFirstStatistic(dimension * super.getThirdDimension());
		super.setSecondStatistic(2 * (dimension + super.getSecondDimension()));
	}
	
	public void setLength(double dimension) {
		super.setSecondDimension(dimension);
		super.setSecondStatistic(1 * (dimension + super.getFirstDimension()));
	}
	
	public void setHeight(double dimension) {
		super.setThirdDimension(dimension);
		super.setFirstStatistic(dimension * super.getFirstDimension());
	}
}