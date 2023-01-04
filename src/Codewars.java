import java.util.*;

public class Codewars {
	/*

	 */

	/*
		Numbers that are a power of their sum of digits
		5 kyu
		3 ms
		
		The number 81 has a special property, a certain power of the sum of its digits is equal to 81 (nine squared). Eighty one (81), is the first number in having this property (not considering numbers of one digit). The next one, is 512. Let's see both cases with the details
	
		8 + 1 = 9 and 92 = 81
		
		512 = 5 + 1 + 2 = 8 and 83 = 512
		
		We need to make a function that receives a number as argument n and returns the n-th term of this sequence of numbers.
		
		Examples (input --> output)
		1 --> 81
		
		2 --> 512
		Happy coding!
	 */
	public static long powerSumDigTerm(int n) {
		ArrayList<Long> list = new ArrayList<>();
		for (int i = 7; i < 80; i++){
			for (int j = 2; j < 10; j++){
				if (getSumOfNumbers((long)Math.pow(i, j)) == i) list.add((long)Math.pow(i, j));
			}
		}
		Collections.sort(list);
		return list.get(n - 1);
	}
	public static int getSumOfNumbers(long number){
		int res = 0;
		while (number > 0) {
			res += number % 10;
			number /= 10;
		}
		return res;
	}
	
	/*
		Make a spiral
		3 kyu

		Your task, is to create a NxN spiral with a given size.
		
		For example, spiral with size 5 should look like this:
		
		00000
		....0
		000.0
		0...0
		00000
		and with the size 10:
		
		0000000000
		.........0
		00000000.0
		0......0.0
		0.0000.0.0
		0.0..0.0.0
		0.0....0.0
		0.000000.0
		0........0
		0000000000
		Return value should contain array of arrays, of 0 and 1, with the first row being composed of 1s. For example for given size 5 result should be:
		
		[[1,1,1,1,1],[0,0,0,0,1],[1,1,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
		Because of the edge-cases for tiny spirals, the size will be at least 5.
		
		General rule-of-a-thumb is, that the snake made with '1' cannot touch to itself.
		
		checking:
		
		int[][] spiral = Codewars.spiralize(20);
        for (int[] row: spiral){
            for (int col: row)
                if (col == 0)
                    System.out.print("..");
                else
                    System.out.print("11");
            System.out.println();
        }
		
	 */
	public static int[][] spiralize(int size) {
		int[][] spiral = new int[size][size];
		for (int[] row: spiral) Arrays.fill(row, 0);
		
		for (int i = 0; i <= size/2 + 1; i += 2) {
			for (int j = i>2?i-2:0; j < size - i - 1; j++) {
				spiral[i][j] = 1;
			}
			for (int j = i; j < size - i; j++){
				spiral[j][size - i - 1] = 1;
			}
		}
		
		for (int i = size - 1; i >= size/2 + 1; i -= 2){
			for (int j = size - i; j < i; j++){
				spiral[i][j] = 1;
			}
			for (int j = i; j > size - i + 1; j--){
				spiral[j][size - i - 1] = 1;
			}
		}
		if ((size - 7) % 4 == 0)
			spiral[size/2 + 1][size/2 - 1] = 1;
		
		return spiral;
	}
	
    /*
        Human Readable Time
        5 kyu
        
	    Write a function, which takes a non-negative integer (seconds) as input and returns the time in a human-readable format (HH:MM:SS)
		
		HH = hours, padded to 2 digits, range: 00 - 99
		MM = minutes, padded to 2 digits, range: 00 - 59
		SS = seconds, padded to 2 digits, range: 00 - 59
		The maximum time never exceeds 359999 (99:59:59)
		
		You can find some examples in the test fixtures.
     */
    public static String makeReadable(int seconds) {
		return String.format("%2d:%2d:%2d", seconds/3600,(seconds%3600)/60,seconds%60).replaceAll(" ", "0");
    }
	
	/*
		If you reverse the word "emirp" you will have the word "prime". That idea is related with the purpose of this kata:
		we should select all the primes that when reversed are a different prime (so palindromic primes should be discarded).

		For example: 13, 17 are prime numbers and the reversed respectively are 31, 71 which are also primes, so 13 and 17
		are "emirps". But primes 757, 787, 797 are palindromic primes, meaning that the reversed number is the same as
		the original, so they are not considered as "emirps" and should be discarded.

		The emirps sequence is registered in OEIS as A006567

		Your task
		Create a function that receives one argument n, as an upper limit, and the return the following array:

		[number_of_emirps_below_n, largest_emirp_below_n, sum_of_emirps_below_n]

		Examples
		find_emirp(10)
		[0, 0, 0] ''' no emirps below 10 '''

		find_emirp(50)
		[4, 37, 98] ''' there are 4 emirps below 50: 13, 17, 31, 37; largest = 37; sum = 98 '''

		find_emirp(100)
		[8, 97, 418] ''' there are 8 emirps below 100: 13, 17, 31, 37, 71, 73, 79, 97; largest = 97; sum = 418 '''
		Happy coding!!

		Advise: Do not use a primality test. It will make your code very slow. Create a set of primes using a prime generator
		or a range of primes producer. Remember that search in a set is faster that in a sorted list or array
	 */
	public static long[] findEmirp(long n){
		ArrayList<Long> resList = new ArrayList<>();
		boolean[] isPrimeB = new boolean[(int) (n * 10 + 1)];
		Arrays.fill(isPrimeB,true);
		isPrimeB[1] = false;
		for (int i = 2; (long) i * i < n * 10; i++)
			if (isPrimeB[i])
				for (int j= i * i; j < n * 10; j += i)
					isPrimeB[j] = false;
		
		for (int i = 11; i <= n; i += 2)
			if(isPrimeB[i]){
				resList.add((long) i);
			}
		
		long number_of_emirps_below_n = 0;
		long largest_emirp_below_n = 0;
		long sum_of_emirps_below_n = 0;
		for (Long prime: resList){
			long reversed = reverseLong(prime);
			if (prime != reversed && isPrimeB[(int)reversed]) {
				number_of_emirps_below_n++;
				sum_of_emirps_below_n += prime;
				largest_emirp_below_n = prime;
			}
		}
		return new long[]{
				number_of_emirps_below_n,
				largest_emirp_below_n,
				sum_of_emirps_below_n
		};
	}
	public static long reverseLong(long n){
		long res = 0;
		while (n > 0){
			res = res * 10 + n % 10;
			n = n / 10;
		}
		return res;
	}
	
	/*
		My friend John and I are members of the "Fat to Fit Club (FFC)". John is worried because each month a list with
		the weights of members is published and each month he is the last on the list which means he is the heaviest.

		I am the one who establishes the list so I told him: "Don't worry any more, I will modify the order of the list".
		It was decided to attribute a "weight" to numbers. The weight of a number will be from now on the sum of its digits.

		For example 99 will have "weight" 18, 100 will have "weight" 1 so in the list 100 will come before 99.

		Given a string with the weights of FFC members in normal order can you give this string ordered by "weights" of these numbers?

		Example:
		"56 65 74 100 99 68 86 180 90" ordered by numbers weights becomes:

		"100 180 90 56 65 74 68 86 99"
		When two numbers have the same "weight", let us class them as if they were strings (alphabetical ordering) and not numbers:

		180 is before 90 since, having the same "weight" (9), it comes before as a string.

		All numbers in the list are positive numbers and the list can be empty.

		Notes
		it may happen that the input string have leading, trailing whitespaces and more than a unique whitespace between two consecutive numbers
	 */
	public static String orderWeight(String strng) {
		boolean repeats;
		do {
			repeats = false;
			if (strng.contains("  ")){
				strng = strng.replaceAll("  ", "");
				repeats = true;
			}
		} while (repeats);
		if (strng.charAt(0) == ' '){
			strng = strng.substring(1);
		}
		if (strng.charAt(strng.length()) == ' '){
			strng = strng.substring(0, strng.length() - 1);
		}
		ArrayList<Integer> strint = new ArrayList<>();
		ArrayList<Integer> weight = new ArrayList<>();
		String tempsi = "";
		int tempw = 0;
		for (char ch: strng.toCharArray()){
			if (ch == ' '){
				strint.add(Integer.parseInt(tempsi));
				weight.add(tempw);
				tempsi = "";
				tempw = 0;
			} else {
				tempsi += ch;
				tempw += Integer.parseInt(String.valueOf(ch));
			}
		}
		strint.add(Integer.parseInt(tempsi));
		weight.add(tempw);
		int[][] finalarr = new int[strint.size()][2];
		for (int i = 0; i < strint.size(); i++) {
			finalarr[i][0] = strint.get(i);
			finalarr[i][1] = weight.get(i);
		}
		for (int i = 0; i < finalarr.length; i++) {
			for (int j = 0; j < finalarr.length; j++) {
				if (finalarr[i][1] < finalarr[j][1]){
					int[] temp = finalarr[i];
					finalarr[i] = finalarr[j];
					finalarr[j] = temp;
				}
			}
		}
		do {
			repeats = false;
			for (int i = 1; i < finalarr.length; i++) {
				if (finalarr[i][1] == finalarr[i - 1][1] && String.valueOf(finalarr[i][0]).charAt(0) < String.valueOf(finalarr[i - 1][0]).charAt(0)) {
					int[] temp = finalarr[i];
					finalarr[i] = finalarr[i - 1];
					finalarr[i - 1] = temp;
					repeats = true;
				}
			}
		} while (repeats);
		String res = "";
		for (int[] f: finalarr){
			res += f[0] + " ";
		}
		return res.substring(0, res.length() - 1);
	}
	
	/*
		The maximum sum subarray problem consists in finding the maximum sum of a contiguous subsequence in an array or list of integers:

		Max.sequence(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
		// should be 6: {4, -1, 2, 1}
		Easy case is when the list is made up of only positive numbers and the maximum sum is the sum of the whole array. If the list is made up of only negative numbers, return 0 instead.

		Empty list is considered to have zero greatest sum. Note that the empty list or array is also a valid sublist/subarray.
	 */
	public static int sequence(int[] arr) {
		if (arr.length == 0) return 0;
		int max = 0;
		for (int i = arr.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				int temp = 0;
				for (int k = j; k <= i; k++) {
					temp += arr[k];
				}
				if (temp > max) max = temp;
			}
		}
		return max;
	}
	
	/*
		Coding decimal numbers with factorials is a way of writing out numbers in a base system that depends
		on factorials, rather than powers of numbers.

		In this system, the last digit is always 0 and is in base 0!. The digit before that is either 0 or 1
		and is in base 1!. The digit before that is either 0, 1, or 2 and is in base 2!, etc. More generally,
		the nth-to-last digit is always 0, 1, 2, ..., n and is in base n!.

		Read more about it at: http://en.wikipedia.org/wiki/Factorial_number_system

		Example
		The decimal number 463 is encoded as "341010", because:

		463 = 3×5! + 4×4! + 1×3! + 0×2! + 1×1! + 0×0!

		If we are limited to digits 0..9, the biggest number we can encode is 10!-1 (= 3628799). So we extend
		0..9 with letters A..Z. With these 36 digits we can now encode numbers up to 36!-1 (= 3.72 × 1041)

		Task
		We will need two functions. The first one will receive a decimal number and return a string with the
		factorial representation.

		Note: the input number is at most a long.

		The second one will receive a string with a factorial representation and produce the decimal representation.

		Given numbers will always be positive.
	 */
	public static String dec2FactString(long nb) {
		int maxFactorial = 1;
		String res = "";
		while (nb / factorial(maxFactorial) >= 1){
			maxFactorial++;
		}
		maxFactorial--;
		for (int i = maxFactorial; i > 0; i--){
			maxFactorial = (int) (nb / factorial(i));
			res += Character.toUpperCase(Character.forDigit(maxFactorial, 36));
			nb %= factorial(i);
		}
		return res + "0";
	}
	public static long factString2Dec(String str) {
		char[] c = str.toCharArray();
		long res = 0L;
		int current = c.length - 1;
		for (char cc: c){
			res += Character.getNumericValue(cc) * factorial(current);
			current--;
		}
		return res;
	}
	public static long factorial(int f){
		int res = 1;
		for (int i = 1; i <= f; i++) res *= i;
		return res;
	}
	
	/*
		POTUS thinks he is all alone in the White House on Christmas Eve.

		But is he?

		The White House has a wall-penetrating radar security system that sees everything.

		Kata Task
		Process the radar image.

		Return true if POTUS really is home alone.

		Legend
		# walls
		X POTUS
		o elves
		Notes
		The house corners are square only
		The radar can also see into nearby buildings
		Examples
		ex1
		   o                o        #######
		 ###############             #     #
		 #             #        o    #     #
		 #  X          ###############     #
		 #                                 #
		 ###################################
		All alone --> true

		ex2
		#################
		#     o         #   o
		#          ######        o
		####       #
		   #       ###################
		   #                         #
		   #                  X      #
		   ###########################
	 */
	public static boolean allAlone(char[][] house) {
		boolean wall = false;
		for (char[] c: house){
			for (int i = 0; i < c.length-1; i++){
				if (c[i] == '#' && i < c.length - 1){
					if (c[i+1] == '#') {
						wall = true;
						while (wall && i < c.length - 1){
							i++;
							wall = c[i] == '#';
						}
					}
					else {
						i++;
						while (c[i] != '#' && i < c.length - 1) {
							if (c[i] == 'o') return false;
							i++;
						}
					}
				}
			}
		}
		return true;
	}
	
	/*
		Why would we want to stop to only 50 shades of grey? Let's see to how many we can go.

		Write a function that takes a number n as a parameter and return an array containing n shades of grey
		in hexadecimal code (#aaaaaa for example). The array should be sorted in ascending order starting with
		'#010101', '#020202', etc. (using lower case letters).

		Examples:

		n =  1:    ["#010101"]
		n = 10:    ["#010101", "#020202", "#030303", "#040404", "#050505", "#060606", "#070707", "#080808", "#090909", "#0a0a0a"]
		As a reminder, the grey color is composed by the same number of red, green and blue: #010101, #aeaeae, or #555555.

		Black: #000000 and white: #ffffff are not accepted values.

		When n is negative, just return an empty array. If n is higher than 254, just return an array of 254 elements.
	 */
	static String[] shadesOfGrey(int num) {
		if (num <= 0) return new String[0];
		if (num > 254) num = 254;
		ArrayList<String> list = new ArrayList<>();
		for (int i = 1; i <= num; i++){
			list.add("#" + String.format("%2h%2h%2h", i, i, i));
			list.set(i-1, list.get(i-1).replaceAll(" ", "0"));
			
		}
		return list.toArray(new String[0]);
	}
	
	public static String formatDuration(int seconds) {
		if (seconds == 0) return "now";
		String res = "";
		String[] words = new String[]{" year"," day"," hour"," minute"," second"};
		int[] count = new int[]{0,0,0,0,0};
		int c = 0;
		count[0] = seconds / 31536000;
		seconds =  seconds % 31536000;
		count[1] = seconds / 86400;
		seconds =  seconds % 86400;
		count[2] = seconds / 3600;
		seconds =  seconds % 3600;
		count[3] = seconds / 60;
		count[4] = seconds % 60;
		for (int cc: count) if (cc > 0) c++;
		for (int i = 0; i < count.length; i++){
			if (count[i] != 0){
				res += count[i] + words[i];
				if (count[i] != 1) res += "s";
				if (--c > 1) res += ", ";
				if (c == 1) res += " and ";
			}
		}
		return res;
	}
	
	/*
		If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

		Finish the solution so that it returns the sum of all the multiples of 3 or 5 below the number passed in. Additionally, if
		the number is negative, return 0 (for languages that do have them).

		Note: If the number is a multiple of both 3 and 5, only count it once.
	 */
	public int solution(int number) {
		if (number < 0) return 0; else
		{
			int result = 0;
			for (int i = 3; i < number; i++){
				if (i % 3 == 0 || i % 5 == 0) result += i;
			}
			return result;
		}
	}
	
	/*
		Trolls are attacking your comment section!

		A common way to deal with this situation is to remove all of the vowels from the trolls' comments, neutralizing the threat.

		Your task is to write a function that takes a string and return a new string with all vowels removed.

		For example, the string "This website is for losers LOL!" would become "Ths wbst s fr lsrs LL!".

		Note: for this kata y isn't considered a vowel.
	 */
	public static String disemvowel(String str) {
		String[] r = new String[]{"a","e","u","i","o","A","E","U","I","O"};
		for (String rr: r){
			str = str.replaceAll(rr, "");
		}
		return str;
	}
	
	/*
		Your goal in this kata is to implement a difference function, which subtracts one list from another and returns the result.

		It should remove all values from list a, which are present in list b keeping their order.

		Kata.arrayDiff(new int[] {1, 2}, new int[] {1}) => new int[] {2}
		If a value is present in b, all of its occurrences must be removed from the other:

		Kata.arrayDiff(new int[] {1, 2, 2, 2, 3}, new int[] {2}) => new int[] {1, 3}
	 */
	public static int[] arrayDiff(int[] a, int[] b) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int bb: b) {
			for (int i = 0; i < a.length; i++){
				if (a[i] == bb) a[i] = Integer.MIN_VALUE;
			}
		}
		for (int aa: a){
			if (aa != Integer.MIN_VALUE) list.add(aa);
		}
		int[] d = new int[list.size()];
		for (int i = 0; i < d.length; i++){
			d[i] = list.get(i);
		}
		return d;
	}
	
	/*
		Write a function that accepts an array of 10 integers (between 0 and 9), that returns a string of those numbers in the form of a phone number.

		Example
		Kata.createPhoneNumber(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}) // => returns "(123) 456-7890"
		The returned format must be correct in order to complete this challenge.

		Don't forget the space after the closing parentheses!
	 */
	public static String createPhoneNumber(int[] numbers) {
		return "("+numbers[0]+numbers[1]+numbers[2]+") "+numbers[3]+numbers[4]+numbers[5]+"-"+numbers[6]+numbers[7]+numbers[8]+numbers[9];
	}
	
	/*
		A square of squares
		You like building blocks. You especially like building blocks that are squares. And what you even like
		more, is to arrange them into a square of square building blocks!

		However, sometimes, you can't arrange them into a square. Instead, you end up with an ordinary rectangle!
		Those blasted things! If you just had a way to know, whether you're currently working in vain… Wait!
		That's it! You just have to check if your number of building blocks is a perfect square.

		Task
		Given an integral number, determine if it's a square number:

		In mathematics, a square number or perfect square is an integer that is the square of an integer;
		in other words, it is the product of some integer with itself.

		The tests will always use some integral number, so don't worry about that in dynamic typed languages.

		Examples
		-1  =>  false
		 0  =>  true
		 3  =>  false
		 4  =>  true
		25  =>  true
		26  =>  false
	 */
	public static boolean isSquare(int n) {
		double nn = Math.sqrt(n);
		return Math.round(nn) == nn;
	}
	
	/*
		Count the number of Duplicates
		Write a function that will return the count of distinct case-insensitive alphabetic characters
		and numeric digits that occur more than once in the input string. The input string can be assumed
		to contain only alphabets (both uppercase and lowercase) and numeric digits.

		Example
		"abcde" -> 0 # no characters repeats more than once
		"aabbcde" -> 2 # 'a' and 'b'
		"aabBcde" -> 2 # 'a' occurs twice and 'b' twice (`b` and `B`)
		"indivisibility" -> 1 # 'i' occurs six times
		"Indivisibilities" -> 2 # 'i' occurs seven times and 's' occurs twice
		"aA11" -> 2 # 'a' and '1'
		"ABBA" -> 2 # 'A' and 'B' each occur twice
	 */
	public static int duplicateCount(String text) {
		int[] arr = new int[255];
		int summ = 0;
		text = text.toLowerCase();
		for (char t: text.toCharArray()) {
			arr[t]++;
		}
		for (int a: arr){
			if (a>1) summ++;
		}
		return summ;
	}
	
	/*
		Simple, given a string of words, return the length of the shortest word(s).

		String will never be empty and you do not need to account for different data types.
	 */
	public static int findShort(String s) {
		char[] str = s.toCharArray();
		int k = 0;
		int shortest = Integer.MAX_VALUE;
		for (char a: str){
			if (a != ' ') k++; else {
				if (k < shortest) shortest = k;
				k = 0;
			}
		}
		if (k < shortest) shortest = k;
		return shortest;
	}
	
	/*
		You live in the city of Cartesia where all roads are laid out in a perfect grid.
		You arrived ten minutes too early to an appointment, so you decided to take the
		opportunity to go for a short walk. The city provides its citizens with a Walk
		Generating App on their phones -- everytime you press the button it sends you an
		array of one-letter strings representing directions to walk (eg. ['n', 's', 'w', 'e']).
		You always walk only a single block for each letter (direction) and you know it
		takes you one minute to traverse one city block, so create a function that will
		return true if the walk the app gives you will take you exactly ten minutes (you
		don't want to be early or late!) and will, of course, return you to your starting
		point. Return false otherwise.

		Note: you will always receive a valid array containing a random assortment of direction
		letters ('n', 's', 'e', or 'w' only). It will never give you an empty array (that's not
		a walk, that's standing still!).
	 */
	public static boolean isValid(char[] walk) {
		int n = 0, s = 0, e = 0, w = 0, i = 0;
		for (int step: walk){
			i++;
			switch ((int) step) {
				case 101 -> {
					w++;
					e--;
				}
				case 119 -> {
					e++;
					w--;
				}
				case 110 -> {
					n++;
					s--;
				}
				case 115 -> {
					s++;
					n--;
				}
			}
		}
		return n == 0 && s == 0 && w == 0 && e == 0 && i == 10;
	}
	
	/*
		Very simple, given an integer or a floating-point number, find its opposite.

		Examples:

		1: -1
		14: -14
		-34: 34
	 */
	public static int opposite(int number){
		return -number;
	}
	
	/*
		Complete the square sum function so that it squares each number passed into it and then sums the results together.

		For example, for [1, 2, 2] it should return 9 because 1^2 + 2^2 + 2^2 = 9.
	 */
	public static int squareSum(int[] n) {
		int sum = 0;
		for (int i = 0; i < n.length; i++) {
			sum += n[i]*n[i];
		}
		return sum;
	}
	
	/*
		Description
		We need a function that can transform a string into a number. What ways of achieving this do you know?

		Note: Don't worry, all inputs will be strings, and every string is a perfectly valid representation of an integral number.

		Examples
		"1234" --> 1234
		"605"  --> 605
		"1405" --> 1405
		"-7" --> -7
	 */
	public static int stringToNumber(String str) {
		return Integer.parseInt(str);
	}
	static long miliseconds = 0;
	static TimerTask task = new TimerTask() {
		@Override
		public void run() {
			miliseconds++;
		}
	};
	static Timer timer = new Timer(true);
	static Thread startTimer = new Thread(() -> {
		timer.scheduleAtFixedRate(task, 0, 1);
	});
}
