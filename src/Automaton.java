import java.util.ArrayList;

public class Automaton {
	//private members
	
	private Rule rule;
	private ArrayList<Generation> generations;
	public char falseSymbol = '0';
	public char trueSymbol = '1';
	
	// public members
	Automaton(int ruleNum, Generation initial){
		
	}
	
	Automaton (String filename){
		
	}
	
	public int evolve (int numSteps) {
		return 0;
	}
	
	public Generation getGeneration(int stepNum) {
		return null;
	}
	
	public Generation getCurrentGeneration() {
		return null;
	}
	
	public int getRuleNum() {
		return 0;
	}
	
	public int getTotalSteps() {
		return 0;
	}
	
	public void saveEvolution(String filename) {
		
	}
	
	public String toString() {
		return null;
	}
}
