public class HW2_Interfaces {
	interface Shape{
		abstract double calcArea();
		abstract void display();
	}
	
	static class Rect implements Shape{
		
		
		private double length;
		private double height;
		
		Rect(double length, double height){
			this.length = length;
			this.height = height;
		}
		
		@Override
		public double calcArea() {
			return length * height;
		}

		@Override
		public void display() {
			System.out.println("A Rectangle!");
			
		}
		
	}
	
	static class Circle implements Shape{
		
		private double radius;
		
		Circle(double radius){
			this.radius	= radius;
		}
		
		@Override
		public double calcArea() {
			return radius * radius * Math.PI;
		}

		@Override
		public void display() {
			System.out.println("A Circle!");
		}
		
	}
	
	static class Triangle implements Shape{
		
		private double length;
		private double height;
		
		Triangle(double length, double height){
			this.length = length;
			this.height = height;
		}
		
		@Override
		public double calcArea() {
			return length * height / 2;
		}

		@Override
		public void display() {
			System.out.println("A Triangle!");
		}
		
	}
	
	public static void main(String[] args){
		Shape square = new Rect(3, 3);
		Triangle tri = new Triangle(3, 2);
		Circle circle = new Circle(3);
		
		square.display();
		System.out.println(square.calcArea());
		
		tri.display();
		System.out.println(tri.calcArea());
		
		circle.display();
		System.out.println(circle.calcArea());
	}

}
