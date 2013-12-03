package com.donarum.tipcalculator;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

public class MainActivity extends Activity {
	
	private static final String BILL = "BILL";
	private static final String TIP = "TIP";
	private static final String TIP_AMOUNT = "TIP_AMOUNT";
	private static final String FINAL_BILL = "FINAL_BILL";
	
	private double bill;
	private int tip;
	private double tipAmount;
	private double finalBill;
	
	private EditText billET;
	private NumberPicker tipNP;
	private EditText tipAmountET;
	private EditText finalBillET;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if(savedInstanceState == null) {
			bill = 0.0;
			tip = 15;
			tipAmount = 0.0;
			finalBill = 0.0;
		} else {
			bill = savedInstanceState.getDouble(BILL);
			tip = savedInstanceState.getInt(TIP);
			tipAmount = savedInstanceState.getDouble(TIP_AMOUNT);
			finalBill = savedInstanceState.getDouble(FINAL_BILL);
		}
		
		billET = (EditText) findViewById(R.id.billEditText);
		tipNP = (NumberPicker) findViewById(R.id.tipNumberPicker);
		tipNP.setMinValue(0);
		tipNP.setMaxValue(100);
		tipNP.setValue(tip);
		tipAmountET = (EditText) findViewById(R.id.tipAmountEditText);
		finalBillET = (EditText) findViewById(R.id.finalBillEditText);
		
		billET.addTextChangedListener(billListener);
		tipNP.setOnValueChangedListener(tipListener);
	}
	
	private OnValueChangeListener tipListener = new OnValueChangeListener() {

		@Override
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			tip = tipNP.getValue();
			updateTipAndFinalBill();
			
		}

	};
	
	private TextWatcher billListener = new TextWatcher() {

		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			
		}

		@Override
		public void onTextChanged(CharSequence value, int arg1, int arg2,
				int arg3) {

			try {
				bill = Double.parseDouble(value.toString());
			} catch (NumberFormatException nfe) {
				bill = 0.0;
			}
			
			updateTipAndFinalBill();
			
		}
		
	};
	
	private void updateTipAndFinalBill() {
		
		tipAmount = bill*tip*0.01;
		finalBill = bill + tipAmount;
		
		tipAmountET.setText(String.format("%.02f", tipAmount));
		finalBillET.setText(String.format("%.02f", finalBill));
	}
	
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		outState.putDouble(BILL, bill);
		outState.putInt(TIP, tip);
		outState.putDouble(TIP_AMOUNT, tipAmount);
		outState.putDouble(FINAL_BILL, finalBill);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
