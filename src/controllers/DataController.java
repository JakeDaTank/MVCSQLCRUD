package controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.*;

@Controller
public class DataController {

	@Autowired
	private AlbumDAO dao;

	@RequestMapping(path="NewSong.do",
			method=RequestMethod.POST)
	public ModelAndView newSong(Song song, HttpSession session) {
		dao.addSong(song);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("songInfo.jsp");
		mv.addObject("song", song);
		mv.addObject("album", dao.getSongList());
		session.setAttribute("song", song);
		dao.rewriteFiles();
		return mv;
	}
	@RequestMapping(path="RemoveSong.do",
			method=RequestMethod.POST)
	public ModelAndView removeSong(Song song, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("song", song);
		dao.removeSong(song);
		mv.addObject("album",(Album) dao.getSongList());
		mv.setViewName("removedSong.jsp");
		session.setAttribute("song", song);
		dao.rewriteFiles();
		return mv;
	}
	
	@RequestMapping(path="GetSongByName.do",
			params="name",
			method=RequestMethod.GET)
	public ModelAndView getSongByName(@RequestParam("name") String inputSongName, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("song", dao.getSongByTitle(inputSongName));						
		mv.addObject("album", (Album) dao.getSongList());
		
		
		
		return mv;
	}
	@RequestMapping(path="GetSongByArtistName.do",
			params="name",
			method=RequestMethod.GET)
	public ModelAndView getSongByArtistName(@RequestParam("name") String inputSongArtistName, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("song", dao.getSongByArtistTitle(inputSongArtistName));
		mv.addObject("album", (Album) dao.getSongList());
		mv.setViewName("songInfo.jsp");			
		
		
		return mv;
	}
//	@RequestMapping(path="GetSong.do",
//			params="name",
//			method=RequestMethod.GET)
//	public ModelAndView getSong(@RequestParam("name") String inputSongName, HttpSession session) {
//		List<Song> tempList = dao.getSongList();
//		ModelAndView mv = new ModelAndView();
//		if (session.getAttribute("song") == null) {
//			mv.setViewName("songInfo.jsp");
//			return mv;
//		}
//		for (Song song : tempList) {
//			if (song.getTitle().equalsIgnoreCase(inputSongName)) {
//				mv.addObject("song", song);
//				mv.setViewName("songInfo.jsp");
//			}			
//		}
//		return mv;
//	}
	
}