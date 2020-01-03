package com.arguijo.ws.soap;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.arguijo.ws.trainings.CreateOrdersRequest;
import com.arguijo.ws.trainings.CreateOrdersResponse;
import com.arguijo.ws.trainings.CustomerOrdersPortType;
import com.arguijo.ws.trainings.GetOrdersRequest;
import com.arguijo.ws.trainings.GetOrdersResponse;
import com.arguijo.ws.trainings.Order;
import com.arguijo.ws.trainings.Product;

public class CustomerOrderWsClient {
	public static void main(String[] args) throws MalformedURLException {
		List<Order> orders = getOrder();
		System.out.println("Number of orders for customer are: " + orders.size());
		boolean created = createOrder();
		System.out.println("Order was created? " + created);
		System.out.println("Number of orders for customer are: " + orders.size());
	}

	private static List<Order> getOrder() throws MalformedURLException {
		CustomerOrdersWsImplService service = new CustomerOrdersWsImplService(
				new URL("http://localhost:8080/wsdlfirstws/customerordersservice?wsdl"));
		CustomerOrdersPortType port = service.getCustomerOrdersWsImplPort();
		GetOrdersRequest request = new GetOrdersRequest();
		request.setCustomerId(BigInteger.valueOf(1));
		GetOrdersResponse response = port.getOrders(request);
		List<Order> orders = response.getOrder();
		return orders;
	}

	private static boolean createOrder() throws MalformedURLException {
		CustomerOrdersWsImplService service = new CustomerOrdersWsImplService(
				new URL("http://localhost:8080/wsdlfirstws/customerordersservice?wsdl"));
		CustomerOrdersPortType port = service.getCustomerOrdersWsImplPort();
		CreateOrdersRequest request = new CreateOrdersRequest();
		request.setCustomerId(BigInteger.valueOf(1));

		Order order = new Order();
		Product product = new Product();
		product.setId("5");
		product.setDescription("Nintendo Switch");
		product.setQuantity(BigInteger.valueOf(2));
		order.getProduct().add(product);

		request.setOrder(order);
		CreateOrdersResponse response = port.createOrders(request);
		return response.isResult();

	}
}
