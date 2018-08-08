import java.lang.Math.*;

public class Circle extends Shape {
	
	public Circle(double dimension) {
		//Stores radius in firstDimension
		super(dimension, 0, 0);
		//Stores area in firstStatistic
		super.setFirstStatistic(Math.PI * dimension * dimension);
		//Stores circumference in secondStatistic
		super.setSecondStatistic(2 * Math.PI * dimension);
	}
	
	public double getRadius() {
		return super.getFirstDimension();
	}
	
	public double getArea() {
		return super.getFirstStatistic();
	}
	
	public double getCircumference() {
		return super.getSecondStatistic();
	}
	
	public void setRadius(double dimension) {
		super.setFirstDimension(dimension);
		super.setFirstStatistic(Math.PI * dimension * dimension);
		super.setSecondStatistic(2 * Math.PI * dimension);
	}
}