import java.util.Random;
import java.util.Vector;

public class QuestionCreator {

	private Question question = null;
	private Random random = null;
	private Vector<Integer> numbers = new Vector<Integer>();
	private Vector<Integer> numbersTemp = new Vector<Integer>();
	private char[] operations = { '+', '-', '*', '/' };
	private int total = 0;
	private String operationString = "";
	private char operation;

	public QuestionCreator() {

		random = new Random();
		for (int i = 0; i < 6; i++) {
			numbers.add(random.nextInt(9) + 1);
		}
		for (int i = 0; i < numbers.size(); i++) {
			numbersTemp.add(numbers.get(i));
		}
		while (!numbers.isEmpty()) {
			operation = operations[random.nextInt(4)];
			if (operation == '+') {
				total += numbers.get((numbers.size() - 1));
				operationString += numbers.get((numbers.size() - 1)) + "(+)";
			} else if (operation == '-') {
				if (total - numbers.get((numbers.size() - 1)) > 0) {
					total -= numbers.get((numbers.size() - 1));
					operationString += numbers.get((numbers.size() - 1))
							+ "(-)";
				} else
					numbers.add(numbers.size() - 1);
			} else if (operation == '*') {
				total *= numbers.get((numbers.size() - 1));
				operationString += numbers.get((numbers.size() - 1)) + "(*)";
			} else {
				if (total % numbers.get((numbers.size() - 1)) == 0) {
					total /= numbers.get((numbers.size() - 1));
					operationString += numbers.get((numbers.size() - 1))
							+ "(/)";
				} else
					numbers.add(numbers.size() - 1);
			}
			numbers.remove(numbers.size() - 1);
		}
		//System.out.println(operationString + " = " + total);
		question = new Question(numbersTemp.get(0), numbersTemp.get(1),
				numbersTemp.get(2), numbersTemp.get(3), numbersTemp.get(4),
				numbersTemp.get(5), total);

		numbers.clear();
		numbersTemp.clear();
		total = 0;
	}

	public Question getQuestion() {
		return this.question;
	}
}
