package your.tutorial.graph;


import java.text.DateFormat;
import java.util.Date;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.app.ActionBar.LayoutParams;


public class HistoryView extends Fragment {

	//in order to implement the ClickListener
	//implements onClickListener must be added :)
		
public final String ACTION_DATA_STORED="your.tutorial.graph.DATA_STORED";
int u=10;
private DynamicGraphActivity mActivity = null;

private static String TAG = "DynamicGraphActivity";
private static String SMG="George";
private Context mContext;
		
		//Deals with charting (Look at the sliding drawer app)
private GraphicalView mChartView;	
public LinearLayout Container;	
private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
private XYSeriesRenderer renderer = new XYSeriesRenderer(); // This will be used to customize line 1
private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); // Holds a collection of XYSeriesRenderer and customizes the graph
private XYSeries series = new XYSeries("PPG");
int i=0;
	    
		/** Called when the activity is first created. */
	//This is the same onCreateView function is used when we want to inflate another view
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
Bundle savedInstanceState) {
Log.i(TAG, "onCreateView");
super.onCreate(savedInstanceState);
View v = inflater.inflate(R.layout.historygraph, container, false);
mActivity = (DynamicGraphActivity) getActivity();
mContext = mActivity.getApplicationContext();
Container = (LinearLayout) v.findViewById(R.id.chart_container1); 
Graph();
return v;					    
}

public void Graph()
{
//This function only creates the features of the graph
	mDataset.addSeries(series);
	mRenderer.setXAxisMin(0);
	mRenderer.setXAxisMax(50);
	mRenderer.setYAxisMin(0);
	mRenderer.setYAxisMax(90);   
//	    mRenderer.addSeriesRenderer(renderer);
	// set some renderer properties
	renderer.setPointStyle(PointStyle.CIRCLE);
	renderer.setFillPoints(true);
//	    renderer.setDisplayChartValues(true);
	// Customization time for line 1!
	renderer.setColor(Color.RED);
	renderer.setPointStyle(PointStyle.DIAMOND);
	renderer.setFillPoints(true);
	// Enable Zoom
	mRenderer.setZoomButtonsVisible(true);	
	String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
	mRenderer.setXTitle(currentDateTimeString);
	//xAxis.titleLabel.textColor 
	mRenderer.setShowGrid(true);
	mRenderer.setYTitle("Heart Rate (bpm)");
	mRenderer.setAxisTitleTextSize(20);
	// Add single renderer to multiple renderer
	mRenderer.addSeriesRenderer(renderer);	
	mRenderer.setZoomEnabled(true, true);
	mRenderer.setPanEnabled(true, true); //use to lock or unlock the x-axis and the y-axis
	mRenderer.setScale((float) 1);
	mRenderer.setYLabels(15);
	mRenderer.setYLabelsAlign(Align.RIGHT);
	mRenderer.setXLabels(15);
	mRenderer.setXLabelsAlign(Align.RIGHT);

	mChartView = ChartFactory.getLineChartView(getActivity().getApplicationContext(),mDataset , mRenderer);		      
	Container.addView(mChartView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	        		        
}

}


