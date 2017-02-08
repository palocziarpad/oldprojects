


public class Options
{
	private String[] themes;

	public Options()
	{
		super();
		this.themes = new String[4];
		themes[0]="resources/mira/";
		themes[1]="resources/basic/";
		themes[2]="resources/mira2/";
		themes[3]="resources/tentacle/";
	}

	public String[] getThemes()
	{
		return themes;
	}
	
}