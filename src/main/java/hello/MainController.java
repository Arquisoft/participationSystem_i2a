package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dto.Category;
import dto.Proposal;
import hello.model.AddProposal;
import hello.producers.KafkaProducer;
import persistence.Persistence;
import persistence.ProposalDao;

@Controller
public class MainController {

	@Autowired
	private KafkaProducer kafkaProducer;

	private ProposalDao pDao = Persistence.getProposalDao();

	private List<Category> categoriesNamesList = Persistence.getCategoryDao().findAllCategories();
	private List<Proposal> proposalList = pDao.getProposals();

	@ModelAttribute
	public AddProposal getAddProposal() {
		return new AddProposal();
	}
	
	@ModelAttribute
	public List<Proposal> getProposals() {
		return pDao.getProposals();
	}
	
	@RequestMapping("/")
	public String landing(Model model) {
		model.addAttribute("addProposal", new AddProposal());
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
		model.addAttribute("addProposal", new AddProposal());
		return "/user/home";
	}

	@RequestMapping("/user/addProposal")
	public String addProposal(Model model, @ModelAttribute AddProposal addProposal) {
		Proposal proposal = new Proposal();
		proposal.setCategory(addProposal.getCategory());
		proposal.setContent(addProposal.getText());
		proposal.setUserId(1);
		proposal.setVotes(0);
		pDao.createProposal(proposal);
		return "redirect:/user/home";
	}

	@RequestMapping(value = "/user/voteProposal/{id}", method = RequestMethod.GET)
	public String voteProposal(@PathVariable("id") String id) {
		Proposal proposal = pDao
				.getProposalById(Integer.parseInt(id));
		pDao.voteProposal(proposal);
		return "redirect:/user/home";
	}

	@RequestMapping("/user/commentProposal/{id}")
	public String commentProposal(Model model, @ModelAttribute AddProposal addProposal) {

		return "/user/home";
	}

}