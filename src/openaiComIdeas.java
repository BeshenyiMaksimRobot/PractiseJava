import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class openaiComIdeas {
    /*
        Here are some realised from ideas from the bot which uses AI

        Quote:
        There are many different types of programs you could create in Java, depending on your interests and goals. Here are a few ideas to get you started:

        1. A simple "Hello, World!" program, which is a common first program for those learning a new programming language.
	*/
	public static void p1(){
		System.out.println("Hello world!");
	}
	/*
        2. A program that converts temperatures from Fahrenheit to Celsius and vice versa.
	*/
	public static float p2(float celsius){
		return celsius * 1.8f + 32f;
	}
	/*
        3. A program that calculates the area and perimeter of different geometric shapes, such as circles, squares, and triangles.
	*/
	public static void p3(float a){
		System.out.println("Square, P = " + (a * 4) + " , S = " + (a * a));
	}
	public static void p3(float a, float b){
		System.out.println("Rectangle, P = " + ((a + b) * 2) + " , S = " + (a * b));
	}
	public static void p3(float a, float b, float c, int angle1, int angle2, int angle3){
		float per = (a + b + c);
		System.out.println("Rectangle, P = " + per + " , S = " + (Math.sqrt(per * (per - a) * (per - b) * (per - c))));
	}
	public static void p3c(float r){
		System.out.println("Circle, P = " + (r * 2 * Math.PI) + " , S = " + (r * r * Math.PI));
	}
	/*
        4. A program that implements a simple calculator, allowing users to perform basic arithmetic operations.
	*/
	public static void p4(){
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter first number - ");
			float a = Float.parseFloat(reader.readLine());
			System.out.print("Enter second number - ");
			float b = Float.parseFloat(reader.readLine());
			
			System.out.println("Enter type of operation (+-*/) - ");
			switch (reader.readLine()) {
				case "+" -> System.out.println(a + b);
				case "-" -> System.out.println(a - b);
				case "*" -> System.out.println(a * b);
				case "/" -> System.out.println(a / b);
			}
		} catch (IOException e){
			System.out.println("\nSomething went wrong");
		}
		
	}
	/*
        5. A program that generates random quotes from a list of pre-defined quotes and displays them to the user.
	*/
	public static void p5(){
		String[] quotes = new String[]{
				"Spread love everywhere you go. Let no one ever come to you without leaving happier. -Mother Teresa",
				"When you reach the end of your rope, tie a knot in it and hang on. -Franklin D. Roosevelt",
				"Always remember that you are absolutely unique. Just like everyone else. -Margaret Mead",
				"Don't judge each day by the harvest you reap but by the seeds that you plant. -Robert Louis Stevenson",
				"The future belongs to those who believe in the beauty of their dreams. -Eleanor Roosevelt",
				"Tell me and I forget. Teach me and I remember. Involve me and I learn. -Benjamin Franklin",
				"The best and most beautiful things in the world cannot be seen or even touched — they must be felt with the heart. -Helen Keller",
				"It is during our darkest moments that we must focus to see the light. -Aristotle",
				"Whoever is happy will make others happy too. -Anne Frank",
				"Do not go where the path may lead, go instead where there is no path and leave a trail. -Ralph Waldo Emerson",
		};
		Random random = new Random();
		System.out.println(quotes[random.nextInt(quotes.length - 1)]);
	}
	/*
        A program that keeps track of a to-do list and allows users to add, remove, and mark tasks as complete.
	*/
	public static void p6(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		List<ToDoTask> tasks = new ArrayList<>();
		while (true){
			try {
				ToDoTask.getMenu();
				switch (Integer.parseInt(reader.readLine())){
					case 1 -> {
						System.out.print("Write a description: ");
						tasks.add(new ToDoTask(reader.readLine()));
						System.out.println("Task created, time is " + tasks.get(tasks.size() - 1).getCreatedAt());
					}
					case 2 -> {
						System.out.println("Which task do you want to complete?");
						for (int i = 0; i < tasks.size(); i++){
							if (!tasks.get(i).isCompleted()) System.out.println((i + 1) + " - " + tasks.get(i).getDescription());
						}
						int index = Integer.parseInt(reader.readLine()) - 1;
						tasks.get(index).completeTask();
						System.out.println("Task completed, time spended - " + tasks.get(index).getTimeToComplete() + " minutes\n");
					}
					case 3 -> {
						System.out.println("Which task's description do you want to redact?");
						for (int i = 0; i < tasks.size(); i++){
							if (!tasks.get(i).isCompleted()) System.out.println((i + 1) + " - " + tasks.get(i).getDescription());
						}
						int index = Integer.parseInt(reader.readLine());
						System.out.print("Enter new description: ");
						tasks.get(index - 1).setDescription(reader.readLine());
						System.out.println("Description was redacted!\n");
					}
					case 4 -> {
						System.out.println("Here is the task list:");
						for (int i = 0; i < tasks.size(); i++){
							System.out.println((i + 1) + " - " + tasks.get(i).getDescription() + (tasks.get(i).isCompleted()?" (COMPLETED)":""));
						}
						System.out.println();
					}
					default -> System.out.println("Invalid option");
				}
				
			} catch (IOException e) {
				System.out.println("Wrong command");
			}
		}
	}
	/*
        A program that simulates a simple game, such as rock-paper-scissors or tic-tac-toe.
	*/
	public static void p7(){
		while (true)
			try {
				Random random = new Random();
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Enter 1 for scissors, 2 for rock, 3 for paper:");
				int player = Integer.parseInt(reader.readLine());
				int bot = random.nextInt(3) + 1;
				switch (player){
					case 1 -> {
						if (bot == 1) System.out.println("Draw, try again");
						if (bot == 2) System.out.println("You lose, try again...");
						if (bot == 3) System.out.println("You won! Another round?");
					}
					case 2 -> {
						if (bot == 1) System.out.println("You won! Another round?");
						if (bot == 2) System.out.println("Draw, try again");
						if (bot == 3) System.out.println("You lose, try again...");
					}
					case 3 -> {
						if (bot == 1) System.out.println("You lose, try again...");
						if (bot == 2) System.out.println("You won! Another round?");
						if (bot == 3) System.out.println("Draw, try again");
					}
					default -> System.out.println("Invalid option");
				}
			} catch (IOException | NumberFormatException e) {
				System.out.println("Invalid value");
			}
	}
}
class ToDoTask {
	private final LocalDateTime createdAt = LocalDateTime.now();
	private boolean completed = false;
	private LocalDateTime completedAt;
	private String description;
	
	public ToDoTask(String description){
		this.description = description;
	}
	public void completeTask(){
		completedAt = LocalDateTime.now();
		completed = true;
	}
	public static void getMenu(){
		System.out.println("Enter command:\n" +
				"1 - Add a task\n" +
				"2 - Complete a task\n" +
				"3 - Redact a task\n" +
				"4 - Display tasks list");
	}
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public boolean isCompleted(){
		return completed;
	}
	public long getTimeToComplete(){
		return createdAt.until(completedAt, ChronoUnit.MINUTES);
	}
	public LocalDateTime getCreatedAt(){
		return createdAt;
	}
}
