public class convert{
	public static void main(String[] args){
		int i=1,j=2;
		System.out.println(i);
		System.out.println(j);
		float f1 = 0.1f;
		System.out.println(f1);
		float f2 = 123f;
		System.out.println(f2);
		long l1=12345678,l2=8888888888l;
		System.out.println(l2);
		System.out.println(l1);
		double d1=2e20,d2=124;
		System.out.println(d1);
		System.out.println(d2);
		byte b1=1,b2=2,b3=(byte)129;
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		j=j+10;
		System.out.println(j);
		i=i/10;
		System.out.println(i);
		//i=i*0.1;
		//System.out.println(i);
		char c1='c',c2=125;
		System.out.println(c1);
		System.out.println(c2);
		byte b=(byte)(b1-b2);
		System.out.println(b);
		char c =(char)(c1+c2-1);
		System.out.println(c);
		float f3=f1+f2;	
		System.out.println(f3);
		float f4=(float)(f1+f2*0.1);
		System.out.println(f4);
		double d = d1*i+j;
		System.out.println(d);
		float f = (float)(d1*5+d2);
		System.out.println(f);
	}
}
