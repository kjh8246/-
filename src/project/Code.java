package project;

public class Code {
	
	//dest연상기호 2진수로 변환
	public String dest(String mnemonic) {
		if(mnemonic.equals("null0"))
			return "000";
		if(mnemonic.equals("M"))
			return "001";
		if(mnemonic.equals("D"))
			return "010";
		if(mnemonic.equals("MD"))
			return "011";
		if(mnemonic.equals("A"))
			return "100";
		if(mnemonic.equals("AM"))
			return "101";
		if(mnemonic.equals("AD"))
			return "110";
		if(mnemonic.equals("AMD"))
			return "111";
		
		return "999";
	}
	
	//comp연상기호 2진수로 변환
	public String comp(String mnemonic) {
		String cCommend = "111";
		if(mnemonic.equals("0"))
			return cCommend+"0101010";
		if(mnemonic.equals("1"))
			return cCommend+"0111111";
		if(mnemonic.equals("-1"))
			return cCommend+"0111010";
		if(mnemonic.equals("D"))
			return cCommend+"0001100";
		if(mnemonic.equals("A"))
			return cCommend+"0110000";
		if(mnemonic.equals("!D"))
			return cCommend+"0001101";
		if(mnemonic.equals("!A"))
			return cCommend+"0110001";
		if(mnemonic.equals("-D"))
			return cCommend+"0001111";
		if(mnemonic.equals("-A"))
			return cCommend+"0110011";
		if(mnemonic.equals("D+1"))
			return cCommend+"0011111";
		if(mnemonic.equals("A+1"))
			return cCommend+"0110111";
		if(mnemonic.equals("D-1"))
			return cCommend+"0001110";
		if(mnemonic.equals("A-1"))
			return cCommend+"0110010";
		if(mnemonic.equals("D+A"))
			return cCommend+"0000010";
		if(mnemonic.equals("D-A"))
			return cCommend+"0010011";
		if(mnemonic.equals("A-D"))
			return cCommend+"0000111";
		if(mnemonic.equals("D&A"))
			return cCommend+"0000000";
		if(mnemonic.equals("D|A"))
			return cCommend+"0010101";
		if(mnemonic.equals("M"))
			return cCommend+"1110000";
		if(mnemonic.equals("!M"))
			return cCommend+"1110001";
		if(mnemonic.equals("-M"))
			return cCommend+"1110011";
		if(mnemonic.equals("M+1"))
			return cCommend+"1110111";
		if(mnemonic.equals("M-1"))
			return cCommend+"1110010";
		if(mnemonic.equals("D+M"))
			return cCommend+"1000010";
		if(mnemonic.equals("D-M"))
			return cCommend+"1010011";
		if(mnemonic.equals("M-D"))
			return cCommend+"1000111";
		if(mnemonic.equals("D&M"))
			return cCommend+"1000000";
		if(mnemonic.equals("D|M"))
			return cCommend+"1010101";
		
		return "9999999999";
	}
	
	//jump연상기호 2진수로 변환
	public String jump(String mnemonic) {
		if(mnemonic.equals("null"))
			return "000";
		if(mnemonic.equals("JGT"))
			return "001";
		if(mnemonic.equals("JEQ"))
			return "010";
		if(mnemonic.equals("JGE"))
			return "011";
		if(mnemonic.equals("JLT"))
			return "100";
		if(mnemonic.equals("JNE"))
			return "101";
		if(mnemonic.equals("JLE"))
			return "110";
		if(mnemonic.equals("JMP"))
			return "111";
		
		return "999";
	}
	
	public String to16bitBinary(String strNumber) {
		int number = Integer.parseInt(strNumber);
		String binary = Integer.toBinaryString(number); // 10진수 -> 2진수
		String base = "0000000000000000";
		
		return "0"+base.substring(1,base.length() - binary.length()) + binary;
	}
	
	
}