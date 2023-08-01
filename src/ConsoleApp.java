import java.util.Scanner;

/**
 * 
 * @author Mounir Zahidi
 *
 */
public class ConsoleApp {

	/*
	 * NOTE: You do not need to modify any lines at or before 
	 * the printOptions() method.
	 */
	
	private static final Generation DEFAULT_GENERATION = new Generation(false, false, false, true, false, false, false);
	private static final int DEFAULT_RULE = 22;

	private Automaton automaton;
	private Scanner input;

	public ConsoleApp() {
		automaton = new Automaton(DEFAULT_RULE, DEFAULT_GENERATION);
		input = new Scanner(System.in);
	}

	public void run() {
		Option option; // store user's input Option

		do {
			// Show possible options
			printOptions();

			// Get user input
			System.out.print("Select option > ");

			try {
				int value = input.nextInt();
				option = Option.fromInt(value);
			} catch (Exception e) {
				// Quit on an invalid input (e.g., lack of input)
				option = Option.QUIT;
			}

			// Process user input
			processOption(option);

			// Print a blank line between each action
			System.out.println();
		} while (option != Option.QUIT);
	}

	private void processOption(Option option) {
		switch (option) {
			case PRINT_RULE:
				printRule();
				break;
			case REINIT_AUTOMATON:
				reinitAutomaton();
				break;
			case EVOLVE:
				evolve();
				break;
			case SET_TRUE_SYMBOL:
				setTrueSymbol();
				break;
			case SET_FALSE_SYMBOL:
				setFalseSymbol();
				break;
			case PRINT_CURRENT_GENERATION:
				printCurrentGeneration();
				break;
			case PRINT_FULL_EVOLUTION:
				printFullEvolution();
				break;
			case QUIT:
				printQuitMessage();
				break;
		}
	}

	private static void printOptions() {
		Option[] options = Option.values();
		for (Option o : options) {
			System.out.println(o.index() + ": " + o);
		}
	}

	/* You will need to implement the methods below. */

	private void printRule() {
		// TODO: Print out the current rule in the format specified by
		// the README.
		System.out.print("Rule: " + this.automaton.getRuleNum());
	}

	private void reinitAutomaton() {
		// This method's implementation is provided for you.
		setTrueSymbol();
		setFalseSymbol();
		setRuleAndGeneration();
	}

	private void setRuleAndGeneration() {
		// TODO: Prompt the user to enter a new rule number and initial
		// generation. Refer to the README for details.
		// read the rule input
		System.out.print("Enter new rule number > ");
		int ruleNumInput = input.nextInt();
		Rule rule = new Rule(ruleNumInput);
		
		//read the input generation
		System.out.print("Enter initial generation > ");
		String initial = input.next();
		
		// print the updates rule prompt
		System.out.println("Rule number updated to " + rule.getRuleNum());
		
		// print the generation 0
		System.out.println("Generation 0:");
		Generation toPrint = new Generation (initial, this.automaton.trueSymbol);
		this.automaton = new Automaton (rule.getRuleNum(), toPrint);
		System.out.println(initial);
	}

	private void setTrueSymbol() {
		// TODO: Prompts the user to enter a new true symbol representation.
		
		//print the current true Symbol
		System.out.print("Current true symbol: ");
		System.out.println(this.automaton.trueSymbol);
		
		//print the prompt and take input from the user
		System.out.print("New true symbol > ");
		char symbol = input.next().charAt(0);
		this.automaton.trueSymbol = symbol;
	}

	private void setFalseSymbol() {
		// TODO: Prompts the user to enter a new false symbol representation.
		
		//print the current false Symbol
		System.out.print("Current false symbol: ");
		System.out.println(this.automaton.falseSymbol);
		
		//print the prompt and take input from the user
		System.out.print("New false symbol > ");
		this.automaton.falseSymbol = input.next().charAt(0);
	}

	private void evolve() {
		// TODO: Prompt the user to enter a number of evolutions and evolves
		// the Automaton.
		
		//print the prompt for the user
		System.out.print("Enter number of evolutions > ");
		int numEvolution = input.nextInt();
		
		//print the output results to the user
		System.out.println("Evolved "+ this.automaton.evolve(numEvolution) + " generation(s)");
		
	}

	private void printCurrentGeneration() {
		// TODO: Print the current generation of the Automaton using the
		// Automaton's true and false symbols.
		System.out.println("Generation " + this.automaton.getTotalSteps() +":");
		System.out.println(this.automaton.getCurrentGeneration().getStates(this.automaton.falseSymbol, this.automaton.trueSymbol));
	}

	private void printFullEvolution() {
		// TODO: Print the full evolution of the automaton.
		System.out.println(this.automaton.toString());
	}

	private void printQuitMessage() {
		// TODO: Print "Bye bye!"
		System.out.println("Bye bye!");
	}
}
