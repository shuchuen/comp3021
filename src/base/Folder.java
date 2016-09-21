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
		ArrayList<String> keyList = new ArrayList<String>();
		
		String temp = "";
		for(int i = 0; i< keyArray.length ; i++){
			if(i+1 < keyArray.length){
				if(keyArray[i+1].equals("or")){
					temp = keyArray[i];
					i++;
				}else if (temp!="" && !keyArray[i+1].equals("or")){
					temp = temp + " " + keyArray[i];
					keyList.add(temp);
					temp = "";
				}else{
					keyList.add(keyArray[i]);
				}
			}else{
				if(keyArray[i-1].equals("or")){
					temp = temp + " " + keyArray[i];
					keyList.add(temp);
					temp = "";
				}else{
					keyList.add(keyArray[i]);
				}
			}
		}
		
		for(Note note : notes){
			boolean flag = true;
			for(String key : keyList){
				boolean br = isExist(note, key);
				if(!br){
					flag = false;
					break;
				}
			}
			
			
			if(flag){
				result.add(note);
			}
		}
		
		return result;
	}
		
	private boolean isExist(Note note, String keyword){
		String[] keys = keyword.split(" ");
		
		for(String temp : keys){
			if(note instanceof ImageNote){
				if(note.getTitle().toLowerCase().contains(temp)){
					return true;
				}
			}
			
			if(note instanceof TextNote){
				if(note.getTitle().toLowerCase().contains(temp)){
					return true;
				}
				
				if(((TextNote)note).content.toLowerCase().contains(temp)){
					return true;
				}
			}
			
		}
		return false;
		
	}
	
	
}