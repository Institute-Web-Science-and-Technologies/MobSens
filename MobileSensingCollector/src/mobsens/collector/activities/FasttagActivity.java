package mobsens.collector.activities;

import java.util.Date;

import mobsens.collector.R;
import mobsens.collector.intents.IntentAnnotation;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FasttagActivity extends Activity
{
	private final OnClickListener tagFromButtonText = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			IntentAnnotation.sendBroadcast(FasttagActivity.this, new Date(), ((Button) v).getText().toString());
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fasttag);

		((Button) findViewById(R.id.fasttag_topLeft)).setOnClickListener(tagFromButtonText);
		((Button) findViewById(R.id.fasttag_topRight)).setOnClickListener(tagFromButtonText);
		((Button) findViewById(R.id.fasttag_bottomLeft)).setOnClickListener(tagFromButtonText);
		((Button) findViewById(R.id.fasttag_bottomRight)).setOnClickListener(tagFromButtonText);

		((Button) findViewById(R.id.fasttag_tag)).setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				IntentAnnotation.sendBroadcast(FasttagActivity.this, new Date(), ((EditText) findViewById(R.id.fasttag_value)).getEditableText().toString());
			}
		});

		final SharedPreferences sp = getSharedPreferences("fasttag", MODE_PRIVATE);

		((EditText) findViewById(R.id.fasttag_value)).setText(sp.getString(R.id.fasttag_value + "text", ""));
	}

	@Override
	protected void onPause()
	{
		super.onPause();

		final SharedPreferences sp = getSharedPreferences("fasttag", MODE_PRIVATE);
		final SharedPreferences.Editor spe = sp.edit();
		spe.putString(R.id.fasttag_value + "text", ((EditText) findViewById(R.id.fasttag_value)).getText().toString());
		spe.commit();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);

		outState.putString(R.id.fasttag_value + "text", ((EditText) findViewById(R.id.fasttag_value)).getText().toString());
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		super.onRestoreInstanceState(savedInstanceState);

		((EditText) findViewById(R.id.fasttag_value)).setText(savedInstanceState.getString(R.id.fasttag_value + "text"));
	}
}
