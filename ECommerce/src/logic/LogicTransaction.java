package logic;

import java.util.HashMap;
import java.util.Iterator;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import data.DataTransaction;
import entities.Product;
import entities.Transaction;

public class LogicTransaction {

	Transaction registerTransaction(int userId, int addressId, int cardId, HashMap<Integer,Integer> shoppingCart){	//shoppingCart has the if of each product as the Key and the quantity as the Value
		DataTransaction dataT = new DataTransaction();
		Transaction transaction = new Transaction();
		transaction.setIdUser(userId);
		transaction.setIdAddress(addressId);
		transaction.setIdCard(cardId);
		HashMap<Product,Integer> cartToPrint =  new HashMap<>();
		LogicProduct lp = new LogicProduct();
	/******		Generating the shopping cart with all the details from products, and calculating the total of the Transaction:	******/
		for(HashMap.Entry<Integer,Integer> product : shoppingCart.entrySet()){
			cartToPrint.put(lp.idSearch(product.getKey()) , product.getValue());
			transaction.setTotal(transaction.getTotal() + (lp.idSearch(product.getKey()).getPrice())); 
		}
	/***** 	Setting the serverDate:	*****/	
		long mlSeconds = System.currentTimeMillis();
		transaction.setServerDate(mlSeconds);
	/*****	Transforming the cart into a string	*****/	
		String printedCart = "+";
		for (int i = 0; i < 50; i++) printedCart += "-";
		printedCart+="+qty--+total---+ <br>";
		for(HashMap.Entry<Product,Integer> product : cartToPrint.entrySet()) {
			for (int i = 0; i < 50; i++) printedCart += "-";
			printedCart+="|-----|--------| <br>";
			printedCart+="|"+product.getKey().getName();
			for (int i = 0; i < (49 - product.getKey().getName().length()); i++) printedCart += " ";
			printedCart+="|"+product.getValue();
			for (int i = 0; i < (5 - product.getValue().toString().length()); i++) printedCart+=" ";
			printedCart+="|"+product.getValue() * product.getKey().getPrice();
			for (int i = 0; i < (8 - (String.valueOf((product.getValue() * product.getKey().getPrice()))).length()); i++) printedCart+=" ";
			printedCart+="| <br>";			
		}	
		printedCart+="+";
		for (int i = 0; i < 50; i++) printedCart += "¯";
		printedCart+="+-----+"+transaction.getTotal();
		for (int i = 0; i < (8 - String.valueOf(transaction.getTotal()).length()); i++) printedCart+=" ";
		printedCart+="+ <br>";
		transaction.setDetail(printedCart);	
	/*****	Calling DataTransaction	*****/	
		if (dataT.saveTransaction(transaction)) {
			
			if (dataT.saveDetails(transaction)) {
				
			}
		}else {
			
		}
		
		
		
	}
	
}
