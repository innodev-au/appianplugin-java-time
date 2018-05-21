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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import innodev.appianplugin.functions.time.Time_isValidLocalDate;

public class Time_isValidTest {

	private final Time_isValidLocalDate function = new Time_isValidLocalDate();;

	@Test
	public void valid() throws Exception {
		assertTrue(function.time_isValidLocalDate("2011-04-30", "yyyy-MM-dd"));
	}
	
	@Test
	public void invalidEndOApril() throws Exception {
		assertFalse(function.time_isValidLocalDate("2011-04-31", "yyyy-MM-dd"));
	}
	
	@Test
	public void invalidMonth() throws Exception {
		assertFalse(function.time_isValidLocalDate("2011-13-31", "yyyy-MM-dd"));
	}
	
	@Test(expected=Exception.class)
	public void invalidPattern() throws Exception {
		assertFalse(function.time_isValidLocalDate("2011-13-31", "yyyy-MM-dddd"));
	}
	
	@Test
	public void nullDate() throws Exception {
		assertTrue(function.time_isValidLocalDate(null, "yyyy-MM-dd"));
	}
	
	/*** Leap ****/
	@Test
	public void validEndOnLeapYear() throws Exception {
		assertTrue(function.time_isValidLocalDate("2012-02-29", "yyyy-MM-dd"));
	}
	
	@Test
	public void invalidEndOnNonLeapYear() throws Exception {
		assertFalse(function.time_isValidLocalDate("2011-02-29", "yyyy-MM-dd"));
	}

}
