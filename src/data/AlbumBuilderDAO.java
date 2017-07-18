package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

public class AlbumBuilderDAO implements AlbumDAO {

	private List<Song> songList = new ArrayList<Song>();
	String orderFile = "/WEB-INF/music.csv";

	@Autowired
	private WebApplicationContext context;
	
	private ServletContext sc;

	@PostConstruct
	public void init() {

//	}
//
//	@PostConstruct
//	public void reader() {
		//InputStream is = context.getServletContext().getResourceAsStream(orderFile);
		InputStream is = sc.getResourceAsStream(orderFile);
		BufferedReader bufIn = null;
		try (BufferedReader buf = new BufferedReader(new InputStreamReader(is))) {
			String line;

			while ((line = buf.readLine()) != null) {
				String[] infoArr = line.split(", ");
				String songName = infoArr[0];
				String artistName = infoArr[1];
				Song song = new Song(songName, artistName);
				songList.add(song);
			}


		} catch (IOException e) {
			System.err.println(e);
		} finally {
			if (bufIn != null) {
				try {
					bufIn.close();
				} catch (IOException e) {
					System.err.println(e);
				}
			}
		}
	}

	

	@Override
    public void rewriteFiles() {
		String path = context.getServletContext().getRealPath("/WEB-INF/music.csv");
        try {
            PrintWriter writer = new PrintWriter(path, "UTF-8");
            for (int i = 0; i < songList.size(); i++) {
                writer.println(songList.get(i).getArtistTitle() + ", " + songList.get(i).getTitle());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

	public void setSongList(List<Song> songList) {
		this.songList = songList;
	}

	@Override
	public void addSong(Song song) {
		songList.add(song);
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
	public Song getSongByArtistTitle(String artistTitle) {
		Song s = null;
		for (Song song : songList) {
			if (song.getArtistTitle().equalsIgnoreCase(artistTitle)) {
				s = song;
			}
		}
		return s;
	}

	@Override
	public List<Song> getSongList() {
		return songList;
	}

	@Override
	public void removeSong(Song song) {
		songList.remove(song);
	}
}
