package com.javalec.spring_pjt_board_controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalec.spring_pjt_board_command.BCommand;
import com.javalec.spring_pjt_board_command.BContentCommand;
import com.javalec.spring_pjt_board_command.BDeleteCommand;
import com.javalec.spring_pjt_board_command.BListCommand;
import com.javalec.spring_pjt_board_command.BModifyCommand;
import com.javalec.spring_pjt_board_command.BReplyCommand;
import com.javalec.spring_pjt_board_command.BReplyViewCommand;
import com.javalec.spring_pjt_board_command.BWriteCommand;
import com.javalec.spring_pjt_board_util.Constant;

@Controller
public class BController {
	
	BCommand command;
	
	public JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}
	
	//리스트 만드는 기능
	@RequestMapping("/list")
	public String list(Model model){
		
		System.out.println("list() 시작");
		command = new BListCommand();
		command.execute(model);

		return "list";
	}
	
	//작성하는 화면
	@RequestMapping("/write_view")
	public String write_view(Model model){
		
		// 작성하는 화면일뿐! 동작이 아님. 그래서 바로 writeview.jsp 화면으로 보냄
		System.out.println("write_view() 시작");
		return "write_view";
		
	}
	
	//작성 기능
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model){
		//form으로 작성한 글을 받아오려고 HttpServletRequest사용

		System.out.println("write() 시작");
		model.addAttribute("request", request);
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	//내용 보기 기능
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model){
		System.out.println("content_view() 시작");
		model.addAttribute("request", request);
		command= new BContentCommand();
		command.execute(model);
		
		return "content_view";
	}
	
	//수정 기능
	@RequestMapping(method = RequestMethod.POST ,value = "/modify")
	public String modify(HttpServletRequest request, Model model){
		System.out.println("modify() 시작");
		model.addAttribute("request",request);
		command = new BModifyCommand();
		command.execute(model);
		return "redirect:list";
	}
	
	//답변 보는 기능
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request , Model model){
		System.out.println("reply_view() 시작");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model); 
		
		return "reply_view";
	}
	
	//답변 작성하는 기능
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model){
		System.out.println("reply() 시작");
		
		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	//삭젝 기능
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model){
		System.out.println("delete() 시작");
		model.addAttribute("request", request);
		
		command = new BDeleteCommand();
		command.execute(model);
		return "redirect:list";
	}
}
