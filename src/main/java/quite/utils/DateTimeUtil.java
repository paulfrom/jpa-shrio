package quite.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @�ļ����� ʱ��Ĺ�����
 * @version CVN #V1# #2014��3��20��#
 */
public final class DateTimeUtil{

	/**
	 * ��õ�ǰ��ϵͳʱ��
	 * @return ��ǰ��ϵͳ����
	 */
	public static Date getCurrentTime(){
		return new Date();
	}

	/**
	 * ��õ�ǰ��ϵͳ���ڣ�������ʱ����
	 * @return ��ǰ��ϵͳ����
	 */
	public static Date getCurrentDate(){
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.clear(Calendar.HOUR);
		c.clear(Calendar.MINUTE);
		c.clear(Calendar.SECOND);
		c.clear(Calendar.MILLISECOND);
		date = c.getTime();
		return date;
	}

	/**
	 * �����ַ�����ʽ���ڣ�����Date
	 * @param date
	 * @return ��ǰ��ϵͳ����
	 */
	public static Date getDate(String date){
		Timestamp ts = Timestamp.valueOf(date);
		return ts;
	}

	/**
	 * �õ���ǰϵͳ����,��ʽ��"yyyy-MM-dd"
	 * @return String
	 */
	public static String getCurDate(){
		return getFormatDate(getCurrentDate());
	}

	/**
	 * ����ַ������͵ĸ�ʽ������ "yyyy-MM-dd"
	 * @param date
	 * @return sDate
	 */
	public static String getFormatDate(Date date){
		return getFormatDate(date,BaseConstants.DATE_PATTERN);
	}

	/**
	 * ����ַ������͵ĸ�ʽ��ʱ�� yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return sDate
	 */
	public static String getFormatTime(Date date){
		return getFormatDate(date,BaseConstants.DATETIME_PATTERN);
	}

	/**
	 * ����ַ������͵ĸ�ʽ������
	 * @param date
	 * @param pattern ʱ���ʽ
	 * @return sDate String
	 */
	public static String getFormatDate(Date date, String pattern){
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	/**
	 * �÷��������ڸ�ʽ��Ϊ��׼���ڸ�ʽ�ַ���
	 * @param date ��ת��������
	 * @return ��׼���ڸ�ʽ�ַ���
	 */
	public static String getCriterionFormatDate(Date date){
		SimpleDateFormat format = new SimpleDateFormat(BaseConstants.DATE_PATTERN);
		format.setLenient(false);
		return format.format(date);
	}

	/**
	 * �õ�ָ�����ڵ��·�,��ʽ��yyyy-mm-dd
	 * @return String
	 */
	public static String getDateMonth(Date date){
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy'-'MM'-'dd");
		format1.setLenient(false);
		String dateStr = format1.format(date);
		int begin = dateStr.indexOf('-') + 1;
		int end = dateStr.lastIndexOf('-');
		return dateStr.substring(begin, end);
	}

	/**
	 * �õ�ĳһ��Ŀ�ʼʱ�䣬��ȷ������
	 * @param date ����
	 * @return ĳһ���0ʱ0��0��0������Ǹ�Date
	 */
	public static Date beginOfDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		date = c.getTime();
		return date;
	}

	/**
	 * �õ�ĳһ������ʱ�䣬��ȷ������
	 * @param date ����
	 * @return ĳһ�����һ���0ʱ0��0��0������Ǹ�Date��ȥ1�������õ���Date
	 */
	public static Date endOfDay(Date date){
		date = beginOfDay(date);
		return endOfDayByBeginOfDate(date);
	}

	/**
	 * ����ĳһ��Ŀ�ʼʱ�䣬�õ�ĳһ������ʱ�䣬��ȷ������
	 * @param date ����
	 * @return ĳһ�����һ���0ʱ0��0��0������Ǹ�Date��ȥ1�������õ���Date
	 */
	public static Date endOfDayByBeginOfDate(Date date){
		date = nextDay(date);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, -1);
		date = c.getTime();
		return date;
	}

	/**
	 * �õ�ָ�����ں������������ by Yin Jian
	 * @param date ָ������
	 * @param days ����
	 * @return ��ָ�����ں�������������
	 */
	public static Date afterDaysSinceDate(Date date, int days){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		date = c.getTime();
		return date;
	}

	/**
	 * �ж�����Date�Ƿ���ͬһ��
	 * @param date1 date1
	 * @param date2 date2
	 * @return �������Date��ͬһ�죬�򷵻�true������false
	 */
	public static boolean isTwoDatesInSameDay(Date date1, Date date2){
		boolean flag=false;
		Date preDate1 = preDay(date1);
		Date nextDate1 = nextDay(date1);
		if (date2.after(preDate1) && date2.before(nextDate1)){
			flag=true;
		}
		return flag;
	}

	/**
	 * �õ�ָ�����ڵ���һ��Ŀ�ʼʱ��
	 * @param date ָ��Date
	 * @return ��һ��Ŀ�ʼʱ��
	 */
	public static Date beginOfNextDay(Date date){
		date = nextDay(date);
		return beginOfDay(date);
	}

	/**
	 * �õ�ָ�����ڵ���һ��
	 * @param date ����
	 * @return �������ڵ���һ��
	 */
	public static Date nextDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		return date;
	}

	/**
	 * �õ�ָ�����ڵ�ǰһ��
	 * @param date  ����
	 * @return �������ڵ�ǰһ��
	 */
	public static Date preDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		date = c.getTime();
		return date;
	}

	/**
	 * �õ���ǰ�·ݵ���һ���·�
	 * @return String
	 */
	public static Date addMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		date = c.getTime();
		return date;
	}

	/**
	 * �õ�������·�,��ʽ��yyyy-mm
	 * @return String
	 */
	public static String getYearMonth(Date date){
		String yearMonthStr = getCriterionFormatDate(date);
		int index = yearMonthStr.lastIndexOf('-');
		yearMonthStr = yearMonthStr.substring(0, index);
		return yearMonthStr;
	}

	/**
	 * �õ���ǰ�µ����һ��
	 * @return String
	 */
	public static Date getLastDayOfMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		date = c.getTime();
		return date;
	}

	/**
	 * �õ���ǰ�µĵ�һ��
	 * @return String
	 */
	public static Date getFirstDayOfMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		date = c.getTime();
		return date;
	}

	/**
	 * �ж�һ�������Ƿ���ָ����ʱ�����
	 * @return String
	 */
	public static boolean inTimeSegment(Date start, Date end, Date date){
		boolean flag=false;
		start = preDay(start);
		end = nextDay(end);
		if (date.after(start) && date.before(end)){
			flag=true;
		}
		return flag;
	}

	/**
	 * �жϵ�ǰ�����Ƿ���ָ����ʱ�����
	 * @param start ʱ��ο�ʼʱ��
	 * @param end ʱ��ν���ʱ��
	 * @return �����ǰ������ָ��ʱ����ڣ���Ϊtrue������Ϊfalse
	 */
	public static boolean isCurrentDateInTimeSegment(Date start, Date end){
		boolean flag=false;
		Date date = getCurrentDate();
		if (inTimeSegment(start, end, date)){
			flag=true;
		}
		return flag;
	}

	/**
	 * �õ�ͬһ�������������ڵļ������
	 * ��ע��������Ҫ�ύ�������ͳһ����
	 * @param start
	 * @param end
	 * @return
	 */
	public static int betweenDaysInOneMonth(Date start, Date end){
		String startStr = getFormatDate(start, "yyyyMMdd");
		String endStr = getFormatDate(end, "yyyyMMdd");
		int days = Integer.parseInt(endStr) - Integer.parseInt(startStr) + 1;
		return days;
	}

	/**
	 * @author �õ��������ڵļ������
	 * @param start
	 * @param end
	 * @return -1˵����ʼ���ڴ��ڽ�������
	 */
	public static int getBetweenDays(Date start, Date end){
		if (start.after(end)){
			return -1;
		}
		Calendar startC = Calendar.getInstance();
		startC.setTime(start);
		Calendar endC = Calendar.getInstance();
		endC.setTime(end);
		endC.add(Calendar.DAY_OF_YEAR, 1);
		int days = 0;
		do{
			days++;
			startC.add(Calendar.DAY_OF_YEAR, 1);
		}while(startC.before(endC));
		return days;
	}

	public static long getBetweenDays2(Date start, Date end) throws Exception{
		long day=0;
		try {
		    Date date1 = start;
		    Date date2 = end;
		    day=date1.getTime()-date2.getTime();
		    day =day/1000/60/60/24;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return day;
	}

	/**
	 * �õ�ָ���·ݵ�����
	 * @param date
	 * @return
	 */
	public static int daysInMonth(Date date){
		Date start = getFirstDayOfMonth(date);
		Date end = getLastDayOfMonth(date);
		int days = betweenDaysInOneMonth(start, end);
		return days;
	}

	/**
	 * �ж�����ʱ����Ƿ�����ص�
	 * @param start1 ��һ��ʱ��εĿ�ʼʱ��
	 * @param end1 ��һ��ʱ��εĽ���ʱ��
	 * @param start2 �ڶ���ʱ��εĿ�ʼʱ��
	 * @param end2 �ڶ���ʱ��εĽ���ʱ��
	 * @return ��������ص�����true������false
	 */
	public static boolean isTimeOverlap(Date start1,Date end1,Date start2,Date end2){
		boolean flag=false;
		if (inTimeSegment(start1, start2, end2)||inTimeSegment(end1, start2, end2)){
			flag=true;
		}
		return flag;
	}

	/**
	 * �Ѵ����ʱ��͵�ǰ��ϵͳʱ����бȽϣ����ȿ������졣
	 * @param formmartTimeString
	 * 	��ʽ����ʱ���ַ��� ������ ********8λ���ַ���
	 * @return comparedResult 1 �����ʱ�����ϵͳ��ǰʱ�䣬0��ȣ�-1��С�ڡ�
	 */
	public static int compareCurrentTime(String formmartTimeString){
		Calendar currentTime = Calendar.getInstance();
		Calendar comparedTime = (Calendar) currentTime.clone();
		comparedTime.set(Calendar.YEAR, Integer.parseInt(formmartTimeString
				.substring(0, 4)));
		comparedTime.set(Calendar.MONTH, Integer.parseInt(formmartTimeString
				.substring(4, 6)) - 1);
		comparedTime.set(Calendar.DAY_OF_MONTH, Integer
				.parseInt(formmartTimeString.substring(6, 8)));
		int comparedResult = comparedTime.compareTo(currentTime);
		return comparedResult;
	}

	/**
	* ���ڲ�(time1 - time2�����ظ�������time1��time2֮ǰ)
	* @param time1
	* @param time2
	* @return ���ڲ�(���ȿ�����Сʱ)
	*/
	public static long getQuot(Date time1, Date time2) {
	   long quot = 0;
	   try {
		   Date date1 = time1;
		   Date date2 = time2;
		   quot = date1.getTime() - date2.getTime();
		   quot = quot/1000/60/60;
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   return quot;
	}

	public static Date getDateFromFormattingString(String dateString){
		Calendar currentTime = Calendar.getInstance();
		currentTime.set(Calendar.YEAR, Integer.parseInt(dateString.substring(0,
				4)));
		currentTime.set(Calendar.MONTH, Integer.parseInt(dateString.substring(
				5, 7)) - 1);
		currentTime.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateString
				.substring(8, 10)));
		currentTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateString
				.substring(11, 13)));
		currentTime.set(Calendar.MINUTE, Integer.parseInt(dateString.substring(
				14, 16)));
		currentTime.set(Calendar.SECOND, Integer.parseInt(dateString.substring(
				17, 19)));
		return currentTime.getTime();
	}

	public static Date getFormatDate(String str) throws ParseException{
		if (str==null||"".equals(str)){
			return null;
		}
		if (str.length()<=10){
			return getDateByString(str,"yyyy-MM-dd");
		} else{
			return getDateByString(str,"yyyy-MM-dd HH:mm:ss");
		}
	}

	public static Date getDateByString(String str, String pattern)
			throws ParseException{
		SimpleDateFormat df3 = new SimpleDateFormat();
		df3.applyPattern(pattern);
		return df3.parse(str);
	}

	public static Date addDate(Date date, int num){
		return new Date(date.getTime()+num*(long)24*3600*1000);
	}

	public static Long getBetweenDays2(String str1,String str2,String pattern) throws Exception {
		Date date1=getDateByString(str1,pattern);
		Date date2=getDateByString(str2,pattern);
		return getBetweenDays2(date1,date2);
	}
	
}

