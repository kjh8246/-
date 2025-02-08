package project.Assembler;
import java.io.IOException;

public class Code {
	Parser parser;
	SymbolTable symbolTable;
	int romAddress = 0;	//rom주소
	int variableAddress = 16;	//변수주소
	String binaryCode = "";	
	
	public Code(String filePath) throws IOException {
		parser = new Parser(filePath);
		symbolTable = new SymbolTable();
		firstPass();
		secondPass();
	}
	
	//1패스
	//라인단위로 처음부터 끝까지 훑으면서 코드는 생성하지 않고 기호태이블 구성
	public void firstPass() throws IOException{
		while(parser.hasMoreCommands()) {
			parser.advance();

			if(parser.commandType().equals(parser.A_COMMAND) || parser.commandType().equals(parser.C_COMMAND)){
				romAddress++;
			}
			if(parser.commandType().equals(parser.L_COMMAND)) {
				symbolTable.addEntry(parser.symbol(), romAddress);
			}
		}
	}
	
	//2패스
	public void secondPass() throws IOException{
		parser.reset();	//처음부터 다시 훑기위해 리셋
		
		while(parser.hasMoreCommands()) {
			parser.advance();
			//System.out.println(parser.command);
			if(parser.commandType().equals(parser.A_COMMAND) && parser.isNumberic(parser.symbol())) {	//A명령어 이고 심볼이 숫자인경우
				binaryCode += to16bitBinary(parser.symbol())+"\n";
				//System.out.println(code.to16bitBinary(parser.symbol()));
			}
			else if(parser.commandType().equals(parser.A_COMMAND) && !parser.isNumberic(parser.symbol())){ 	//A명령어 이고 심볼이 숫자가 아닌경우
				if(symbolTable.contains(parser.symbol())) {
					int address = symbolTable.getAddress(parser.symbol());
					binaryCode += to16bitBinary(Integer.toString(address))+"\n";
					//System.out.println(code.to16bitBinary(Integer.toString(address))+" "+address);
				}else {
					symbolTable.addEntry(parser.symbol(), variableAddress);
					binaryCode += to16bitBinary(Integer.toString(variableAddress))+"\n";
					//System.out.println(code.to16bitBinary(Integer.toString(variableAddress)));
					variableAddress++;
				}
			}
			else if(parser.commandType().equals(parser.C_COMMAND)) {	//c명령어
				binaryCode += comp(parser.comp())+dest(parser.dest())+jump(parser.jump())+"\n";
				//System.out.println(code.comp(parser.comp())+code.dest(parser.dest())+code.jump(parser.jump()));
			}
		}
		
		//System.out.println(binaryCode);
	}
	
	
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
