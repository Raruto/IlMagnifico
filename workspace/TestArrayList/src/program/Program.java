package program;

import java.util.ArrayList;
import java.util.Iterator;

public class Program {
	public static void main(String args[]) {
		/*
		 * Creation of ArrayList<String>: Array of String elements
		 */
		ArrayList<String> obj = new ArrayList<String>();

		/* Add elements to the Array list */
		obj.add("Ajeet");
		obj.add("Harry");
		obj.add("Chaitanya");
		obj.add("Steve");
		obj.add("Anuj");

		/* Displaying array list elements */
		System.out.println("Array list elements: " + obj);

		/* Add element at the given index */
		obj.add(0, "Rahul");
		obj.add(1, "Justin");

		System.out.println("\nAppend elements: at index 0 (\"Rahul\"), at index 1 (\"Justin\")");
		System.out.println("Array list elements: " + obj);

		/* Remove elements from array */
		obj.remove("Chaitanya");
		obj.remove("Harry");

		System.out.println("\nRemoved elements: \"Chaitanya\", \"Harry\"");
		System.out.println("Array list elements: " + obj);

		/* Remove element from the given index */
		obj.remove(1);

		System.out.println("\nRemoved element: at index 1");
		System.out.println("Array list elements: " + obj);

		ArrayList<String> list = obj;

		// Standard for-loop
		System.out.println("\n#1 normal for loop");
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}

		// foreach-loop
		System.out.println("\n\n#2 advance for loop");
		for (String s : list) {
			System.out.print(s + " ");
		}

		// while-loop
		System.out.println("\n\n#3 while loop");
		int j = 0;
		while (list.size() > j) {
			System.out.print(list.get(j) + " ");
			j++;
		}

		// iterator
		System.out.println("\n\n#4 iterator");
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
	}
}
