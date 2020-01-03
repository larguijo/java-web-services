package com.arguijo.ws.soap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;

import org.apache.cxf.feature.Features;

import com.arguijo.ws.trainings.CreateOrdersRequest;
import com.arguijo.ws.trainings.CreateOrdersResponse;
import com.arguijo.ws.trainings.CustomerOrdersPortType;
import com.arguijo.ws.trainings.DeleteOrdersRequest;
import com.arguijo.ws.trainings.DeleteOrdersResponse;
import com.arguijo.ws.trainings.GetOrdersRequest;
import com.arguijo.ws.trainings.GetOrdersResponse;
import com.arguijo.ws.trainings.Order;
import com.arguijo.ws.trainings.Product;

@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class CustomerOrdersWsImpl implements CustomerOrdersPortType {

	Map<BigInteger, List<Order>> customerOrders = new HashMap<>();
	int currentId;

	public CustomerOrdersWsImpl() {
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		System.out.println("INIT");
		List<Order> orders = new ArrayList();
		Order order = new Order();
		order.setId(BigInteger.valueOf(1));

		Product product = new Product();
		product.setId("100");
		product.setDescription("Iphone");
		product.setQuantity(BigInteger.valueOf(3));

		order.getProduct().add(product);
		orders.add(order);
		customerOrders.put(BigInteger.valueOf(++currentId), orders);
		System.out.println("Customer Orders: " + customerOrders);

	}

	@Override
	public GetOrdersResponse getOrders(GetOrdersRequest request) {
		BigInteger customerId = request.getCustomerId();
		List<Order> orders = customerOrders.get(customerId);
		GetOrdersResponse response = new GetOrdersResponse();
		response.getOrder().addAll(orders);
		return response;
	}

	@Override
	public CreateOrdersResponse createOrders(CreateOrdersRequest request) {
		BigInteger customerId = request.getCustomerId();
		Order order = request.getOrder();
		List<Order> orders = customerOrders.get(customerId);
		orders.add(order);
		CreateOrdersResponse response = new CreateOrdersResponse();
		response.setResult(true);

		return response;
	}

	@Override
	public DeleteOrdersResponse deleteOrders(DeleteOrdersRequest request) {
		BigInteger customerId = request.getCustomerId();
		if (customerOrders.get(customerId) != null) {
			customerOrders.get(customerId).clear();
		}
		DeleteOrdersResponse response = new DeleteOrdersResponse();
		response.setResult(true);
		return response;
	}

}
