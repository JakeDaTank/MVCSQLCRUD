package data;

public class Artist {
	int id, albumId;
	String firstName, lastName;
	
	public Artist() {
		
	}
	
	public Artist(int id, int albumId, String firstName, String lastName) {
		super();
		this.id = id;
		this.albumId = albumId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
