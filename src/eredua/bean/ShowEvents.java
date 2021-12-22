package eredua.bean;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;

public class ShowEvents {
	
	BLFacade facadeBL;
	private Date date;
	private List<Event> events;

	public ShowEvents() {
		facadeBL = FacadeBean.getBusinessLogic();
	}
	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void getEventsOnDate(SelectEvent event) {
		this.date = (Date) event.getObject();
		this.events = facadeBL.getEvents(this.date);
	}
	
	public void back() {
		this.events = null;
		this.date = null;
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("Hasiera.xhtml");
		    FacesContext.getCurrentInstance().responseComplete();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
