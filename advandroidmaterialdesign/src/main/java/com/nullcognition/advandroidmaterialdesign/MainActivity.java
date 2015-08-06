package com.nullcognition.advandroidmaterialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

// Material Design: uses 500 as the base color, pic a lighter color in the 100 range, and a darker
// color in the 700-800 range - after choose an A for accent color to harmonize with the first

public class MainActivity extends AppCompatActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		int id = item.getItemId();
		if(id == R.id.action_settings){

			Toast.makeText(MainActivity.this, "Starting main2Activity", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(this, ActivityDetail.class));
			return true;
		}

		if(false){

			ActivityOptionsCompat aoc = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
			ActivityCompat.startActivity(this, new Intent(this, MainActivity.class), aoc.toBundle());
		}

		return super.onOptionsItemSelected(item);
	}
}


// transitions
/**
 give the destination imageview the android:transitionName = "@string/detail_icon_transition"

 then in the from view, viewCompat.setTransitionName(forecastAdapterViewHolder.iconView, "iconView"+position);
  which is the unique name given when trying to return from the activity
 If the sharedview is filled out when the activity is first created, do no more.

 But if it is a dynamic task,  you can supportPostponeEnterTransition(); until things load, then,
 supportStartPostponedEnterTransition(); when things are ready


 you will want to <attr name ="sharedElementTransition" format="boolean"/> and add it to the fragment
 creation as an argument, which is read on onCreate, thus reading the attribute will allow postponing the transition
 in the fragment set app:sharedElementTransitions = " true"
 in the fragments onActivityCreated if(holdForTransition) { getActivity().supportPostponeEnterTransition();}

 for recyclerview use the onPredrawListener to wait until children have been inflated to start the transition,
 for detail fragment onLoadFinished is fine.

 CoordinatorLayout will is the nested parent then have support appbarlayout, support toolbar with the
 app:layout_scrollFlags = "scroll|enterAlways"

 below that you will have the app:layout_behavior="@string/appbar_scrolling_behaviour"
 give the other view a layout_anchor = "someViewElement" and a layout_anchorGravity = "fill"
 see forecast_fragment.xml

 final AppBarLayout appbarView = (AppBarLayout)rootView.findViewById(R.id.appbar);
         if (null != appbarView) {
             ViewCompat.setElevation(appbarView, 0);
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                 mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                     @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                     @Override
                     public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                         if (0 == mRecyclerView.computeVerticalScrollOffset()) {
                             appbarView.setElevation(0);
                         } else {
                             appbarView.setElevation(appbarView.getTargetElevation());
                         }
                     }
                 });
             }
         }
 which will add the shadow on the toolbar when the it is not flush with the view under it in position,
 but will add the shadow when the toolbar is over the bottom element due to an upwards scroll, thus
 the toolbar has greater elevation



























 **/
