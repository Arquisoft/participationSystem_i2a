package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import dto.Proposal;
import dto.User;
import hello.model.CreateProposal;
import hello.producers.KafkaProducer;
import persistence.Persistence;

@Controller
public class MainController {

	@Autowired
	private KafkaProducer kafkaProducer;


	@RequestMapping("/")
	public String landing(Model model) {
		model.addAttribute("createProposal", new CreateProposal());
		model.addAttribute("proposals", Persistence.getProposalDao().getProposals());
		return "/user/home"; // return "index";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping("/send")
	public String send(Model model, @ModelAttribute Message message) {
		kafkaProducer.send("exampleTopic", message.getMessage());
		return "redirect:/";
	}
	
	@RequestMapping("/user/home")
	public String send(Model model) {
		model.addAttribute("proposals", Persistence.getProposalDao().getProposals());
		model.addAttribute("createProposal", new CreateProposal());
		return "/user/home";
	}
	
	@RequestMapping("/user/createProposal")
	public String createProposal(Model model, @ModelAttribute CreateProposal createProposal) {
		Proposal proposal = new Proposal();
		//proposal.setCategory(new Category().setName(createProposal.getCateogry()));
		proposal.setContent(createProposal.getText());
		proposal.setUserId(1);
		proposal.setVotes(0);
		Persistence.getProposalDao().createProposal(proposal);
		return "redirect:/user/home";
	}
	
	


}