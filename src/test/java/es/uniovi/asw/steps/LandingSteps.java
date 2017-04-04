package es.uniovi.asw.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import hello.Application;

@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class LandingSteps {

	@Autowired
	protected WebApplicationContext context;

	protected MockMvc mvc;
	protected MvcResult result;

	@Value("${local.server.port}")
	protected int port;

	@Given("^the user \"([^\"]*)\" /$")
	public void the_user(String str) {
		System.out.println("The user with login " + str);
	}

	@Given("^password \"([^\"]*)\" /$")
	public void password(String str) {
		System.out.println("and password " + str);
	}

	@When("^the users clicks on Login /$")
	public void the_user_clicks_on_Login() {
		System.out.println("the user clicks on login");
	}

	@Then("^the user sees the proposal list$")
	public void the_user_sees_the_proposal_list() {
		System.out.println("and if it's correct, sees the proposal list");
	}

	@When("^the admin clicks on Login /$")
	public void the_admin_clicks_on_login() {
		System.out.println("the admin clicks on login");
	}

	@Then("^the admin is able to choose parameters for the app$")
	public void the_admin_is_able_to_choose_parameters_for_the_app() {
		System.out.println("the admin is able to choose parameters for the app");
	}

	@When("^the admin writes a new category /$")
	public void the_admin_writes_a_new_category() {
		System.out.println("the admin writes a new category");
	}

	@When("^press the accept button /$")
	public void press_the_accept_button() {
		System.out.println("the admin press the accept button");
	}

	@Then("^the new category will be added to the database /$")
	public void the_new_category_will_be_added_to_the_database() {
		System.out.println("the new category will be added to the database");
	}

	@Then("^there will be a new category to choose from in the proposals$")
	public void there_will_be_a_new_category_to_choose_from_in_the_database() {
		System.out.println("therw will be a new category in the database");
	}

	@When("^the admin writes non-allowes words /$")
	public void the_admin_writes_non_allowed_words() {
		System.out.println("the admin writes non-allowed words");
	}

	@Then("^the new words will be added to the database /$")
	public void the_new_words_will_be_added_to_the_database() {
		System.out.println("the new words are added to the database");
	}

	@Then("^they won't be allowed to be used in the proposals$")
	public void they_wont_be_allowed_to_be_used() {
		System.out.println("the words won't be allowed in the proposals");
	}

	@When("^the admin writes the id of a proposal /$")
	public void the_admin_writes_the_id_of_a_proposal() {
		System.out.println("the admin writes the id of the proposal");
	}

	@Then("^the proposal will be deleted$")
	public void the_proposal_will_be_deleted() {
		System.out.println("the proposal is deleted");
	}

	@When("^the user writes a proposal /$")
	public void the_user_writes_a_proposal() {
		System.out.println("the user writes a proposal");
	}

	@When("^selects a category /$")
	public void selects_a_category() {
		System.out.println("the user selects a category");
	}

	@When("^press the add proposal button/$")
	public void press_the_add_proposal_button() {
		System.out.println("the user clicks the add proposal button");
	}

	@Then("^the new proposal will be added to the database$")
	public void the_new_proposal_will_be_added_to_the_database() {
		System.out.println("the new proposal is added to the BD");
	}

	@When("^the user clicks on view proposals /$")
	public void the_user_clicks_on_view_proposals() {
		System.out.println("the user clicks on view proposals");
	}

	@Then("^the he can vote each one of them /$")
	public void he_can_vote_each_one_of_them() {
		System.out.println("the user can vote each one of them");
	}

	@Then("^see their details$")
	public void see_their_details() {
		System.out.println("the user can see the details of the proposals");
	}

	@When("^the user clicks on view details of a proposal /$")
		public void the_user_clicks_on_view_details_of_a_proposal(){
			System.out.println("the user clicks on view details");
		}

	@Then("^he can comment it/$")
		public void he_can_comment_it(){
			System.out.println("the user can comment the proposal");
		}

	@Then("^see its comments/$")
		public void see_its_comments(){
			System.out.println("the user can see the comments of the proposal");
		}

	@Then("^vote the comments/$")
	public void vote_the_comments(){
		System.out.println("the user can vote the comments");
	}

	@Then("^order the comments$")
		public void order_the_comments(){
			System.out.println("the user can order the comments");
		}
	

}
