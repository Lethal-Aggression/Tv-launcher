package com.d4cx.tvlauncher;
import android.app.*;import android.content.*;import android.graphics.*;import android.graphics.drawable.*;import android.view.*;import android.widget.*;
final class UI {
 static View iconButton(Context c,String label,String type,Runnable click){FrameLayout b=new FrameLayout(c);b.setFocusable(true);b.setFocusableInTouchMode(true);b.setClickable(true);b.setContentDescription(label);Icon i=new Icon(c,type);FrameLayout.LayoutParams p=new FrameLayout.LayoutParams(dp(c,19),dp(c,19),Gravity.CENTER);b.addView(i,p);b.setOnFocusChangeListener((v,f)->{b.setBackground(bg(f?0xffeeeeee:0x00000000,0));i.color=f?0xff111111:0xffdddddd;i.invalidate();b.animate().scaleX(f?1.08f:1).scaleY(f?1.08f:1).setDuration(140).start();});b.setOnClickListener(v->click.run());return b;}
 static LinearLayout appButton(Context c,String name,Drawable logo,Runnable click){
  LinearLayout b=new LinearLayout(c);b.setOrientation(LinearLayout.VERTICAL);b.setGravity(Gravity.CENTER);b.setPadding(dp(c,14),dp(c,18),dp(c,14),dp(c,18));b.setFocusable(true);b.setFocusableInTouchMode(true);b.setClickable(true);b.setBackground(null);b.setContentDescription(name);
  // Rasterize at display size rather than retaining a full-size app banner.
  Bitmap bitmap=Bitmap.createBitmap(dp(c,44),dp(c,44),Bitmap.Config.ARGB_8888);Canvas canvas=new Canvas(bitmap);logo.setBounds(0,0,bitmap.getWidth(),bitmap.getHeight());logo.draw(canvas);
  ImageView icon=new ImageView(c);icon.setImageBitmap(bitmap);icon.setScaleType(ImageView.ScaleType.FIT_CENTER);b.addView(icon,new LinearLayout.LayoutParams(dp(c,44),dp(c,44)));
  icon.setAlpha(.8f);View dot=new View(c);dot.setBackground(bg(0xffeeeeee,0));dot.setAlpha(0);LinearLayout.LayoutParams marker=new LinearLayout.LayoutParams(dp(c,4),dp(c,4));marker.topMargin=dp(c,13);b.addView(dot,marker);
  b.setOnFocusChangeListener((v,f)->{icon.animate().scaleX(f?1.22f:1).scaleY(f?1.22f:1).translationY(f?-dp(c,2):0).alpha(f?1:.8f).setDuration(160).start();dot.animate().alpha(f?1:0).setDuration(160).start();});b.setOnClickListener(v->click.run());return b;
 }
 static int dp(Context c,float n){return (int)(n*c.getResources().getDisplayMetrics().density+.5f);}
 static void full(Activity a){a.getWindow().getDecorView().setSystemUiVisibility(5894);}
 static GradientDrawable bg(int fill,int stroke){GradientDrawable d=new GradientDrawable();d.setColor(fill);d.setCornerRadius(14);if(stroke!=0)d.setStroke(2,stroke);return d;}
 static TextView text(Context c,String s,int size,int color){TextView t=new TextView(c);t.setText(s);t.setTextSize(size);t.setTextColor(color);t.setGravity(17);return t;}
 static LinearLayout button(Context c,String label,String icon,boolean vertical,Runnable click){
  LinearLayout b=new LinearLayout(c);b.setOrientation(vertical?LinearLayout.VERTICAL:LinearLayout.HORIZONTAL);b.setGravity(17);b.setPadding(dp(c,12),dp(c,10),dp(c,12),dp(c,10));b.setFocusable(true);b.setFocusableInTouchMode(true);b.setClickable(true);
  Icon v=new Icon(c,icon); b.addView(v,new LinearLayout.LayoutParams(dp(c,vertical?36:22),dp(c,vertical?36:22)));
  TextView t=text(c,label,14,0xffeeeeee);t.setMaxLines(2);t.setEllipsize(android.text.TextUtils.TruncateAt.END);LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2);if(vertical)p.topMargin=dp(c,8);else {p.width=0;p.weight=1;p.leftMargin=dp(c,10);t.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);}b.addView(t,p);
  b.setBackground(bg(0xc0202020,0xff333333));b.setOnFocusChangeListener((w,f)->{b.setBackground(bg(f?0xffeeeeee:0xc0202020,f?0xffffffff:0xff333333));t.setTextColor(f?0xff111111:0xffeeeeee);v.color=f?0xff111111:0xffeeeeee;v.invalidate();});b.setOnClickListener(w->click.run());return b;
 }
 static class Icon extends View {
  static final java.util.Map<String,Bitmap> cache=new java.util.HashMap<>();Bitmap asset;final RectF bounds=new RectF(0,0,40,40);Paint p=new Paint(3);String type;int color=0xffeeeeee;Icon(Context c,String t){super(c);type=t;int resource=t.equals("home")?R.drawable.nav_home:t.equals("wifi")?R.drawable.nav_wifi:t.equals("apps")?R.drawable.nav_apps:0;if(resource!=0){asset=cache.get(t);if(asset==null){BitmapFactory.Options options=new BitmapFactory.Options();options.inSampleSize=t.equals("home")?8:2;asset=BitmapFactory.decodeResource(c.getResources(),resource,options);cache.put(t,asset);}}setImportantForAccessibility(IMPORTANT_FOR_ACCESSIBILITY_NO);}
  protected void onDraw(Canvas c){super.onDraw(c);c.save();c.scale(getWidth()/40f,getHeight()/40f);if(asset!=null){p.setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.SRC_IN));c.drawBitmap(asset,null,bounds,p);c.restore();return;}p.setColorFilter(null);p.setColor(color);p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(2);p.setStrokeJoin(Paint.Join.ROUND);p.setStrokeCap(Paint.Cap.ROUND);
   if(type.equals("apps")){for(int x=0;x<2;x++)for(int y=0;y<2;y++)c.drawRoundRect(5+x*18,5+y*18,17+x*18,17+y*18,2,2,p);}
   else if(type.equals("wifi")){p.setStrokeWidth(2.3f);c.drawArc(2,4,38,40,225,90,false,p);c.drawArc(9,11,31,33,225,90,false,p);c.drawArc(15,17,25,27,225,90,false,p);p.setStyle(Paint.Style.FILL);c.drawCircle(20,27,1.5f,p);}
   else if(type.equals("refresh")){c.drawArc(7,7,33,33,35,285,false,p);c.drawLine(32,5,32,14,p);c.drawLine(32,14,23,14,p);}
   else if(type.equals("back")){c.drawLine(25,7,12,20,p);c.drawLine(12,20,25,33,p);}
   else if(type.equals("home")){Path z=new Path();z.moveTo(4,18);z.lineTo(20,4);z.lineTo(36,18);z.lineTo(33,18);z.lineTo(33,35);z.lineTo(24,35);z.lineTo(24,25);z.lineTo(16,25);z.lineTo(16,35);z.lineTo(7,35);z.lineTo(7,18);c.drawPath(z,p);}
   else if(type.equals("hdmi")){Path z=new Path();z.moveTo(3,10);z.lineTo(37,10);z.lineTo(37,22);z.lineTo(29,30);z.lineTo(11,30);z.lineTo(3,22);z.close();c.drawPath(z,p);c.drawLine(10,17,30,17,p);}
   else if(type.equals("settings")){c.drawCircle(20,20,13,p);c.drawCircle(20,20,5,p);for(int i=0;i<8;i++){double a=i*Math.PI/4;c.drawLine(20+(float)Math.cos(a)*13,20+(float)Math.sin(a)*13,20+(float)Math.cos(a)*18,20+(float)Math.sin(a)*18,p);}}
   else {c.drawRoundRect(3,7,37,33,4,4,p);Path z=new Path();z.moveTo(17,13);z.lineTo(27,20);z.lineTo(17,27);z.close();c.drawPath(z,p);}c.restore();
  }
 }
}
