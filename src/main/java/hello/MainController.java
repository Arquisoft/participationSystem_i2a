package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dto.Category;
import dto.Commentary;
import dto.Proposal;
import dto.User;
import hello.model.AddComment;
import hello.model.AddProposal;
import hello.model.ControlAdmin;
import hello.producers.KafkaProducer;
import persistence.CommentaryDao;
import persistence.Persistence;
import persistence.ProposalDao;

@Controller
public class MainController {

	@Autowired
	private KafkaProducer kafkaProducer;

	private ProposalDao pDao = Persistence.getProposalDao();
	private CommentaryDao cDao = Persistence.getCommentaryDao();

	private List<Proposal> proposalList = pDao.getProposals();
	private User user;

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
		model.addAttribute("controlAdmin", new ControlAdmin());
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		this.user = Persistence.getUserDao().getUserByEmail(email);
		
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ADMIN]"))
			return "/admin";
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
		model.addAttribute("proposals", pDao.getProposals());
		return "/user/home";
	}

	@RequestMapping("/admin")
	public String go(Model model) {
		model.addAttribute("proposals", pDao.getProposals());
		model.addAttribute("controlAdmin", new ControlAdmin());
		return "/admin";
	}

	@RequestMapping(value = "/user/addForm", method = RequestMethod.GET)
	public String goToForm(Model model) {
		model.addAttribute("addProposal", new AddProposal());
		model.addAttribute("categoriesList", Persistence.getCategoryDao().findAllCategories());
		return "/user/add-form";
	}

	@RequestMapping(value = "/user/viewProposal/{id}", method = RequestMethod.GET)
	public String viewProposal(Model model, @PathVariable("id") String id) {
		Integer idInt = Integer.parseInt(id);
		Proposal proposal = pDao.getProposalById(idInt);
		List<Commentary> commentsList = cDao.getCommentariesFromProposalId(idInt);

		model.addAttribute("proposal", proposal);
		model.addAttribute("commentsList", commentsList);
		model.addAttribute("addComment", new AddComment());

		return "/user/proposal";
	}

	@RequestMapping("/user/addProposal")
	public String addProposal(Model model, @ModelAttribute AddProposal addProposal) {
		Proposal proposal = new Proposal();
		proposal.setContent(addProposal.getText());
		proposal.setCategory(Integer.parseInt(addProposal.getCategory().getName()));
		proposal.setUserId(user.getId());
		proposal.setVotes(0);
		try {
			pDao.createProposal(proposal);
		} catch (Exception e) {
			System.out.println("funca bien");
			return "/mensajeError";
		}
		return "redirect:/user/home";
	}

	@RequestMapping(value = "/user/voteProposal/{id}", method = RequestMethod.GET)
	public String voteProposal(@PathVariable("id") String id) {
		Proposal proposal = pDao.getProposalById(Integer.parseInt(id));
		pDao.voteProposal(proposal);
		return "redirect:/user/home";
	}

	@RequestMapping(value = "/user/voteComment/{id}", method = RequestMethod.GET)
	public String voteComment(@PathVariable("id") String id) {
		Commentary comment = cDao.getCommentaryById(Integer.parseInt(id));
		cDao.voteComment(comment);
		return "redirect:/user/viewProposal/" + comment.getProposalId();
	}

	@RequestMapping("/user/commentProposal/{id}")
	public String commentProposal(Model model, @ModelAttribute AddComment addComment, @PathVariable("id") String id) {
		String content = addComment.getComment();

		Commentary comment = new Commentary(content, Integer.parseInt(id), user.getId());
		try {
			cDao.createComment(comment);
		} catch (Exception e) {
			return "/mensajeError";
		}

		return "redirect:/user/viewProposal/" + comment.getProposalId();
	}

	@RequestMapping("/addCategory")
	public String addCategory(Model model, @ModelAttribute ControlAdmin controlAdmin) {
		Category category = new Category(controlAdmin.getCategory());
		Persistence.getCategoryDao().createCategory(category);
		return "/admin";
	}

	@RequestMapping("/addNotAllowedWords")
	public String addNotAllowedWords(Model model, @ModelAttribute ControlAdmin controlAdmin) {
		Persistence.getWordDao().add(controlAdmin.getPalabras());
		return "/admin";
	}

	@RequestMapping("/deleteProposal")
	public String deleteProposal(Model model, @ModelAttribute ControlAdmin controlAdmin) {
		Persistence.getProposalDao().deleteProposalById(Integer.parseInt(controlAdmin.getProposal()));

		model.addAttribute("controlAdmin", new ControlAdmin());
		return "/admin";
	}

	@RequestMapping("/mensajeError")
	public String mensajeError(Model model) {
		return "/mensajeError";
	}
	
	@RequestMapping(value = "/user/orderCommentsPopularity/{id}", method = RequestMethod.GET)
	public String orderCommentsPopularity(Model model, @PathVariable("id") String id) {
		Integer idInt = Integer.parseInt(id);
		Proposal proposal = pDao.getProposalById(idInt);
		List<Commentary> commentsList = cDao.getCommentariesFromProposalIdOrderedByPopularity(idInt);

		model.addAttribute("proposal", proposal);
		model.addAttribute("commentsList", commentsList);
		model.addAttribute("addComment", new AddComment());

		return "/user/proposal";
	}
	
	@RequestMapping(value = "/user/orderCommentsDate/{id}", method = RequestMethod.GET)
	public String orderCommentsDate(Model model, @PathVariable("id") String id) {
		Integer idInt = Integer.parseInt(id);
		Proposal proposal = pDao.getProposalById(idInt);
		List<Commentary> commentsList = cDao.getCommentariesFromProposalIdOrderedByDate(idInt);

		model.addAttribute("proposal", proposal);
		model.addAttribute("commentsList", commentsList);
		model.addAttribute("addComment", new AddComment());

		return "/user/proposal";
	}

}