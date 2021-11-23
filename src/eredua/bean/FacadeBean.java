package eredua.bean;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

public class FacadeBean {

	private static FacadeBean singleton = new FacadeBean();
	
	private static BLFacade facadeInterface;
	
	private FacadeBean() {
		try {
			facadeInterface = new BLFacadeImplementation(new DataAccess());
		} catch (Exception e) {
			System.out.println("FacadeBean: negozioaren logika sortzearen errorea: "+e.getMessage());
		}
	}
	
	public static BLFacade getBusinessLogic() {
		return facadeInterface;
	}
	
	
}
