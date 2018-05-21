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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;

import org.junit.Test;

import innodev.appianplugin.functions.time.Time_parseLocalDate;

public class Time_parseDateTest {

	private final Time_parseLocalDate function = new Time_parseLocalDate();

	@Test
	public void twoDigits() throws Exception {
		Date date = function.time_parseLocalDate("2011-12-13", "yyyy-MM-dd");
						
		assertEquals("2011-12-13", date.toString());			
	}
	
	@Test
	public void twoDigitsYearAsU() throws Exception {
		Date date = function.time_parseLocalDate("2011-12-13", "uuuu-MM-dd");
						
		assertEquals("2011-12-13", date.toString());			
	}
	
	@Test
	public void singleDigits() throws Exception {
		Date date = function.time_parseLocalDate("2011-2-20", "yyyy-M-d");
						
		assertEquals("2011-02-20", date.toString());			
	}
	
	@Test
	public void alternativeFormat() throws Exception {
		Date date = function.time_parseLocalDate("20/2/2011", "d/M/yyyy");
						
		assertEquals("2011-02-20", date.toString());			
	}
	
	@Test(expected=Exception.class)
	public void invalidDayOfMonth() throws Exception {
		function.time_parseLocalDate("2011-04-31", "yyyy-MM-dd");
						
	}	
	
	@Test
	public void invalidWithHour() throws Exception {
		try {
			function.time_parseLocalDate("2011-12-13 20", "yyyy-MM-dd HH");
			fail();
		}
		catch (Exception e) {
			assertTrue(e.getMessage().toLowerCase().contains("invalid pattern"));
			assertTrue(e.getMessage().contains("[H]"));
		}
						
	}
	
	@Test
	public void invalidPatternExtraD() throws Exception {
		try {
			function.time_parseLocalDate("2011-12-13", "yyyy-MM-ddd");											
			fail();
		}
		catch (Exception e) {
			assertTrue(e.getMessage().toLowerCase().contains("invalid pattern"));
		}
	}

	@Test
	public void nullDate() throws Exception {
		Date date = function.time_parseLocalDate(null, "yyyy-MM-dd");
						
		assertNull(date);			
	}
	
	@Test
	public void emptyStringDate() throws Exception {
		Date date = function.time_parseLocalDate("", "yyyy-MM-dd");
						
		assertNull(date);			
	}
}
