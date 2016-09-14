package base;

import java.io.File;

public class ImageNote extends Note{

	File image;
	
	public ImageNote(String title) {
		super(title);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImageNote other = (ImageNote) obj;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		return true;
	}

}
