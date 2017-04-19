package www.hahaha.hehe.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import www.hahaha.hehe.service.Board;
import www.hahaha.hehe.service.boarddao;

@Controller
public class Cadd {
	@Autowired
	boarddao dao;
	//입력화면
	@RequestMapping(value="/board/add", method = RequestMethod.GET)
	public String boardform(){
		System.out.println("form 실행 컨트롤러");
		return "form";
	}
	//입력처리
	@RequestMapping(value="/board/add", method = RequestMethod.POST)
	public String boardform(Board b){
		System.out.println("회원가입 입력 컨트롤러");
		
		dao.insertBoard(b);
		System.out.println("insertBoard 메서드 호출 후 실행 완료");
		return "redirect:list";
	}
	
	//리스트
	@RequestMapping(value="/board/list", method = RequestMethod.GET)
	public String boardList(Model model,@RequestParam(value="currentPage",required=false,defaultValue="1" )int currentPage){
			int boardCount = dao.getBoardCount();
			int pagePerRow = 10;
			int lastpage = (int)(Math.ceil(boardCount/pagePerRow));
			List<Board> list = dao.getBoardList(currentPage, pagePerRow);
			
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("boardCount",boardCount);
			model.addAttribute("pagePerRow",pagePerRow);
			model.addAttribute("lastpage",lastpage);
			model.addAttribute("list",list);
		return "/list";
	}
	//화면
	@RequestMapping(value="/board/view" ,method = RequestMethod.GET)
	public String boardview(Model model,@RequestParam(value="boardNo",required=false,defaultValue="1")int boardNo){
		Board board = dao.getBoard(boardNo);
		
		model.addAttribute("board", board);
		
		return "/view";
	}
	// 삭제처리
	@RequestMapping(value="/board/RemoveAction",method = RequestMethod.POST)
	public String boardRemove(@RequestParam(value="boardNo")int boardNo,@RequestParam(value="boardPw")String boardPw){
		dao.deleteBoard(boardNo, boardPw);
		return "redirect:list";
	}
	//삭제 폼(비번입력)
	@RequestMapping(value="/board/RemoveForm",method = RequestMethod.GET)
	public String boardRemove(@RequestParam(value="boardNo")int boardNo){
	
		return "/Remove";
	}
	//수정폼
	@RequestMapping(value="/board/BoardModify",method = RequestMethod.GET)
	public String boardModify(Model model,@RequestParam(value="boardNo")int boardNo){
		Board board = dao.getBoard(boardNo);
		model.addAttribute("board", board);
		return "/boardModifyForm";
	}
	//수정처리
	@RequestMapping(value="/board/BoardModifyAction",method = RequestMethod.POST)
	public String boardModify(Board board){
		
		System.out.println(board.getBoard_title()+"<------title");
		dao.updateBoard(board);
		
		return "redirect:view?boardNo="+board.getBoard_no();
	}



}
