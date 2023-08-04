import java.util.Arrays;

public class Generation {
	//private members
	private boolean [] cellStates;
	
	// public members
	Generation (boolean ... states) {
		boolean [] helper;
		if (states == null || states.length == 0) {
			helper = new boolean[] {false};
		} else {
			helper = new boolean [states.length];
			for (int i=0; i< states.length; ++i){
				helper[i] = states[i];
			}
		}
		cellStates = Arrays.copyOf(helper, helper.length);
	}
	
	Generation (String states, char trueSymbol) {
		boolean [] helper;
		if (states == null || states == ""){
			helper = new boolean[] {false};
		} else {
			helper = new boolean [states.length()];
			for (int i =0; i < states.length(); ++i){
				if (states.charAt(i)== trueSymbol){
					helper[i] = true;
				} else {
					helper[i] = false;
				}
			}
		}
		cellStates = helper;
	}
	
	public boolean getState(int idx) {
		return cellStates[idx];
	}
	
	public boolean [] getStates() {
		return cellStates.clone();
	}
	
	public String getStates(char falseSymbol, char trueSymbol) {
		String result = "";
		for (int i =0; i< cellStates.length; ++i){
			if (cellStates[i]){
				result += trueSymbol;
			} else {
				result += falseSymbol;
			}
		}
		
		return result;
	}
	
	public int size() {
		return cellStates.length;
	}
}
