package com.example.sweet_xue.customswitchbutton;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.CompoundButton;
import com.example.sweet_xue.customswitchbutton.switchbutton.SwitchButton;

public class StyleActivity extends ActionBarActivity {

	private SwitchButton mFlymeSb, mMiuiSb, mCustomSb, mDefaultSb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_style);

		SwitchButton disableSb = (SwitchButton) findViewById(R.id.sb_disable_control);
		mFlymeSb = (SwitchButton) findViewById(R.id.sb_custom_flyme);
		mMiuiSb = (SwitchButton) findViewById(R.id.sb_custom_miui);
		mCustomSb = (SwitchButton) findViewById(R.id.sb_custom);
		mDefaultSb = (SwitchButton) findViewById(R.id.sb_default);

		disableSb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				mFlymeSb.setEnabled(isChecked);
				mMiuiSb.setEnabled(isChecked);
				mCustomSb.setEnabled(isChecked);
				mDefaultSb.setEnabled(isChecked);
			}
		});
	}

}
