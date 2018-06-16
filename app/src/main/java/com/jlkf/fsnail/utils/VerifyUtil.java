package com.jlkf.fsnail.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证工具类
 * 
 * @author lanyan
 * 
 */
public class VerifyUtil {
	/**
	 * 邮箱验证
	 * 
	 * @param target
	 * @return
	 */
	public static boolean isValidEmail(CharSequence target) {
		if (target == null) {
			return false;
		}
		return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
	}

	/**
	 * 检验手机号是否有效
	 */
	public static boolean isMobileNO(String mobiles) {
		if (TextUtils.isEmpty(mobiles)||mobiles.trim().length()!=11) return false;
		for (Character c:mobiles.toCharArray()){
			if (c>57||c<48) return false;
		}

		String regex = "^((13[0-9])|(14[5|7])|(15([0-9]))|(17[0-9])|(18[0-9]))\\d{8}$";
		Pattern p = Pattern
				.compile(regex);
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 验证电话号码 ^[\\d+-]*$
	 */
	public static boolean isPhone(String str) {
		Pattern p = Pattern.compile("^[\\d+-]*$");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 身份证的有效验证
	 * 
	 * @param IDStr
	 *            身份证号
	 * @return 有效：true 无效：false
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public static boolean IDCardValidate(String IDStr) {
		IDStr = IDStr.toLowerCase(Locale.CHINA);
		String errorInfo = "";// 记录错误信息
		String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
				"3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
				"9", "10", "5", "8", "4", "2" };
		// String[] Checker = {"1","9","8","7","6","5","4","3","2","1","1"};
		String Ai = "";

		// ================ 号码的长度 15位或18位 ================
		if (IDStr.length() != 15 && IDStr.length() != 18) {
			errorInfo = "号码长度应该为15位或18位。";
			System.out.println(errorInfo);
			return false;
		}
		// =======================(end)========================

		// ================ 数字 除最后以为都为数字 ================
		if (IDStr.length() == 18) {
			Ai = IDStr.substring(0, 17);
		} else if (IDStr.length() == 15) {
			Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
		}
		if (isNumeric(Ai) == false) {
			errorInfo = "15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
			System.out.println(errorInfo);
			return false;
		}
		// =======================(end)========================

		// ================ 出生年月是否有效 ================
		String strYear = Ai.substring(6, 10);// 年份
		String strMonth = Ai.substring(10, 12);// 月份
		String strDay = Ai.substring(12, 14);// 月份

		if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
			errorInfo = "生日无效。";
			System.out.println(errorInfo);
			return false;
		}

		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		try {
			if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
					|| (gc.getTime().getTime() - s.parse(
							strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
				errorInfo = "生日不在有效范围。";
				System.out.println(errorInfo);
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
			errorInfo = "月份无效";
			System.out.println(errorInfo);
			return false;
		}
		if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
			errorInfo = "日期无效";
			System.out.println(errorInfo);
			return false;
		}
		// =====================(end)=====================

		// ================ 地区码时候有效 ================
		Hashtable<String, String> h = GetAreaCode();
		if (h.get(Ai.substring(0, 2)) == null) {
			errorInfo = "地区编码错误。";
			System.out.println(errorInfo);
			return false;
		}
		// ==============================================

		// ================ 判断最后一位的值 ================
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi
					+ Integer.parseInt(String.valueOf(Ai.charAt(i)))
					* Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];
		Ai = Ai + strVerifyCode;

		if (IDStr.length() == 18) {
			if (Ai.equals(IDStr) == false) {
				errorInfo = "身份证无效，最后一位字母错误";
				System.out.println(errorInfo);
				return false;
			}
		} else {
			System.out.println("所在地区:" + h.get(Ai.substring(0, 2).toString()));
			System.out.println("新身份证号:" + Ai);
			return true;
		}
		// =====================(end)=====================
		System.out.println("所在地区:" + h.get(Ai.substring(0, 2).toString()));
		return true;
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		}
		return false;
		/*
		 * 判断一个字符时候为数字 if(Character.isDigit(str.charAt(0))) { return true; }
		 * else { return false; }
		 */
	}

	/**
	 * 判断字符串是否为日期格式
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDate(String strDate) {
		Pattern pattern = Pattern
				.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher m = pattern.matcher(strDate);
		return m.matches();
	}

	/**
	 * 设置地区编码
	 * 
	 * @return Hashtable 对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Hashtable GetAreaCode() {
		Hashtable hashtable = new Hashtable();
		hashtable.put("11", "北京");
		hashtable.put("12", "天津");
		hashtable.put("13", "河北");
		hashtable.put("14", "山西");
		hashtable.put("15", "内蒙古");
		hashtable.put("21", "辽宁");
		hashtable.put("22", "吉林");
		hashtable.put("23", "黑龙江");
		hashtable.put("31", "上海");
		hashtable.put("32", "江苏");
		hashtable.put("33", "浙江");
		hashtable.put("34", "安徽");
		hashtable.put("35", "福建");
		hashtable.put("36", "江西");
		hashtable.put("37", "山东");
		hashtable.put("41", "河南");
		hashtable.put("42", "湖北");
		hashtable.put("43", "湖南");
		hashtable.put("44", "广东");
		hashtable.put("45", "广西");
		hashtable.put("46", "海南");
		hashtable.put("50", "重庆");
		hashtable.put("51", "四川");
		hashtable.put("52", "贵州");
		hashtable.put("53", "云南");
		hashtable.put("54", "西藏");
		hashtable.put("61", "陕西");
		hashtable.put("62", "甘肃");
		hashtable.put("63", "青海");
		hashtable.put("64", "宁夏");
		hashtable.put("65", "新疆");
		hashtable.put("71", "台湾");
		hashtable.put("81", "香港");
		hashtable.put("82", "澳门");
		hashtable.put("91", "国外");
		return hashtable;
	} 
	
	 public static long getLong(byte[] bb) { 
	       return ((((long) bb[ 0] & 0xff) << 56) 
	               | (((long) bb[ 1] & 0xff) << 48) 
	               | (((long) bb[ 2] & 0xff) << 40) 
	               | (((long) bb[ 3] & 0xff) << 32) 
	               | (((long) bb[ 4] & 0xff) << 24) 
	               | (((long) bb[ 5] & 0xff) << 16) 
	               | (((long) bb[ 6] & 0xff) << 8) | (((long) bb[ 7] & 0xff) << 0)); 
	  } 
	 public static long getLong2(byte[] bb) { 
	       return ((((long) bb[ 0] & 0xff) << 56) 
	               | (((long) bb[ 1] & 0xff) << 48) 
	               | (((long) bb[ 2] & 0xff) << 40) 
	               | (((long) bb[ 3] & 0xff) << 32)); 
	  } 
	 public static byte[] long2Bytes(long num) {  
		    byte[] byteNum = new byte[8];  
		    for (int ix = 0; ix < 8; ++ix) {  
		        int offset = 64 - (ix + 1) * 8;  
		        byteNum[ix] = (byte) ((num >> offset) & 0xff);  
		    }
			return byteNum;  
	 }
	 /**   
		 * 字符串转换成十六进制字符串  
		 * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
		 */   
	 public static String str2HexStr(byte[] b)
		{      
		 String str = "";
			for (int i = 0; i <b.length; i++) {
				str += Integer.toHexString(b[i] & 0xff) + "";
			} 
			return str;
		}  
	 /**    
	 * @author lch 把字节数组转换成16进制字符串
	 * @param bArray
	 *            字节数组
	 * @return String 转换成的字符串
	 */
	public static final String bytesToHexString(byte[] bArray, int len) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		// int v_len = bArray.length;
		int last = len - 1;
		//sb.append("0x");
		for (int i = 0; i < len; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append("0");
			sb.append(sTemp.toUpperCase());
			if (i != last)
				sb.append(" ");
		}
		return sb.toString();
	}
	 /**    
	 * @author lch  字节数组转为整形
	 * @param bArray
	 *           
	 * @return  转换成的字符串
	 */
	public static int bytesToInt(byte[] data) {
		int len = data.length;
		int ret = 0;
		for (int i = 0; i < len; i++) {
			ret |= data[len - i] << (8 * i);
		}
		return ret;
	}
	 /**    
	 * @author lch  字节数组转为整形
	 * @param bArray
	 *           
	 * @return int 转换成的字符串
	 */
	public static int bytesToInt(byte[] ary, int offset) {  
	    int value;    
	    value = (int) ((ary[offset]&0xFF)   
	            | ((ary[offset+1]<<8) & 0xFF00)  
	            | ((ary[offset+2]<<16)& 0xFF0000)   
	            | ((ary[offset+3]<<24) & 0xFF000000));  
	    return value;  
	}  
	
}
