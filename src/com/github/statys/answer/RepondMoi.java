package com.github.statys.answer;

import com.github.statys.parser.*;
import java.util.ArrayList;
import java.util.List;

public class RepondMoi {
	
	List<Token> liste ;
	public static String[]listeMots = {"git","java","c","php","ruby","bash","vb","csh","cpp","html","css","vba","sql","python"};
	
	public RepondMoi(ArrayList<Token> l){
		liste = l; // pour récuper le résultat du parser
	}
	
	
	public String Analyseur(){
		for (Token token : liste) {
			for (String mot : listeMots){
				if (mot.equals(token.texte)){
					return "Je vous mets en relation avec un conseiller en "+mot+"." ;
				}
			}
				
		}
		return "je n'ai pas compris pouvez mieux formuler votre demande";
	}
	
	
}
