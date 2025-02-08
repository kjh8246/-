package project;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
		Parser parser = new Parser();
		Code code = new Code();
		SymbolTable symbolTable = new SymbolTable();
		
		int romAddress = 0;
		int variableAddress = 16;
		String binaryCode = "";
		
		
		//1패스
		while(parser.hasMoreCommands()) {
			
			if(parser.commandType().equals(parser.A_COMMAND) || parser.commandType().equals(parser.C_COMMAND)){
				romAddress++;
			}
			if(parser.commandType().equals(parser.L_COMMAND)) {
				symbolTable.addEntry(parser.symbol(), romAddress);
			}
			parser.advance();
		}
		parser.reset();
		//2패스
		while(parser.hasMoreCommands()) {
			System.out.println(parser.command);
			if(parser.commandType().equals(parser.A_COMMAND) && parser.isNumberic(parser.symbol())) {	//A명령어 이고 심볼이 숫자인경우
				binaryCode += code.to16bitBinary(parser.symbol())+"\n";
				System.out.println(code.to16bitBinary(parser.symbol()));
			}
			else if(parser.commandType().equals(parser.A_COMMAND) && !parser.isNumberic(parser.symbol())){ 	//A명령어 이고 심볼이 숫자가 아닌경우
				if(symbolTable.contains(parser.symbol())) {
					int address = symbolTable.getAddress(parser.symbol());
					binaryCode += code.to16bitBinary(Integer.toString(address))+"\n";
					System.out.println(code.to16bitBinary(Integer.toString(address))+" "+address);
				}else {
					symbolTable.addEntry(parser.symbol(), variableAddress);
					binaryCode += code.to16bitBinary(Integer.toString(variableAddress))+"\n";
					System.out.println(code.to16bitBinary(Integer.toString(variableAddress)));
					variableAddress++;
				}
			}
			else if(parser.commandType().equals(parser.C_COMMAND)) {
				binaryCode += code.comp(parser.comp())+code.dest(parser.dest())+code.jump(parser.jump())+"\n";
				System.out.println(code.comp(parser.comp())+code.dest(parser.dest())+code.jump(parser.jump()));
			}
			parser.advance();
		}
		System.out.println("================");
		System.out.println(binaryCode);

		
		// 기호없는 버전
		/**
       	while(parser.hasMoreCommands()) {
			//System.out.println(parser.command);
			if(parser.commandType().equals(parser.A_COMMAND)) {
				//System.out.println(code.to16bitBinary(parser.symbol()));
				binaryCode += code.to16bitBinary(parser.symbol())+"\n";

			}
			else if(parser.commandType().equals(parser.C_COMMAND)) {
				System.out.println(code.comp(parser.comp())+code.dest(parser.dest())+code.jump(parser.jump()));
				binaryCode += code.comp(parser.comp())+code.dest(parser.dest())+code.jump(parser.jump())+"\n";
			}
			
			paser.advance();
		}
		System.out.println("================");
		System.out.println(binaryCode);
		*/

		// 1. 파일 객체 생성            
		File saveFile = new File("/workspace/compiler/src/project/Prog.hack");             
		// 2. 파일 존재여부 체크 및 생성            
		if (!saveFile.exists()) {                
			saveFile.createNewFile();           
		}
		
		// 3. Writer 생성            
		FileWriter fw = new FileWriter(saveFile);            
		PrintWriter writer = new PrintWriter(fw);
		writer.write(binaryCode);
		writer.close();
		
    }

}
