import java.util.Scanner;

public class ShapeTester {
	
	public static void main(String args[]) {
		
		double firstDimension;
		double secondDimension;
		double thirdDimension;
		int option;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please select one of the following options:");
		System.out.println("1) Square");
		System.out.println("2) Rectangle");
		System.out.println("3) Circle");
		System.out.println("4) Parallelogram");
		System.out.println("5) Rhombus");
		System.out.println("6) Triangle");
		System.out.println("7) Cube");
		System.out.println("8) Cuboid");
		option = input.nextInt();
		
		switch(option) {
			case 1:
				System.out.println("Please enter the square's width: ");
				firstDimension = input.nextDouble();
				Square square = new Square(firstDimension);
				System.out.println("The square's width is " + square.getWidth());
				System.out.println("The square's area is " + square.getArea());
				System.out.println("The square's perimeter is " + square.getPerimeter());
				break;
			case 2:
				System.out.println("Please enter the rectangle's width: ");
				firstDimension = input.nextDouble();
				System.out.println("Please enter the rectangle's length: ");
				secondDimension = input.nextDouble();
				Rectangle rectangle = new Rectangle(firstDimension, secondDimension);
				System.out.println("The rectangle's width is " + rectangle.getWidth());
				System.out.println("The rectangle's length is " + rectangle.getLength());
				System.out.println("The rectangle's area is " + rectangle.getArea());
				System.out.println("The rectangle's perimeter is " + rectangle.getPerimeter());
				break;
			case 3:
				System.out.println("Please enter the circle's radius: ");
				firstDimension = input.nextDouble();
				Circle circle = new Circle(firstDimension);
				System.out.println("The circle's radius is " + circle.getRadius());
				System.out.println("The circle's area is " + circle.getArea());
				System.out.println("The circle's circumference is " + circle.getCircumference());
				break;
			case 4:
				System.out.println("Please enter the parallelogram's base: ");
				firstDimension = input.nextDouble();
				System.out.println("Please enter the parallelogram's length: ");
				secondDimension = input.nextDouble();
				System.out.println("Please enter the parallelogram's height: ");
				thirdDimension = input.nextDouble();
				Parallelogram parallelogram = new Parallelogram(firstDimension, secondDimension, thirdDimension);
				System.out.println("The parallelogram's base is " + parallelogram.getBase());
				System.out.println("The parallelogram's length is " + parallelogram.getLength());
				System.out.println("The parallelogram's height is " + parallelogram.getHeight());
				System.out.println("The parallelogram's area is " + parallelogram.getArea());
				System.out.println("The parallelogram's perimeter is " + parallelogram.getPerimeter());
				break;
			case 5:
				System.out.println("Please enter the rhombus's base: ");
				firstDimension = input.nextDouble();
				System.out.println("Please enter the rhombus's height: ");
				secondDimension = input.nextDouble();
				Rhombus rhombus = new Rhombus(firstDimension, secondDimension);
				System.out.println("The rhombus's base is " + rhombus.getBase());
				System.out.println("The rhombus's height is " + rhombus.getHeight());
				System.out.println("The rhombus's area is " + rhombus.getArea());
				System.out.println("The rhombus's perimeter is " + rhombus.getPerimeter());
				break;
			case 6:
				System.out.println("Please enter the triangle's base: ");
				firstDimension = input.nextDouble();
				System.out.println("Please enter the triangle's height: ");
				secondDimension = input.nextDouble();
				Triangle triangle = new Triangle(firstDimension, secondDimension);
				System.out.println("The triangle's base is " + triangle.getBase());
				System.out.println("The triangle's height is " + triangle.getHeight());
				System.out.println("The triangle's area is " + triangle.getArea());
				break;
			case 7:
				System.out.println("Please enter the cube's width: ");
				firstDimension = input.nextDouble();
				Cube cube = new Cube(firstDimension);
				System.out.println("The cube's width is " + cube.getWidth());
				System.out.println("The cube's surface area is " + cube.getSurfaceArea());
				System.out.println("The cube's volume is " + cube.getVolume());
				break;
			case 8:
				System.out.println("Please enter the cuboid's width: ");
				firstDimension = input.nextDouble();
				System.out.println("Please enter the cuboid's length: ");
				secondDimension = input.nextDouble();
				System.out.println("Please enter the cuboid's height: ");
				thirdDimension = input.nextDouble();
				Cuboid cuboid = new Cuboid(firstDimension, secondDimension, thirdDimension);
				System.out.println("The cuboid's width is " + cuboid.getWidth());
				System.out.println("The cuboid's length is " + cuboid.getLength());
				System.out.println("The cuboid's height is " + cuboid.getHeight());
				System.out.println("The cuboid's surface area is " + cuboid.getSurfaceArea());
				System.out.println("The cuboid's volume is " + cuboid.getVolume());
				break;
		}
	}
}