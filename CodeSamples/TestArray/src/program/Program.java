package program;

public class Program {
	public static void main(String[] args) {
		String[] s = { "1", "2", "3" };
		String i = s[1];

		System.out.println("PRIMA");

		System.out.println(s[1]);
		System.out.println(i);
		
		s[1]=null;

		System.out.println("DOPO");

		System.out.println(s[1]);
		System.out.println(i);

	}

}
