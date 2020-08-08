package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;

import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;
import net.bounceme.chronos.testjopas.dto.Customer;
import net.bounceme.chronos.testjopas.dto.Order;
import net.bounceme.chronos.testjopas.dto.Product;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.testjopas.services.DroolsService;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;

/**
 * The Class SessionBean.
 */
@ManagedBean(name = ReglasBean.NAME)
@ViewScoped
public class ReglasBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;

	/** The Constant NAME. */
	public static final String NAME = "reglasBean";

	/** The logger. */
	private Log logger;

	/** The app bean. */
	@ManagedProperty(value = "#{appBean}")
	private AppBean appBean;

	/** The app bean. */
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;

	@Autowired
	private DroolsService droolsService;
	
	private List<Order> orders;

	@PostConstruct
	public void initialize() {
		try {
			logger = LogFactory.getInstance().getLogger(ReglasBean.class, "LOG4J");
			populateData();
			
			for (Order order : orders) {
				droolsService.insertOn(order);
			}
			
			droolsService.apply();
		} catch (ServiceException e) {
			logger.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}

	private void populateData() {
		orders = Arrays.asList(getOrderWithDefaultCustomer(), getOrderWithSilverCustomer(),
				getOrderWithGoldCustomer(), getOrderWithGoldCustomerAndTenProducts());
	}
	
	private Order getOrderWithDefaultCustomer() {
		final Order order = new Order(getDefaultCustomer());
		order.getProducts().add(getProduct1());
		return order;
	}
	
	private Order getOrderWithGoldCustomer() {
		final Order order = new Order(getGoldCustomer());
		order.getProducts().add(getProduct1());
		return order;
	}
	
	private Order getOrderWithSilverCustomer() {
		final Order order = new Order(getSilverCustomer());
		order.getProducts().add(getProduct1());
		return order;
	}
	
	private Order getOrderWithGoldCustomerAndTenProducts() {
		final Order order = new Order(getSilverCustomer());
		for (int i = 0; i < 10; i++) {
			order.getProducts().add(getProduct1());
		}
		return order;
	}
	
	private Customer getDefaultCustomer() {
		return new Customer(Customer.DEFAULT_CUSTOMER, "Cliente estandar");
	}

	private Customer getSilverCustomer() {
		return new Customer(Customer.SILVER_CUSTOMER, "Cliente SILVER");
	}

	private Customer getGoldCustomer() {
		return new Customer(Customer.GOLD_CUSTOMER, "Cliente GOLD");
	}

	private Product getProduct1() {
		return new Product(1, "Producto 1", 100d);
	}

	public void calcular() {
		try {
			for (Order order : orders) {
				droolsService.insertOn(order);
			}
			
			droolsService.apply();
		} catch (ServiceException e) {
			logger.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}

	/**
	 * @return the appBean
	 */
	public AppBean getAppBean() {
		return appBean;
	}

	/**
	 * @param appBean the appBean to set
	 */
	public void setAppBean(AppBean appBean) {
		this.appBean = appBean;
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * @return the orders
	 */
	public List<Order> getOrders() {
		return orders;
	}
}
