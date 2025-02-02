package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Paser {
	BufferedReader reader;
	String filePath = "/workspace/compiler/src/project/Prog.asm";
	String A_COMMAND = "A_COMMAND";
	String C_COMMAND = "C_COMMAND";
	String L_COMMAND = "L_COMMAND";
	String command;
    
	public Paser() throws IOException{
		this.reader = new BufferedReader(new FileReader(filePath));
		advance();
    }
	
	public void reset() throws IOException{
		this.reader = new BufferedReader(new FileReader(filePath));
		advance();
	}
	
	//명령이 더 잇는지 확인
	public Boolean hasMoreCommands() {
		return command == null ? false : true;
	}
	
	//다음명령 읽기
    public void advance() throws IOException{
		command = this.reader.readLine();
		if(command != null)
			command = command.trim();
	}
	
	//명령어 타입 반환
	public String commandType() {
		//공백
		if(command.length() == 0)
			return "BLANK";
		//주석-꼬롬하다
		if(command.substring(0, 2).equals("//"))
			return "COMMENT";
		//첫 문자가 @이면 A명령어
		if(command.substring(0, 1).equals("@")) 
			return A_COMMAND;
		//명령어에 = ; 기호가 들어가면 C명령어
		if(command.contains("=") || command.contains(";"))
			return C_COMMAND;
		//명령가 () 로 감싸져 있으면 L명령어
		if(command.substring(0, 1).equals("(") && command.substring(command.length()-1, command.length()).equals(")"))
			return L_COMMAND;
		
		return "?_COMMAND";
	} 
	
	//@xxx나 (xxx)에서 xxx를 반환
	public String symbol() {
		if(!(commandType().equals(A_COMMAND) || commandType().equals(L_COMMAND)))
			return "not a A_COMMAND or L_COMMAND";
		
		
		if(commandType().equals(A_COMMAND))
			return command.substring(command.indexOf("@")+1);	//@이후 출력
		
		if(commandType().equals(L_COMMAND))
			return command.substring(command.indexOf("(")+1, command.indexOf(")"));	//( 와 ) 사이 출력
		
		return "?_SYMBOL";
	}
	
	public boolean isNumberic(String str) {
    	try {
    	  Double.parseDouble(str);
      } catch (NumberFormatException e) {
    	  return false;
      }
      return true;
    }
	
	//c명령어의 dest(8종류) 연상기호 반환
	public String dest() {
		if(!((commandType().equals(C_COMMAND))))
			return "not a C_COMMAND";
		
		// = 기호가 없는 경우 null
		int idx = command.indexOf("=");
		if(idx == -1)
			return "null0";
		
		//= 기호 앞에 있음,
		String dest = command.substring(0, idx);	//= 앞부분 추출
		if(dest.equals("M") || 
		   dest.equals("D") || 
		   dest.equals("MD") || 
		   dest.equals("A") || 
		   dest.equals("AM") || 
		   dest.equals("AD") || 
		   dest.equals("AMD"))
			return dest;
		
		return "not a dest symbol";
	}
    
	//c명령어의 comp(28종류) 연상기호 반환
	public String comp() {
		if(!((commandType().equals(C_COMMAND))))
			return "not a C_COMMAND";
		
		//=기호와 ;기호 사이에 있음
		int equalIdx = command.indexOf("="); 
		int semicolonIdx = command.indexOf(";"); 
		
		//=기호와 ;기호 둘다 없는 경우
		if(equalIdx == -1 && semicolonIdx == -1)
			return command;
		//=기호 없는 경우
		if(equalIdx == -1)
			return command.substring(0, semicolonIdx);
		//;기호 없는 경우
		if(semicolonIdx == -1)
			return command.substring(equalIdx+1);
		
		//=기호와 ;기호 둘다 있는 경우
		String comp = command.substring(equalIdx+1, semicolonIdx);
		if(comp.equals("0") ||
		   comp.equals("1") ||
		   comp.equals("-1") ||
		   comp.equals("D") ||
		   comp.equals("A") ||
		   comp.equals("!D") ||
		   comp.equals("!A") ||
		   comp.equals("-D") ||
		   comp.equals("-A") ||
		   comp.equals("D+1") ||
		   comp.equals("A+1") ||
		   comp.equals("D-1") ||
		   comp.equals("A-1") ||
		   comp.equals("D+A") ||
		   comp.equals("D-A") ||
		   comp.equals("A-D") ||
		   comp.equals("D&A") ||
		   comp.equals("D|A") ||
		   comp.equals("M") ||
		   comp.equals("!M") ||
		   comp.equals("-M") ||
		   comp.equals("M+1") ||
		   comp.equals("M-1") ||
		   comp.equals("D+M") ||
		   comp.equals("D-M") ||
		   comp.equals("M-D") ||
		   comp.equals("D&M") ||
		   comp.equals("D|M"))
		return comp;
		
		return "not a comp symbol";
	}
	
	public String jump() {
		if(!((commandType().equals(C_COMMAND))))
			return "not a C_COMMAND";
		
		// ; 기호가 없는 경우 null
		int idx = command.indexOf(";");
		if(idx == -1)
			return "null";
		
		//; 기호 뒤에 있음,
		String jump = command.substring(idx+1);	//; 뒤부분 추출
		if(jump.equals("JGT") || 
		   jump.equals("JEQ") || 
		   jump.equals("JGE") || 
		   jump.equals("JLT") || 
		   jump.equals("JNE") || 
		   jump.equals("JLE") || 
		   jump.equals("JMP"))
			return jump;
		
		return "not a jump symbol";
		
	}
}
