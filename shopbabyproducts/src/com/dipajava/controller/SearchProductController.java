package com.dipajava.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dipajava.bean.Product;
import com.dipajava.common.CSVUtils;



/**
 * @author Dipa Nakrani
 *
 */
@Controller
public class SearchProductController {
	
	@RequestMapping(value = "/searchProduct", method = RequestMethod.GET)
	public ModelAndView searchProduct() {
		return new ModelAndView("searchProduct");
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam("prodname") String prodName) {
		
		Map<String, String> model = new HashMap<String, String>();
		
		String result = "none";
		
		Float sum = 0f;
		String[] prodNames = null;
		Scanner scanner = null;
		List<Product> prodList = new ArrayList<Product>();
		Map<Integer, Float> priceList = new HashMap<Integer, Float>();
		List<Integer> shopIdss = new ArrayList<Integer>();
		Map<Integer,Boolean> containsProd = new HashMap<Integer, Boolean>();
		Boolean flag =false;
		try {
			// read csv data file 
			String csvFile = "/data.csv";
	        scanner = new Scanner(new File(csvFile));
	        while (scanner.hasNext()) {
	        	Product prd = new Product();
	        	// read csv data file line by line and set in Product bean
	            List<String> line = CSVUtils.parseLine(scanner.nextLine());
	            prd.setShopId(Integer.parseInt(line.get(0)));
	            prd.setPrice(Float.parseFloat(line.get(1)));
	            System.out.println("Data [id= " + line.get(0) + ", code= " + line.get(1) + " , name=" + line.get(2) + "]");
	            prd.setProdName(line.get(2));
	            prodList.add(prd);
	            if(!shopIdss.contains(Integer.parseInt(line.get(0))))
	            	shopIdss.add(Integer.parseInt(line.get(0)));
	        }
	        // close reader
	        scanner.close();
	        
			if (prodName.contains(",")) {
				prodNames = prodName.split(",");
			}else{
				prodNames =new String[]{prodName};
			}
			
			// calculate price for products
			for(int id :shopIdss){
				sum=0f;
				for (String s : prodNames) {
					for(Product p:prodList){
						if(id==p.getShopId()){
							if(p.getProdName().contains(s)){
								sum = sum + p.getPrice();
								priceList.put(p.getShopId(), sum);
								containsProd.put(p.getShopId(), true);
							}else{
								containsProd.put(p.getShopId(), false);
							}
						}
						
					}
				}
			}
			
			// get minimum price
			Entry<Integer, Float> min = null;
			if(priceList.size()>0){
				for (Entry<Integer, Float> entry : priceList.entrySet()) {
					if (min == null || min.getValue() > entry.getValue()) {
						min = entry;
					}
				}
				result = min.getKey() + "," + min.getValue();
			}else{
				result = "none";
			}
			
			//below is if one of the product not foubd in same shop
			if(containsProd.containsKey(min.getKey())){
				flag = containsProd.get(min.getKey());
				if(!flag)
					result = "none";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.put("result", result);
		
		return new ModelAndView("result", model);
	}	
}
