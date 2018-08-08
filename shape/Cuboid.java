public class Cuboid extends Shape {
	
	private Shape cuboid;
	
	public Cuboid(double dimension1, double dimension2, double dimension3) {
		//Stores width in firstDimension, length in secondDimension, height in thirdDimension
		super(dimension1, dimension2, dimension3);
		//Stores surface area in firstStatistic
		super.setFirstStatistic((dimension1 * dimension2) * 2 + (dimension1 * dimension3) * 2 + (dimension2 * dimension3) * 2);
		//Stores volume in secondStatistic
		super.setSecondStatistic(dimension1 * dimension2 * dimension3);
	}
	
	public double getWidth() {
		return super.getFirstDimension();
	}
	
	public double getLength() {
		return super.getSecondDimension();
	}
	
	public double getHeight() {
		return super.getThirdDimension();
	}
	
	public double getSurfaceArea() {
		return super.getFirstStatistic();
	}
	
	public double getVolume() {
		return super.getSecondStatistic();
	}
	
	public void setWidth(double dimension) {
		super.setFirstDimension(dimension);
		super.setFirstStatistic((dimension * super.getSecondDimension()) * 2 + (dimension * super.getThirdDimension()) * 2 + (super.getSecondDimension() * super.getThirdDimension()) * 2);
		super.setSecondStatistic(dimension * super.getSecondDimension() * super.getThirdDimension());
	}
	
	public void setLength(double dimension) {
		super.setSecondDimension(dimension);
		super.setFirstStatistic((super.getFirstDimension() * dimension) * 2 + (super.getFirstDimension() * super.getThirdDimension()) * 2 + (dimension * super.getThirdDimension()) * 2);
		super.setSecondStatistic(super.getFirstDimension() * dimension * super.getThirdDimension());
	}
	
	public void setHeight(double dimension) {
		super.setThirdDimension(dimension);
		super.setFirstStatistic((super.getFirstDimension() * super.getSecondDimension()) * 2 + (super.getFirstDimension() * dimension) * 2 + (super.getSecondDimension() * dimension) * 2);
		super.setSecondStatistic(super.getFirstDimension() * super.getSecondDimension() * dimension);
	}
}