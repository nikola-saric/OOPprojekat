package Servisi;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class testMain {
	public static void main(String[] args) {
		Calendar calendar = new GregorianCalendar();
		Date vremeIzdavanja = calendar.getTime();
		System.out.println(vremeIzdavanja);
	}
	
}
