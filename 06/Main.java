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
		Paser paser = new Paser();
		Code code = new Code();
		SymbolTable symbolTable = new SymbolTable();
		
		int romAddress = 0;
		int variableAddress = 16;
		String binaryCode = "";
		
		
		//1패스
		while(paser.hasMoreCommands()) {
			
			if(paser.commandType().equals(paser.A_COMMAND) || paser.commandType().equals(paser.C_COMMAND)){
				romAddress++;
			}
			if(paser.commandType().equals(paser.L_COMMAND)) {
				symbolTable.addEntry(paser.symbol(), romAddress);
			}
			paser.advance();
		}
		paser.reset();
		//2패스
		while(paser.hasMoreCommands()) {
			System.out.println(paser.command);
			if(paser.commandType().equals(paser.A_COMMAND) && paser.isNumberic(paser.symbol())) {	//A명령어 이고 심볼이 숫자인경우
				binaryCode += code.to16bitBinary(paser.symbol())+"\n";
				System.out.println(code.to16bitBinary(paser.symbol()));
			}
			else if(paser.commandType().equals(paser.A_COMMAND) && !paser.isNumberic(paser.symbol())){ 	//A명령어 이고 심볼이 숫자가 아닌경우
				if(symbolTable.contains(paser.symbol())) {
					int address = symbolTable.getAddress(paser.symbol());
					binaryCode += code.to16bitBinary(Integer.toString(address))+"\n";
					System.out.println(code.to16bitBinary(Integer.toString(address))+" "+address);
				}else {
					symbolTable.addEntry(paser.symbol(), variableAddress);
					binaryCode += code.to16bitBinary(Integer.toString(variableAddress))+"\n";
					System.out.println(code.to16bitBinary(Integer.toString(variableAddress)));
					variableAddress++;
				}
			}
			else if(paser.commandType().equals(paser.C_COMMAND)) {
				binaryCode += code.comp(paser.comp())+code.dest(paser.dest())+code.jump(paser.jump())+"\n";
				System.out.println(code.comp(paser.comp())+code.dest(paser.dest())+code.jump(paser.jump()));
			}
			paser.advance();
		}
		System.out.println("================");
		System.out.println(binaryCode);
		

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
