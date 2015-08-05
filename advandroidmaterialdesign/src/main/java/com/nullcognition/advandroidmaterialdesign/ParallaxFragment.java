package com.nullcognition.advandroidmaterialdesign;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 Parallax scrolling with recyclerview via the dx and dy cords of the scroll.
 */
public class ParallaxFragment extends Fragment{

	public ParallaxFragment(){
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_parallax, container, false);

//		RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
//
//		final View parallaxView = rootView.findViewById(R.id.parallax_bar);
//		if(null != parallaxView){
//
//			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
//
//				mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
//
//					@TargetApi(Build.VERSION_CODES.HONEYCOMB)
//					@Override
//					public void onScrolled(RecyclerView recyclerView, int dx, int dy){
//						super.onScrolled(recyclerView, dx, dy);
//
//						int max = parallaxView.getHeight();
//						if(dy > 0){
//							parallaxView.setTranslationY(Math.max(-max, parallaxView.getTranslationY() - dy / 2));
//						}
//						else{
//							parallaxView.setTranslationY(Math.min(0, parallaxView.getTranslationY() - dy / 2));
//						}
//					}
//				});
//			}
//		}

		return rootView;
	}

	@Override
	public void onDestroyView(){
		super.onDestroyView();
//		mRecyclerView.clearOnScrollListeners();
	}
}
