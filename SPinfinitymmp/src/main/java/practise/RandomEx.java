package practise;

import java.util.Random;

public class RandomEx {

	public static void main(String[] args) {
		Random rand = new Random();
		int i = rand.nextInt(100);
		System.out.println(i);
		String emailID = "QA AutomationTeam@gmail.com";
		
		/*char ch = 'A';
		int i1 = ch;
		System.out.println(i1);
		
		int i2 =122;
		char ch1 = (char) i2;
		System.out.println(ch1);*/
		
		int u = 65+ rand.nextInt(26);
		System.out.println("UpperCase :: " + u);
		char upperCase =(char )u;
		
		int l = 97+rand.nextInt(122-97+1);
		System.out.println("lowercase ::"+ l);
		char lowercase = (char)	i;
		
		System.out.println(emailID+upperCase+lowercase+i+"gmail.com");

	}

}
