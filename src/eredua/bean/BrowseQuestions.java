package eredua.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;

public class BrowseQuestions {

	BLFacade facadeBL;
	private Date eventDate;
	private List<Event> events;
	private Event selectedEvent;
	private List<Question> questions;

	public BrowseQuestions() {
		facadeBL = FacadeBean.getBusinessLogic();
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Event getSelectedEvent() {
		return selectedEvent;
	}

	public void setSelectedEvent(Event ev) {
		this.selectedEvent = ev;
	}

	public void onDateSelect(SelectEvent event) {
		this.questions = null;
		this.eventDate = (Date) event.getObject();
		this.events = facadeBL.getEvents(this.eventDate);
	}
	
	public void onEventSelect(SelectEvent event) {
		System.out.println((Event) event.getObject());
		this.selectedEvent = (Event) event.getObject();
		this.questions = selectedEvent.getQuestions();
	}
	
	public void reset() {
		this.eventDate = null;
		this.events = null;
		this.questions = null;
		this.selectedEvent = null;
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("Hasiera.xhtml");
		    FacesContext.getCurrentInstance().responseComplete();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
