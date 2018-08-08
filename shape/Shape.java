public class Shape {
	
	/*
	Dimension variables store dimensions relevant to a given shape, with unused
	dimensions defaulting to zero. Statistic variables store relevant statistics
	and default to zero otherwise.
	*/
	private double firstDimension = 0;
	private double secondDimension = 0;
	private double thirdDimension = 0;
	private double firstStatistic = 0;
	private double secondStatistic = 0;
	
	public Shape(double dimension1, double dimension2, double dimension3) {
		this.firstDimension = dimension1;
		this.secondDimension = dimension2;
		this.thirdDimension = dimension3;
	}
	
	public double getFirstDimension() {
		return firstDimension;
	}
	
	public double getSecondDimension() {
		return secondDimension;
	}
	
	public double getThirdDimension() {
		return thirdDimension;
	}
	
	public double getFirstStatistic() {
		return firstStatistic;
	}
	
	public double getSecondStatistic() {
		return secondStatistic;
	}
	
	public void setFirstDimension(double dimension) {
		this.firstDimension = dimension;
	}
	
	public void setSecondDimension(double dimension) {
		this.secondDimension = dimension;
	}
	
	public void setThirdDimension(double dimension) {
		this.thirdDimension = dimension;
	}
	
	public void setFirstStatistic(double statistic) {
		this.firstStatistic = statistic;
	}
	
	public void setSecondStatistic(double statistic) {
		this.secondStatistic = statistic;
	}
}