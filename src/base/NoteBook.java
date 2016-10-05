package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Folder> folders;
	
	public NoteBook(){
		folders = new ArrayList<Folder>();
	}
	
	/**
	*
	* Constructor of an object NoteBook from an object serialization on disk
	*
	* @param file, the path of the file for loading the object serialization
	*/
	public NoteBook(String file){
		try{
			// TODO
			FileInputStream fis = null;
			ObjectInputStream in = null;
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			NoteBook n = (NoteBook) in.readObject();
			folders = new ArrayList<Folder>();
			this.folders.addAll(n.getFolders());
		// TODO
			in.close();
		}catch(Exception e){
			System.out.println("File to load the file");
			e.printStackTrace();
		}
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}
	
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
		
	public ArrayList<Folder> getFolders() {
		return this.folders;
	}
	
	private boolean insertNote(String folderName, Note note){
		
		Folder f = null;
		Folder temp = new Folder(folderName);
		
		for (Folder f1 : folders) {
			if(f1.equals(temp)){
				f = f1;
			}
		}
		if (f == null) {
			f = new Folder(folderName);
			this.folders.add(f);
		}
		
		for (Note n : f.getNotes()) {
			if(n.equals(note)){
				System.out.println("Create note " + note.getTitle() 
				+ " under folder " + folderName + " failed");
				return false;
			}
		}
		
		f.addNote(note);
		return true;
		
	}
	
	public void sortFolders(){
		for(Folder folder : folders){
			folder.sortNotes();
		}
		
		Collections.sort(folders);
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> result = new ArrayList<Note>();
		for(Folder folder: folders){
			result.addAll(folder.searchNotes(keywords));
		}
		
		return result;
	}
	
	
	/**
	* method to save the NoteBook instance to file
	*
	* @param file, the path of the file where to save the object serialization
	* @return true if save on file is successful, false otherwise
	*/
	public boolean save(String file){
//		TODO
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
//		TODO
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
			
		} catch (Exception e) {
			return false;
		}
		return true;
		}
}
