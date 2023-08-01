import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 
 * @author Mounir Zahidi & Nick Rodriguez
 *
 */

public class Automaton {

	// class fields
	private Rule rule;
	private ArrayList<Generation> generations = new ArrayList <Generation>();
	public char falseSymbol = '0';
	public char trueSymbol = '1';
	
	//Constructor
	
	public Automaton ( int ruleNum, Generation initial) {
		this.rule = new Rule (ruleNum);
		this.generations.add(initial);
	}
	
	public Automaton (String fileName) throws IOException{
		try {
			Scanner file = new Scanner (new File (fileName)); // asks about why u write New File
			
			this.rule = new Rule(file.nextInt());
			file.nextLine();
			
			this.falseSymbol =file.next().charAt(0);
	
			this.trueSymbol = file.next().charAt(0);
			file.nextLine();
			
			String initialCellStates = file.nextLine();
			
			boolean [] cellStates = new boolean [initialCellStates.length()];
			
			for (int i =0; i < initialCellStates.length(); ++i) {
				cellStates[i] = (initialCellStates.charAt(i) == this.trueSymbol);
				
	//			if (initialCellStates.charAt(i) == this.falseSymbol) {
	//				cellStates [i] = false;
	//			}
	//			else if (initialCellStates.charAt(i) == this.trueSymbol) {
	//				cellStates [i] = true;
	//			}
			}
			
			Generation addThis = new Generation (cellStates);
			
			this.generations.add(addThis);
		}
		catch (NoSuchElementException f) {
			f.printStackTrace();
		}
	}
	
	// evolve method
	public int evolve (int numSteps){
		if (numSteps > 0) {
			for (int i =0; i < numSteps; ++i) {
				Generation toAdd = this.rule.evolve(this.getCurrentGeneration());		
				this.generations.add(toAdd);
			}
			return numSteps;
		}
		else {
			return 0;
		}
	}
	
	// getter methods
	
	public Generation getGeneration (int stepNum){
		if (stepNum > this.getTotalSteps()) {
		this.evolve(stepNum - this.getTotalSteps());
		return this.generations.get(stepNum);
		}
		else {
		return this.generations.get(stepNum);
		}
	}
	
	public Generation getCurrentGeneration() {
		return this.generations.get(this.generations.size()-1);
	}
	
	public int getRuleNum () {
		return this.rule.getRuleNum();
	}
	
	public int getTotalSteps () {
		return this.generations.size()-1;
	}
	
	//helper methods
	
	
	public void saveEvolution (String fileName) throws IOException{
			
		FileWriter file = new FileWriter (fileName, false);
	
		file.write(this.toString());
		
		file.close();
	}
	
	@Override
	public String toString (){
		
		String result = "";
		
		result += this.getGeneration(0).getStates(falseSymbol, trueSymbol);
		
		for (int i =1; i < this.generations.size(); ++i) {
			result += System.lineSeparator() + this.getGeneration(i).getStates(falseSymbol, trueSymbol);
		}
		return result;
		
	}
	
}
