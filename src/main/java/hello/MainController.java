package hello;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.Category;
import dto.Proposal;
import dto.User;
import hello.producers.KafkaProducer;
import persistence.Persistence;

@Controller
public class MainController {

	@Autowired
	private KafkaProducer kafkaProducer;
	
	@ModelAttribute("categories")
    public List<Category> categories() {
        return  Persistence.getCategoryDao().findAllCategories();
    }

	@RequestMapping("/")
	public String landing(Model model) {
		// model.addAttribute("message", new Message());
		model.addAttribute("user", new User());
		return "login"; // "index"
	}

	@RequestMapping(value = "/add")
	public String userHome(Model model) {
		model.addAttribute("createProposal", new Proposal());
		return "add";
	}

	@RequestMapping("/send")
	public String send(Model model, @ModelAttribute Message message) {
		kafkaProducer.send("exampleTopic", message.getMessage());
		return "redirect:/";
	}

	@RequestMapping("/login")
	public String send(Model model, @ModelAttribute User userLogged) {
		User user = Persistence.getUserDao().getUserById(userLogged.getId());
		Proposal proposal = new Proposal();
		proposal.setContent("");
		model.addAttribute("proposal", proposal);
		return "redirect:/add";
	}

	@RequestMapping("/createProposal")
	public String send(Model model, @ModelAttribute Proposal proposal) {
		//proposal.setUserId(user.getId());
		System.out.println(proposal);
		return "redirect:Proposals.html";
	}

}