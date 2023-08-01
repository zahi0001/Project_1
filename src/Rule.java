import java.util.Arrays;
/**
 * 
 * @author Mounir Zahidi & Nick Rodriguez
 *
 */

public class Rule {
	
	// private fields
	private int ruleNum;
	private boolean [][] wolframCode= {
			{false,false,false},
			{false,false, true},
			{false,true,false},
			{false,true,true},
			{true,false,false},
			{true,false,true},
			{true,true,false},
			{true,true,true}
	};
	
	
	// constructors DONE
	public Rule (int ruleNum ) {
		if (ruleNum > 255) {
			this.ruleNum = 255;
		}
		else if (ruleNum < 0) {
			this.ruleNum = 0;
		}
		else {
			this.ruleNum = ruleNum;
		}
		
		}
	
	//Getters
	public int getRuleNum() {
		
		return this.ruleNum;
	}
	
	public static boolean [] getNeighborhood (int index, Generation gen) {
		
		boolean [] neighborhood = new boolean [3];
		
		if (gen.size() == 0) {
			return neighborhood;
		}
		
		// (-1 + 3) % 3 = 2
		//  0 % 3 =  0
		//  1 % 3 =  1
		//  2 % 3 =  2
		//  3 % 3 =  0
		
		neighborhood [0] = gen.getState((index - 1 + gen.size()) % gen.size());
		neighborhood [1] = gen.getState(index);
		neighborhood [2] = gen.getState((index + 1) % gen.size());
		
		return neighborhood;
		
//		else if (gen.size() == 1) {
//			neighborhood [0] = gen.getState(0);
//			neighborhood [1] = gen.getState(0);
//			neighborhood [2] = gen.getState(0);
//		}
//		
//		else if (index == 0) {
//			neighborhood [0] = gen.getState(gen.size() -1);
//			neighborhood [1] = gen.getState(0);
//			neighborhood [2] = gen.getState(1);
//		}
//		else if (index == gen.size() -1) {
//			neighborhood [0] = gen.getState(gen.size() -2);
//			neighborhood [1] = gen.getState(gen.size() -1);;
//			neighborhood [2] = gen.getState(0);
//		}
//		else {
//			neighborhood [0] = gen.getState(index -1);
//			neighborhood [1] = gen.getState(index);
//			neighborhood [2] = gen.getState(index + 1);
//		}
	}
	
	//evolve methods
	
	public boolean evolve (boolean [] neighborhood) {
		int indexInWolfram =-1;
		
		for (int i=0; i < 8; ++i) {
			if (Arrays.compare(neighborhood, wolframCode [i]) == 0) {
				indexInWolfram = i;
				break;
			}
		}
		//apply the rule of the neighborhood array
		int [] rule = ruleIntArray();
		// return the middle cell state a.k.a the value at the index of rule array 
		return (rule [indexInWolfram] == 1);
		
		  
	}
	
	public Generation evolve (Generation gen) {
		boolean [] statesCopy = gen.getStates();
		
		for (int i =0; i < gen.size(); ++i) {
			boolean [] neighborhood = getNeighborhood(i, gen);
			statesCopy [i] = evolve (neighborhood);
		}
		
		Generation result = new Generation (statesCopy);
		
		return result;
	}
	
	
	// 
	public int [] ruleIntArray () {
		
		int [] result = new int [8];
		String ruleAsString = String.format("%8s", Integer.toBinaryString(this.ruleNum)).replace(' ', '0');
		
		for (int i =0; i < result.length; ++i) {
			result [i] = Integer.parseInt(String.valueOf(ruleAsString.charAt(result.length-1 -i)));
			
		}
		return result;
	}
	
}
