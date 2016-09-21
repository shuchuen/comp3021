package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>{
	private ArrayList<Note> notes;
	private String name;

	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	public void addNote(Note note){
		notes.add(note);
	}
	public String getName(){
		return this.name;
	}
	
	public ArrayList<Note> getNotes() {
		return notes;
	}
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
//	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for(Note note: notes){
			if(note instanceof TextNote){
				nText++;
			}
			
			if(note instanceof ImageNote){
				nImage++;
			}
		}
		
		return name + ":" + nText + ":" + nImage;
	}
	
	@Override
	public int compareTo(Folder o) {
		return this.getName().compareTo(o.getName());
	}
	
	public void sortNotes(){
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> result = new ArrayList<Note>();
		String[] keyArray = keywords.toLowerCase().split(" ");
		
		for(Note note : notes){
			boolean temp = search(note, 0, keyArray);
			if(temp){
				result.add(note);
			}
		}
		
		return result;
	}
	
	private boolean search(Note note, int pos, String[] keys){
		
		if(pos+1==keys.length){
			return isExist(note, keys[pos]);
		}
		
		if(keys[pos+1].equals("or")){
			return isExist(note, keys[pos])|| search(note, pos+2, keys);
		}else{
			return isExist(note, keys[pos])&& search(note, pos+1, keys);
		}
		
	}
	
	private boolean isExist(Note note, String keyword){
		
		if(note instanceof ImageNote){
			if(note.getTitle().toLowerCase().contains(keyword)){
				return true;
			}
		}
		
		if(note instanceof TextNote){
			if(note.getTitle().toLowerCase().contains(keyword)){
				return true;
			}
			
			if(((TextNote)note).content.toLowerCase().contains(keyword)){
				return true;
			}
		}
		
		return false;
		
	}
	
	
}