package code_optim;

import java.util.regex.Pattern;

public class TNSDLspecificPatterns {
	
	public Pattern INPUT;
	public Pattern PROCEDURE;
	public Pattern ENDPROCEDURE;
	public Pattern DECISION;
	public Pattern ENDDECISION;
	public Pattern DECISIONbranch;
	public Pattern ELSE;
	public TNSDLspecificPatterns() 
	{
		super();
		this.INPUT = Pattern.compile("^\\s*(INPUT\\s+(INTERNAL\\s+)?\\w+).*");
		this.PROCEDURE = Pattern.compile("^\\s*(PROCEDURE\\s*..*?)\\b.*");
		this.ENDPROCEDURE = Pattern.compile("^\\s*(ENDPROCEDURE.*;).*");
		this.DECISION = Pattern.compile("^\\s*((DECISION|WHILE).*)");
		this.ENDDECISION = Pattern.compile("^\\s*((ENDDECISION|ENDWHILE).*;).*");
		this.DECISIONbranch = Pattern.compile("^\\s*(\\(.*\\)\\s*:).*");
		this.ELSE = Pattern.compile("^\\s*(ELSE\\s*:).*");
		
	}
	
	
	
}
