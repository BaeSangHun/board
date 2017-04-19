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
	//�Է�ȭ��
	@RequestMapping(value="/board/add", method = RequestMethod.GET)
	public String boardform(){
		System.out.println("form ���� ��Ʈ�ѷ�");
		return "form";
	}
	//�Է�ó��
	@RequestMapping(value="/board/add", method = RequestMethod.POST)
	public String boardform(Board b){
		System.out.println("ȸ������ �Է� ��Ʈ�ѷ�");
		
		dao.insertBoard(b);
		System.out.println("insertBoard �޼��� ȣ�� �� ���� �Ϸ�");
		return "redirect:list";
	}
	
	//����Ʈ
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
	//ȭ��
	@RequestMapping(value="/board/view" ,method = RequestMethod.GET)
	public String boardview(Model model,@RequestParam(value="boardNo",required=false,defaultValue="1")int boardNo){
		Board board = dao.getBoard(boardNo);
		
		model.addAttribute("board", board);
		
		return "/view";
	}
	// ����ó��
	@RequestMapping(value="/board/RemoveAction",method = RequestMethod.POST)
	public String boardRemove(@RequestParam(value="boardNo")int boardNo,@RequestParam(value="boardPw")String boardPw){
		dao.deleteBoard(boardNo, boardPw);
		return "redirect:list";
	}
	//���� ��(����Է�)
	@RequestMapping(value="/board/RemoveForm",method = RequestMethod.GET)
	public String boardRemove(@RequestParam(value="boardNo")int boardNo){
	
		return "/Remove";
	}
	//������
	@RequestMapping(value="/board/BoardModify",method = RequestMethod.GET)
	public String boardModify(Model model,@RequestParam(value="boardNo")int boardNo){
		Board board = dao.getBoard(boardNo);
		model.addAttribute("board", board);
		return "/boardModifyForm";
	}
	//����ó��
	@RequestMapping(value="/board/BoardModifyAction",method = RequestMethod.POST)
	public String boardModify(Board board){
		
		System.out.println(board.getBoard_title()+"<------title");
		dao.updateBoard(board);
		
		return "redirect:view?boardNo="+board.getBoard_no();
	}



}
