package com.youxiachai.animutils.example;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author youxiachai
 * @date   2013-5-30
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.bShopcart).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if( getShopCart() == null){
					showShopCart();
				}else{
					hideShopCart((ViewGroup) findViewById(R.id.shopcart));
				}
			}
		});
		
		findViewById(R.id.bShop).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 //TODO Auto-generated method stub
	
				addShop();
			}
		});
		
	}
	
	/**播放view 的动画
	 * @param v
	 * @param id
	 */
	private void startViewAnimation(View v,int id){
		Animation animation = AnimationUtils.loadAnimation(this, id);
		v.startAnimation(animation);
	}
	
	private void addShop(){
		if( getShopCart() != null){
//			getScene().removeView(v);
//			getScene().addView(v);
//			startViewAnimation(v, R.anim.block_move_right);
			Button b = new Button(this);
			ViewGroup.LayoutParams lp = new LayoutParams(200, 50);
			b.setText("xxx");
			b.setLayoutParams(lp);
			getShopCart().addView(b);
			startViewAnimation(b, R.anim.block_move_right);
		}else{
			Animation shopCartAnim = AnimationUtils.loadAnimation(this, R.anim.in_translate_top);
			shopCartAnim.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub
					new Handler().postDelayed(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							addShop();
						}
					}, 500);
				}
			});
			
			View v = buildShopCart();
			getScene().addView(v);
			v.startAnimation(shopCartAnim);
		}
	}
	
	private ViewGroup getShopCart(){
		return (ViewGroup) findViewById(R.id.shopcart);
	}
	
	
	/**
	 * 显示购物车
	 */
	private void showShopCart(){
		View v= buildShopCart();
		
		//利用@android:anim/bounce_interpolator 出现的时候弹一下
		startViewAnimation(v, R.anim.in_translate_top);
		getScene().addView(v);
	}
	
	/**隐藏购物车
	 * @param v
	 */
	private void hideShopCart(ViewGroup v){
		TextView tv = (TextView) v.getChildAt(0);
		tv.setText("向下一点，然后往顶部抛离开");
		
		// 利用 @android:anim/anticipate_interpolator 向下一点，然后离开
		startViewAnimation(v, R.anim.out_translate_top);
		getScene().removeView(v);
	}
	
	
	
	
	/**创建购物车对象
	 * @return
	 */
	private View buildShopCart(){
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		TextView tv = new TextView(this);
		tv.setTextColor(Color.WHITE);
		tv.setText("从顶部下来，到底部的时候弹一下");
		ll.setId(R.id.shopcart);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(400, 400);
		lp.setMargins(0, 10, 0, 0);
		ll.setBackgroundColor(getResources().getColor(R.color.pink));
		ll.setLayoutParams(lp);
		ll.setGravity(Gravity.CENTER);
		ll.addView(tv);
		return ll;
	}
	
	/**获得动画播放view group
	 * @return
	 */
	private ViewGroup getScene(){
		return (ViewGroup) findViewById(R.id.animContext);
	}
	
}
