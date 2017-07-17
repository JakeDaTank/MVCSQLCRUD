package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;


public class AlbumBuilderDAO {
	
	private List<Album> albumList = new ArrayList<Album>();
	
	@Autowired 
	private WebApplicationContext context;



	public List<Album> getAlbumList() {
		return albumList;
	}



	public void setAlbumList(List<Album> albumList) {
		this.albumList = albumList;
	}



	@PostConstruct
	public void reader() {	
		BufferedReader bufIn = null;
		InputStream is = context.getServletContext().getResourceAsStream("/WEB-INF/music.csv");
		try (BufferedReader buf = new BufferedReader(new InputStreamReader(is))) {
			String line;


			while ((line = buf.readLine()) != null) {
				String[] infoArr = line.split(", ");
				String bandName = infoArr[0];
				String albumName = infoArr[1];
				List<Song> listOfSongs = new ArrayList<Song>();
				for (int i = 2; i < infoArr.length; i++) {
					Song song1 = new Song(infoArr[i]);
					listOfSongs.add(song1);
				}
				
				Album newAlbum = new Album(listOfSongs, bandName, albumName);
				albumList.add(newAlbum);
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
}
