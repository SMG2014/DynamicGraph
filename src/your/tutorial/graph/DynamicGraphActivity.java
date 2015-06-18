package your.tutorial.graph;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import your.tutorial.graph.DynamicGraphView;

/*4/8/2015 - STOPPED WORKING ON BROADCAST
 * Using this app to learn how to design the app,
 * This class deals with adding fragment tabs to the DynamicGraphicActivity
 * Just like the TI app
 * 
 */

public class DynamicGraphActivity extends ViewPagerActivity
{   String TAG="CONNECTED";
	private DynamicGraphView DynamicGraphview=null;
	private HistoryView Historyview=null ;
	int i=0; //counter to check 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		DynamicGraphview = new DynamicGraphView();
		Historyview= new HistoryView();
    startService(new Intent(this, MockData.class));
		mSectionsPagerAdapter.addSection(DynamicGraphview, "Graph Display");
		mSectionsPagerAdapter.addSection(Historyview,"History");
					
}
	
	@Override
		protected void onPostResume() {
			// TODO Auto-generated method stub
		//This function is called after the activity is resumed
		//It will start the service
			super.onPostResume();
			
		}
	
	
	private final BroadcastReceiver DataReceiver = new BroadcastReceiver() 
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			final String action = intent.getAction();
			Log.d(TAG, "action = " + action);
			
			if (DynamicGraphview != null) 
			{
			//int I=intent.getIntExtra(MockData.Data, -1);
		//		Toast.makeText(context, "Broadcast  "+ intent.getIntExtra(MockData.Data, -1),
				//			Toast.LENGTH_LONG).show();	

			
				 //returns -255
			}
				 else 
				{
					Log.e(TAG, "DynamicGraphView is not ready");
				}
		}
	};

	@Override
	protected void onResume() 
	{
		Log.d(TAG, "onResume");
		super.onResume();
		registerReceiver(DataReceiver, makeDataUpdateIntentFilter());
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "onPause");
		super.onPause();
		unregisterReceiver(DataReceiver);
	}
	private static IntentFilter makeDataUpdateIntentFilter() 
	{
		final IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(MockData.action);
		return intentFilter;
	}
	
	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");
		DynamicGraphview=null;
		Historyview=null;
		super.onDestroy();
	}
}
