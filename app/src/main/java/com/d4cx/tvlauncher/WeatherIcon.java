package com.d4cx.tvlauncher;
import android.content.Context;import android.graphics.*;import android.view.View;
final class WeatherIcon extends View {
 final Paint p=new Paint(3);final Path cloud=new Path(),bolt=new Path(),moon=new Path();WeatherCondition condition=WeatherCondition.from(-1,true);boolean day=true;
 WeatherIcon(Context c){super(c);setImportantForAccessibility(IMPORTANT_FOR_ACCESSIBILITY_NO);
  cloud.moveTo(10,27);cloud.cubicTo(0,27,0,15,10,15);cloud.cubicTo(11,3,28,3,30,14);cloud.cubicTo(41,11,46,27,35,27);cloud.close();
  bolt.moveTo(24,26);bolt.lineTo(17,35);bolt.lineTo(23,35);bolt.lineTo(20,43);bolt.lineTo(32,31);bolt.lineTo(25,31);bolt.close();
  moon.moveTo(29,4);moon.cubicTo(8,0,7,30,28,31);moon.cubicTo(19,26,17,11,29,4);moon.close();
 }
 void update(int code,boolean daylight){day=daylight;condition=WeatherCondition.from(code,daylight);setContentDescription(condition.label);invalidate();}
 void sun(Canvas c,float x,float y,float radius){c.drawCircle(x,y,radius,p);for(int i=0;i<8;i++){double a=i*Math.PI/4;float dx=(float)Math.cos(a),dy=(float)Math.sin(a);c.drawLine(x+dx*(radius+3),y+dy*(radius+3),x+dx*(radius+6),y+dy*(radius+6),p);}}
 protected void onDraw(Canvas c){super.onDraw(c);float scale=Math.min(getWidth(),getHeight())/48f;c.save();c.translate((getWidth()-48*scale)/2,(getHeight()-48*scale)/2);c.scale(scale,scale);p.setColor(0xffeeeeee);p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(1.9f);p.setStrokeCap(Paint.Cap.ROUND);p.setStrokeJoin(Paint.Join.ROUND);String k=condition.kind;
  if(k.equals("clear")){if(day)sun(c,24,23,8);else c.drawPath(moon,p);}
  else if(k.equals("unknown")){c.drawLine(17,24,31,24,p);}
  else if(k.equals("fog")){c.drawLine(10,16,37,16,p);c.drawLine(5,24,40,24,p);c.drawLine(12,32,34,32,p);}
  else {
   if(k.equals("partly")){c.save();c.scale(.66f,.66f);if(day)sun(c,18,11,7);else c.drawPath(moon,p);c.restore();}
   c.drawPath(cloud,p);
   if(k.equals("storm")||k.equals("hail")){c.drawPath(bolt,p);if(condition.intensity==3){c.drawLine(9,31,6,38,p);c.drawLine(37,31,34,38,p);}if(k.equals("hail")){c.drawCircle(11,40,1.4f,p);c.drawCircle(35,42,1.4f,p);}}
   else if(k.equals("rain")||k.equals("drizzle")||k.equals("ice")){int n=condition.intensity+1;for(int i=0;i<n;i++){float x=11+i*(25f/(n-1));if(k.equals("drizzle")){p.setStyle(Paint.Style.FILL);c.drawCircle(x,33,1.1f,p);c.drawCircle(x-2,39,1.1f,p);p.setStyle(Paint.Style.STROKE);}else{c.drawLine(x,32,x-3,condition.intensity==3?42:38,p);if(k.equals("ice"))c.drawCircle(x-2,43,1,p);}}}
   else if(k.equals("snow")){int n=condition.intensity==3?3:condition.intensity;for(int i=0;i<n;i++){float x=n==1?23:12+i*(22f/(n-1));float y=i%2==0?36:40;c.drawLine(x-2,y,x+2,y,p);c.drawLine(x-1,y-2,x+1,y+2,p);c.drawLine(x+1,y-2,x-1,y+2,p);}}
  }c.restore();
 }
}
