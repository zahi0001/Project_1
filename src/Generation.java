import java.util.Arrays;
/**
 * 
 * @author Mounir Zahidi & Nick Rodriguez
 *
 */

public class Generation {
	
	// private fields
	private boolean [] cellStates;
	
	// constructors
	public Generation (boolean ... states) {
		
		if (states == null || states.length == 0) {
			boolean [] r = new boolean [1];
			r [0] = false;
			this.cellStates = Arrays.copyOf(r, r.length);
		}
		
		else {
			this.cellStates = new boolean [states.length];
			
			for (int i =0; i < states.length; ++i) {
				this.cellStates [i] = states [i];
			}
		}
		
	}
	
	public Generation (String states, char trueSymbol) {
		
		if (states == null || states == "") {
			boolean [] r = new boolean [1];
			r [0] = false;
			this.cellStates = Arrays.copyOf(r, r.length);
		}
		
		else {
			this.cellStates = new boolean [states.length()];
			
			for (int i =0; i < states.length (); ++i) {
				this.cellStates [i] = (trueSymbol == states.charAt(i));// upper case and lower case issues possible
			}
		}
	}
	
	// getters
	
	public boolean getState (int index) { // out of bound index problems ??
		return this.cellStates [index];
	}
	
	public boolean [] getStates () {
		return Arrays.copyOf(this.cellStates, this.cellStates.length); // immutable remember
	}
	
	public String getStates (char falseSymbol, char trueSymbol) {
		String result = "";
		
		for (int i =0; i < this.cellStates.length; ++i) {
			if (this.cellStates [i]) {
				result += trueSymbol;
			}
			else {
				result += falseSymbol;
			}
		}
		
		return result;
	}
	
	public int size() {
		return this.cellStates.length;
	}
	
}
