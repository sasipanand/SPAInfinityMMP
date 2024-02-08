package practise;



import org.iit.healthcare.mmp.util.AppLibrary;

/**
 * Hello world!
 *
 */
public class App extends AppLibrary
{
	
	
	
	
	
	public int add(int a,int b)
	{
		//AppLibrary appLib = new AppLibrary();
		//System.out.println(value);
		int c= a+b;
		return c;
	}
	
	
	
	public boolean validateString()
	{
		String actual ="";
		String expected="";
		boolean result = actual.equals(expected);
		return result;
	}
	 
	 
	 
	
	
	
	public static long getRandomNumber(long n) {
	
	    return (long)(Math.floor(Math.random() * n));
	}
	
	
	
	
    public static void main( String[] args )
    {
//    	double d = 12.6;
//    	System.out.println(Math.ceil(d));//largest nearest number
//    	//floor smallest nearest number
//    	
//    	System.out.println("Math.random()   "+  (Math.floor(Math.random()*10000000)));
//    	System.out.println("Math.random()   "+   (Math.floor(Math.random()*1000000)));
//    	System.out.println("Math.random()   "+  (Math.floor (Math.random()*100000)));
//    	System.out.println("Math.random()   "+  (Math.floor (Math.random()*10000)));
    	
    	
    	
    	
    	long l = getRandomNumber(10000000);
     	System.out.println("checking "  + l);
//    	
//    	String s="1,3380.05";
//    	double d4 = Double.parseDouble(s.replace(",",""));
//    	System.out.println(d4);
//    	
//    	
//    	//Logic 1
//    	double d3 = 19.99;
//    	String value = String.format("%.1f",d3);
//    	System.out.println("value" + value );
//  
//    	
//    	
//    	//Logic 2
//    	
//    	double d2 = 19.99998*100.0;
//    	
//    	
//    	
//    	System.out.println("d2 " + d2);
//    	System.out.println("Math.round(d2) " + Math.round(d2));
//    	System.out.println(Math.round(d2)/100.0 );
//    	
//    	
////        String s="	+ 19.99";
////        double actual = Double.parseDouble(s.replace("+","").trim());
////        System.out.println("Actual"+ actual);
//        
//        /**
//         * CPrice-PClose
//         * --------------- * 100
//         *   PClose
//         * 
//         */
//        double cPrice=79.38;
//        double pClose=66.15;
//        double expected = Math.round((cPrice-pClose)*100.0/pClose);
//        System.out.println("Expected"+ expected);
       
        
    }
}
