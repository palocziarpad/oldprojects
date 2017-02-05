
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SnakePic
{
	public Image[]				BIARRAY;
	// static String designPrefix="resources/basic/";
	private static String	designPrefix;
	static{
		designPrefix=getStdDesign();
	}
	
	public static String getDesignPrefix()
	{
		return designPrefix;
	}

	public void setDesignPrefix(String designPrefix)
	{
		SnakePic.designPrefix = designPrefix;
		resetBi();
	}
	public void resetBi(){
		try
		{
			BIARRAY[0] = ImageIO.read(getClass().getResourceAsStream(
					designPrefix + PicFile.EMPTY));
			BIARRAY[1] = ImageIO.read(getClass().getResourceAsStream(
					designPrefix + PicFile.TAILLEFT));
			BIARRAY[2] = ImageIO.read(getClass().getResourceAsStream(
					designPrefix + PicFile.TAILUP));
			BIARRAY[3] = ImageIO.read(getClass().getResourceAsStream(
					designPrefix + PicFile.TAILRIGHT));
			BIARRAY[4] = ImageIO.read(getClass().getResourceAsStream(
					designPrefix + PicFile.TAILDOWN));
			BIARRAY[5] = ImageIO.read(getClass().getResourceAsStream(
					designPrefix + PicFile.HEADUP));
			BIARRAY[6] = ImageIO.read(getClass().getResourceAsStream(
					designPrefix + PicFile.HEADRIGHT));
			BIARRAY[7] = ImageIO.read(getClass().getResourceAsStream(
					designPrefix + PicFile.HEADDOWN));
			BIARRAY[8] = ImageIO.read(getClass().getResourceAsStream(
					designPrefix + PicFile.HEADLEFT));
			BIARRAY[9] = ImageIO.read(getClass().getResourceAsStream(
					designPrefix + PicFile.BODYHOR));
			BIARRAY[10] = ImageIO.read(getClass().getResourceAsStream(
					designPrefix + PicFile.BODYVER));
			BIARRAY[11] = ImageIO.read(getClass().getResourceAsStream(
					designPrefix + PicFile.FOOD));
			BIARRAY[12] = ImageIO.read(getClass().getResourceAsStream(
					designPrefix + PicFile.GAMEBG));
			BIARRAY[13] = ImageIO.read(getClass().getResourceAsStream(
					designPrefix + PicFile.TABLE));
			BIARRAY[14] = ImageIO.read(getClass().getResourceAsStream(
					designPrefix + PicFile.HIGHSCORE));
			
			BIARRAY[15] = ImageIO.read(getClass()
					.getResourceAsStream(designPrefix + PicFile.SLANTRD));
			BIARRAY[16] = ImageIO.read(getClass()
					.getResourceAsStream(designPrefix + PicFile.SLANTLD));
			BIARRAY[17] = ImageIO.read(getClass()
					.getResourceAsStream(designPrefix + PicFile.SLANTLU));
			BIARRAY[18] = ImageIO.read(getClass()
					.getResourceAsStream(designPrefix + PicFile.SLANTRU));
			BIARRAY[19] = ImageIO.read(getClass()
					.getResourceAsStream(designPrefix + PicFile.FOOD2));
			BIARRAY[20] = ImageIO.read(getClass()
					.getResourceAsStream(designPrefix + PicFile.NEWPART));
		} catch (IOException e)
		{
			getClass().getName();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public SnakePic()
	{
		designPrefix	= getStdDesign();
		BIARRAY = new BufferedImage[21];
		
		resetBi();
	}
	public static String getStdDesign(){
		return "resources/mira/";
	}
	/*
	 * public String getDesign() { return designPrefix; } public void
	 * setDesign(String design) { SnakePic.designPrefix = design; }
	 */
	public enum PIC
	{
		EMPTY, TAILLEFT, TAILUP, TAILRIGHT, TAILDOWN, HEADUP, HEADRIGHT, 
		HEADDOWN, HEADLEFT, BODYHOR, BODYVER, FOOD, GAMEBG, TABLE, HIGHSCORE, 
		SLANTRD, SLANTLD, SLANTLU, SLANTRU, FOOD2
	}
	
	public abstract class PicFile
	{
		public static final String	EMPTY			= "ures.png";
		public static final String	HEADRIGHT		= "fejjobb.png";
		public static final String	HEADDOWN		= "fejle.png";
		public static final String	HEADLEFT		= "fejbal.png";
		public static final String	HEADUP			= "fejfel.png";
		public static final String	TAILRIGHT		= "farokjobb.png";
		public static final String	TAILDOWN		= "farokle.png";
		public static final String	TAILLEFT		= "farokbal.png";
		public static final String	TAILUP			= "farokfel.png";
		public static final String	BODYHOR			= "testfugg.png";
		public static final String	BODYVER			= "testvizsint.png";
		public static final String	FOOD			= "kaja.png";
		public static final String	OPENBG			= "openbg.png";
		public static final String	GAMEBG			= "gamebg.png";
		public static final String	TABLE			= "table.png";
		public static final String	HIGHSCORE		= "highscore.png";
		public static final String	SLANTRD			= "ferde1.png";			// rightdown
		public static final String	SLANTLD			= "ferde2.png";			// leftdown
		public static final String	SLANTLU			= "ferde3.png";			// leftup
		public static final String	SLANTRU			= "ferde4.png";			// rightup		
		public static final String	GAMEOVERSTUN	= "gameover_stunned.png";
		public static final String	GAMEOVERBITE	= "gameover_dead.png";
		public static final String	FOOD2			= "kaja2.png";
		public static final String	NEWPART			= "zoldkor.png";
	}
}
