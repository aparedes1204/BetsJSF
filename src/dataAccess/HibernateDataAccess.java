package dataAccess;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import org.hibernate.Query;
import org.hibernate.Session;

import configuration.UtilDate;
import domain.Bet;
import domain.Event;
import domain.Question;
import domain.User;
import exceptions.QuestionAlreadyExist;

public class HibernateDataAccess implements DataAccessInterface {

	public HibernateDataAccess(boolean initializeMode) {

		// System.out.println("Creating DataAccess instance => isDatabaseLocal:
		// "+c.isDatabaseLocal()+" getDatabBaseOpenMode: ");

		//open(initializeMode);

		//if (initializeMode)
			//initializeDB();
	}

	public HibernateDataAccess() {
		new HibernateDataAccess(false);
	}

	public void initializeDB() {
		System.out.println("1");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println("2");
		session.beginTransaction();
		System.out.println("3");
		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}

			Event ev1 = new Event("Atlético-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = new Event("Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event("Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event("Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event("Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event("Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event("Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event("Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event("Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event("Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event("Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = new Event("Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = new Event("Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = new Event("Alavés-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = new Event("Español-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = new Event("Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));

			Event ev17 = new Event("Málaga-Valencia", UtilDate.newDate(year, month, 28));
			Event ev18 = new Event("Girona-Leganés", UtilDate.newDate(year, month, 28));
			Event ev19 = new Event("Real Sociedad-Levante", UtilDate.newDate(year, month, 28));
			Event ev20 = new Event("Betis-Real Madrid", UtilDate.newDate(year, month, 28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);

			}

			session.persist(q1);
			session.persist(q2);
			session.persist(q3);
			session.persist(q4);
			session.persist(q5);
			session.persist(q6);
			
			session.persist(ev1);
			session.persist(ev2);
			session.persist(ev3);
			session.persist(ev4);
			session.persist(ev5);
			session.persist(ev6);
			session.persist(ev7);
			session.persist(ev8);
			session.persist(ev9);
			session.persist(ev10);
			session.persist(ev11);
			session.persist(ev12);
			session.persist(ev13);
			session.persist(ev14);
			session.persist(ev15);
			session.persist(ev16);
			session.persist(ev17);
			session.persist(ev18);
			session.persist(ev19);
			session.persist(ev20);

			User usr1 = new User ("admin", "12345678");
			User usr2 = new User ("aitor", "12345678");
			
			session.persist(usr1);
			session.persist(usr2);
			

			session.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query q = session.createQuery("from Event where eventNumber = :num");
		q.setParameter("num", event.getEventNumber());
		Event ev = (Event)q.uniqueResult();

		if (ev.DoesQuestionExists(question))
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		Question qu = ev.addQuestion(question, betMinimum);
		// db.persist(q);
		session.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions
						// property of Event class
						// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		session.getTransaction().commit();
		return qu;

	}
	
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Vector<Event> res = new Vector<Event>();	
		Query q = session.createQuery("FROM Event ev WHERE eventDate=:data");
		q.setParameter("data", date);
		List<Event> events = q.list();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	session.getTransaction().commit();
	 	return res;
	}
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		Query q = session.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN :first and :last");
		q.setParameter("first", firstDayMonthDate);
		q.setParameter("last", lastDayMonthDate);
		List<Date> dates = q.list();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	session.getTransaction().commit();
	 	return res;
	}
	@Override
	public void open(){
		HibernateUtil.getSessionFactory().openSession();
	}
	
	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Event where eventNumber = :num");
		q.setParameter("num", event.getEventNumber());
		Event ev = (Event)q.uniqueResult();
		session.getTransaction().commit();
		return ev.DoesQuestionExists(question);
	}
	
	public void close(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.close();
		System.out.println("DataBase closed");
	}
	
	@Override
	public void emptyDatabase() {
		
		//File f=new File(c.getDbFilename());
		//f.delete();
		//File f2=new File(c.getDbFilename()+"$");
		//f2.delete();
	}
	
	public boolean validateLogin(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from User where username = :usr and password = :psw");
		q.setParameter("usr", username);
		q.setParameter("psw", password);
		List<User> users = q.list();
		session.getTransaction().commit();
		return !users.isEmpty();
	}
	
	public boolean validateRegister(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from User where username = :usr");
		q.setParameter("usr", username);
		List<User> users = q.list();
		if(users.isEmpty()) {
			User usr = new User(username, password);
			session.persist(usr);
		}
		session.getTransaction().commit();
		return users.isEmpty();
	}
	
	public boolean makeBet(Question selectedQuestion, String answer, float bet, String username) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from User where username = :usr");
		q.setParameter("usr", username);
		User user = (User)q.uniqueResult();
		if(user.doesBetExist(selectedQuestion)) {
			session.getTransaction().commit();
			return false;
		} else {
			user.addBet(selectedQuestion, answer, bet);
			session.persist(user);
			session.getTransaction().commit();
			return true;
		}
	}

}
