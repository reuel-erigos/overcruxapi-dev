package br.com.crux.infra.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class Java8DateUtil {

	public static Date getDate(LocalDateTime ldt) {
		if(ldt == null) return null;
		return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static LocalDateTime getLocalDateTime(Date ts) {
		Instant instant = Instant.ofEpochMilli(ts.getTime());
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	}
	
	public static LocalDateTime getLocalDateTimeZeroTiming(Date ts) {
		return getLocalDate(ts).atStartOfDay();
	}
	
	public static LocalDateTime getLocalDateTimeLastTiming(Date ts) {
		LocalTime specificTime = LocalTime.of(23, 59, 59, 999999999);
		return LocalDateTime.of(getLocalDateTime(ts).toLocalDate(), specificTime);
	}
	
	public static LocalDateTime getLocalDateTimeLastSecond(Date ts) {
		LocalTime specificTime = LocalTime.of(23, 59, 59);
		return LocalDateTime.of(getLocalDateTime(ts).toLocalDate(), specificTime);
	}
	
	public static LocalDate getLocalDate(Date ts) {
		return ts.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static LocalDateTime parseLocalDateTime(String str){
		if(str == null) return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt;
		try {
			dt = sdf.parse(str);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return getLocalDateTime(dt);
	}
	
	public static LocalDate parseLocalDate(String str){
		if(str == null) return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt;
		try {
			dt = sdf.parse(str);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return getLocalDate(dt);
	}

	public static boolean isVigente(LocalDate inicio, LocalDate fim) {
		LocalDate hoje = LocalDate.now();

		if (hoje.equals(inicio)) {return true;}
		if (hoje.equals(fim)) {return true;}
		if (hoje.isBefore(inicio)) {return false;}
		if (Objects.isNull(fim)) {return true;}     
		if (hoje.isAfter(inicio) && hoje.isBefore(fim)) {return true;}
		
		return false;
	}

	public static boolean isVigente(LocalDate dataRefencia, LocalDate inicio, LocalDate fim) {
		if (dataRefencia.equals(inicio)) {return true;}
		if (dataRefencia.equals(fim)) {return true;}
		if (dataRefencia.isBefore(inicio)) {return false;}
		if (Objects.isNull(fim)) {return true;}     
		if (dataRefencia.isAfter(dataRefencia) && dataRefencia.isBefore(fim)) {return true;}
		
		return false;
	}
	
	public static Long horaStringToLong(String hora) {
		String teste = hora.replace(":","");
		return Long.valueOf(teste);
		
	}
	
}
