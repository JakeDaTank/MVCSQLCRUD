package data;

import java.util.List;

public interface AlbumDAO {
	public Song getSongByTitle(String name);
	public List<Song> getSongsByArtistTitle(String artistTitle);
	public void addSong(Song song);
	public List<Song> getSongList();
	public void rewriteFiles();
	public void removeSong(Song song);
	public void removeSongByTitle(String artistName);
	public int getAlbumIDByTitle(String name);
	
	
}
