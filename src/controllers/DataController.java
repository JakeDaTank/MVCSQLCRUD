package controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import data.*;

@Controller
public class DataController {

	@Autowired
	private AlbumDAO dao;

	@RequestMapping(path = "NewSong.do", method = RequestMethod.POST)
	public ModelAndView newSong(@RequestParam("title") String title,@RequestParam("albumName") String albumName, RedirectAttributes redir) {
		Song song = new Song();
		song.setTitle(title);
		song.setAlbumId(dao.getAlbumIDByTitle(albumName));
		dao.addSong(song);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:NewSong2.do");
		redir.addFlashAttribute("song", song);
		return mv;
	}

	@RequestMapping(path = "NewSong2.do", method = RequestMethod.GET)
	public ModelAndView newSong2(RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		// mv.addObject("song", (Song) redir.getFlashAttributes());
		mv.addObject("album", dao.getSongList());
		mv.setViewName("songInfo.jsp");
		return mv;
	}

	@RequestMapping(path = "ShowSongs.do", method = RequestMethod.GET)
	public ModelAndView showSongs(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("songs", dao.getSongList());
		mv.setViewName("songInfo.jsp");
		session.setAttribute("songs", dao.getSongList());
		dao.rewriteFiles();
		return mv;
	}

	@RequestMapping(path = "RemoveSongTitle.do", method = RequestMethod.POST)
	public ModelAndView removeSongByTitle(@RequestParam("title") String songName, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Song song = null;

		if (songName != null) {
			song = dao.getSongByTitle(songName);
			System.out.println(song);
			dao.removeSongByTitle(songName);
		}
		mv.addObject("album", dao.getSongList());
		mv.addObject("song", song);
		mv.setViewName("removedSong.jsp");
		session.setAttribute("song", song);
		dao.rewriteFiles();
		return mv;
	}

	@RequestMapping(path = "GetSongByName.do", params = "name", method = RequestMethod.GET)
	public ModelAndView getSongByName(@RequestParam("name") String inputSongName, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("songInfo.jsp");
		mv.addObject("album", dao.getSongList());
		mv.addObject("song", dao.getSongByTitle(inputSongName));
		return mv;
	}

	@RequestMapping(path = "GetSongByArtistName.do", params = "name", method = RequestMethod.GET)
	public ModelAndView getSongByArtistName(@RequestParam("name") String inputSongArtistName, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("name", inputSongArtistName);
		mv.addObject("songs", dao.getSongsByArtistTitle(inputSongArtistName));
		mv.addObject("album", dao.getSongList());
		mv.setViewName("songInfo.jsp");
		return mv;
	}

}