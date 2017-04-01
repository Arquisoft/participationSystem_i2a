package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.Category;
import dto.Proposal;
import dto.User;
import hello.model.AddProposal;
import hello.producers.KafkaProducer;
import persistence.Persistence;

@Controller
public class MainController {

	@Autowired
	private KafkaProducer kafkaProducer;
	
	private List<Category> categoriesNamesList = Persistence.getCategoryDao().findAllCategories();
	private List<Proposal> proposalList = Persistence.getProposalDao().getProposals();

	@RequestMapping("/")
	public String landing(Model model) {
		model.addAttribute("createProposal", new AddProposal());
		model.addAttribute("proposals", proposalList);
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
		model.addAttribute("createProposal", new AddProposal());
		return "/user/home";
	}
	
	@RequestMapping("/user/addProposal")
	public String createProposal(Model model, @ModelAttribute AddProposal addProposal) {
		Proposal proposal = new Proposal();
		//proposal.setCategory(new Category().setName(createProposal.getCateogry()));
		proposal.setContent(addProposal.getText());
		proposal.setUserId(1);
		proposal.setVotes(0);
		Persistence.getProposalDao().createProposal(proposal);
		return "redirect:/user/home";
	}
	


}