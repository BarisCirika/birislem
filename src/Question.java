
public class Question {

	private int number1;
	private int number2;
	private int number3;
	private int number4;
	private int number5;
	private int number6;
	
	private int findIT;
	
	public Question(int number1,int number2,int number3,int number4,int number5,int number6,int findIT){
		this.number1=number1;
		this.number2=number2;
		this.number3=number3;
		this.number4=number4;
		this.number5=number5;
		this.number6=number6;
		this.findIT=findIT;
	}
	
	public int getNumber1() {
		return number1;
	}

	public int getNumber2() {
		return number2;
	}

	public int getNumber3() {
		return number3;
	}

	public int getNumber4() {
		return number4;
	}

	public int getNumber5() {
		return number5;
	}

	public int getNumber6() {
		return number6;
	}

	public int getFindIT() {
		return findIT;
	}
	
	public String parseForNetworking(){
		StringBuilder questions = new StringBuilder();
		questions.append(number1);
		questions.append(",");
		questions.append(number2);
		questions.append(",");
		questions.append(number3);
		questions.append(",");
		questions.append(number4);
		questions.append(",");
		questions.append(number5);
		questions.append(",");
		questions.append(number6);
		questions.append(",");
		questions.append(findIT);
		
		return questions.toString();
	}
}
