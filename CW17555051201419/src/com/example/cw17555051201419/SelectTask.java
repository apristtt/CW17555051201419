package com.example.cw17555051201419;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SelectTask extends AsyncTask<Void, Void, String>{

	Context mContext;
	String mUrl;
	
	public SelectTask(Context context, String url) {
		super();
		mContext = context;
		mUrl = url;
	}
	
	//	instantly do when class is called next call method onPostExecute()
	@Override
	protected String doInBackground(Void... params) {
		// TODO Auto-generated method stub
		String jsonString = JsonHttp.makeHttpRequest(mUrl);
		return jsonString;
	}

	@Override
	protected void onPostExecute(String jsonString) {
		// TODO Auto-generated method stub
//		super.onPostExecute(result);
		ArrayList<HashMap<String, String>> nameList;
		try {
			JSONObject json = new JSONObject(jsonString);
			int success = json.getInt("success"); // have data? success = 1
			if (success == 1) {
				JSONArray people = json.getJSONArray("people");
				nameList = new ArrayList<HashMap<String, String>>();
				//	read JSON
				for (int i = 0; i < people.length(); i++) {
					JSONObject person = people.getJSONObject(i);
					String id = person.getString("_id");
					String name = person.getString("pName");
					String date = person.getString("pDate");
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("_id", id);
					map.put("pName", name);
					map.put("pDate", date);
					nameList.add(map);
					String[] key = new String[]{
							"_id",
							"pName",
							"pDate"
					};
					int[] views = new int[]{
							R.id.item_id,
							R.id.item_name,
							R.id.item_date
					};
					// Show ListView
					ListAdapter adapter = new SimpleAdapter(mContext, nameList, R.layout.item, key, views);
					((MainActivity)mContext).list.setAdapter(adapter);
				}
				}else if(success == 0){  //db = null
					((MainActivity)mContext).list.setAdapter(null);
					// Show toast
					String msg = json.getString("message");
					Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
