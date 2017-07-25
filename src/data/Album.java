package data;

public class Album {
	
	int id, artistId;
	String title;
	
	public Album() {
		
	}
	public Album(int id, int artistId, String title) {
		super();
		this.id = id;
		this.artistId = artistId;
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getArtistId() {
		return artistId;
	}
	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
