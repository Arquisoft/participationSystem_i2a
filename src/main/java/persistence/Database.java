package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import dto.Category;
import dto.Commentary;
import dto.Proposal;
import dto.User;

public class Database {
	private static UserDao uDao = Persistence.getUserDao();
	private static ProposalDao pDao = Persistence.getProposalDao();
	private static CommentaryDao comDao = Persistence.getCommentaryDao();
	private static CategoryDao catDao = Persistence.getCategoryDao();

	public static Connection getConnection() {
		try {
			Connection db = DriverManager.getConnection("jdbc:postgresql:citizens", "postgres", "postgres");
			return db;
		} catch (SQLException e) {
			System.err.println("Error al conectar con la bbdd: ");
			System.err.println(e);
			return null;
		}
	}

	public static void buildDatabase() {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		try {
			User user = new User("76756556E", "admin", "admin", format.parse("13/05/1967"), "C/ Uría, 9",
					"admin@email.com", "Español", 0);
			uDao.createUser(user);
			user = new User("798431467A", "Carlos", "Fernández", format.parse("13/05/1967"), "C/ Uría, 3",
					"carlos@email.com", "Español", 3);
			uDao.createUser(user);
			user = new User("9172457631Y", "María", "Pérez", format.parse("26/12/1984"), "C/ Ayala, 6",
					"maria@email.com", "Español", 1);
			uDao.createUser(user);
			user = new User("718346981R", "Simon", "Neil", format.parse("31/08/1979"), "C/ Biffy, 3",
					"simon@email.com", "Británico", 2);
			uDao.createUser(user);
			
			Category cat = new Category("General");
			catDao.createCategory(cat);
			cat = new Category("Entertainment");
			catDao.createCategory(cat);
			
			Proposal prop = new Proposal("Build a park", 1, 1, 2);
			pDao.createProposal(prop);
			prop = new Proposal("Open shopping mall", 0, 2, 3);
			pDao.createProposal(prop);
			prop = new Proposal("Metallica concert", 4, 2, 4);
			pDao.createProposal(prop);
			prop = new Proposal("Host a literature convention", 2, 2, 3);
			pDao.createProposal(prop);
			prop = new Proposal("Clean the streets", 0, 1, 2);
			pDao.createProposal(prop);
			
			Commentary com = new Commentary("Yeah! This would rock :D", 0, format.parse("02/04/2017"), 3, 3);
			comDao.createComment(com);
			com = new Commentary("And bring Foo Fighters!", 0, format.parse("01/04/2017"), 3, 2);
			comDao.createComment(com);
			com = new Commentary("Great for children and pets", 0, format.parse("24/03/2017"), 1, 2);
			comDao.createComment(com);
			com = new Commentary("With a cinema maybe?", 0, format.parse("07/04/2017"), 2, 4);
			comDao.createComment(com);
			com = new Commentary("Yes! With a daycarer's for children", 0, format.parse("08/04/2017"), 2, 3);
			comDao.createComment(com);
			com = new Commentary("Bring George RR Martin :3", 0, format.parse("01/04/2017"), 4, 2);
			comDao.createComment(com);

		} catch (ParseException e) {
			System.err.println("Error parsing date");
		}

	}
}
