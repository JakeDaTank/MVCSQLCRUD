package data;

public class Song {
	String title, artistTitle;
	public Song() {		
	}
	public Song(String title, String artistTitle){
		this.title = title;
		this.artistTitle = artistTitle;
	}
	public String getArtistTitle() {
		return artistTitle;
	}
	public void setArtistTitle(String artistTitle) {
		this.artistTitle = artistTitle;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
