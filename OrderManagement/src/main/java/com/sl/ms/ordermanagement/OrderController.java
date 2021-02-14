package com.sl.ms.ordermanagement;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController 
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	private OrdersRepo ordersrepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	private static final Logger log=LoggerFactory.getLogger(OrderController.class);
	
	@GetMapping("/orders")
	public List<Orders> findAllOrders(){
		log.info("List orders");
		return ordersrepo.findAll();		
	}
	
	@GetMapping("/orders/{order_id}")
	public ResponseEntity<Orders> findOne(@PathVariable Integer order_id) throws OrderNotfoundException {
		log.info("Get orders");
		Orders order = ordersrepo.findById(order_id).orElseThrow(() -> new OrderNotfoundException("Invalid order id : " + order_id));
		return ResponseEntity.ok().body(order);
	}
	
	@PostMapping("/orders/{order_id}")
	@HystrixCommand(fallbackMethod = "getFallbackplaceOrder")
	public String placeOrder(@RequestBody Orders order)  {				 
		List<Items> itemlist = order.getItems();				
		for(Items item : itemlist) {
			log.info("Before calling Inventory service");
			Product product = restTemplate.getForObject("http://localhost:8889/dev/products/"+ item.getItemid(), Product.class);
			log.info("After calling Inventory service");
			 	int quantity = product.getQuantity();
			 	if (quantity == 0)
				{
					return "Product "+ item.getName() +" has quantity as 0 in PRODUCTS";
				}
			}		
		ordersrepo.save(order);		
		return "Order placed";
	}
	
	public String getFallbackplaceOrder(@RequestBody Orders order)  {		
		return "Looks like Inventory service unavailable. Please try later.";
	}
	
	@DeleteMapping("/orders/{order_id}")
	public ResponseEntity<Object> deleteOne(@PathVariable Integer order_id) throws OrderNotfoundException {
		log.info("Deleting orders");
		@SuppressWarnings("unused")
		Orders order = ordersrepo.findById(order_id).orElseThrow(() -> new OrderNotfoundException("Invalid order id : " + order_id));
		ordersrepo.deleteById(order_id);
		return new ResponseEntity<>("Order deleted", HttpStatus.OK);
	}
}
