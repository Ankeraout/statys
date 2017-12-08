package com.github.statys.parser;

public enum TypeToken {
	MOTS("[A-Za-z0-9ιθΰω]+"),
	CARACTERES("[(|)|[|]|°|$|£|€|.|,|'|\"|<|>|@|\\|/|;|:|!|?|#|^|*|-|+|=|=|}|{|~|&|\\|]"),
	ESPACES("[ \t\f\r\n]+");
	
	public final String expression;
	private TypeToken(String expre) {
		this.expression = expre ;
	}
}
 