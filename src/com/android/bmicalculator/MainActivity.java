package com.android.bmicalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
 
public class MainActivity extends Activity {
  // Default string message.
  private final String defaut = "Please enter your information and press the 'Calculate BMI' button.";
  // String message for megafunction
  private final String megaString = "You have a perfect weight!";
     
  Button send = null;
  Button clear = null;
     
  EditText weight = null;
  EditText height = null;
     
  RadioGroup group = null;
     
  TextView result = null;
     
  CheckBox mega = null;
     
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
         
    // Retrieving necessary views
    send = (Button)findViewById(R.id.calc);
         
    clear = (Button)findViewById(R.id.clear);
         
    height = (EditText)findViewById(R.id.height);
    weight = (EditText)findViewById(R.id.weight);
         
    mega = (CheckBox)findViewById(R.id.mega);
         
    group = (RadioGroup)findViewById(R.id.group);
 
    result = (TextView)findViewById(R.id.result);
 
    // Adding a listener per view
    send.setOnClickListener(sendListener);
    clear.setOnClickListener(clearListener);
    height.addTextChangedListener(textWatcher);
    weight.addTextChangedListener(textWatcher);
    mega.setOnClickListener(checkedListener);
  }
 
  private TextWatcher textWatcher = new TextWatcher() {
 
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
      result.setText(defaut);
    }
         
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
      int after) {
   
    }

	@Override
	public void afterTextChanged(Editable s) {
		
	}
  };
     
  // Listener for the send button
  private OnClickListener sendListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      if(!mega.isChecked()) {
        // If megafunction unchecked, retrieve height
        String t = height.getText().toString();
        // and then retrieve weight
        String p = weight.getText().toString();
             
        float tValue = Float.valueOf(t);
             
        // Checking the height is appropriate
        if(tValue == 0)
          Toast.makeText(MainActivity.this, "Your height seems unnaturally small", Toast.LENGTH_SHORT).show();
        else {
          float pValue = Float.valueOf(p);
          // If the user checks the centimeters, we check the id to make sure the second one is selected.
          if(group.getCheckedRadioButtonId() == R.id.radio2)
            tValue = tValue / 100;
 
          tValue = (float)Math.pow(tValue, 2);
          float bmi = pValue / tValue;
          result.setText("Your BMI is " + String.valueOf(bmi));
        }
      } else
        result.setText(megaString);
    }
  };
     
  // Listener for the clear button
  private OnClickListener clearListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      weight.getText().clear();
      height.getText().clear();
      result.setText(defaut);
    }
  };
     
  // Listener for the megafunction button
  private OnClickListener checkedListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
      // We go back to the default text if the text for the megafunction was displayed
      if(!((CheckBox)v).isChecked() && result.getText().equals(megaString))
        result.setText(defaut);
    }
  };
}