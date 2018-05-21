/**
 * Copyright 2018 Innodev
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package innodev.appianplugin.functions.time;

import java.sql.Date;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.format.DateTimeParseException;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Provides implementation of date-related functions
 */
public class DateHelper {

	private static final Collection<Character> LOCAL_DATE_PATTERN_ALLOWED_CHARS;

	static {
		LOCAL_DATE_PATTERN_ALLOWED_CHARS = new ArrayList<>();
		LOCAL_DATE_PATTERN_ALLOWED_CHARS.add('u');
		LOCAL_DATE_PATTERN_ALLOWED_CHARS.add('y');
		LOCAL_DATE_PATTERN_ALLOWED_CHARS.add('M');
		LOCAL_DATE_PATTERN_ALLOWED_CHARS.add('d');
		LOCAL_DATE_PATTERN_ALLOWED_CHARS.add(' ');
		LOCAL_DATE_PATTERN_ALLOWED_CHARS.add('-');
		LOCAL_DATE_PATTERN_ALLOWED_CHARS.add('/');
		LOCAL_DATE_PATTERN_ALLOWED_CHARS.add('.');
	}

	public static DateTimeFormatter newDateFormatter(String pattern) {
		restrictPatternToChars(pattern, LOCAL_DATE_PATTERN_ALLOWED_CHARS);
		
		try {
			DateTimeFormatter formatter = 
				new DateTimeFormatterBuilder()
					.appendPattern(pattern)
					.parseDefaulting(ChronoField.ERA, 1 /* era is AD */)
					.toFormatter();
	
			return formatter
				.withChronology(IsoChronology.INSTANCE)
				.withResolverStyle(ResolverStyle.STRICT);
		
		}
		catch (Exception e) {
			throw new InvalidPatternException("Invalid pattern provided. " + e.getMessage());
		}
		
	}

	private static void restrictPatternToChars(String pattern, Collection<Character> alloweChars) {
		for (int i = 0; i < pattern.length(); i++) {
			char c = pattern.charAt(i);

			if (!alloweChars.contains(c)) {
				throw new InvalidPatternException("Invalid pattern provided. Character [" + c + "] is not supported");
			}
		}

	}

	public static boolean isNullOrEmpty(String string) {
		return string == null || "".equals(string);
	}

	public static Date parseDate(String dateText, String pattern) throws DateTimeParseException {
		if (DateHelper.isNullOrEmpty(dateText)) {
			return null;
		}

		LocalDate localDate = LocalDate.parse(dateText, newDateFormatter(pattern));
		
		long millis = toMillis(localDate);
		
		return new Date(millis);

	}

	private static long toMillis(LocalDate localDate) {
		return localDate.atStartOfDay().atOffset(ZoneOffset.UTC).toInstant().toEpochMilli();
	}
}
