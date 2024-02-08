package practise;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateExample {

	public static void main(String[] args) {
		
		Calendar calendar = Calendar.getInstance(); // Calendar is an abstract class so we cannot create an object of an abstract class only we can call its methods so we are not using new
		calendar.add(Calendar.DAY_OF_MONTH, 5);
		Date d = calendar.getTime();
		System.out.println(d);// calendar.getTime() method get todays date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYY");
        System.out.println(sdf.format(d));
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MMMM/YYY");
        System.out.println(sdf1.format(d));
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MMMMM/YYY");
        System.out.println(sdf2.format(d));
        
        //String date = sdf2.format(d);
       
	}

}
