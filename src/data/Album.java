package data;

import java.util.ArrayList;
import java.util.List;

public class Album {
	String bandName, albumName;
	List<Song> listOfSongs;

	public Album(List<Song> listOfSongs, String bandName, String albumName) {
		super();
		this.listOfSongs = listOfSongs;
		this.bandName = bandName;
		this.albumName = albumName;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public void addSongToAlbum(Song song){
		if(this.listOfSongs == null){
			this.listOfSongs = new ArrayList<Song>();
			this.listOfSongs.add(song);
		}
		else{
			this.listOfSongs.add(song);
		}
	}
	public String getBandName() {
		return bandName;
	}
	
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public Album() {
	}

	public List<Song> getListOfSongs() {
		return listOfSongs;
	}

	public void setListOfSongs(List<Song> listOfSongs) {
		this.listOfSongs = listOfSongs;
	}
	


}
