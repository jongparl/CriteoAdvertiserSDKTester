package com.example.com.criteo.sdktestapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.criteo.events.*;
import com.criteo.events.product.Product;
import com.mobileapptracker.MATEvent;
import com.mobileapptracker.MATEventItem;
import com.mobileapptracker.MobileAppTracker;
//import com.example.com.criteo.testapp.R;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;

import java.io.IOException;     
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;


/**
 * @author Tester
 * @author Tester
 */
public class MainActivity extends Activity{
	   //public MobileAppTracker mobileAppTracker = null;
	   String m_My_Advertiser_ID = null;
	   String m_My_Conversion_Key = null;
	   public EventService criteoEvent=null;
	   
	   Button m_ViewHome = null;
	   Button m_ViewProduct = null;
	   Button m_ViewList = null;
	   Button m_Purchase = null;
	   Button m_ViewCart = null;
	   Button m_Search = null;
	   
	   PrintInfo m_PrintInfo = null;
	   
	   // Criteo data object
	   CriteoDO m_CriteoDO = null;
	
	   
	   //Make date to YYYY-MM-DD type
	   SimpleDateFormat m_Criteo_Date;
	   Date m_Date1;
	   Date m_Date2;
	   
	   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
             
        //Create Criteo data object
        m_CriteoDO = new CriteoDO();
        
        //Create Print info module
        m_PrintInfo = new PrintInfo();
        
        // Assign Button resources  
        onButtonInit();
        
        //Initialize Criteo event Test1 : Passed
        //criteoEvent = new EventService(getApplicationContext()); 
        //criteoEvent.setCountry("KR");
        //criteoEvent.setLanguage("kr");
        //criteoEvent.setCustomerId(m_CriteoDO.m_Advertiser_ID);
        
        // Test2
        criteoEvent = new EventService(getApplicationContext(), "KR", "kr" );
        criteoEvent.setCustomerId(m_CriteoDO.m_Advertiser_ID);
        
        // Test3
        //criteoEvent = new EventService(getApplicationContext(), "KR", "kr", m_CriteoDO.m_Advertiser_ID);
        
        //Create the app launch event
        AppLaunchEvent appLaunch = new AppLaunchEvent();
        
        criteoEvent.send(appLaunch);
        
        // Collect Google Play Advertising ID; REQUIRED for attribution of Android apps distributed via Google Play
        new Thread(new Runnable() {
            @Override public void run() {
                // See sample code at http://developer.android.com/google/play-services/id.html
  
            }
        }).start();
        
    }

    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		 
        
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //Not implemented anything here
        
        return true;
    }
   
	 /**
     * This method initializes resources and Criteo retargeting events.
     * @param void
     * @return void
     */
	public void onButtonInit() {
        // Inflate the menu; this adds items to the action bar if it is present.
		
		// MAT parameters setting for Criteo ViewHome event
		m_ViewHome =(Button)findViewById(R.id.button1);
		m_ViewHome.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("Criteo ViewHome onClick", "Button1");
				
				//
				HomeViewEvent homeView = new HomeViewEvent();
				homeView.addExtraData("DateExtraData", m_Date1);
				homeView.addExtraData("FloatExtraData", (float) 55.34);
				homeView.addExtraData("StringExtraData", "222extradata");
				homeView.addExtraData("IntExtraData", 100);
				
				criteoEvent.send(homeView);
				
				
			}
		});
		
		// MAT parameters setting for Criteo ViewProduct event
		m_ViewProduct =(Button)findViewById(R.id.button2);
		m_ViewProduct.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("Criteo ViewProduct onClick", "Button2");
				/* Test 1
				Product product = new Product("1111111", (float) 999.85);
				
				float d_price = product.getPrice();
				String str = product.getProductId();
				
				ProductViewEvent pvEvent = new ProductViewEvent(product);
				
				Currency currency = Currency.getInstance("USD");
				
				pvEvent.setCurrency(currency);
				*/ 
				
				// Test 2 
				//ProductViewEvent pvEvent = new ProductViewEvent("333444555", (float) 344.65);				
				//Currency currency = Currency.getInstance("USD");
				
				//Test 3 
				Product product = new Product("1111111", (float) 999.85);
				Currency currency = Currency.getInstance("USD");
				ProductViewEvent pvEvent = new ProductViewEvent(product, currency, m_Date1, m_Date2);
				
				//pvEvent.setCurrency(currency);
				
				criteoEvent.send(pvEvent);
				
			}
		});
		
		// MAT parameters setting for Criteo ViewList event
		m_ViewList =(Button)findViewById(R.id.button3);
		m_ViewList.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("Criteo ViewList onClick", "Button3");
				
				ProductListViewEvent pvlEvent = new ProductListViewEvent(m_CriteoDO.m_ListProduct);
				
				criteoEvent.send(pvlEvent);
			}
		});
		
		// MAT parameters setting for Criteo ViewBasket event
		m_ViewCart =(Button)findViewById(R.id.button4);
		m_ViewCart.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("Criteo ViewCart onClick", "Button4");
				
				BasketViewEvent bvEvent = new BasketViewEvent(m_CriteoDO.m_BasketProductList);
				
				criteoEvent.send(bvEvent);			
			}
		});
		
		// MAT parameters setting for Criteo trackTransaction event
		m_Purchase =(Button)findViewById(R.id.button5);
		m_Purchase.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("Criteo Purchase onClick", "Button5");
				
				TransactionConfirmationEvent tcEvent = new TransactionConfirmationEvent("123456789999", m_CriteoDO.m_BasketProductList);
				
				criteoEvent.send(tcEvent);	
				
			}
		});
		
		// MAT parameters setting for Criteo TransactionConfirmation with dates
		m_Search =(Button)findViewById(R.id.button6);
		m_Search.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				Log.d("Criteo Search onClick", "Button6");
				
				//Google recommends use GregorianCalendar because Date(String) method deprecated. 
				//Note for using (2013,11,12) does give the Thu Dec 12 00:00:00 EST 2013
				GregorianCalendar Date1 = new GregorianCalendar(2015, 05, 07);  // This means 2015-06-07
				GregorianCalendar Date2 = new GregorianCalendar(2015, 06, 10);  // This means 2015-07-10 
				
				DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
				try {
					m_Date1 = sdFormat.parse("2015-05-01");
					m_Date2 = sdFormat.parse("2015-06-30");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				TransactionConfirmationEvent tcEvent = new TransactionConfirmationEvent("5678901234", m_CriteoDO.m_BasketProductList, m_Date1, m_Date2 );
				
				criteoEvent.send(tcEvent);
				
			}
		});
		
		// MAT parameters setting for Criteo User Level event
				m_Search =(Button)findViewById(R.id.button7);
				m_Search.setOnClickListener(new Button.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						// TODO Auto-generated method stub
						Log.d("Criteo Search onClick", "Button7");
						

					}
				});
				
				
				// MAT parameters setting for Criteo User Status event
				m_Search =(Button)findViewById(R.id.button8);
				m_Search.setOnClickListener(new Button.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						// TODO Auto-generated method stub
						Log.d("Criteo Search onClick", "Button8");
						

					}
				});
				
				// MAT parameters setting for Criteo Achievement Unlocked event
				m_Search =(Button)findViewById(R.id.button9);
				m_Search.setOnClickListener(new Button.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						// TODO Auto-generated method stub
						Log.d("Criteo Search onClick", "Button9");
						
					}
				});
				
        return ;
    }
	

}
