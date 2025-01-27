package project;

import java.util.Hashtable;

public class SymbolTable {
	Hashtable<String, Integer> table;

	
	public SymbolTable() {
		table = new Hashtable<>();
		
        initializePredefinedSymbols();
	}
	
	private void initializePredefinedSymbols() {
        for (int i = 0; i < 16; i++) {
            table.put("R" + i, i);
        }

        table.put("SP", 0);
        table.put("LCL", 1);
        table.put("ARG", 2);
        table.put("THIS", 3);
        table.put("THAT", 4);
        table.put("SCREEN", 16384);
        table.put("KBD", 24576);
    }
	
	public void addEntry(String symbol, int address) {
		table.put(symbol, address);
	}
	
	public Boolean contains(String symbol) {
		return table.containsKey(symbol);
	}
	
	public int getAddress(String symbol) {
		return table.get(symbol);
	}
	
	

}