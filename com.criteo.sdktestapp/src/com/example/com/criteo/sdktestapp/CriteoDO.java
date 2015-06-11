package com.example.com.criteo.sdktestapp;

import java.util.ArrayList;
import java.util.List;

import android.location.Location;

import com.criteo.events.*;
import com.criteo.events.product.BasketProduct;
import com.criteo.events.product.Product;

/**
 * This class initialize Criteo parameters. 
 * The parameters can be used in for sending Criteo retargeting events 
 * @param void
 * @return void
 */
public class CriteoDO {	
	//This class defines data object for testing parameters of Criteo retargeting
	
	// Advertiser ID and Conversion key. Thses parameter were generated by MAT
	String m_Advertiser_ID = "17207";  // For CriteoAdvertiser= criteomobilegameskr
	String m_Conversion_Key = "b187792e8b2b850b3722b31ce50f978b";  // This conversion key was generated from TUNE dashboard 
	
	String m_StartApp="6398";
	String m_ViewProduct="6402";
	String m_ViewList="6422";
	String m_ViewCart="6406";
	String m_Purchase="6400";
	String m_Search="6404";
	
	// Countgry code and language
	String m_CountryCode="kr";
	String m_Language="ko";
	
	//Criteo Campaign ID
	String m_CampaignId="12345";
	
	//Date in and Date out
	String m_din="2015-04-03";
	String m_dout="2015-05-01";

	
	//Criteo SDK View Product List Items
	Product m_Product1 = null;
	Product m_Product2 = null;
	Product m_Product3 = null;
	List<Product> arr_ProductList = null;
	Iterable<Product> m_ListProduct = null;
	
	//Criteo SDK View Product List Items
	BasketProduct m_BasketProduct1 = null;
	BasketProduct m_BasketProduct2 = null;
	BasketProduct m_BasketProduct3 = null;
	List<BasketProduct> arr_BasketProductList = null;
	Iterable<BasketProduct> m_BasketProductList = null;
	
	//List <MATEventItem> m_EventListingItems = null;
	
	//List <MATEventItem> m_EventViewListItems = null;
	
	//Criteo cn parameter
	Location m_Location = null;
	
	//Criteo Currency parameter
	String m_Currency = "USD";
	
	// Criteo Hashed Email
	String m_HashedEmail = "79892287a68598e188555e56132f92ba";
	
	public CriteoDO() {
		super();
		// TODO Auto-generated constructor stub
		
		// Criteo cn parameter
		this.m_Location = new Location("kr");
		
		//Criteo SDK ProductList
		m_Product1 = new Product("111111111", (float)55.34);
		m_Product2 = new Product("2222222222", (float)100.34);
		m_Product3 = new Product("3333333333", (float)200.34);
		arr_ProductList = new ArrayList<Product> ();
		arr_ProductList.add(m_Product1);
		arr_ProductList.add(m_Product2);
		arr_ProductList.add(m_Product3);
		
		m_ListProduct = arr_ProductList;
		
		//Criteo SDK BasketProductList
		m_BasketProduct1 = new BasketProduct("55555555", (float)33.34, 3);
		m_BasketProduct2 = new BasketProduct("6666666666", (float)400.34,2);
		m_BasketProduct3 = new BasketProduct("7777777777", (float)500.34,4);
		arr_BasketProductList = new ArrayList<BasketProduct> ();
		arr_BasketProductList.add(m_BasketProduct1);
		arr_BasketProductList.add(m_BasketProduct2);
		arr_BasketProductList.add(m_BasketProduct3);
		
		m_BasketProductList = arr_BasketProductList;


		
		return;
	}
	
}
