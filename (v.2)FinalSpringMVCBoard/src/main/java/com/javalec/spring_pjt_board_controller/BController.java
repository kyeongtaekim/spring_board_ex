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
	
	//����Ʈ ����� ���
	@RequestMapping("/list")
	public String list(Model model){
		
		System.out.println("list() ����");
		command = new BListCommand();
		command.execute(model);

		return "list";
	}
	
	//�ۼ��ϴ� ȭ��
	@RequestMapping("/write_view")
	public String write_view(Model model){
		
		// �ۼ��ϴ� ȭ���ϻ�! ������ �ƴ�. �׷��� �ٷ� writeview.jsp ȭ������ ����
		System.out.println("write_view() ����");
		return "write_view";
		
	}
	
	//�ۼ� ���
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model){
		//form���� �ۼ��� ���� �޾ƿ����� HttpServletRequest���

		System.out.println("write() ����");
		model.addAttribute("request", request);
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	//���� ���� ���
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model){
		System.out.println("content_view() ����");
		model.addAttribute("request", request);
		command= new BContentCommand();
		command.execute(model);
		
		return "content_view";
	}
	
	//���� ���
	@RequestMapping(method = RequestMethod.POST ,value = "/modify")
	public String modify(HttpServletRequest request, Model model){
		System.out.println("modify() ����");
		model.addAttribute("request",request);
		command = new BModifyCommand();
		command.execute(model);
		return "redirect:list";
	}
	
	//�亯 ���� ���
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request , Model model){
		System.out.println("reply_view() ����");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model); 
		
		return "reply_view";
	}
	
	//�亯 �ۼ��ϴ� ���
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model){
		System.out.println("reply() ����");
		
		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	//���� ���
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model){
		System.out.println("delete() ����");
		model.addAttribute("request", request);
		
		command = new BDeleteCommand();
		command.execute(model);
		return "redirect:list";
	}
}
