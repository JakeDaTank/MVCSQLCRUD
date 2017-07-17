package controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.*;

@Controller
public class DataController {

	@Autowired
	AlbumBuilderDAO dao;

	@RequestMapping("GetAlbum.do")
	public ModelAndView getAlbum(@RequestParam("album") String InputAlbumName, HttpSession session) {
		List<Album> tempList = dao.getAlbumList();
		ModelAndView mv = new ModelAndView();
		for (Album album : tempList) {
			if (album.getAlbumName().equalsIgnoreCase(InputAlbumName.trim())) {
				session.setAttribute("album", album);
				mv.setViewName("albumInfo.jsp");
				mv.addObject("album", album);
			}
		}
		return mv;
	}
//	@RequestMapping("GetSong.do")
//	public ModelAndView getSong(@RequestParam("song") String inputSongName, HttpSession session) {
//		List<Album> tempList = dao.getAlbumList();
//		ModelAndView mv = new ModelAndView();
//		Song song = session.	
//
//				mv.setViewName("songInfo.jsp");
//				mv.addObject("song", song);
//			
//		
//		
//		return mv;
//	}
}