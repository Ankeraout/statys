package com.github.statys.parser;

public class Token {
	public TypeToken type;
	public String texte;

	public Token(TypeToken type, String txt) {
		this.type = type;
		this.texte = txt;
	}
	@Override
    public String toString() {
      return String.format("(%s %s)", type.name(), texte);
	}
	      
}