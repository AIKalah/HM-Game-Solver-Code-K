import java.util.LinkedList;


public class smartAIOption {
	
	private LinkedList<kalahOption> options = new LinkedList<kalahOption>();
	private int score;
	public smartAIOption (LinkedList<kalahOption> options, int score){
		setOptions(options);
		setScore(score);
	}
	
	public LinkedList<kalahOption> getOptions() {
		return options;
	}

	public void setOptions(LinkedList<kalahOption> options) {
		this.options = options;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
