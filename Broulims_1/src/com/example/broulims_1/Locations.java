package com.example.broulims_1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Locations extends Activity {

	ListView locations;
	
	String[] locationItems = {
			"Afton -\n\t141 North Washington Street \n\tAfton, WY 83110 \n\tMon-Sat 7:00am-10:00pm \n",
			"Alpine - \n\t100 Grey's River Road \n\tAlpine, WY 83129 \n\tMon-Sat 8:00am-9:00pm \n\tSunday 9:00am-6:00pm \n",
			"Driggs - \n\t240 South Main Street \n\tDriggs, ID 83422 \n\tMon-Sat 7:00am-11:00pm \n",
			"Montpelier -\n\t130 South 4th Street \n\tMontpelier, ID 83254 \n\tMon-Sat 7:00am-10:00pm \n",
			"Rexburg - \n\t124 West Main \n\tRexburg, ID 83440 \n\tMon-Sat 6:00am-12:00am \n",
			"Rigby - \n\t150 North State Street \n\tRigby, ID 83442 \n\tMon-Sat 7:00am-11:00pm \n",
			"Shelley \n\t570 South State \n\tShelley, ID 83274 \n\tMon-Sat 7:00am- 10:00pm \n",
			"Soda Springs - \n\t89 2nd South Street \n\tSoda Springs, ID 83276 \n\tMon-Sat 7:00am-9:00pm \n",
			"St. Anthony - \n\t301 Aspen Square \n\tSt. Anthony, ID 83445 \n\tMon-Sat 7:00am-10:00pm \n"
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locations);
		locations = (ListView) findViewById(R.id.location_list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locationItems);
		locations.setAdapter(adapter);
		
		
	}
}
