package data;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.Statement;

@Component
public class AlbumDBImpl implements AlbumDAO {
	private static String url = "jdbc:mysql://localhost:3306/musicuserdb";
	private String user = "musicuser";
	private String pass = "musicuser";
	public List<Artist> artistList = new ArrayList<>();
	public List<Album> albumList = new ArrayList<>();
	public List<Song> songList = new ArrayList<>();

	@PostConstruct
	public void init() {
		try {
			// creating a list of Artists
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "select id, aa.Album_id ,first_name, last_name from Artist a join album_has_artist aa ON Artist_id = a.id;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// Artist(int id, int albumId, String firstName, String lastName)
				Artist a1 = new Artist(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
				artistList.add(a1);

			}
			if (artistList.size() == 0) {
				artistList = null;
			}
			// Creating list of Albums
			Connection conn1 = DriverManager.getConnection(url, user, pass);
			String sql1 = "Select a.id, aa.Artist_id, a.title a from Album a join album_has_artist aa ON Album_id = a.id";
			PreparedStatement stmt1 = conn1.prepareStatement(sql1);
			ResultSet rs1 = stmt1.executeQuery();
			while (rs1.next()) {
				// Album(id, artistId, title)
				Album alb1 = new Album(rs1.getInt(1), rs1.getInt(2), rs1.getString(3));
				albumList.add(alb1);

			}
			if (albumList.size() == 0) {
				albumList = null;
			}
			Connection conn2 = DriverManager.getConnection(url, user, pass);
			String sql2 = "Select s.id, a.id , s.title FROM Song s Join Album a ON s.album_id = a.id";
			PreparedStatement stmt2 = conn2.prepareStatement(sql2);
			ResultSet rs2 = stmt2.executeQuery();
			while (rs2.next()) {
				// Song(id, albumId, title)

				Song s1 = new Song(rs2.getInt(1), rs2.getInt(2), rs2.getString(3));
				songList.add(s1);
			}
			if (songList.size() == 0) {
				songList = null;
			}
			rs.close();
			stmt.close();
			conn.close();
			rs1.close();
			stmt1.close();
			conn1.close();
			rs2.close();
			stmt2.close();
			conn2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public int getAlbumIDByTitle(String name) {
		Album a = null;
		for (Album album : albumList) {
			if (album.getTitle().equalsIgnoreCase(name)) {
				a = album;
			}
			else {
				Album newalb = new Album();
				newalb.setTitle(name);
				return newalb.getId();
			}
		}
		return a.getId();
	}

	@Override
	public Song getSongByTitle(String name) {
		Song s = null;
		for (Song song : songList) {
			if (song.getTitle().equalsIgnoreCase(name)) {
				s = song;
			}
		}
		return s;
	}

	@Override
	public List<Song> getSongsByArtistTitle(String artistTitle) {
		List<Song> localList = new ArrayList<>();
		try {
			Connection conn2 = DriverManager.getConnection(url, user, pass);
			String sql2 = "select s.id, s.album_id, s.title From song s \n" + "	Join Album a ON s.album_id = a.id\n"
					+ "    Join album_has_artist aha ON a.id = aha.Album_id\n"
					+ "    Join Artist art ON aha.Artist_id = art.id\n" + "		Where art.first_name LIKE '?'";
			PreparedStatement stmt2 = conn2.prepareStatement(sql2);
			stmt2.setString(1, "%".concat(artistTitle).concat("%"));
			ResultSet rs2 = stmt2.executeQuery();
			while (rs2.next()) {
				// Song(id, albumId, title)

				Song s1 = new Song(rs2.getInt(1), rs2.getInt(2), rs2.getString(3));
				localList.add(s1);
			}
			if (localList.size() == 0) {
				localList = null;
			}
			rs2.close();
			stmt2.close();
			conn2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return localList;
	}

	@Override
	public void addSong(Song song) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			System.out.println("test");
			String sql = "INSERT INTO Song (title) VALUES(?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, song.getTitle());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newSongID = keys.getInt(1);
					song.setId(newSongID);;
				}
			}
			songList.add(song);
			conn.commit(); // COMMIT TRANSACTION
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Song> getSongList() {
		return songList;
	}

	@Override
	public void rewriteFiles() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeSong(Song song) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "DELETE FROM song WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, song.getId());
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error deleting song");
		}
	}

	@Override
	public void removeSongByTitle(String songTitle) {	
		for (Song song : songList) {
			if (song.getTitle().equals(songTitle)) {
				removeSong(song);
			}
		}

	}

}
