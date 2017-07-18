package data;

import java.util.ArrayList;
import java.util.List;

public class Album {
	List<Song> album = new ArrayList<Song>();

	public List<Song> getAlbum() {
		return album;
	}

	public void setAlbum(List<Song> album) {
		this.album = album;
	}

}
