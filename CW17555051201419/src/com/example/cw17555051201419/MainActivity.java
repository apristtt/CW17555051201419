package com.example.cw17555051201419;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
	
	EditText name;
	ListView list;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        name = (EditText) this.findViewById(R.id.name);
        list = (ListView) this.findViewById(R.id.list);
        list.setOnItemClickListener(this);
    }
    
    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getData();
	}

	public void getData() {
		// TODO Auto-generated method stub
		String url = "http://192.168.144.135/phpsr32/select_all.php";
		SelectTask task = new SelectTask(this, url);
		task.execute();
	}
	
	public void click(View v){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//	prepair data
		String date = format.format(new Date());
		List<NameValuePair> para = new ArrayList<NameValuePair>();
		para.add(new BasicNameValuePair("pName", name.getText().toString()));
		para.add(new BasicNameValuePair("pDate", date));
		String url = "http://192.168.144.135/phpsr32/insert.php";
		InsertDeleteTask task = new InsertDeleteTask(this, url, para);
		task.execute();
		getData();
		name.setText(null);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		// Delete data
		String row = ((TextView)view.findViewById(R.id.item_id)).getText().toString();
		List<NameValuePair> para = new ArrayList<NameValuePair>();
		para.add(new BasicNameValuePair("_id", row));
		String url = "http://192.168.144.135/phpsr32/i.php";
		InsertDeleteTask task = new InsertDeleteTask(this, url, para);
		task.execute();
		getData();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
