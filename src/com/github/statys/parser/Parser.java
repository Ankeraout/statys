package com.github.statys.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;





public class Parser {

	public static void main(String[] args) {
		String input = "ceci ,est, \\un> test avec beaucoup de mots oui je sais je ne suis pas inventif\n et si tu es pas content c\'est pareil|espèce de \"COLéoPtaire\" à je suis une Un1cod3 c@r je suis.un.gameur";
		System.out.println(input);
		System.out.println("---------------------");
	    // Create tokens and print them
	    ArrayList<Token> tokens = lex(input);
	    for (Token token : tokens)
	      System.out.println(token);
	  }
	
	
	public static ArrayList<Token> lex(String input) {
		 // The tokens to return
	    ArrayList<Token> tokens = new ArrayList<Token>();

	    // Lexer logic begins here
	    StringBuffer tokenPatternsBuffer = new StringBuffer();
	    for (TypeToken TypeToken : TypeToken.values())
	      tokenPatternsBuffer.append(String.format("|(?<%s>%s)", TypeToken.name(), TypeToken.expression));
	    Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

	    // Begin matching tokens
	    Matcher matcher = tokenPatterns.matcher(input);
	    while (matcher.find()) {
	      if (matcher.group(TypeToken.MOTS.name()) != null) {
	        tokens.add(new Token(TypeToken.MOTS, matcher.group(TypeToken.MOTS.name())));
	        continue;
	      } else if (matcher.group(TypeToken.CARACTERES.name()) != null) {
	        tokens.add(new Token(TypeToken.CARACTERES, matcher.group(TypeToken.CARACTERES.name())));
	        continue;
	      } else if (matcher.group(TypeToken.ESPACES.name()) != null)
	        continue;
	    }
	    
	    return tokens;
	  }

}
	