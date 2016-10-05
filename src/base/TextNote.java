package base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TextNote extends Note{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String content;
	
	public TextNote(String title) {
		super(title);
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	/**
	* load a TextNote from File f
	*
	* the tile of the TextNote is the name of the file
	* the content of the TextNote is the content of the file
	*
	* @param File f
	 * @throws Exception 
	*/
	public TextNote(File f) throws Exception {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	
	/**
	* get the content of a file
	*
	* @param absolutePath of the file
	* @return the content of the file
	 * @throws Exception 
	*/
	private String getTextFromFile(String absolutePath) throws Exception {
	String result = "";
	// TODO
		FileInputStream fis = null;
		ObjectInputStream in = null;
		
		fis = new FileInputStream(absolutePath);
		in = new ObjectInputStream(fis);
		result = ((TextNote) in.readObject()).content;
		in.close();
			
	return result;
	}
	
	
	/**
	* export text note to file
	*
	*
	* @param pathFolder path of the folder where to export the note
	* the file has to be named as the title of the note with extension ".txt"
	*
	* if the tile contains white spaces " " they has to be replaced with underscores "_"
	 * @throws IOException 
	*
	*
	*/
	public void exportTextToFile(String pathFolder) throws IOException {
	//TODO
		BufferedWriter writer = null;
		File file = new File( pathFolder + File.separator + this.getTitle().replaceAll(" ", "_") +".txt");
	    writer = new BufferedWriter( new FileWriter( file));
	    writer.write(this.content);
	    writer.close();
	}
}
