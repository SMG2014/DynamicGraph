package your.tutorial.graph;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class MockData extends Service{

	public final static String action="CONNECTED";
	public final static String Data="Value";

	public static Point getDataFromReceiver(int x)
	{	
		return new Point(x, 40);
		//40 was replaced by generateRandomData() function
	}
	
/*	void generateRandomData()
	{   Random random = new Random();
		int i=10;
	//	broadcastUpdate(ACTION_DATA_SUPPLY);
		broadcastUpdate(action);
		int someInt = random.nextInt(40);
	}
	*/
	
	public void broadcastUpdate(final String action)
			//final String action,final BluetoothGattCharacteristic characteristic			
	{
	final Intent intent = new Intent(action);
	int i=10;
	intent.putExtra(Data, i);
	sendBroadcast(intent);
	i++;
	}

	@Override
	public IBinder onBind(Intent intent) {
    // TODO: Return the communication channel to the service.
	throw new UnsupportedOperationException("Not yet implemented");	
	}
	
	@Override
	public boolean onUnbind(Intent intent) 
	{
		// After using a given device, you should make sure that
		// BluetoothGatt.close() is called
		// such that resources are cleaned up properly. In this particular example,
		// close() is
		// invoked when the UI is disconnected from the Service.
	    return super.onUnbind(intent);
	}
	
	//This function is called every time the client
	//starts the service by explicitly calling the startservice
	
	public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        broadcastUpdate(action);
        return START_STICKY;
    }


}
